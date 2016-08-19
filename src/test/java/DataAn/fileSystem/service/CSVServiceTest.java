package DataAn.fileSystem.service;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.lang.reflect.Field;
import java.nio.charset.Charset;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.bson.Document;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import DataAn.Analysis.dto.ConstraintDto;
import DataAn.common.utils.DateUtil;
import DataAn.common.utils.UUIDGeneratorUtil;
import DataAn.fileSystem.dto.SaveCSVFileResult;
import DataAn.fileSystem.option.J9Series_Star_ParameterGroupType;
import DataAn.fileSystem.service.impl.CSVServiceImpl;
import DataAn.mongo.db.MongodbUtil;

import com.alibaba.fastjson.JSON;
import com.csvreader.CsvWriter;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext-hibernate.xml","classpath:applicationContext.xml"})
public class CSVServiceTest {

	@Resource
	private ICSVService csvService;
	@Resource
	private IJ9Series_Star_Service j9Series_Star_Service;
	
	
	
//	private String filePath = "C:\\j9-02--2015-08-17.csv";
	
//	private String filePath = "C:\\excel_result\\数管分系统.csv";
	
	private String filePath = "D:\\temp\\data\\2015\\5\\j9-02--2015-05-10.csv";
	
	@Test
	public void test(){
//		String fileName = "j9-02--2015-08-10.csv";
//		String[] strs = fileName.substring(0, fileName.lastIndexOf(".csv")).split("--");
//		for (String str : strs) {
//			System.out.println(str);
//		}
//		String[] stars = strs[0].split("-");
//		System.out.println(stars[1]);
		System.out.println(j9Series_Star_Service);
	}
	
	@Test
	public void testData(){
		String data = "#1234";
		System.out.println(data.indexOf("#"));
	}
	
	@Test
	public void readCSVFileToDocAndSaveCacheFile(){
		long begin = System.currentTimeMillis();
		InputStream in = null;
		try {
			String uuId = UUIDGeneratorUtil.getUUID();
			in = new BufferedInputStream(new FileInputStream(new File(filePath)));
			SaveCSVFileResult<Document> result = csvService.readCSVFileToDocAndSaveCacheFile("j9-02--2015-05-10.csv", in, uuId);
			List<Document> list = result.getDatas();
			System.out.println("size: " + list.size());
			System.out.println(result.getCacheFilePath());
			System.out.println(result.getTitle());
		} catch (Exception e) {
			e.printStackTrace();
		}
		long end = System.currentTimeMillis();
		System.out.println("time: " + (end - begin));
	}
	
	@Test
	public void readCSVFileToDoc(){
		long begin = System.currentTimeMillis();
		try {
			String uuId = UUIDGeneratorUtil.getUUID();
			List<Document> list = csvService.readCSVFileToDoc(filePath,uuId);
			System.out.println("size: " + list.size());
//			System.out.println(list.get(list.size() - 1));
//			list.remove(list.size()-1);
//			System.out.println("size: " + list.size());
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
		String type = J9Series_Star_ParameterGroupType.FLYWHEEL.getName();
		//"电流","转速","温度","指令","供电状态","角动量"
		List<String> params = J9Series_Star_ParameterGroupType.getFlywheelTypeOnParams();
		Map<String,String> map =  j9Series_Star_Service.getAllParameterList_allZh_and_enByOption(type,params);
		Set<String> keys = map.keySet();
		List<String> titleList = new ArrayList<String>();
		titleList.add("时间");
		int count = 1;
		for (String key: keys) {
			//让参数动态变化
			if(day<15){
				if(count > (day + 3)){
					titleList.add(key);				
				}				
			}else{
				if(count > (day - 3)){
					titleList.add(key);				
				}
			}
			count++;
		}
		
//		String outputFile = "C:\\j9-02--2016-01-10.csv";
		String dirPath = "E:\\data\\"+year+"\\"+month;
		File dir = new File(dirPath);
		if(!dir.exists()){
			dir.mkdirs();
		}
		String outputFile = dirPath +"\\" +"j9-02--" + year +"-0" + month +"-0" + day +".csv";
		if(month > 9){
			outputFile = dirPath +"\\" +"j9-02--" + year +"-" + month + "-" + day +".csv";
		}
		if(day > 9){
			outputFile = dirPath +"\\" +"j9-02--" + year +"-0" + month +"-" + day +".csv";
		}
		if(month > 9 && day > 9){
			outputFile = dirPath +"\\" +"j9-02--" + year +"-" + month +"-" + day +".csv";
		}
//		File file = new File(outputFile);
//		OutputStream outputStream = new FileOutputStream(file,true);
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
		DecimalFormat df = new DecimalFormat("#.00");
		while(tempDate.before(date2)){
			tempDate = new Date(time);
			csvOutput.write(DateUtil.format(tempDate, format));
			for (int i = 1; i < titleList.size(); i++) {
				Double data = 5 * i + Math.random() * Math.random() * 10;
				
				csvOutput.write(df.format(data));					
			}
			csvOutput.endRecord();
			time = time + 1000;
		}
		csvOutput.close();
		
	}
	
	@Test
	public void testWriteCSVByJavacsvList() throws Exception{
		long begin = System.currentTimeMillis();
		for (int year = 2010; year <= 2015; year++) {
			for (int month = 1; month <= 3; month++) {
				for (int day = 1; day <= 5; day++) {
					this.testWriteCSVByJavacsv(year,month,day);		
				}							
			}
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
