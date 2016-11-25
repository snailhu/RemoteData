package DataAn.fileSystem.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import DataAn.common.utils.DateUtil;
import DataAn.galaxyManager.option.J9SeriesParamConfigService;

import com.csvreader.CsvWriter;

public class MakeCSVFile {

	@Test
	public void testCopyDataCSV() throws Exception{
		List<String> list = new ArrayList<String>();
		File file = new File("C:\\feb-9-05.csv");
		InputStream in = new FileInputStream(file);
		InputStreamReader inputStreamReader = new InputStreamReader(in, "gb2312");
		BufferedReader reader = new BufferedReader(inputStreamReader);// 换成你的文件名
		String line = reader.readLine();// 第一行信息，为标题信息，不用,如果需要，注释掉
		list.add(line);
		while ((line = reader.readLine()) != null) {
			list.add(line);
		}
		System.out.println("size: " + list.size());
		for (int year = 2010; year <= 2012; year++) {
			for (int month = 1; month <= 12; month++) {
				this.writeCSV(list, year, month);		
			}
		}
	}
	
	protected void writeCSV(List<String> list, int year,int month) throws Exception {
		String strMonth = "0" + month;
		//目录
		String dirPath = "E:\\data\\flywheel\\" + year;
		//文件路径
		String outputFile = dirPath +"\\" +"j9-02--" + year +"-0" + month +"-01" +".csv";
		if(month > 9){
			//dirPath = "E:\\data\\flywheel\\" + year + "\\" + month;
			outputFile = dirPath +"\\" +"j9-02--" + year +"-" + month + "-01" +".csv";
			strMonth = String.valueOf(month);
		}
		strMonth = strMonth + "月";
		String strYear = year + "年";
		File dir = new File(dirPath);
		if(!dir.exists()){
			dir.mkdirs();
		}
		CsvWriter csvOutput = new CsvWriter(outputFile, ',',Charset.forName("gb2312"));
		String line1 = list.get(0);
		String[] array1 = line1.split(",");
		for (String title : array1) {
			csvOutput.write(title);
		}
		csvOutput.endRecord();
		for (int i = 1; i < list.size(); i++) {
			String[] array = list.get(i).split(",");
			String datetime = array[0].replace("2016年", strYear).replace("02月", strMonth);
			csvOutput.write(datetime);
			for (int j = 1; j < array.length; j++) {
				csvOutput.write(array[j]);
			}
			csvOutput.endRecord();
		}
		csvOutput.close();
		System.out.println("file: " + outputFile);
	}
	
	@Test
	public void testMakeDataCSV() throws Exception{
		for (int year = 2000; year <= 2000; year++) {
			for (int month = 5; month <= 5; month++) {
				for (int day = 1; day < 10; day++) {
					this.writeCSV(year, month, day);							
				}
			}
		}
		
	}
	protected void writeCSV( int year,int month, int day) throws Exception {
		
		List<String> titleList = J9SeriesParamConfigService.getJ9Series_FlywheelParamConfigList();
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
		CsvWriter csvOutput = new CsvWriter(outputFile, ',',Charset.forName("gb2312"));
		for (String title : titleList) {
			csvOutput.write(title);
		}
		csvOutput.endRecord();
		
		Date date1 = new Date(year,month-1,day,0,0,0);
		Date date2 = new Date(year,month-1,day+1,0,0,0);
		Date tempDate = date1;
		long time = tempDate.getTime();
		long beginTime = time;
		String format = year+ "年MM月dd日HH时mm分ss秒";
		DecimalFormat df = new DecimalFormat("#.00");
		
		int exceptionValue = 50; //异常值
		int beginExceptionTime = 1 * 60 * 60 * 1000; //开始出现异常时间
		int exceptionTime = 10 * 1000; //异常时间范围 10秒
		
		int specialValue = 100; //特殊工况值
		int beginspecialTime =  1 * 30 * 60 * 1000 ; //开始出现特殊工况时间
		int specialTime = 5 * 1000; //特殊工况时间范围 5秒
		int count = 1;
		while(tempDate.before(date2)){
			
			if(count == 2000)
				break;
			count++;
			
			tempDate = new Date(time);
			csvOutput.write(DateUtil.format(tempDate, format));
			if((time - beginTime) % beginExceptionTime <= exceptionTime){ //开始出现异常点
				if((time - beginTime) % beginspecialTime <= specialTime){//出现特殊工况点
					for (int i = 1; i < titleList.size(); i++) {
						Double data = specialValue + Math.random() * Math.random() * (month + day) * 10;
						csvOutput.write(df.format(data));							
					}
				}else{
					for (int i = 1; i < titleList.size(); i++) {
						Double data = exceptionValue + Math.random() * Math.random() * (month + day) * 10;
						csvOutput.write(df.format(data));							
					}
				}
			}else{
				for (int i = 1; i < titleList.size(); i++) {
					Double data = 10 + Math.random() * Math.random() * 100;
//					if(data < 15){
//						csvOutput.write("#"+df.format(data));
//					}else{
//						csvOutput.write(df.format(data));										
//					}
					csvOutput.write(df.format(data));	
				}
			}
			csvOutput.endRecord();
			time = time + 250;
		}
		csvOutput.close();
		System.out.println("file: " + outputFile);
	}
	
	@Test
	public void testMath(){
		Date date1 = new Date(2015,2,20,5,2,0);
		System.out.println(date1.getHours());
	}
}
