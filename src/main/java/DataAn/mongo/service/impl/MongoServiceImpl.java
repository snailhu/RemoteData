package DataAn.mongo.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.bson.Document;
import org.springframework.stereotype.Service;
import com.mongodb.client.MongoCursor;
import DataAn.mongo.client.MongodbUtil;
import DataAn.mongo.init.InitMongo;
import DataAn.mongo.service.IMongoService;

@Service
public class MongoServiceImpl implements IMongoService{


	@Override
	public void saveCSVData(String series, String star,String paramType, String date,
			List<Document> documents, String versions) throws Exception {
		MongodbUtil mg = MongodbUtil.getInstance();
		String databaseName = InitMongo.getDataDBBySeriesAndStar(series, star);
		Set<String> isexistCols = mg.getExistCollections(databaseName);		
		String collectionName = paramType;
		try {
			long begin = System.currentTimeMillis();
			if(documents != null && documents.size() > 0){
				if(isexistCols != null && isexistCols.size() > 0){
					if(isexistCols.contains(collectionName)){
						//设置同一时间段的数据的状态为0
						Date beginDate = documents.get(0).getDate("datetime");
						Date endDate = documents.get(documents.size() - 1).getDate("datetime");
						mg.updateByDate(databaseName, collectionName, beginDate, endDate);						
					}
				}
				mg.insertMany(databaseName, collectionName, documents);					
			}
			long end = System.currentTimeMillis();
			System.out.println("保存数据在： " + databaseName + "." + collectionName + " 记录： " + documents.size());
			System.out.println("耗时： " + (end - begin) );
		} catch (Exception e) {
			e.printStackTrace();
			mg.update(databaseName, collectionName, "versions", versions);
			throw new Exception("csv 文件解析失败！！！");
		}
	}
	
	@Override
	public void saveCSVData(String series, String star, String paramType,
			String date, Map<String, List<Document>> map, String versions)
			throws Exception {
		MongodbUtil mg = MongodbUtil.getInstance();
		
		String databaseName = InitMongo.getDataDBBySeriesAndStar(series, star);
		
		Set<String> isexistCols = mg.getExistCollections(databaseName);
		
		Set<String> keys = map.keySet();
		for (String key : keys) {
			//集合名称： 参数名+等级
			String collectionName = "";
			if (key.equals("0s"))
				collectionName = paramType;
			else
				collectionName = paramType + key;
			
			List<Document> documents = map.get(key);
			if(documents != null && documents.size() > 0){
				long begin = System.currentTimeMillis();
				
				if(isexistCols != null && isexistCols.size() > 0){
					if(isexistCols.contains(collectionName)){
						//设置同一时间段的数据的状态为0
						Date beginDate = documents.get(0).getDate("datetime");
						Date endDate = documents.get(documents.size() - 1).getDate("datetime");
						mg.updateByDate(databaseName, collectionName, beginDate, endDate);
					}
				}
				
				//保存数据到mongodb中
				mg.insertMany(databaseName, collectionName, documents);
				
				long end = System.currentTimeMillis();
				System.out.println("保存数据在： " + databaseName + "." + collectionName + " 记录： " + documents.size());
				System.out.println("耗时： " + (end - begin) );
				
				Thread.sleep(10000);
			}
		}
		
	}
	
	@Override
	public void updateCSVDataByVersions(String series, String star,
			String paramType, String versions) {
		MongodbUtil mg = MongodbUtil.getInstance();
		String databaseName = InitMongo.getDataDBBySeriesAndStar(series, star);
//		List<String> list = InitMongo.getGradingCollectionNames(paramType);
//		if(list != null && list.size() > 0){
//			Set<String> isexistCols = mg.getExistCollections(databaseName);
//			if(isexistCols != null && isexistCols.size() > 0){
//				for (String collectionName : list) {
//					if(isexistCols.contains(collectionName)){
//						//设置某一个版本的数据的状态为0
//						mg.update(databaseName, collectionName, "versions", versions);							
//					}
//				}			
//			}
//		}
		
		Set<String> isexistCols = mg.getExistCollections(databaseName);
		if(isexistCols != null && isexistCols.size() > 0){
			for (String collectionName : isexistCols) {
				if(collectionName.indexOf(paramType) > -1){
					//设置某一个版本的数据的状态为0
					mg.update(databaseName, collectionName, "versions", versions);	
				}
			}
		}
	}
	
	@Override
	public void updateCSVDataByDate(String series, String star,
			String paramType, Date beginDate, Date endDate, boolean isTemp) {
		MongodbUtil mg = MongodbUtil.getInstance();
		String databaseName = InitMongo.getDataDBBySeriesAndStar(series, star);
//		List<String> list = InitMongo.getGradingCollectionNames(paramType);
//		if(list != null && list.size() > 0){
//			Set<String> isexistCols = mg.getExistCollections(databaseName);
//			if(isexistCols != null && isexistCols.size() > 0){
//				for (String collectionName : list) {
//					if(isexistCols.contains(collectionName)){
//						//设置同一时间段的数据的状态为0
//						mg.updateByDate(databaseName, collectionName, beginDate, endDate);								
//					}
//				}			
//			}
//		}
		
		Set<String> isexistCols = mg.getExistCollections(databaseName);
		if(isexistCols != null && isexistCols.size() > 0){
			for (String collectionName : isexistCols) {
				if(collectionName.indexOf(paramType) > -1){
					if(isTemp){
						//设置同一时间段的数据的状态为2,此为临时状态
//						mg.updateByDate(databaseName, collectionName, beginDate, endDate, 2);
						mg.updateByDate(databaseName, collectionName, beginDate, endDate, "status", 1, 2);
					}else{
						//设置同一时间段的数据的状态为0
						mg.updateByDate(databaseName, collectionName, beginDate, endDate, 0);						
					}
				}
			}
		}
	}

	@Override
	public MongoCursor<Document> findByYear_month_day(String series,
			String star, String paramType, String date) {
		MongodbUtil mg = MongodbUtil.getInstance();
		String databaseName = InitMongo.getDataDBBySeriesAndStar(series, star);
		String collectionName =  paramType;
		
		return mg.find(databaseName, collectionName, "year_month_day", date);
		
	}

	@Override
	public MongoCursor<Document> findByWeek_of_year(String series,
			String star, String paramType, int week_of_year) {
		MongodbUtil mg = MongodbUtil.getInstance();
		String databaseName = InitMongo.getDataDBBySeriesAndStar(series, star);
		String collectionName =  paramType;
		
		return mg.find(databaseName, collectionName, "week_of_year", week_of_year);
	}

	@Override
	public MongoCursor<Document> findByDate(String series, String star,
			String paramType, Date beginDate, Date endDate) {
		MongodbUtil mg = MongodbUtil.getInstance();
		String databaseName = InitMongo.getDataDBBySeriesAndStar(series, star);
		//1s 等级数据集
		String collectionName =  paramType + "1s";
		//开始时间向前推进 1m
		beginDate = new Date(beginDate.getTime() - 1000);
		
		return mg.find(databaseName, collectionName, beginDate, endDate);
	}

	@Override
	public long findJobNumByDeviceName(String series, String star, String paramType, String deviceName, 
			Date beginDate,Date endDate) {
		MongodbUtil mg = MongodbUtil.getInstance();
		String databaseName = InitMongo.getDataDBBySeriesAndStar(series, star);
		String collectionName =  paramType + "_job";
		return mg.countByDate(databaseName, collectionName, beginDate, endDate, "deviceName", deviceName);
	}

}
