package DataAn.fileSystem.service;

import java.util.List;
import java.util.Map;

import org.bson.Document;
import org.junit.Before;
import org.junit.Test;

import DataAn.fileSystem.service.impl.CSVServiceImpl;
import DataAn.mongo.db.MongodbUtil;

import com.alibaba.fastjson.JSON;


public class CSVServiceTest {

	private ICSVService csvService;
	
	private String filePath = "C:\\j9-02--2015-08-15.csv";
	@Before
	public void init(){
		csvService = new CSVServiceImpl();
	}
	
	@Test
	public void test(){
		String fileName = "j9-02--2015-08-10.csv";
		String[] strs = fileName.substring(0, fileName.lastIndexOf(".csv")).split("--");
		for (String str : strs) {
			System.out.println(str);
		}
		String[] stars = strs[0].split("-");
		System.out.println(stars[1]);
	}
	
	@Test
	public void testData(){
		String data = "#1234";
		System.out.println(data.indexOf("#"));
	}
	@Test
	public void readCSVFileToDoc(){
		long begin = System.currentTimeMillis();
		try {
			List<Document> list = csvService.readCSVFileToDoc(filePath);
			System.out.println("size: " + list.size());
			for (Document document : list) {
				System.out.println(document);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		long end = System.currentTimeMillis();
		System.out.println("time: " + (end - begin));
	}
	
	@Test
	public void readCSVFileOfJsonToDoc(){
		long begin = System.currentTimeMillis();
		try {
			List<Document> list = csvService.readCSVFileOfJsonToDoc(filePath);
			System.out.println("size: " + list.size());
			for (Document document : list) {
				System.out.println(document);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		long end = System.currentTimeMillis();
		System.out.println("time: " + (end - begin));
	}
	
	@Test
	public void readCSVFile(){
		long begin = System.currentTimeMillis();
		try {
			List<Map<String,String>> list = csvService.readCSVFile(filePath);
			System.out.println(JSON.toJSONString(list));
		} catch (Exception e) {
			e.printStackTrace();
		}
		long end = System.currentTimeMillis();
		System.out.println("time: " + (end - begin));
	}
	@Test
	public void readCSVFileToJson(){
		long begin = System.currentTimeMillis();
		try {
			String json = csvService.readCSVFileOfJson(filePath);
			System.out.println(json);
		} catch (Exception e) {
			e.printStackTrace();
		}
		long end = System.currentTimeMillis();
		System.out.println("time: " + (end - begin));
	}
	
	
}
