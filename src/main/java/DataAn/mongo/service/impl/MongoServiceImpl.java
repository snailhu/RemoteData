package DataAn.mongo.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.bson.Document;
import org.springframework.stereotype.Service;
import com.mongodb.client.MongoCursor;
import DataAn.common.utils.DateUtil;
import DataAn.galaxyManager.option.J9Series_Star_ParameterType;
import DataAn.mongo.client.MongodbUtil;
import DataAn.mongo.init.InitMongo;
import DataAn.mongo.service.IMongoService;

@Service
public class MongoServiceImpl implements IMongoService{

	private MongodbUtil mg = null;//MongodbUtil.getInstance();

	@Override
	public void saveCSVData(String series, String star,String paramType, String date,
			List<Document> documents, String versions) throws Exception {
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
	public void updateCSVDataByVersions(String series, String star, String paramType, 
			String date, String versions){
		String databaseName = InitMongo.getDataDBBySeriesAndStar(series, star);
		String collectionName = DateUtil.formatString(date, "yyyy-MM-dd", "yyyy");
		collectionName = paramType + collectionName;
		mg.update(databaseName, collectionName, "versions", versions);
	}
	
	@Override
	public void updateCSVDataByVersions(String series, String star,
			String paramType, String versions) {
		String databaseName = InitMongo.getDataDBBySeriesAndStar(series, star);
		String collectionName =  paramType;
		mg.update(databaseName, collectionName, "versions", versions);
	}
	

	@Override
	public void updateCSVDataByDate(String series, String star,
			String paramType, Date beginDate, Date endDate) {
		String databaseName = InitMongo.getDataDBBySeriesAndStar(series, star);
		List<String> list = InitMongo.getGradingCollectionNames(J9Series_Star_ParameterType.FLYWHEEL.getValue());
		if(list != null && list.size() > 0){
			Set<String> isexistCols = mg.getExistCollections(databaseName);
			if(isexistCols != null && isexistCols.size() > 0){
				for (String collectionName : list) {
					if(isexistCols.contains(collectionName)){
						//设置同一时间段的数据的状态为0
						mg.updateByDate(databaseName, collectionName, beginDate, endDate);								
					}
				}			
			}
		}
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

	@Override
	public MongoCursor<Document> findByYear_month_day(String series,
			String star, String paramType, String date) {
		String databaseName = InitMongo.getDataDBBySeriesAndStar(series, star);
		String collectionName =  paramType;
		
		return mg.find(databaseName, collectionName, "year_month_day", date);
		
//		return mg.findAll(databaseName, collectionName);
	}

	@Override
	public MongoCursor<Document> findByWeek_of_year(String series,
			String star, String paramType, int week_of_year) {
		String databaseName = InitMongo.getDataDBBySeriesAndStar(series, star);
		String collectionName =  paramType;
		
		return mg.find(databaseName, collectionName, "week_of_year", week_of_year);
	}

	@Override
	public MongoCursor<Document> findByDate(String series, String star,
			String paramType, Date beginDate, Date endDate) {
		String databaseName = InitMongo.getDataDBBySeriesAndStar(series, star);
		//1s 等级数据集
		String collectionName =  paramType + "1s";
		//开始时间向前推进 1m
		beginDate = new Date(beginDate.getTime() - 1000);
		
		return mg.find(databaseName, collectionName, beginDate, endDate);
	}

	@Override
	public long findMovableNumByParamCode(String series, String star, String collectionName, String paramName,
			Date beginDate, Date endDate) {
		String databaseName = InitMongo.getDataDBBySeriesAndStar(series, star);
		return mg.countByDate(databaseName, collectionName, beginDate, endDate, "paramName", paramName);
	}

}
