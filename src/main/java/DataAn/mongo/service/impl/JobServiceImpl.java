package DataAn.mongo.service.impl;

import java.io.File;
import java.util.Date;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import DataAn.common.config.Config;
import DataAn.common.utils.FileUtil;
import DataAn.mongo.db.MongodbUtil;
import DataAn.mongo.service.IJobService;

@Service
public class JobServiceImpl implements IJobService{

	//test 没5秒执行一次
//	@Scheduled(cron = "0/5 * * * * *")  
	//每月25号的晚上23点执行此方法
	@Scheduled(cron = "0 0 23 25 * ?")  
	@Override
	public void delMongoDBInvalidValueJob() {
//		System.out.println(" JobServiceImpl DelMongoDBValueJob date:" + new Date().toString());  
		MongodbUtil mg = MongodbUtil.getInstance();
		mg.deleteMany("star2", "status", 0);
	}

	//每月20号的晚上23点执行此方法
	@Scheduled(cron = "0 0 23 20 * ?")  
	@Override
	public void delCacheFileJob() {
		String sPath = Config.getCACHE_PATH();
		FileUtil.deleteDirectory(sPath,false);
	}

}
