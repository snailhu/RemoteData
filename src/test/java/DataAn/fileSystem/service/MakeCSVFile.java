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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import DataAn.common.utils.DateUtil;

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
		for (int year = 2015; year <= 2017; year++) {
			for (int month = 1; month <= 12; month++) {
				for (int i = 0; i < 5; i++) {
					MakeCSVFileHelper.writeTopCSV(list, year, month,i*7, (i+1)*7);							
				}
			}
		}
	}
	
	/**
	* 拷贝生成飞轮数据
	*/
	@Test
	public void copyFlywheelDataCSV() throws Exception{
		String filePath = "C:\\j9-02--2016-02-01.csv";
		File file = new File(filePath);
		InputStream in = new FileInputStream(file);
		InputStreamReader inputStreamReader = new InputStreamReader(in, "gb2312");
		BufferedReader reader = new BufferedReader(inputStreamReader);// 换成你的文件名
		String line = reader.readLine();// 第一行信息，为标题信息，不用,如果需要，注释掉
		String titleLine = line;
		
		Map<Integer,List<String>> map = new HashMap<Integer,List<String>>();
		List<String> list = null;
		String[] firstaArray = null;
		Calendar cal = Calendar.getInstance();
		while ((line = reader.readLine()) != null) {
			firstaArray = line.split(",");
			Date datetime = DateUtil.format(firstaArray[0], "yyyy年MM月dd日HH时mm分ss秒");
			cal.setTime(datetime);
			int day = cal.get(Calendar.DAY_OF_MONTH);
			int week = day/8;
			
			list = map.get(week);
			if(list == null)
				list = new ArrayList<String>();
			list.add(line);
			map.put(week, list);
		}
		for (int year = 2015; year <= 2017; year++) {
			for (int month = 1; month <= 12; month++) {
				for (int week : map.keySet()) {
					list = map.get(week);
					System.out.println("week " + week +" size: " + list.size());
					MakeCSVFileHelper.writeFlywheelCSV(titleLine,list, year, month);													
				}
			}
		}
	}
	
	@Test
	public void copyFlywheelDataCSV2() throws Exception{
		List<String> list = new ArrayList<String>();
		String filePath = "C:\\j9-02--2016-02-01.csv";
		File file = new File(filePath);
		InputStream in = new FileInputStream(file);
		InputStreamReader inputStreamReader = new InputStreamReader(in, "gb2312");
		BufferedReader reader = new BufferedReader(inputStreamReader);// 换成你的文件名
		String line = reader.readLine();// 第一行信息，为标题信息，不用,如果需要，注释掉
		String titleLine = line;
		
		while ((line = reader.readLine()) != null) {
			list.add(line);
		}
		System.out.println("size: " + list.size());
		for (int year = 2016; year <= 2016; year++) {
			for (int month = 1; month <= 1; month++) {
				int count = list.size()/100000 + 1;
				for (int i = 0; i < count; i++) {
					int fromIndex = i * 100000;
					int toIndex = (i + 1) * 100000;
					if(toIndex > (list.size() - 1))
						toIndex = list.size() - 1;
					
					MakeCSVFileHelper.writeFlywheelCSV(titleLine,list.subList(fromIndex, toIndex), year, month);							
				}
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
		int week = 4/8;
		System.out.println(week);
	}
}
