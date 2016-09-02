package DataAn.mongo.service;

import java.util.List;

import org.bson.Document;

public interface IMongoService {

	public void saveCSVData(String series, String star, String date,
			List<Document> documents);

	public void saveCSVData(String series, String star, String paramType,
			String date, List<Document> documents, String versions)
			throws Exception;

	public void updateCSVDataByVersions(String series, String star,
			String date, String value);

	public void find();
}
