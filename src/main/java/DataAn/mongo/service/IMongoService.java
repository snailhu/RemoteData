package DataAn.mongo.service;

import java.util.List;

import org.bson.Document;

public interface IMongoService {

	public void saveCSVData(List<Document> documents);
}
