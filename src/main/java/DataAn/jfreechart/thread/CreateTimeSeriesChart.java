package DataAn.jfreechart.thread;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.concurrent.RecursiveTask;

import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.data.time.TimeSeriesCollection;

import DataAn.jfreechart.chart.ChartFactory;

public class CreateTimeSeriesChart extends RecursiveTask<String>{

	private static final long serialVersionUID = 1L;
	
	private List<TimeSeriesCollection> datasetList;
	private Date beginDate;
	private Date endDate;
	private String cachePath;
	
	public CreateTimeSeriesChart(List<TimeSeriesCollection> datasetList,
			Date beginDate, Date endDate, String cachePath) {
		super();
		this.datasetList = datasetList;
		this.beginDate = beginDate;
		this.endDate = endDate;
		this.cachePath = cachePath;
	}


	@Override
	protected String compute() {
		String title = "";
		String categoryAxisLabel = "";
		String valueAxisLabel = "";

		try {
			JFreeChart chart = ChartFactory.createTimeSeriesChart(title,
					categoryAxisLabel, valueAxisLabel, datasetList, beginDate, endDate);
			if(chart != null){
				String chartName = "lineChart.png";
				File file = new File(cachePath, chartName);
				int width = 1024;
				int height = 620;
				// ChartUtilities.saveChartAsJPEG(file, chart, width, height);
				ChartUtilities.saveChartAsPNG(file, chart, width, height);
				return file.getAbsolutePath();				
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
