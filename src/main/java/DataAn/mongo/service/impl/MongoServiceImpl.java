package DataAn.mongo.service.impl;

import java.util.List;

import org.bson.Document;
import org.springframework.stereotype.Service;

import DataAn.mongo.client.MongodbUtil;
import DataAn.mongo.service.IMongoService;

@Service
public class MongoServiceImpl implements IMongoService{

	private MongodbUtil mg = null;

	@Override
	public void saveCSVData(List<Document> documents) {
		mg = MongodbUtil.getInstance();
	}
}
