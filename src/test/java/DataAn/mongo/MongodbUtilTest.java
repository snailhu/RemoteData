package DataAn.mongo;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bson.Document;
import org.junit.Before;
import org.junit.Test;

import DataAn.fileSystem.service.ICSVService;
import DataAn.fileSystem.service.impl.CSVServiceImpl;
import DataAn.mongo.db.MongodbUtil;

public class MongodbUtilTest {

	private MongodbUtil mg;
	private ICSVService csvService;
	private String filePath = "C:\\j9-02--2015-08-10.csv";
	private String collectionName = "star2";
	
	@Before
	public void init(){
		mg = MongodbUtil.getInstance();
		csvService = new CSVServiceImpl();
	}
	@Test
	public void saveCSVFileData(){
		long begin = System.currentTimeMillis();
		try {
			List<Document> documents = csvService.readCSVFileToDoc(filePath);
			mg.insert(collectionName, documents);;
		} catch (Exception e) {
			e.printStackTrace();
		}
		long end = System.currentTimeMillis();
		System.out.println("time: " + (end - begin));
	}
	
	@Test
	public void find(){
		Document doc = mg.findFirst(collectionName);
		System.out.println(doc.get("datetime") + " : " + doc.get("flywheel_a_motor_current"));
		
//		Set<String> set = doc.keySet();
//		for (String key : set) {
//			System.out.println(key);
//		}
//		Collection<Object> cols = doc.values();
//		for (Object object : cols) {
//			System.out.println(object);
//		}
	}

	@Test
	public void findAll(){
//		List<Float> list = mg.findAll(collectionName);
//		for (Float value : list) {
//			System.out.println(value);
//		}
		Map<String,Float> map = mg.findAll(collectionName);
//		Set<String> keySet = map.keySet();
//		for (String key : keySet) {
//			System.out.println(key + " : " + map.get(key));
//		}
	}

}
