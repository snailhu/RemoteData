package DataAn.mongo.service.impl;

import java.util.List;

import org.bson.Document;
import org.springframework.stereotype.Service;

import com.mongodb.client.MongoCursor;

import DataAn.common.utils.DateUtil;
import DataAn.fileSystem.option.J9SeriesType;
import DataAn.mongo.client.MongodbUtil;
import DataAn.mongo.init.InitMongo;
import DataAn.mongo.service.IMongoService;

@Service
public class MongoServiceImpl implements IMongoService{

	private MongodbUtil mg = MongodbUtil.getInstance();


	@Override
	public void saveCSVData(String series, String star,String paramType, String date,
			List<Document> documents, String versions) throws Exception {
		String databaseName = InitMongo.getDataBaseNameBySeriesAndStar(series, star);
//		String databaseName = InitMongo.DATABASE_TEST;
		String collectionName = DateUtil.formatString(date, "yyyy-MM-dd", "yyyy");
		//collectionName = "tb" + collectionName;
		collectionName = paramType + collectionName;
		try {
			//设置同一时间段的数据的状态为0
//			String beginDate = documents.get(0).getString("datetime");
//			String endDate = documents.get(documents.size() - 1).getString("datetime");
//			mg.updateByDate(databaseName, collectionName, beginDate, endDate);
			
			mg.insertMany(databaseName, collectionName, documents);
		} catch (Exception e) {
			e.printStackTrace();
			mg.update(databaseName, collectionName, "versions", versions);
			throw new Exception("csv 文件解析失败！！！");
		}
	}
	
	@Override
	public void updateCSVDataByVersions(String series, String star, String paramType, 
			String date, String versions){
		String databaseName = InitMongo.getDataBaseNameBySeriesAndStar(series, star);
		String collectionName = DateUtil.formatString(date, "yyyy-MM-dd", "yyyy");
		collectionName = paramType + collectionName;
		mg.update(databaseName, collectionName, "versions", versions);
	}
	
	@Override
	public void find() {
		String databaseName = InitMongo.DB_J9STAR2;
		String collectionName = "tb2016";
		MongoCursor<Document> cursor = mg.findAll(databaseName, collectionName);
		while(cursor.hasNext()){
			
			Document doc = cursor.next();
			System.out.println(DateUtil.format(doc.getDate("datetime")));
		}
	}

}
