package DataAn.jfreechart;

import java.util.Vector;

import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;

import DataAn.jfreechart.chart.ChartFactory;
import DataAn.jfreechart.chart.Serie;

public class ChartFactoryTest {

	public static void main(String[] args) {
		
		Vector<String> categoriesV = new Vector<String>();
		// 标注类别
		String[] categories = { "Jan", "Feb", "Mar", "Apr", "May", "Jun",
				"Jul", "Aug", "Sep", "Oct", "Nov", "Dec" };
		for (String categorie : categories) {
			categoriesV.add(categorie);			
		}
		
		Vector<Serie> series = new Vector<Serie>();
		// 柱子名称：柱子所有的值集合
		Serie serieTokyo = new Serie("Tokyo", new Double[] { 49.9, 71.5, 106.4, 129.2,144.0, 
									176.0, 135.6, 148.5, 216.4, 194.1, 95.6, 54.4 });
		serieTokyo.setY2Axis(false);
		series.add(serieTokyo);
		
		Serie serieNewYork = new Serie("New York", new Double[] { 83.6, 78.8, 98.5, 93.4,
				106.0, 84.5, 105.0, 104.3, 91.2, 83.5, 106.6, 92.3 });
		serieNewYork.setY2Axis(false);
		series.add(serieNewYork);
		
		Serie serieLondon = new Serie("London", new Double[] { 48.9, 38.8, 39.3, 41.4,
				47.0, 48.3, 59.0, 59.6, 52.4, 65.2, 59.3, 51.2 });
		serieLondon.setY2Axis(true);
		series.add(serieLondon);
		
		Serie serieBerlin = new Serie("Berlin", new Double[] { 42.4, 33.2, 34.5, 39.7,
				52.6, 75.5, 57.4, 60.4, 47.6, 39.1, 46.8, 51.1 });
		serieBerlin.setY2Axis(true);
		series.add(serieBerlin);
		
		String title = "Monthly Average Rainfall";
		String categoryAxisLabel = "";
		String valueAxisLabel = "Rainfall (mm)";
		
		JFreeChart chart = ChartFactory.createLineChartDoubleY(title,
				categoryAxisLabel, valueAxisLabel, series, categoriesV);
		ChartFrame frame = new ChartFrame("多坐标轴", chart);
		frame.pack();
		frame.setVisible(true);
	}
}
