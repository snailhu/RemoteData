package DataAn.common.service.impl;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import DataAn.common.config.CommonConfig;
import DataAn.common.service.IJobService;
import DataAn.common.utils.DateUtil;
import DataAn.common.utils.FileUtil;
import DataAn.common.utils.UUIDGeneratorUtil;
import DataAn.fileSystem.dao.IVirtualFileSystemDao;
import DataAn.fileSystem.domain.VirtualFileSystem;
import DataAn.fileSystem.service.IVirtualFileSystemService;
import DataAn.galaxy.option.J9Series_Star_ParameterType;
import DataAn.mongo.client.MongodbUtil;
import DataAn.mongo.fs.IDfsDb;
import DataAn.mongo.fs.MongoDfsDb;
import DataAn.mongo.init.InitMongo;
import DataAn.reportManager.dao.IStarParamDao;
import DataAn.reportManager.domain.StarParam;
import DataAn.reportManager.service.IReoportService;
import DataAn.status.dao.IStatusTrackingDao;
import DataAn.status.domain.StatusTracking;
import DataAn.status.dto.StatusYstepDTO;
import DataAn.status.service.IStatusTrackingService;
import DataAn.storm.status.StatusTrackingType;
import DataAn.wordManager.config.OptionConfig;

@Service
public class JobServiceImpl implements IJobService{

	@Resource
	private IVirtualFileSystemDao fileDao;
	@Resource
	private IVirtualFileSystemService fileService;
	@Resource
	private IStarParamDao starParamDao;
	@Resource
	private IReoportService reoportService;
	@Resource
	private IStatusTrackingDao statusTrackingDao;
	@Resource
	private IStatusTrackingService statusTrackingService;
	
	//test 没5秒执行一次
//	@Scheduled(cron = "0/5 * * * * *")  
	//每月25号的晚上2点执行此方法
//	@Scheduled(cron = "0 0 2 25 * ?")  
	@Scheduled(cron = "0 0 2 25 * ?")  
	@Override
	public void delMongoDBInvalidValueJob() {
		MongodbUtil mg = MongodbUtil.getInstance();
		Set<String> databaseNames = mg.getDatabaseNames();
		for (String databaseName : databaseNames) {
			if(databaseName.indexOf("db") > -1){
				Set<String> isexistCols = mg.getExistCollections(databaseName);
				if(isexistCols != null && isexistCols.size() > 0){
					for (String collectionName : isexistCols) {
						mg.deleteMany(databaseName, collectionName, "status", 0);				
					}
				}
			}
		}
	}

	//每月20号的晚上23点执行此方法
	@Scheduled(cron = "0 0 23 20 * ?")  
	@Override
	public void delCacheFileJob() {
		List<VirtualFileSystem> list = fileDao.selectByFileTypeIsFileAndCachePathISNotNull();
		if(list == null || list.size() == 0){
			String sPath = CommonConfig.getCachePath();
			FileUtil.deleteDirectory(sPath,false);			
		}
	}

	//每天晚上22点执行此方法
	@Scheduled(cron = "0 0 1 * * ?") 
	@Override
	public void updateFileStatusJob() {
//		try {
//			List<StatusTracking> statusTrackings = statusTrackingDao.getStatusTrackingByParams(null);
//			if(statusTrackings != null && statusTrackings.size() > 0){
//				VirtualFileSystem file = null;
//				String statusType = StatusTrackingType.PREHANDLEFAIL.getValue();
//				for (StatusTracking statusTracking : statusTrackings) {
//					file = fileDao.selectByParameterTypeAndFileName(statusTracking.getUserType(),statusTracking.getFileName());
//					if(file != null){
//						statusTrackingService.updateStatusTracking(file.getFileName(), statusType, 
//								file.getParameterType(),"后台数据处理超时...");
//						fileService.deleteFileByUUId(file.getMongoFSUUId());
//					}
//				}
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		
	}
	
	//每天晚上22点执行此方法
	@Scheduled(cron = "0 0 22 * * ?") 
	@Override
	@Transactional
	public void saveFileOfCSV() throws Exception {
		List<VirtualFileSystem> list = fileDao.selectByFileTypeIsFileAndCachePathISNotNull();
		if(list != null && list.size() > 0){
			for (VirtualFileSystem file : list) {
				System.out.println(file);
				//保存csv 原文件				
				IDfsDb dfs = MongoDfsDb.getInstance();
				BufferedInputStream bis = null;
				try {
					String databaseName = InitMongo.getFSBDNameBySeriesAndStar(file.getSeries(), file.getStar());
					bis = new BufferedInputStream(new FileInputStream(file.getCachePath()));  
					dfs.upload(databaseName, file.getFileName(), file.getMongoFSUUId(), bis);
				} catch(Exception e){
//					e.printStackTrace();
					//删除上传csv原文件
					dfs.delete(file.getMongoFSUUId());
					throw new Exception("csv 文件上传失败！！！");
				}finally {
					if(bis != null){
						bis.close();
					}
				}
				file.setCachePath(null);
				fileDao.update(file);
			}
		}
		
	}

	//每个星期一晚上11点触发 生产环境
//	@Scheduled(cron = "0 0 23 ? * MON") 
	//每天晚上22点执行此方法 测试使用
	//每两小时
	@Scheduled(cron = "0 0 13 * * ?") 
	@Override
	public void createReport() throws Exception {
		String templateUrl = OptionConfig.getWebPath() + "\\report\\wordtemplate\\卫星状态报告.doc";
		String templateNullUrl = OptionConfig.getWebPath() + "\\report\\wordtemplate\\nullData.doc";
		List<StarParam> starList = starParamDao.getStarParamByParts();
		for (StarParam starParam : starList) {
			String seriesId = starParam.getSeries();
			String starId = starParam.getStar();
			String partsType = starParam.getPartsType() ;
			
			String starTime =DateUtil.getLastWeekTime();
			String endTime = DateUtil.getYesterdayTime();
			Date beginDate = DateUtil.format(starTime,"yyyy-MM-dd");
			Date endDate =  DateUtil.format(endTime,"yyyy-MM-dd");
			String time = DateUtil.getNowTime("yyyy-MM-dd");
			
			String partsName = "";
			if(J9Series_Star_ParameterType.FLYWHEEL.getValue().equals(partsType)) {
				partsName = J9Series_Star_ParameterType.FLYWHEEL.getName();
				templateUrl = OptionConfig.getWebPath() + "\\report\\wordtemplate\\卫星状态报告_flywheel.doc";
			}else if(J9Series_Star_ParameterType.TOP.getValue().equals(partsType)) {
				partsName = J9Series_Star_ParameterType.TOP.getName();
				templateUrl = OptionConfig.getWebPath() + "\\report\\wordtemplate\\卫星状态报告_top.doc";
			}
			//TODO 切换数据库
			String fsDBName = InitMongo.DATABASE_TEST;
//			String fsDBName = InitMongo.getReportFSBySeriesAndStar(seriesId, starId);
			String filename = seriesId+"_"+starId+"_"+partsName+"_"+time+".doc";
//			String docPath = OptionConfig.getWebPath() + "report\\"+filename;
			String docPath = OptionConfig.getWebPath() + File.separator + 
					"report" + File.separator + 
					DateUtil.format(new Date(), "yyyy-MM-dd")+ File.separator + 
					UUIDGeneratorUtil.getUUID()+filename;
			try {
				MongodbUtil mg = MongodbUtil.getInstance();
				String dataDB = InitMongo.getDataDBBySeriesAndStar(seriesId, starId);
				//1s 等级数据集 或原数据集
				String collectionName =  partsType;
				long count = mg.countByDate(dataDB, collectionName, beginDate, endDate);
				if(count > 0){
					reoportService.createReport(beginDate, endDate, filename, templateUrl, docPath, seriesId, starId, partsType);
					reoportService.insertReportToDB(filename, docPath,seriesId,starId, partsType,starTime,endTime,fsDBName,partsName);
					reoportService.removeDoc(docPath);
					//线程休眠10s
					Thread.sleep(10000);
				}
			} catch (Exception e) {
				reoportService.reportNullDoc(filename,templateNullUrl, docPath, starTime, endTime,e.getMessage());
				reoportService.insertReportToDB(filename, docPath,seriesId,starId, partsType,starTime,endTime,fsDBName,partsName);
				reoportService.removeDoc(docPath);
			}
		}
	}
	//每天晚上22点执行此方法 测试使用
	@Scheduled(cron = "0 0 22 * * ?") 
	public void createReport2() throws Exception {
//		String templateUrl = OptionConfig.getWebPath() + "\\report\\wordtemplate\\卫星状态报告.doc";
//		String templateNullUrl = OptionConfig.getWebPath() + "\\report\\wordtemplate\\nullData.doc";
//		List<StarParam> starList = starParamDao.getStarParamByParts();
//		for (StarParam starParam : starList) {
//			String seriesId = starParam.getSeries();
//			String starId = starParam.getStar();
//			String partsType = starParam.getPartsType() ;
//			
//			String starTime =DateUtil.getLastWeekTime();
//			String endTime = DateUtil.getYesterdayTime();
//			Date beginDate = DateUtil.format(starTime,"yyyy-MM-dd");
//			Date endDate =  DateUtil.format(endTime,"yyyy-MM-dd");
//			String time = DateUtil.getNowTime("yyyy-MM-dd");
//			
//			String partsName = "";
//			if("flywheel".equals(partsType)) {
//				partsName = "飞轮";
//				templateUrl = OptionConfig.getWebPath() + "\\report\\wordtemplate\\卫星状态报告_flywheel.doc";
//			}else if("top".equals(partsType)) {
//				partsName = "陀螺";
//				templateUrl = OptionConfig.getWebPath() + "\\report\\wordtemplate\\卫星状态报告_top.doc";
//			}
//			//TODO 切换数据库
//			String databaseName = InitMongo.DATABASE_TEST;
////			String databaseName = InitMongo.getReportFSBySeriesAndStar(seriesId, starId);
//			String filename = seriesId+"_"+starId+"_"+partsName+"_"+time+".doc";
////			String docPath = OptionConfig.getWebPath() + "report\\"+filename;
//			String docPath = OptionConfig.getWebPath() + File.separator + 
//					"report" + File.separator + 
//					DateUtil.format(new Date(), "yyyy-MM-dd")+ File.separator + 
//					UUIDGeneratorUtil.getUUID()+filename;
//			try {
//				reoportService.createReport(beginDate, endDate, filename, templateUrl, docPath, seriesId, starId, partsType);
//				reoportService.insertReportToDB(filename, docPath,seriesId,starId, partsType,starTime,endTime,databaseName,partsName);
//				reoportService.removeDoc(docPath);
//			} catch (Exception e) {
//				reoportService.reportNullDoc(filename,templateNullUrl, docPath, starTime, endTime,e.getMessage());
//				reoportService.insertReportToDB(filename, docPath,seriesId,starId, partsType,starTime,endTime,databaseName,partsName);
//				reoportService.removeDoc(docPath);
//			}
//		}
	}

}
