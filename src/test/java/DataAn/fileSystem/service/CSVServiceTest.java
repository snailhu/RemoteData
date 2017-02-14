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
import DataAn.galaxyManager.option.J9Series_Star_ParameterType;
import DataAn.galaxyManager.service.IJ9Series_Star_Service;
import DataAn.galaxyManager.service.IParameterService;

import com.csvreader.CsvWriter;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext-*.xml")
public class CSVServiceTest {

	@Resource
	private ICSVService csvService;
	@Resource
	private IJ9Series_Star_Service j9Series_Star_Service;
	@Resource
	private IParameterService paramService;
	
	
//	private String filePath = "E:\\data\\flywheel\\2000\\01\\j9-02--2000-01-01.csv";
	
//	private String filePath = "C:\\excel_result\\数管分系统.csv";
	
	private String filePath = "E:\\data\\top\\2005\\j9-04--2005-01-01.csv";
	
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
			String series = "j9";
			String star = "04";
			String paramType = "top";
//			CSVFileDataResultDto<Document> result = csvService.readCSVFileToDocAndgetTitle(filePath);
//			System.out.println(result.getTitle());
//			String title = result.getTitle();
			String title = "星上系统钟,F0W53_54:滚动陀螺角速度(00131),F0W55_56:俯仰陀螺角速度(00133),F0W57_58:偏航陀螺角速度(00135),F6W81_84:1A星敏感器滚动姿态(00199),F6W85_88:1A星敏感器俯仰姿态(00208),F6W89_92:1A星敏感器偏航姿态(00217),F6W93_96:1B星敏感器滚动姿态(00225),F6W97_100:1B星敏感器俯仰姿态(00231),F6W101_104:1B星敏感器偏航姿态(00241),F7W81_82:陀螺组合标志(00200),F7W85_86:星敏感器组合标志(00209),F5W89_92:X轴陀螺漂移估计(00216),F5W93_96:Y轴陀螺漂移估计(00224),F5W97_100:Z轴陀螺漂移估计(00230),F23W214:陀螺1a温度(00925),F24W214:陀螺1b温度(00926),F25W214:陀螺2温度(00927)";
			String[] param_zhs = title.split(",");
			for (String param_zh : param_zhs) {
				System.out.println(param_zh + "--->" + paramService.getParameter_en_by_allZh(series, star, paramType, param_zh));
			}
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
		if(list != null && list.size() > 0)
			System.out.println("list size: " + list.size());
		for (Document document : list) {
			System.out.println(document);
		}
		
		Map<String,List<Document>> map = result.getMap();
		if(map != null){
			for (String key : map.keySet()){
				list = map.get(key);
				if(list != null && list.size() > 0)
					System.out.println(key + " size: " + list.size());
			}			
		}

		long end = System.currentTimeMillis();
		System.out.println("time: " + (end - begin));
	}
	
//	@Test
	public void testWriteCSVByJavacsv(int year,int month,int day) throws Exception{
		String type = J9Series_Star_ParameterType.FLYWHEEL.getName();
		//飞轮-->"电流","转速","温度","指令","供电状态","角动量"
		List<String> params = J9Series_Star_ParameterType.getFlywheelParamTypeList();
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
		String dirPath = "E:\\data\\flywheel\\" + year + "\\" + "0" + month;
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
	
	
}
