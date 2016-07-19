package DataAn.fileSystem.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.lang.reflect.Field;
import java.nio.charset.Charset;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.bson.Document;
import org.junit.Before;
import org.junit.Test;

import DataAn.Analysis.dto.ConstraintDto;
import DataAn.common.utils.DateUtil;
import DataAn.fileSystem.service.impl.CSVServiceImpl;
import DataAn.mongo.db.MongodbUtil;

import com.alibaba.fastjson.JSON;
import com.csvreader.CsvWriter;


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
	
//	@Test
	public void testWriteCSVByJavacsv(int year,int month,int day) throws Exception{
		Class<?> pojoClass = Class.forName("DataAn.fileSystem.option.FlyWheel");
		Object obj = pojoClass.newInstance();
		Field[] fields = pojoClass.getDeclaredFields();
		List<String> titleList = new ArrayList<String>();
		titleList.add("时间");
		for (Field field : fields) {
//			field.setAccessible(true);// 修改访问控制权限
			titleList.add(field.get(obj).toString());
		}
		
//		String outputFile = "C:\\j9-02--2016-01-10.csv";
		String outputFile = "C:\\j9-02--" + year +"-0" + month +"-0" + day +".csv";
		if(month > 9){
			outputFile = "C:\\j9-02--" + year +"-" + month +"-10.csv";
		}
		if(day > 9){
			outputFile = "C:\\j9-02--" + year +"-0" + month +"-" + day +".csv";
		}
		if(month > 9 && day > 9){
			outputFile = "C:\\j9-02--" + year +"-" + month +"-" + day +".csv";
		}
		File file = new File(outputFile);
		OutputStream outputStream = new FileOutputStream(file,true);
//		OutputStreamWriter writer = new OutputStreamWriter(outputStream, "utf-8");
//		FileWriter fileWriter = new FileWriter(file,true);
		CsvWriter csvOutput = new CsvWriter(outputFile, ',',Charset.forName("gb2312"));
		
		for (String title : titleList) {
			csvOutput.write(title);
		}
		csvOutput.endRecord();
		//月份从0开始计数
//		Date date1 = new Date(2015,5,10,0,0,0);
//		Date date2 = new Date(2015,5,11,0,0,0);
		Date date1 = new Date(year,month-1,day,0,0,0);
		Date date2 = new Date(year,month-1,day+1,0,0,0);
		Date tempDate = date1;
		long time = tempDate.getTime();
		String format = year+ "年MM月dd日HH时mm分ss秒";
		while(tempDate.before(date2)){
			tempDate = new Date(time);
			csvOutput.write(DateUtil.format(tempDate, format));
			for (int i = 1; i < titleList.size(); i++) {
				Double data = Math.random() * Math.random();
				if(data < 0.01){
					csvOutput.write("0.01");
				}else{
					csvOutput.write(String.valueOf(data.floatValue()));					
				}
			}
			csvOutput.endRecord();
			time = time + 500;
		}
		csvOutput.close();
		
	}
	
	@Test
	public void testWriteCSVByJavacsvList() throws Exception{
		long begin = System.currentTimeMillis();
		for (int i = 1; i <= 6; i++) {
			this.testWriteCSVByJavacsv(2016,i,10);		
		}
		long end = System.currentTimeMillis();
		System.out.println("time: " + (end - begin));
	}
	@Test
	public void testDate() throws Exception{
		Date date = new java.text.SimpleDateFormat("yyyy年MM月dd日HH时mm分ss秒").parse("2015年08月10日13时2分20秒");
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		System.out.println(cal.get(Calendar.YEAR));
		//月份是从0开始计数的
		System.out.println(cal.get(Calendar.MONTH) + 1);
		System.out.println(cal.get(Calendar.DATE));
		System.out.println(cal.get(Calendar.HOUR_OF_DAY));
		System.out.println(cal.get(Calendar.MINUTE));
		System.out.println(cal.get(Calendar.SECOND));
		Date date1 = new Date(2015,9,10,0,0,0);
		Date date2 = new Date(2015,9,11,0,0,0);
		Date tempDate = date1;
		long time = tempDate.getTime();
		while(tempDate.before(date2)){
			tempDate = new Date(time);
			System.out.println("time: " + tempDate.getTime() + "--" + tempDate);
			time = time + 500;
		}
	}
	
	@Test
	public void testMath(){
		Double data = Math.random() * Math.random();
		System.out.println(data.floatValue());
	}
}
