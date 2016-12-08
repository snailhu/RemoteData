package DataAn.common.service.impl;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import DataAn.common.config.CommonConfig;
import DataAn.common.service.IJobService;
import DataAn.common.utils.DateUtil;
import DataAn.common.utils.FileUtil;
import DataAn.fileSystem.dao.IVirtualFileSystemDao;
import DataAn.fileSystem.domain.VirtualFileSystem;
import DataAn.mongo.db.MongodbUtil;
import DataAn.mongo.fs.IDfsDb;
import DataAn.mongo.fs.MongoDfsDb;
import DataAn.mongo.init.InitMongo;
import DataAn.reportManager.dao.IStarParamDao;
import DataAn.reportManager.domain.StarParam;
import DataAn.reportManager.service.IReoportService;
import DataAn.wordManager.config.OptionConfig;

@Service
public class JobServiceImpl implements IJobService{

	@Resource
	private IVirtualFileSystemDao fileDao;
	
	@Resource
	private IStarParamDao starParamDao;
	
	@Resource
	private IReoportService reoportService;
	
	//test 没5秒执行一次
//	@Scheduled(cron = "0/5 * * * * *")  
	//每月25号的晚上23点执行此方法
	@Scheduled(cron = "0 0 23 25 * ?")  
	@Override
	public void delMongoDBInvalidValueJob() {
//		System.out.println(" JobServiceImpl DelMongoDBValueJob date:" + new Date().toString());
		String databaseName = InitMongo.DB_J9STAR2;
		MongodbUtil mg = MongodbUtil.getInstance();
		//mg.deleteMany(databaseName, "star2", "status", 0);
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
			
			String  endTime = DateUtil.getYesterdayTime();
			String  starTime =DateUtil.getLastWeekTime();
			String time = DateUtil.getNowTime("yyyy-MM-dd");
			
			Date beginDate = DateUtil.format(starTime,"yyyy-MM-dd HH:mm:ss");
			Date endDate =  DateUtil.format(endTime,"yyyy-MM-dd HH:mm:ss");
			String partsName = "";
			if("flywheel".equals(partsType)) {
				partsName = "飞轮";
			}else if("top".equals(partsType)) {
				partsName = "陀螺";
			}
			
			String databaseName = InitMongo.DATABASE_TEST;
			String filename = seriesId+"_"+starId+"_"+partsName+"_"+time+".doc";
			String docPath = OptionConfig.getWebPath() + "report\\"+filename;
			try {
				reoportService.createReport(beginDate, endDate, filename, templateUrl, docPath, seriesId, starId, partsType);
				reoportService.insertReportToDB(filename, docPath,seriesId,starId, partsType,starTime,endTime,databaseName,partsName);
				reoportService.removeDoc(docPath);
			} catch (Exception e) {
				reoportService.reportNullDoc(filename,templateNullUrl, docPath, starTime, endTime,e.getMessage());
				reoportService.insertReportToDB(filename, docPath,seriesId,starId, partsType,starTime,endTime,databaseName,partsName);
				reoportService.removeDoc(docPath);
			}
		}
	}
}
