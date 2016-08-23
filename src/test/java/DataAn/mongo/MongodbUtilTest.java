package DataAn.mongo;

import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bson.Document;
import org.junit.Before;
import org.junit.Test;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;

import DataAn.common.utils.DateUtil;
import DataAn.common.utils.UUIDGeneratorUtil;
import DataAn.fileSystem.dto.CSVFileDataResultDto;
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
			CSVFileDataResultDto<Document> result= csvService.readCSVFileToDoc(filePath,uuId);
			List<Document> documents = result.getDatas();
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
	public void getCollection(){
//		Date date1 = new Date(2016,1,10);
//		Date date2 = new Date(2016,2,10);
//		Date date3 = new Date(2016,3,10);
//		Date date4 = new Date(2016,4,10);
//		Date date5 = new Date(2016,5,10);
//		Date date6 = new Date(2016,6,10);
		Calendar date1 = Calendar.getInstance();
		String strDate = "2010-01-01 00:00:13";
		String strDate2 = "2012-02-03 11:06:28";
		date1.setTime(DateUtil.format(strDate));
		Date startDate = date1.getTime();
		Calendar date2 = Calendar.getInstance();
		date2.setTime(DateUtil.format(strDate2));
		Date endDate = date2.getTime();
		MongoCollection<Document> collection = mg.getCollection(collectionName);
		System.out.println(date1.getTime() + "--" + DateUtil.format(date1.getTime()));
		System.out.println(date2.getTime() + "--" + DateUtil.format(date2.getTime()));
		long month1 = collection.count(Filters.and(Filters.gte("datetime", startDate),Filters.lte("datetime", endDate)));
		System.out.println(month1);
		//mg.findAll(collectionName);
	}
}
