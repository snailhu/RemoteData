package DataAn.mongo;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.bson.Document;
import org.junit.Before;
import org.junit.Test;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;

import DataAn.common.utils.DateUtil;
import DataAn.galaxyManager.option.J9SeriesType;
import DataAn.galaxyManager.option.J9Series_Star_ParameterType;
import DataAn.galaxyManager.option.SeriesType;
import DataAn.mongo.client.MongodbUtil;
import DataAn.mongo.init.InitMongo;

public class MongodbUtilTest {

	private MongodbUtil mg;
	private String filePath = "C:\\j9-02--2015-08-17.csv";
	
	@Before
	public void init(){
		mg = MongodbUtil.getInstance();
	}

	@Test
	public void test(){
		boolean flag = mg.isExistCollection(InitMongo.DB_J9STAR2, "flywheel5s");
		System.out.println("flag: " + flag);
		Set<String> dbNames = mg.getDatabaseNames();
		for (String dbName : dbNames) {
			System.out.println(dbName);
		}
	}

	@Test
	public void update(){
		
		String databaseName = "db_j9_05";
		String collectionName = "flywheel_job";
		mg.update(databaseName, collectionName, "status", 1, "status", 2);
		
	}
	
	@Test
	public void testCount(){
		Date beginDate = DateUtil.format("2016-12-01 00:00:00");
		Date endDate = DateUtil.format("2016-12-01 00:00:00");
		MongoCollection<Document> collection = mg.getCollection("db_j9_02", "flywheel1s");
		long count = collection.count(Filters.and(Filters.gte("datetime", beginDate),
							   Filters.lte("datetime", endDate)));
		System.out.println("count: " + count);
	}
	
	@Test
	public void testFind(){
		String paramStr = "sequence_00817,sequence_00424,sequence_00426,";
		String[] paramStrs = paramStr.split(",");
		List<String> paramSet = new ArrayList<String>();
		for (String param : paramStrs) {
			paramSet.add(param);
		}
		
		String databaseName = "db_j9_05";
		String collectionName =  "flywheel";
		
		Date beginDate = DateUtil.format("205-01-01 00:00:00");
		Date endDate = DateUtil.format("2016-01-07 00:00:00");
				
		MongoCursor<Document> cursor = mg.find(databaseName, collectionName, beginDate, endDate);
		int count = 0;
		Document doc = null;
		String value = "";
	    while (cursor.hasNext()) {
	    	count++;
	    	doc = cursor.next();
	    	System.out.print(DateUtil.format(doc.getDate("datetime")));
	    	for (String paramCode : paramSet) {
	    		value = doc.getString(paramCode);
	    		int i = value.length();
	    		if(i < 10)
	    			for (; i < 8; i++) 
	    				value += " ";
	    		
				System.out.print(" : " + value);
			}
	    	System.out.println();
		}
	    System.out.println("count: " + count);
	}

}
