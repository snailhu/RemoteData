package DataAn.fileSystem.service.impl;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Service;

import DataAn.fileSystem.service.ISaveFileTaskSingleService;
import DataAn.galaxyManager.service.IParameterService;
import DataAn.mongo.service.IMongoService;

@Service
public class SaveFileTaskSingleServiceImpl implements ISaveFileTaskSingleService, BeanPostProcessor {

	
	@Autowired
	private IParameterService paramService;
	
	@Autowired
	private  IMongoService mongoService;

	public IParameterService getParamService() {
		return paramService;
	}

	public void setParamService(IParameterService paramService) {
		this.paramService = paramService;
	}

	public IMongoService getMongoService() {
		return mongoService;
	}

	public void setMongoService(IMongoService mongoService) {
		this.mongoService = mongoService;
	}

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		return bean;
	}
	
	private boolean flag;

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		if(!flag){
			//开另外一个线程处理存入kafka的数据
			new Thread(new SaveFileToKafka(paramService, mongoService)).start();
			flag=true;
		}
		return bean;
	}
	
	
}
