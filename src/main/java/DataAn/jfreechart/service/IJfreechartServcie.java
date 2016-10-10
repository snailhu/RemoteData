package DataAn.jfreechart.service;

import java.util.Vector;

import DataAn.jfreechart.chart.Serie;


public interface IJfreechartServcie {

	public String createLineChart(String title,
			String categoryAxisLabel, String valueAxisLabel,
			Vector<Serie> series, Vector<String> categories) throws Exception;
}
