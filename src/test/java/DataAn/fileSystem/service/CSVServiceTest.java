package DataAn.fileSystem.service;

import java.io.File;
import java.nio.charset.Charset;
import java.text.DecimalFormat;
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
import DataAn.common.utils.DateUtil;
import DataAn.common.utils.UUIDGeneratorUtil;
import DataAn.fileSystem.dto.CSVFileDataResultDto;
import DataAn.fileSystem.option.J9Series_Star_ParameterType;
import com.csvreader.CsvWriter;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext-*.xml")
public class CSVServiceTest {

	@Resource
	private ICSVService csvService;
	@Resource
	private IJ9Series_Star_Service j9Series_Star_Service;
	
	
	
	private String filePath = "C:\\j9-02--2016-02-01.csv";
	
//	private String filePath = "C:\\excel_result\\数管分系统.csv";
	
//	private String filePath = "D:\\temp\\data\\2015\\1\\j9-02--2015-01-01.csv";
	
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
	public void readCSVFileToDocAndgetTitle(){
		long begin = System.currentTimeMillis();
		try {
			CSVFileDataResultDto<Document> result = csvService.readCSVFileToDocAndgetTitle(filePath);
			System.out.println(result.getTitle());
		} catch (Exception e) {
			e.printStackTrace();
		}
		long end = System.currentTimeMillis();
		System.out.println("time: " + (end - begin));
	}
	
	@Test
	public void readCSVFileToDoc() throws Exception{
		long begin = System.currentTimeMillis();

		String uuId = UUIDGeneratorUtil.getUUID();
		CSVFileDataResultDto<Document> result= csvService.readCSVFileToDoc(filePath,uuId);
//		IDfsDb fs = MongoDfsDb.getInstance();
//		CSVFileDataResultDto<Document> result= csvService.readCSVFileToDoc(fs.downLoadToStream(InitMongo.FS_J9STAR2, "48d504d0612d46819956979cc5c2e37c"),uuId);
		List<Document> list = result.getDatas();
		System.out.println("size: " + list.size());
		
		Map<String,List<Document>> map = result.getMap();
		List<Document> docList_1s = map.get("1s");
		System.out.println("1s..." + docList_1s.size());
		List<Document> docList_5s = map.get("5s");
		System.out.println("5s..." + docList_5s.size());
		List<Document> docList_15s = map.get("15s");
		System.out.println("15s..." + docList_15s.size());
		List<Document> docList_30s = map.get("30s");
		System.out.println("30s..." + docList_30s.size());
		List<Document> docList_1m = map.get("1m");
		System.out.println("1m..." + docList_1m.size());
		List<Document> docList_5m = map.get("5m");
		System.out.println("5m..." + docList_5m.size());
		List<Document> docList_15m = map.get("15m");
		System.out.println("15m..." + docList_15m.size());
		List<Document> docList_30m = map.get("30m");
		System.out.println("30m..." + docList_30m.size());
		List<Document> docList_1h = map.get("1h");
		System.out.println("1h..." + docList_1h.size());
		List<Document> docList_6h = map.get("6h");
		System.out.println("6h..." + docList_6h.size());
		List<Document> docList_12h = map.get("12h");
		System.out.println("12h..." + docList_12h.size());
		List<Document> docList_1d = map.get("1d");
		System.out.println("1d..." + docList_1d.size());

		long end = System.currentTimeMillis();
		System.out.println("time: " + (end - begin));
	}
	
//	@Test
	public void testWriteCSVByJavacsv(int year,int month,int day) throws Exception{
		String type = J9Series_Star_ParameterType.FLYWHEEL.getName();
		//飞轮-->"电流","转速","温度","指令","供电状态","角动量"
		List<String> params = J9Series_Star_ParameterType.getFlywheelTypeOnParams();
		Map<String,String> map =  j9Series_Star_Service.getAllParameterList_allZh_and_enByOption(type,params);
		Set<String> keys = map.keySet();
		List<String> titleList = new ArrayList<String>();
		titleList.add("时间");
		int count = 1;
		for (String key: keys) {
			//让参数动态变化
//			if(day<15){
//				if(count > (day + 3)){
//					titleList.add(key);				
//				}				
//			}else{
//				if(count > (day - 3)){
//					titleList.add(key);				
//				}
//			}
			titleList.add(key);
			count++;
		}
		//目录
		String dirPath = "E:\\data\\flywheel\\" + year + "\\" + "-0" + month;
		//文件路径
		String outputFile = dirPath +"\\" +"j9-02--" + year +"-0" + month +"-0" + day +".csv";
		if(month > 9){
			dirPath = "E:\\data\\flywheel\\" + year + "\\" + month;
			outputFile = dirPath +"\\" +"j9-02--" + year +"-" + month + "-0" + day +".csv";
		}
		if(day > 9){
			outputFile = dirPath +"\\" +"j9-02--" + year +"-0" + month +"-" + day +".csv";
		}
		if(month > 9 && day > 9){
			outputFile = dirPath +"\\" +"j9-02--" + year +"-" + month +"-" + day +".csv";
		}
		File dir = new File(dirPath);
		if(!dir.exists()){
			dir.mkdirs();
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
				Double data = i * year / 100 + Math.random() * Math.random() * (month + day) * 10;
				if(data < 100){
					data += 100;
				}
				csvOutput.write(df.format(data));					
			}
			csvOutput.endRecord();
			time = time + 1000;
		}
		csvOutput.close();
		System.out.println("file: " + outputFile);
	}
	
	@Test
	public void testWriteCSVByJavacsvList() throws Exception{
		long begin = System.currentTimeMillis();
		for (int year = 2010; year <= 2012; year++) {
			for (int month = 1; month <= 12; month++) {
				for (int day = 1; day <= 10; day++) {
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
