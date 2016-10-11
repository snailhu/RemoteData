package DataAn.jfreechart.service.impl;

import java.io.File;
import java.util.Vector;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.springframework.stereotype.Service;
import DataAn.common.config.CommonConfig;
import DataAn.jfreechart.chart.ChartFactory;
import DataAn.jfreechart.chart.Serie;
import DataAn.jfreechart.service.IJfreechartServcie;

@Service
public class JfreechartServiceImpl implements IJfreechartServcie{

	@Override
	public String createLineChart(String title,
			String categoryAxisLabel, String valueAxisLabel,
			Vector<Serie> series, Vector<String> categories) throws Exception {
		
		JFreeChart chart = ChartFactory.createLineChartDoubleY(title,
				categoryAxisLabel, valueAxisLabel, series, categories);
		
		String cachePath = CommonConfig.getChartCachePath();
		File parentDir = new File(cachePath);
		if (!parentDir.exists()) {
			parentDir.mkdirs();
		}
		
		File file = new File(cachePath,"lineChart.png");
		int width = 1024;
		int height = 420;
		//ChartUtilities.saveChartAsJPEG(file, chart, width, height);
		ChartUtilities.saveChartAsPNG(file, chart, width, height);
		return file.getAbsolutePath();
	}

	
}
