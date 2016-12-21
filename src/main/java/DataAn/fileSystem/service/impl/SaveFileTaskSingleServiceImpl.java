package DataAn.fileSystem.service.impl;

import javax.annotation.Resource;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Service;
import DataAn.common.service.IInitDataService;
import DataAn.fileSystem.service.ISaveFileTaskSingleService;
import DataAn.galaxyManager.service.IParameterService;
import DataAn.mongo.service.IMongoService;

@Service
public class SaveFileTaskSingleServiceImpl implements ISaveFileTaskSingleService, BeanPostProcessor {
	
	@Resource
	private IParameterService paramService;
	@Resource
	private  IMongoService mongoService;
	@Resource
	private IInitDataService initDataService;

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		return bean;
	}
	
	private static volatile boolean flag;

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		if(!flag){
			flag=true;
			//开另外一个线程处理存入kafka的数据
			//new Thread(new SaveFileToKafka(paramService, mongoService)).start();
			//初始化数据 //TODO ?
			//initDataService.initDataBase();
		}
		return bean;
	}
	
	
}
