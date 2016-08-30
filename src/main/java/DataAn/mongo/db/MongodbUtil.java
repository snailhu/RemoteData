package DataAn.mongo.db;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.bson.Document;

import DataAn.Analysis.dto.YearAndParamDataDto;
import DataAn.common.utils.DataTypeUtil;
import DataAn.common.utils.DateUtil;
import DataAn.common.utils.LogUtil;
import DataAn.mongo.init.InitMongo;





import com.mongodb.BasicDBObject;
import com.mongodb.CommandResult;
import com.mongodb.DB;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;


public class MongodbUtil {

	private MongoClient mg = null;
	
	private static ConcurrentHashMap<String, MongoDatabase> dbs = new ConcurrentHashMap<String, MongoDatabase>();
	
	private static ConcurrentHashMap<String,MongoCollection<Document>> cols = new ConcurrentHashMap<String,MongoCollection<Document>>();
		
	private static volatile boolean isShard = false; //分片标示
	
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
			LogUtil.getInstance().getLogger(MongodbUtil.class).debug("DataAn.mongo.client.MongodbUtil() - start "); //$NON-NLS-1$
		}
		if(mg == null){
			if (isShard && DataTypeUtil.isNotEmpty(InitMongo.SERVER_HOSTS)) {
				System.out.println("-----启动集群分片数据库：{}" + InitMongo.SERVER_HOSTS);
				LogUtil.getInstance().getLogger(this.getClass()).info("-----启动集群分片数据库：{}",InitMongo.SERVER_HOSTS);
				List<ServerAddress> addresses = new ArrayList<ServerAddress>();
				String[] list = InitMongo.SERVER_HOSTS.split(",");
				for (String ip : list) {
					ServerAddress address = new ServerAddress(ip, InitMongo.SERVER_PORT);
					addresses.add(address);
				}
				mg = new MongoClient(addresses);
			}else{
				System.out.println("启动单机数据库：{}" + InitMongo.SERVER_HOST);
				LogUtil.getInstance().getLogger(this.getClass()).info("启动单机数据库：{}",InitMongo.SERVER_HOST);
				mg = new MongoClient(InitMongo.SERVER_HOST, InitMongo.SERVER_PORT);
			}
			dbs.put(InitMongo.DATABASE_TEST, getDB(InitMongo.DATABASE_TEST));
			dbs.put(InitMongo.DATABASE_J9STAR2, getDB(InitMongo.DATABASE_J9STAR2));
			//test
//			mg = new MongoClient("127.0.0.1", 10000);
//			dbs.put(InitMongo.DATABASE_TEST, getDB(InitMongo.DATABASE_TEST));
		}
	}
	
	/**
	 * 创建分片数据库
	 * createShardDB:
	 * @param databaseName
	 */
	private void createShardDB(String databaseName) {
		System.out.println("创建新的数据库{},并分片" + databaseName);
		LogUtil.getInstance().getLogger(getClass()).info("创建新的数据库{},并分片",databaseName);
//		MongoDatabase admin = mg.getDatabase("admin");
//		Document doc = new Document();
//		doc.put("enablesharding", databaseName);
//		Document command = admin.runCommand(doc);
		//基于DB 成功创建分片数据库
		DB admin = mg.getDB("admin");
		DBObject obj = new BasicDBObject();
		obj.put("enablesharding", databaseName);
		CommandResult command = admin.command(obj);
		LogUtil.getInstance().getLogger(getClass()).info("分片结果{}",command);
	}

	/**
	 * dropDB:(删除数据库). 
	 * @param databaseName
	 * @return
	 */
	public void dropDB(String databaseName){
		LogUtil.getInstance().getLogger(getClass()).info("删除数据库",databaseName);
		MongoDatabase db = dbs.get(databaseName);
		if (db==null) {
			db = mg.getDatabase(databaseName);
			dbs.put(databaseName, db);
		}
		dbs.remove(databaseName);
		db.drop();
	}
	
	/**
	 * getDB:(获取数据库). 
	 * @param databaseName
	 * @return
	 */
	public MongoDatabase getDB(String databaseName){
		MongoDatabase db = dbs.get(databaseName);
		if (db == null) {
			if ((!this.isExistDB(databaseName)) && isShard) {
				createShardDB(databaseName);
			}
			db = mg.getDatabase(databaseName);
			dbs.put(databaseName, db);
		}
		return db;
	}
	
	/**
	 * 判断数据库是否存在
	 * @param databaseName
	 * @return
	 */
	public boolean isExistDB(String databaseName){
		List<String> databaseNames = mg.getDatabaseNames();
		if (databaseNames.contains(databaseName)) {
			return true;
		}
		return false;
	}
	
	/**
	 * 创建表，并进行分片
	 * createShardCollection:
	 * @param databaseName
	 * @param collectionName
	 */
	private void createShardCollection(String databaseName,String collectionName) {
		System.out.println("创建新的表{}.{},并分片"+databaseName+collectionName);
		LogUtil.getInstance().getLogger(getClass()).info("创建新的表{}.{},并分片",databaseName,collectionName);
//		MongoDatabase admin = mg.getDatabase("admin");
//		Document shard = new Document();
//		Document key = new Document();
//		key.put("_id", "hashed");
//		shard.put("shardcollection", databaseName+"."+collectionName);
//		shard.put("key", key);
//		Document command = admin.runCommand(shard);
		//基于DB 成功创建分片数据库-集合
		DB admin = mg.getDB("admin");
		DBObject shard = new BasicDBObject();
		DBObject key = new BasicDBObject();
		//散列片键
//		key.put("_id", "hashed");
		//升序片键
//		key.put("_id", 1);
		key.put("datetime", 1);
		shard.put("shardcollection", databaseName+"."+collectionName);
		shard.put("key", key);
		CommandResult command = admin.command(shard);
		LogUtil.getInstance().getLogger(getClass()).info("分片结果{}",command);
	}
	
	/**
	 * getCollection:(获取表集合). 
	 * @param databaseName
	 * @param collectionName
	 * @return
	 */
	public MongoCollection<Document> getCollection(String databaseName,String collectionName){
		MongoCollection<Document> dbCollection = cols.get(databaseName+collectionName);
		if (dbCollection==null) {
			MongoDatabase db = getDB(databaseName);
			//需要判断集合时候存在
			MongoIterable<String> collectionNames = db.listCollectionNames();
			boolean flag = false;
			for (String collection : collectionNames) {
				if(collection.equals(collectionName)){
					flag = true;
					break;
				}
			}
			if (!flag && isShard) {
				createShardCollection(databaseName, collectionName);
			}
			dbCollection = db.getCollection(collectionName);
			cols.put(databaseName+collectionName, dbCollection);
		}
		return dbCollection;
	}
	
	/**
	 * dropCollection:(删除表集合). 
	 * @param databaseName
	 * @param collectionName
	 * @return
	 */
	public void dropCollection(String databaseName,String collectionName){
		MongoCollection<Document> dbCollection = cols.get(databaseName+collectionName);
		if (dbCollection==null) {
			MongoDatabase db = getDB(databaseName);
			dbCollection = db.getCollection(collectionName);
			cols.put(databaseName+collectionName, dbCollection);
		}
		dbCollection.drop();
	}
	
	public void getIndex(String databaseName,String collectionName){
		MongoCollection<Document> collection = this.getCollection(databaseName, collectionName);
		for (final Document index : collection.listIndexes()) {
			System.out.println(index.toJson());
		}
	}

	/**
	* @Title: insert
	* @Description: 为相应的集合添加数据
	* @param databaseName 数据库名称
	* @param collectionName 集合名称
	* @author Shenwp
	* @date 2016年7月6日
	* @version 1.0
	*/
	public void insertOne(String databaseName,String collectionName, Map<String, Object> map){
		MongoCollection<Document> collection = this.getCollection(databaseName, collectionName);
		Document doc = new Document(map);
		collection.insertOne(doc);
	}

	/**
	* @Title: insert
	* @Description: 批量插入数据
	* @param databaseName 数据库名称 InitMongo.DATABASE_J9STAR2
	* @param collectionName 集合名称 tb+年
	* @param documents doc集合
	* @author Shenwp
	* @date 2016年7月6日
	* @version 1.0
	*/
	public void insertMany(String databaseName,String collectionName, List<Document> documents){
		MongoCollection<Document> collection = this.getCollection(databaseName, collectionName);
		collection.insertMany(documents);
	}
	
	
	/**
	* @Title: insert
	* @Description: 添加一个Json格式的数据类型
	* @param databaseName 数据库名称
	* @param collectionName 集合名称
	* @param json 单个json格式数据
	* @author Shenwp
	* @date 2016年7月6日
	* @version 1.0
	*/
	public void insertOne(String databaseName,String collectionName, String json){
		MongoCollection<Document> collection = this.getCollection(databaseName, collectionName);
		Document doc = Document.parse(json);
		collection.insertOne(doc);
	}

	public void deleteMany(String databaseName, String collectionName,String key,Object value){
		MongoCollection<Document> collection = this.getCollection(databaseName, collectionName);
		// key == value
		collection.deleteMany(Filters.eq(key, value));
	}
	
	/**
	* Description: 将某一行的状态标志为0
	* @param databaseName 数据库名称
	* @param collectionName 集合名称
	* @param key uuId
	* @param value
	* @author Shenwp
	* @date 2016年8月2日
	* @version 1.0
	*/
	public void update(String databaseName, String collectionName,String key,String value){
		MongoCollection<Document> collection = this.getCollection(databaseName, collectionName);
		collection.updateMany(Filters.eq(key, value), Updates.set("status", 0));
		// 更新。若包含companyid属性，则热度加10后更新
//        collection.updateOne(
//                Filters.eq(key, value),
//                new Document("$inc", new Document("status", 1)));
        // 批量更新。所有包含hotness属性的其hotness值乘以2
//        collection.updateMany(Filters.exists("hotness"), new Document(
//                "$mul", new Document("hotness", 2)));
	}
	
	public void updateBy(String databaseName, String collectionName, String beginDate, String endDate) {
		MongoCollection<Document> collection = this.getCollection(databaseName, collectionName);
		collection.updateMany(
							Filters.and(Filters.gte("datetime", beginDate),
										Filters.lte("datetime", endDate)),
							Updates.set("status", 0));
	}


	public Document findFirst(String databaseName, String collectionName){
		MongoCollection<Document> collection = this.getCollection(databaseName, collectionName);
		Document doc = collection.find().first();
		return doc;
	}
	
	public Document findFirstByYear_month_day(String databaseName, String collectionName,String date){
		MongoCollection<Document> collection = this.getCollection(databaseName, collectionName);
		Document doc = collection.find(Filters.eq("year_month_day", date)).first();
		return doc;
	}
	//根据日期 获取某个参数在改时间段内的总的点数

	public YearAndParamDataDto getDataList(int selectParamSize,String ...params) throws InterruptedException{
		MongoCollection<Document> collection = this.getCollection(InitMongo.DATABASE_TEST, "star2");		
		//string 类型的时间转换成日期
		Calendar date1 = Calendar.getInstance();
		date1.setTime(DateUtil.format(params[0]));
		Date startDate = date1.getTime();//DateUtil.format(params[0]);
		Calendar date2 = Calendar.getInstance();
		date2.setTime(DateUtil.format(params[1]));
		Date endDate = date2.getTime();		
		int startYear = DateUtil.getYear(startDate);
		int endYear = DateUtil.getYear(endDate);		
		//根据年来确定需要开启的线程数
		int threadOpen = endYear-startYear;		 
		MongoCursor<Document> cursor =  null;
		long paramCount= 0;
		//Long totalDate =  (endDate.getTime() - startDate.getTime())/(1000*60*60*24);		
		//存放子线程

		paramCount = collection.count(Filters.and(Filters.gte("datetime", startDate),Filters.lte("datetime", endDate)));						

		List<Thread> allChridThread = new ArrayList<Thread>();
		HashMap<Integer,YearAndParamDataDto> resultMap = new HashMap<Integer,YearAndParamDataDto>();
		YearAndParamDataDto yearAndParam = new YearAndParamDataDto();
		

		//查出来的数据总量
	//	paramCount =  collection.count(Filters.and(Filters.gte("datetime", startDate),Filters.lte("datetime", endDate)));
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
			//getResults(cursor,yearAndParam, params);	
			List<String> yearValue=new ArrayList<String>();
			List<String> paramValue =  new ArrayList<String>();
					
			FindIterable<Document> document_It = collection.find(Filters.and(Filters.gte("datetime", startDate),Filters.lte("datetime", endDate)));						
			for (Document doc : document_It) {
			    	if(doc.getString(params[2])!=null){	
			    	yearValue.add(DateUtil.format(doc.getDate("datetime")));
			        paramValue.add(doc.getString(params[2]));	
			    	}		    
			}
		    yearAndParam.setParamValue(paramValue);
		    yearAndParam.setYearValue(yearValue);
					
			return yearAndParam;					
		}
		else{	//如果比每页显示总数大
			
//			ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 10, 200, TimeUnit.SECONDS,
//	                 new ArrayBlockingQueue<Runnable>(5));
			long pear_param = total/selectParamSize;						
				//获取分子就是取多少个点
			long getpoint = paramCount/pear_param;	
			List<String> yearValue=new ArrayList<String>();
			List<String> paramValue =  new ArrayList<String>();
			if(threadOpen == 0){
				FindIterable<Document> document_It = collection.find(Filters.and(Filters.gte("datetime", startDate),Filters.lte("datetime", endDate)));						
				int count_for = 0;
				for (Document doc : document_It) {
					if(count_for%getpoint==0){
				    	if(doc.getString(params[2])!=null){	
				    	yearValue.add(DateUtil.format(doc.getDate("datetime")));
				        paramValue.add(doc.getString(params[2]));	
				    	}		    
					}
					count_for++;
				}
				System.out.println(Thread.currentThread().getName()+".....装载完毕"+count_for);
				yearAndParam.setYearValue(yearValue);
			    yearAndParam.setParamValue(paramValue);
				return yearAndParam;
			}
			if(threadOpen == 1){
				 
				FindPointByYearHead  headThread = new FindPointByYearHead(1,getpoint,resultMap, collection, params);
				allChridThread.add(headThread);
				headThread.start();
				
				
				FindPointByYearEnd  endThread =  new FindPointByYearEnd(2,getpoint,resultMap, collection, params);
				allChridThread.add(endThread);
				endThread.start();	
//				executor.execute(headThread);
//				executor.execute(endThread);
			}
			if(threadOpen>1){
				int i = 1;
				FindPointByYearHead  headThread = new FindPointByYearHead(1,getpoint,resultMap, collection, params);
				allChridThread.add(headThread);
				headThread.start();	
//				executor.execute(headThread);
				
				for(;i<threadOpen;i++){
					FindPointByYear yearThread =  new FindPointByYear(i+1,getpoint,resultMap, collection, (startYear+i), params);					
					allChridThread.add(yearThread);
					yearThread.start();
//					executor.execute(yearThread);				
				}
				FindPointByYearEnd  endYearThread =  new FindPointByYearEnd(i+1,getpoint,resultMap,collection, params);
				allChridThread.add(endYearThread);	
				endYearThread.start();
			
				//executor.execute(endYearThread);
											
			}
			
			for(Thread year_Thread : allChridThread){			
				try {
					year_Thread.join();
				} catch (InterruptedException e) {
					e.printStackTrace();
				} 
			}			
			for(int i=1;i<=threadOpen+1;i++){	
				yearValue.addAll(resultMap.get(i).getYearValue());
				paramValue.addAll(resultMap.get(i).getParamValue());
			}
			 yearAndParam.setParamValue(paramValue);
			 yearAndParam.setYearValue(yearValue);			
			return yearAndParam;
		}			
	}

//	private void getResults(MongoCursor<Document> cursor,YearAndParamDataDto yearAndParam,
//			String... params) {
//		try {
//			List<String> yearValue=new ArrayList<String>();
//			List<String> paramValue =  new ArrayList<String>();
//
//		    while (cursor.hasNext()) {
//		    	Document doc = cursor.next();
//		 
//		    	if(doc.getString(params[2])!=null){	
//		    	yearValue.add(doc.getDate("datetime")+"");
//		        paramValue.add(doc.getString(params[2]));
//		    	}		    		
//		    }
//		    yearAndParam.setParamValue(paramValue);
//		    yearAndParam.setYearValue(yearValue);
// 
//		} finally {
//		    cursor.close();
//		}
//	}
		
//	public List<String> getDateList(int selectParamSize,String ...params){
//		MongoCollection<Document> collection = db.getCollection("star2");
//	//	MongoCursor<Document> cursor = collection.find().iterator();
//		MongoCursor<Document> cursor = collection.find(Filters.eq("year_month_day", "2016-10-10")).iterator();
//		try {
//			List<String> paramValue =  new ArrayList<String>();
////			HashMap<String,List<String>> paramMap = new HashMap<String,List<String>>();
//			int count=0;
//		    while (cursor.hasNext()) {	
//		    	if(count>=10000){break;}
//		    	Document doc = cursor.next();
//		    //	DateUtil.formatString("2015年08月10日00时15分01秒","yyyy-MM-dd HH:mm:ss")
//		    	//String value = DateUtil.formatString(doc.getString("datetime"),"yyyy-MM-dd HH:mm:ss");
//		    	String value = doc.getString("datetime");
//		    	//String value = doc.getString("flywheel_b_power_plus_5V");	        
//	            paramValue.add(value);	
//	           count++;
//		    }
//		    return paramValue;  
//		} finally {
//		    cursor.close();
//		}		
//	}			
}
