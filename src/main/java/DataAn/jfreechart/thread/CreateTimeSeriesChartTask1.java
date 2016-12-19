package DataAn.jfreechart.thread;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.RecursiveTask;

import org.apache.log4j.Logger;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.data.time.Millisecond;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;

import DataAn.common.utils.DateUtil;
import DataAn.common.utils.Log4jUtil;
import DataAn.jfreechart.chart.ChartFactory;
import DataAn.jfreechart.chart.ChartUtils;
import DataAn.jfreechart.dto.ConstraintDto;
import DataAn.jfreechart.dto.LineTimeSeriesDto;

/**
 * 多线程中生成TimeSeries和图片一起做
 *
 */
public class CreateTimeSeriesChartTask1 extends RecursiveTask<String>{

	private Logger logger = Log4jUtil.getInstance().getLogger(CreateTimeSeriesChartTask1.class);
	
	private static final long serialVersionUID = 1L;
	
	private List<ConstraintDto> constraintList;
	private LineTimeSeriesDto[] arrayData;
	private Date beginDate;
	private Date endDate;
	private String cachePath;
	private String chartName;
	
	public CreateTimeSeriesChartTask1(List<ConstraintDto> constraintList,
			LineTimeSeriesDto[] arrayData, Date beginDate, Date endDate,
			String cachePath, String chartName) {
		super();
		this.constraintList = constraintList;
		this.arrayData = arrayData;
		this.beginDate = beginDate;
		this.endDate = endDate;
		this.cachePath = cachePath;
		this.chartName = chartName;
	}



	@Override
	protected String compute() {
		
		try {
			long begin = System.currentTimeMillis();
			
			Map<String, String> params = new HashMap<String, String>();
			for (ConstraintDto constraintDto : constraintList) {
				params.put(constraintDto.getParamCode(),constraintDto.getParamName());
			}
			Map<String, TimeSeries> lineMap = new HashMap<String, TimeSeries>();
			//参数集
			Set<String> en_params = params.keySet();
			TimeSeries timeseries = null;
			
			Map<String, Double> paramMap = null;
			for (LineTimeSeriesDto lineTimeSeriesDto : arrayData) {
				paramMap = lineTimeSeriesDto.getParam();
				if(paramMap != null){
					for (String paramCode : en_params) {
						timeseries = lineMap.get(paramCode);
						if (timeseries == null) {
							timeseries = ChartUtils.createTimeseries(params.get(paramCode));
						}
						// 往序列里面添加数据
						timeseries.addOrUpdate(new Millisecond(lineTimeSeriesDto.getDatetime()), paramMap.get(paramCode));
						lineMap.put(paramCode, timeseries);
					}
				}
				
			}
			
			List<TimeSeriesCollection> datasetList = new ArrayList<TimeSeriesCollection>();
			if(constraintList.size() == 2){
				//双Y轴
				for (ConstraintDto constraintDto : constraintList) {
					TimeSeriesCollection dataset = new TimeSeriesCollection();
					TimeSeries timeSeries = lineMap.get(constraintDto.getParamCode());
					if(timeSeries != null){
						dataset.addSeries(timeSeries);						
						datasetList.add(dataset);						
					}else{
						logger.info(DateUtil.format(beginDate) + " 到 "+ DateUtil.format(endDate) +" " + constraintDto.getParamName()+" 未找到报告数据！2");
					}
				}
			}else{
				// 多条线图表数据
				TimeSeriesCollection dataset = new TimeSeriesCollection();
				boolean flag = false;
				for (ConstraintDto constraintDto : constraintList) {
					TimeSeries timeSeries = lineMap.get(constraintDto.getParamCode());		
					if(timeSeries != null){
						dataset.addSeries(timeSeries);						
						flag = true;
					}else{
						logger.info(DateUtil.format(beginDate) + " 到 "+ DateUtil.format(endDate) +" " + constraintDto.getParamName()+" 未找到报告数据！3");
					}
				}
				//至少有一次
				if(flag){
					datasetList.add(dataset);					
				}
			}
			
			String title = "";
			String categoryAxisLabel = "";
			String valueAxisLabel = "";

			
			JFreeChart chart = ChartFactory.createTimeSeriesChart(title,
					categoryAxisLabel, valueAxisLabel, datasetList, beginDate, endDate);
			chartName = chartName+"_lineChart.png";
			File file = new File(cachePath, chartName);
			if(chart != null){
				int width = 1024;
				int height = 620;
				// ChartUtilities.saveChartAsJPEG(file, chart, width, height);
				ChartUtilities.saveChartAsPNG(file, chart, width, height);
			}
			long end_getData = System.currentTimeMillis();
			
			System.out.println();
			for (String paramCode : en_params) {
				timeseries = lineMap.get(paramCode);
				System.out.println(params.get(paramCode) + " count " + timeseries.getItemCount());
			}
			System.out.println(chartName + "chart time: " + (end_getData - begin));
			
			return file.getAbsolutePath();
		} catch (IOException e) {
			e.printStackTrace();
		}				
		
		return null;
	}

}
