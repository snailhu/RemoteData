package DataAn.mongo;

import org.bson.Document;
import org.junit.Before;
import org.junit.Test;

import com.mongodb.client.MongoCursor;

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
		
	}

}
