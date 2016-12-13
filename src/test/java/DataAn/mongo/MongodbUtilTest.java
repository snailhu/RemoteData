package DataAn.mongo;

import java.util.Date;

import org.bson.Document;
import org.junit.Before;
import org.junit.Test;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;

import DataAn.common.utils.DateUtil;
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
	}

	@Test
	public void test2(){

		Date startDate = DateUtil.format("2000-01-01 10:58:00");
		Date endDate = DateUtil.format("2000-01-01 13:58:01");
		MongoCollection<Document> collection = mg.getCollection("db_j9_02", "flywheel1s");
		FindIterable<Document> document_It = collection.find(Filters.and(Filters.gte("datetime", startDate),Filters.lte("datetime", endDate)));						
		long begin = System.currentTimeMillis();
		int count =0;
		for (Document document : document_It) {
			String paraVal = document.getString("year");
			//System.out.println(document);
			count++;
		}
		long end=System.currentTimeMillis();
				System.out.println("多少个数据："+count+"耗时："+(end-begin));
		
	}
	
	@Test
	public void testCount(){
		Date beginDate = DateUtil.format("2016-12-01 00:00:00");
		Date endDate = DateUtil.format("2016-12-07 00:00:00");
		MongoCollection<Document> collection = mg.getCollection("db_j9_02", "flywheel1s");
		long count = collection.count(Filters.and(Filters.gte("datetime", beginDate),
							   Filters.lte("datetime", endDate)));
		System.out.println("count: " + count);
	}
	
	@Test
	public void testFind(){
		Date beginDate = DateUtil.format("2016-12-01 00:00:00");
		Date endDate = DateUtil.format("2016-12-07 00:00:00");
		MongoCollection<Document> collection = mg.getCollection("db_j9_02", "flywheel1s");
		Document keys = new Document();
		keys.put("_id", 0);
	    keys.put("datetime", 1);
	    keys.put("sequence_00428", 1);
	    MongoCursor<Result> cursor = collection.find(Filters.and(Filters.gte("datetime", beginDate),
				   Filters.lte("datetime", endDate)),Result.class).iterator();
	    while (cursor.hasNext()) {
			System.out.println(cursor.next());
		}
	    
	}

}
