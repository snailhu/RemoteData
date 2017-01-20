package DataAn.fileSystem.service;

import java.io.File;
import java.nio.charset.Charset;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.csvreader.CsvWriter;

import DataAn.common.utils.DateUtil;
import DataAn.galaxyManager.option.J9SeriesParamConfigService;

public class MakeCSVFileHelper {

	//陀螺数据构造，生成一个月的数据集， 拷贝原有的数据点
	public static void writeTopCSV(List<String> list, int year,int month,int beginDay,int endDay) throws Exception {
		if(beginDay < 1)
			beginDay = 1;
		if(endDay < 1)
			endDay = 1;
		if(beginDay > endDay)
			return;
		
		DecimalFormat df = new DecimalFormat("00");
		//目录
		String dirPath = "E:\\data\\top\\" + year+"\\"+df.format(month);
		//文件路径
		String outputFile = dirPath +"\\" +"j9-05--" + year +"-" + df.format(month) +"-" + df.format(beginDay) +".csv";
		String strYear = year + "年";
		String strMonth = df.format(month) + "月";
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
		
		int days = getDays(year, month);
		if(days < endDay)
			endDay = days;
		
		for (int day = beginDay; day <= endDay; day++) {
			String strDay = df.format(day)+"日";
			for (int i = 1; i < list.size(); i++) {
				String[] array = list.get(i).split(",");
				String datetime = array[0].replace("2014年", strYear).replace("01月", strMonth).replace("31日", strDay);
				//datetime = DateUtil.formatString(datetime, "yyyy年MM月dd日HH时mm分ss.SSS秒","yyyy年MM月dd日HH时mm分ss秒");
				csvOutput.write(datetime);
				for (int j = 1; j < array.length; j++) {
					csvOutput.write(array[j]);
				}
				csvOutput.endRecord();
			}
		}
		csvOutput.close();
		System.out.println("file: " + outputFile);
	}
	
	//陀螺数据构造 拷贝原有的数据点
	public static void writeFlywheelCSV(String titleLine, List<String> datalist, int year,int month) throws Exception {
		if(datalist == null || datalist.size() == 0)
			return;
		
		String[] firstaArray = datalist.get(0).split(",");
		Date firstDate = DateUtil.format(firstaArray[0], "yyyy年MM月dd日HH时mm分ss秒");
		Calendar cal = Calendar.getInstance();
		cal.setTime(firstDate);
		int day = cal.get(Calendar.DAY_OF_MONTH);
		
		DecimalFormat df = new DecimalFormat("00");
		//目录
		String dirPath = "E:\\data\\flywheel\\" + year;
//		String dirPath = "E:\\data\\flywheel\\" + year + "\\" + df.format(month);
		//文件路径
		String outputFile = dirPath +"\\" +"j9-05--" + year +"-" + df.format(month) +"-" + df.format(day) +".csv";
		String strYear = year + "年";
		String strMonth = df.format(month) + "月";
		File dir = new File(dirPath);
		if(!dir.exists()){
			dir.mkdirs();
		}
		CsvWriter csvOutput = new CsvWriter(outputFile, ',',Charset.forName("gb2312"));
		String[] titleArray = titleLine.split(",");
		for (String title : titleArray) {
			csvOutput.write(title);
		}
		csvOutput.endRecord();
		
		for (int i = 1; i < datalist.size(); i++) {
			String[] array = datalist.get(i).split(",");
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
	
	//飞轮数据构造 随机点数生成
	public static void writeCSV( int year,int month, int day) throws Exception {
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
			
			if(count == 20000)
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
	
	private static int getDays(int year, int month) {
		int days = 0;
		if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
			days = 31;
		} else if (month == 4 || month == 6 || month == 9 || month == 11) {
			days = 30;
		} else {
			if (year % 4 == 0 || year % 400 == 0 && year % 100 != 0) {
				days = 28;
			} else {
				days = 29;
			}
		}
		return days;
	}
}
