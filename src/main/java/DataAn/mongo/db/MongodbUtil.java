package DataAn.mongo.db;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;






import org.bson.Document;

import DataAn.Analysis.dto.YearAndParamDataDto;
import DataAn.common.utils.DateUtil;
import DataAn.common.utils.LogUtil;
import DataAn.mongo.init.InitMongo;






import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.ListIndexesIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;


public class MongodbUtil {

	private MongoClient mg = null;
	
	private  MongoDatabase db = null;
	
	private volatile static MongodbUtil singleton = null;
	
	public static MongodbUtil getInstance() {
		if (singleton == null) {
			synchronized (MongodbUtil.class) {
				if (singleton == null) {
					singleton = new MongodbUtil();
				}
			}
			singleton = new MongodbUtil();
		}
		return singleton;
	}
	
	private MongodbUtil(){
		if (LogUtil.getInstance().getLogger(MongodbUtil.class).isDebugEnabled()) {
			LogUtil.getInstance().getLogger(MongodbUtil.class).debug("MongodbCollectionManagerUtil() - start "); //$NON-NLS-1$
		}
		mg = new MongoClient( InitMongo.SERVER_HOST , InitMongo.SERVER_PORT );
		db = mg.getDatabase(InitMongo.DATABASE_TEST);
//		if(mg == null){
//		}
	}
	
	public void getListCollections(){
		MongoIterable<String> collections = db.listCollectionNames();
		for (String collection : collections) {
			System.out.println(collection);
		}
	}
	
	public void dropCollection(){
		MongoCollection<Document> collection = db.getCollection("testNew");
		collection.drop();
	}
	public void getIndex(){
		MongoCollection<Document> collection = db.getCollection("testNew");
		for (final Document index : collection.listIndexes()) {
			System.out.println(index.toJson());
		}
	}

	/**
	* @Title: insert
	* @Description: 为相应的集合添加数据
	* @param collectionName 集合名称
	* @author Shenwp
	* @date 2016年7月6日
	* @version 1.0
	*/
	public void insert(String collectionName, Map<String, Object> map){
		MongoCollection<Document> collection = db.getCollection(collectionName);
		Document doc = new Document(map);
		collection.insertOne(doc);
	}

	/**
	* @Title: insert
	* @Description: 批量插入数据
	* @param collectionName 集合名称
	* @param documents doc集合
	* @author Shenwp
	* @date 2016年7月6日
	* @version 1.0
	*/
	public void insert(String collectionName, List<Document> documents){
		MongoCollection<Document> collection = db.getCollection(collectionName);
		collection.insertMany(documents);
	}
	
	
	/**
	* @Title: insert
	* @Description: 添加一个Json格式的数据类型
	* @param collectionName 集合名称
	* @param json 单个json格式数据
	* @author Shenwp
	* @date 2016年7月6日
	* @version 1.0
	*/
	public void insert(String collectionName, String json){
		MongoCollection<Document> collection = db.getCollection(collectionName);
		Document doc = Document.parse(json);
		collection.insertOne(doc);
	}
	
	public void deleteOne(){
		MongoCollection<Document> collection = db.getCollection("testNew");
		collection.deleteOne(Filters.eq("i", 9));
	}
	public void deleteMany(String collectionName,String key,Object value){
		MongoCollection<Document> collection = db.getCollection(collectionName);
		// key == value
		collection.deleteMany(Filters.eq(key, value));
	}
	
	/**
	* Description: 将某一行的状态标志为0
	* @param collectionName 集合名称
	* @param key uuId
	* @param value
	* @author Shenwp
	* @date 2016年8月2日
	* @version 1.0
	*/
	public void update(String collectionName,String key,String value){
		MongoCollection<Document> collection = db.getCollection(collectionName);
		collection.updateMany(Filters.eq(key, value), Updates.set("status", 0));
		// 更新。若包含companyid属性，则热度加10后更新
//        collection.updateOne(
//                Filters.eq(key, value),
//                new Document("$inc", new Document("status", 1)));
        // 批量更新。所有包含hotness属性的其hotness值乘以2
//        collection.updateMany(Filters.exists("hotness"), new Document(
//                "$mul", new Document("hotness", 2)));
	}
	
	public void updateBy(String collectionName, String beginDate, String endDate) {
		MongoCollection<Document> collection = db.getCollection(collectionName);
		collection.updateMany(
							Filters.and(Filters.gte("datetime", beginDate),
										Filters.lte("datetime", endDate)),
							Updates.set("status", 0));
	}


	public Document findFirst(String collectionName){
		MongoCollection<Document> collection = db.getCollection(collectionName);
		Document doc = collection.find().first();
		return doc;
	}
	
	public Document findFirstByYear_month_day(String collectionName,String date){
		MongoCollection<Document> collection = db.getCollection(collectionName);
		Document doc = collection.find(Filters.eq("year_month_day", date)).first();
		return doc;
	}
	
	public Map<String,Float> findAll(String collectionName){
		List<Float> list = new ArrayList<Float>();
		Map<String,Float> map = new HashMap<String,Float>();
		MongoCollection<Document> collection = db.getCollection(collectionName);
		MongoCursor<Document> cursor = collection.find().iterator();
		try {
			int count = 1;
		    while (cursor.hasNext()) {
		    	if(count == 50){
		    		break;
		    	}
		    	Document doc = cursor.next();
		    	System.out.println(doc.getDate("datetime") + "--" + DateUtil.format(doc.getDate("datetime")));
//		    	float value = Float.parseFloat(doc.getString("flywheel_a_power_plus_5V"));
//		    	System.out.println(key + " : " + value);
//		    	map.put(key, value);
//		    	list.add(value);
		    	count ++;
		    }
		} finally {
		    cursor.close();
		}
		return map;
	}
	public MongoCollection<Document> getCollection(String collectionName){
		MongoCollection<Document> collection = db.getCollection(collectionName);
		return collection;
	}
	public void find(){
		MongoCollection<Document> collection = db.getCollection("testNew");
		Document doc = collection.find(Filters.eq("i", 7)).first();
		System.out.println(doc.toJson());
	}
	public List<String> findAllByTie(String param){
		MongoCollection<Document> collection = db.getCollection("star2");
	//	MongoCursor<Document> cursor = collection.find().iterator();
		MongoCursor<Document> cursor = collection.find(Filters.eq("year_month_day", "2016-10-10")).iterator();
		try {
			List<String> paramValue =  new ArrayList<String>();
//			HashMap<String,List<String>> paramMap = new HashMap<String,List<String>>();
			int count=0;
		    while (cursor.hasNext()) {
		    	if(count>=10000){break;}
		    	Document doc = cursor.next();
		    	if(doc.getString(param)!=null){
		    		
	    		//Float value = Float.parseFloat(doc.getString(param));	  
	            paramValue.add(doc.getString(param));	
		    	}	    	
	            count++;
		    }
		    return 	paramValue;  
		} finally {
		    cursor.close();
		}	
	}	
	//根据日期 获取某个参数在改时间段内的总的点数

	public YearAndParamDataDto getDataList(int selectParamSize,String ...params) throws InterruptedException{
		MongoCollection<Document> collection = db.getCollection("star2");
		
		//string 类型的时间转换成日期
		Date startDate = DateUtil.format(params[0]);
		Date endDate = DateUtil.format(params[1]);
		int startYear = DateUtil.getYear(startDate);
		int endYear = DateUtil.getYear(endDate);		
		//根据年来确定需要开启的线程数
		int threadOpen = endYear-startYear;		 
		MongoCursor<Document> cursor =  null;
		//存放子线程
		List<Thread> allChridThread = new ArrayList<Thread>();
		HashMap<Integer,YearAndParamDataDto> resultMap = new HashMap<Integer,YearAndParamDataDto>();
		YearAndParamDataDto yearAndParam = new YearAndParamDataDto();
		long paramCount=0;

		//查出来的数据总量
		paramCount =  collection.count(Filters.and(Filters.gte("datetime", params[0]),Filters.lte("datetime", params[1])));
		//获取所有的结果集合
	
		//	FindIterable<Document> a= collection.find(Filters.and(Filters.gte("datetime", params[0]),Filters.lte("datetime", params[1]))).batchSize(20);	
								
		//设置每页可显示的最多点数
		long total = 50000;
		if(selectParamSize==1){
			total =5000;
		}else if(selectParamSize==2){
			total =10000;
		}
		
		//如果参数的个数乘以每个参数查出来的数据小于等于总数则直接将查询结果返回
		if(paramCount*selectParamSize<=total){	
			cursor = collection.find(Filters.and(Filters.gte("datetime", params[0]),Filters.lte("datetime", params[1]))).iterator();
			getResults(cursor,yearAndParam, params);	
			return yearAndParam;					
		}
		else{	//如果比每页显示总数大
			long pear_param = total/selectParamSize;						
				//获取分子就是取多少个点
			long getpoint = paramCount/pear_param;	
			List<String> yearValue=new ArrayList<String>();
			List<String> paramValue =  new ArrayList<String>();
			if(threadOpen == 0){
				try {					
					int count = 0;
				    while (cursor.hasNext()) {
				    	Document doc = cursor.next();
				    	if(count%getpoint==0){
				    		if(doc.getString(params[2])!=null){	
					    	yearValue.add(doc.getString("datetime"));
					        paramValue.add(doc.getString(params[2]));
				    		}
				    	}		    	
				        count++;
				    }
				    yearAndParam.setParamValue(paramValue);
				    yearAndParam.setYearValue(yearValue);
		 
				} finally {
				    cursor.close();
				}
				return yearAndParam;
			}
			if(threadOpen == 1){
				FindPointByYearHead  headThread = new FindPointByYearHead(1,getpoint,resultMap, collection, params);
				allChridThread.add(headThread);
				headThread.start();
				
				FindPointByYearEnd  endThread =  new FindPointByYearEnd(2,getpoint,resultMap, collection, params);
				allChridThread.add(headThread);
				endThread.start();	
			}
			if(threadOpen>=1){
				int i = 1;
				FindPointByYearHead  headThread = new FindPointByYearHead(1,getpoint,resultMap, collection, params);
				allChridThread.add(headThread);
				headThread.start();	
				
				for(;i<threadOpen;i++){
					FindPointByYear yearThread =  new FindPointByYear(i+1,getpoint,resultMap, collection, (startYear+i)+"", params);					
					allChridThread.add(yearThread);
					yearThread.start();
									
				}
				FindPointByYearEnd  endYearThread =  new FindPointByYearEnd(i+1,getpoint,resultMap, collection, params);
				allChridThread.add(endYearThread);	
				endYearThread.start();
											
			}
			
			for(Thread year_Thread : allChridThread){			
				try {
					year_Thread.join();
				} catch (InterruptedException e) {
					e.printStackTrace();
				} 
			}			
			for(int i=1;i<=threadOpen+1;i++){
				
				yearValue.addAll(i-1,resultMap.get(i).getYearValue());
				paramValue.addAll(i-1, resultMap.get(i).getParamValue());
			}
			 yearAndParam.setParamValue(paramValue);
			 yearAndParam.setYearValue(yearValue);
			
			return yearAndParam;
		}			
	}

	private void getResults(MongoCursor<Document> cursor,YearAndParamDataDto yearAndParam,
			String... params) {
		try {
			List<String> yearValue=new ArrayList<String>();
			List<String> paramValue =  new ArrayList<String>();

		    while (cursor.hasNext()) {
		    	Document doc = cursor.next();
		 
		    	if(doc.getString(params[2])!=null){	
		    	yearValue.add(doc.getString("datetime"));
		        paramValue.add(doc.getString(params[2]));
		    	}		    		
		    }
		    yearAndParam.setParamValue(paramValue);
		    yearAndParam.setYearValue(yearValue);
 
		} finally {
		    cursor.close();
		}
	}
		
	public List<String> getDateList(int selectParamSize,String ...params){
		MongoCollection<Document> collection = db.getCollection("star2");
	//	MongoCursor<Document> cursor = collection.find().iterator();
		MongoCursor<Document> cursor = collection.find(Filters.eq("year_month_day", "2016-10-10")).iterator();
		try {
			List<String> paramValue =  new ArrayList<String>();
//			HashMap<String,List<String>> paramMap = new HashMap<String,List<String>>();
			int count=0;
		    while (cursor.hasNext()) {	
		    	if(count>=10000){break;}
		    	Document doc = cursor.next();
		    //	DateUtil.formatString("2015年08月10日00时15分01秒","yyyy-MM-dd HH:mm:ss")
		    	//String value = DateUtil.formatString(doc.getString("datetime"),"yyyy-MM-dd HH:mm:ss");
		    	String value = doc.getString("datetime");
		    	//String value = doc.getString("flywheel_b_power_plus_5V");	        
	            paramValue.add(value);	
	           count++;
		    }
		    return paramValue;  
		} finally {
		    cursor.close();
		}		
	}			
}
