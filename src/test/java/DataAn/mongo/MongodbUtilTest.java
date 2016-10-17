package DataAn.mongo;

import org.junit.Before;
import DataAn.mongo.db.MongodbUtil;

public class MongodbUtilTest {

	private MongodbUtil mg;
	private String filePath = "C:\\j9-02--2015-08-17.csv";
	
	@Before
	public void init(){
		mg = MongodbUtil.getInstance();
	}

	

	

}
