package DataAn.common.service.impl;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
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

	//每天晚上1点执行此方法
	@Scheduled(cron = "0 0 01 * * ?") 
	@Override
	public void createReport() throws Exception {
		String imgUrl = OptionConfig.getWebPath() + "\\report\\wordtemplate\\satellite.jpg";  
		String templateUrl = OptionConfig.getWebPath() + "\\report\\wordtemplate\\卫星状态报告.doc";
		String templateName = "Employees";
		
		List<StarParam> starList = starParamDao.getStarParamByParts();
		for (StarParam starParam : starList) {
			String seriesId = starParam.getSeries();
			String starId = starParam.getStar();
			String partsType = starParam.getPartsType();
			
			String time = DateUtil.getBeforeDate();
			String filename = seriesId+"_"+starId+"_"+partsType+"_"+time+".doc";
			String docPath = OptionConfig.getWebPath() + "report\\"+filename;
			//TODO
			//reoportService.createReport(time, filename, imgUrl, templateUrl, templateName, docPath, seriesId, starId, partsType);
			reoportService.insertReportToDB(filename, docPath,seriesId,starId, partsType);
			reoportService.removeDoc(docPath);
		}
	}
}
