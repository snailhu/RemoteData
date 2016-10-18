package DataAn.jfreechart;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.junit.Before;
import org.junit.Test;

import DataAn.common.utils.DateUtil;
import DataAn.jfreechart.chart.ChartFactory;
import DataAn.jfreechart.chart.Serie;


public class ChartFactoryTest2 {

	List<String> categoriesList = new ArrayList<String>();
	List<String> tokyoList = new ArrayList<String>();
	List<String> newYorkList = new ArrayList<String>();
	List<String> londonList = new ArrayList<String>();
	List<String> berlinList = new ArrayList<String>();
	
	
	@SuppressWarnings("deprecation")
	@Before
	public void init(){
		List<String> titleList = new ArrayList<String>();
		titleList.add("Tokyo");
		titleList.add("NewYork");
		titleList.add("London");
		titleList.add("Berlin");
		int year = 2016;
		int month = 1;
		int day = 1;
		Date date1 = new Date(year,month-1,day,0,0,0);
		Date date2 = new Date(year,month-1,day+1,0,0,0);
		Date tempDate = date1;
		long time = tempDate.getTime();
		String format = year+ "年MM月dd日HH时mm分ss秒";
		DecimalFormat df = new DecimalFormat("#.00");
		while(tempDate.before(date2)){
			tempDate = new Date(time);
			categoriesList.add(DateUtil.format(tempDate, format));
			for (int i = 0; i < titleList.size(); i++) {
				Double data =  Math.random() * Math.random() * (month + day) * 10;
				
				if(titleList.get(i).equals("Tokyo")){
					tokyoList.add(df.format(data));
				}
				if(titleList.get(i).equals("NewYork")){
					newYorkList.add(df.format(data));	
				}
				if(titleList.get(i).equals("London")){
					londonList.add(df.format(data));
				}
				if(titleList.get(i).equals("Berlin")){
					berlinList.add(df.format(data));
				}
			}
			time = time + 100000;			
		}
	}
	@Test
	public void testLineChart() throws IOException{
		
		String[] categories = new String[categoriesList.size()];
		Double[] tokyoData = new Double[categoriesList.size()];
		Double[] newYorkData = new Double[categoriesList.size()];
		Double[] londonData = new Double[categoriesList.size()];
		Double[] berlinData = new Double[categoriesList.size()];
		for (int i = 0; i < categoriesList.size(); i++) {
			categories[i] = categoriesList.get(i);
			tokyoData[i] = Double.parseDouble(tokyoList.get(i));
//			newYorkData[i] = Double.parseDouble(newYorkList.get(i));
//			londonData[i] = Double.parseDouble(londonList.get(i));
//			berlinData[i] = Double.parseDouble(berlinList.get(i));
		}
		System.out.println(categories.length);
		System.out.println(tokyoData.length);
		System.out.println(newYorkData.length);
		System.out.println(londonData.length);
		System.out.println(berlinData.length);
		
		Vector<Serie> series = new Vector<Serie>();
		// 柱子名称：柱子所有的值集合
		series.add(new Serie("Tokyo", tokyoData));
//		series.add(new Serie("NewYork", newYorkData));
//		series.add(new Serie("London", londonData));
//		series.add(new Serie("Berlin", berlinData));

		String title = "Monthly Average Rainfall";
		String categoryAxisLabel = "";
		String valueAxisLabel = "Rainfall (mm)";
		JFreeChart chart = ChartFactory.createLineChartOneY(title,
				categoryAxisLabel, valueAxisLabel, series, categories);
		File file = new File("D:\\temp\\chart\\lineChart.png");
		int width = 1024;
		int height = 420;
		//ChartUtilities.saveChartAsJPEG(file, chart, width, height);
		ChartUtilities.saveChartAsPNG(file, chart, width, height);
	}
}
