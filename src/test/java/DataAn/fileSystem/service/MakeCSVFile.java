package DataAn.fileSystem.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.junit.Test;

public class MakeCSVFile {

	/**
	* 拷贝生成陀螺数据
	*/
	@Test
	public void copyTopDataCSV() throws Exception{
		List<String> list = new ArrayList<String>();
		String filePath = "C:\\2014-01.csv";
		File file = new File(filePath);
		InputStream in = new FileInputStream(file);
		InputStreamReader inputStreamReader = new InputStreamReader(in, "gb2312");
		BufferedReader reader = new BufferedReader(inputStreamReader);// 换成你的文件名
		String line = reader.readLine();// 第一行信息，为标题信息，不用,如果需要，注释掉
		list.add(line);
		while ((line = reader.readLine()) != null) {
			if(line.indexOf("2014年01月31日") > -1)
				list.add(line);
		}
		System.out.println("size: " + list.size());
		for (int year = 2016; year <= 2016; year++) {
			for (int month = 1; month <= 12; month++) {
				MakeCSVFileHelper.writeTopCSV(list, year, month);		
			}
		}
	}
	
	/**
	* 拷贝生成飞轮数据
	*/
	@Test
	public void copyFlywheelDataCSV() throws Exception{
		List<String> list = new ArrayList<String>();
		String filePath = "C:\\feb-9-05.csv";
		File file = new File(filePath);
		InputStream in = new FileInputStream(file);
		InputStreamReader inputStreamReader = new InputStreamReader(in, "gb2312");
		BufferedReader reader = new BufferedReader(inputStreamReader);// 换成你的文件名
		String line = reader.readLine();// 第一行信息，为标题信息，不用,如果需要，注释掉
		list.add(line);
		while ((line = reader.readLine()) != null) {
			list.add(line);
		}
		System.out.println("size: " + list.size());
		for (int year = 2016; year <= 2016; year++) {
			for (int month = 1; month <= 12; month++) {
				MakeCSVFileHelper.writeFlywheelCSV(list, year, month);		
			}
		}
	}
	
	/**
	* 随机生成飞轮数据
	*/
	@Test
	public void testMakeDataCSV() throws Exception{
		for (int year = 2010; year <= 2010; year++) {
			for (int month = 1; month <= 2; month++) {
				for (int day = 1; day < 2; day++) {
					MakeCSVFileHelper.writeCSV(year, month, day);							
				}
			}
		}
	}
	
	@Test
	public void testMath(){
		Date date1 = new Date(2015,2,20,5,2,0);
		System.out.println(date1.getHours());
		DecimalFormat df = new DecimalFormat("00");
		System.out.println(df.format(9));
		Calendar cal = Calendar.getInstance();
	}
}
