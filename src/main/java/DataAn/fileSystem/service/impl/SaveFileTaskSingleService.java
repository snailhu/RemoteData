package DataAn.fileSystem.service.impl;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Service;

import DataAn.galaxyManager.service.IParameterService;
import DataAn.mongo.service.IMongoService;

@Service
public class SaveFileTaskSingleService implements BeanPostProcessor {

	private IParameterService paramService;
	
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
		return null;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		//开另外一个线程处理存入kafka的数据
		new Thread(new SaveFileToKafka(paramService, mongoService)).start();
		return null;
	}
	
	
}
