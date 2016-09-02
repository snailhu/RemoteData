package DataAn.common.service.impl;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import DataAn.common.config.Config;
import DataAn.common.service.IJobService;
import DataAn.common.utils.FileUtil;
import DataAn.fileSystem.dao.IVirtualFileSystemDao;
import DataAn.fileSystem.domain.VirtualFileSystem;
import DataAn.mongo.db.MongodbUtil;
import DataAn.mongo.fs.IDfsDb;
import DataAn.mongo.fs.MongoDfsDb;
import DataAn.mongo.init.InitMongo;

@Service
public class JobServiceImpl implements IJobService{

	@Resource
	private IVirtualFileSystemDao fileDao;
	
	//test 没5秒执行一次
//	@Scheduled(cron = "0/5 * * * * *")  
	//每月25号的晚上23点执行此方法
	@Scheduled(cron = "0 0 23 25 * ?")  
	@Override
	public void delMongoDBInvalidValueJob() {
//		System.out.println(" JobServiceImpl DelMongoDBValueJob date:" + new Date().toString());
		String databaseName = InitMongo.DATABASE_J9STAR2;
		MongodbUtil mg = MongodbUtil.getInstance();
		mg.deleteMany(databaseName, "star2", "status", 0);
	}

	//每月20号的晚上23点执行此方法
	@Scheduled(cron = "0 0 23 20 * ?")  
	@Override
	public void delCacheFileJob() {
		List<VirtualFileSystem> list = fileDao.selectByFileTypeIsFileAndCachePathISNotNull();
		if(list == null || list.size() == 0){
			String sPath = Config.getCachePath();
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

}
