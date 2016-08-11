package DataAn.mongo;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bson.Document;
import org.junit.Before;
import org.junit.Test;

import DataAn.common.utils.DateUtil;
import DataAn.common.utils.UUIDGeneratorUtil;
import DataAn.fileSystem.service.ICSVService;
import DataAn.fileSystem.service.impl.CSVServiceImpl;
import DataAn.mongo.db.MongodbUtil;

public class MongodbUtilTest {

	private MongodbUtil mg;
	private ICSVService csvService;
	private String filePath = "C:\\j9-02--2015-08-17.csv";
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
			String uuId = UUIDGeneratorUtil.getUUID();
			String dateVersions = DateUtil.format(new Date());
			List<Document> documents = csvService.readCSVFileToDoc(filePath,dateVersions);
			for (Document document : documents) {
				System.out.println(document);
			}
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
	
	@Test
	public void getIndex(){
		mg.getIndex();
	}

	@Test
	public void update(){
		mg.update(collectionName, "versions", "2016-07-22 15:14:10");
		mg.findAll(collectionName);
	}
	
	@Test
	public void updateBy(){
		mg.updateBy(collectionName, "2015年08月10日00时15分02秒", "2015年08月10日00时15分04秒");
		mg.findAll(collectionName);
	}
	
	@Test
	public void findFirstByYear_month_day(){
		Document doc = mg.findFirstByYear_month_day(collectionName, "2016-01-10");
		Set<String> keys = doc.keySet();
		for (String key : keys) {
			System.out.println(key);
		}
	}
	@Test
	public void test(){
		String date1 = "2016-10-1";
		String date2 = "2016-1-10";
		Date date = new Date();
	}
	
	@Test
	public void getCollections(){
		mg.getListCollections();
	}
}
