package DataAn.jfreechart.thread;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.RecursiveTask;
import org.apache.log4j.Logger;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import DataAn.common.utils.DateUtil;
import DataAn.common.utils.Log4jUtil;
import DataAn.jfreechart.chart.ChartFactory;
import DataAn.jfreechart.dto.ConstraintDto;
import DataAn.reportManager.config.OptionConfig;

/**
 * 只多线程生成图片
 *
 */
public class CreateTimeSeriesChartTask2 extends RecursiveTask<String>{

	private Logger logger = Log4jUtil.getInstance().getLogger(CreateTimeSeriesChartTask2.class);
	
	private static final long serialVersionUID = 1L;
	
	private List<ConstraintDto> constraintList;
	private Map<String, String> paramsMap;
	private Map<String, TimeSeries> lineMap;
	private Date beginDate;
	private Date endDate;
	private String cachePath;
	private String chartName;

	public CreateTimeSeriesChartTask2(List<ConstraintDto> constraintList,
			Map<String, String> paramsMap, Map<String, TimeSeries> lineMap,
			Date beginDate, Date endDate, String cachePath, String chartName) {
		super();
		this.constraintList = constraintList;
		this.paramsMap = paramsMap;
		this.lineMap = lineMap;
		this.beginDate = beginDate;
		this.endDate = endDate;
		this.cachePath = cachePath;
		this.chartName = chartName;
	}

	@Override
	protected String compute() {
		String noDataChartPath = OptionConfig.getWebPath() + "\\report\\wordtemplate\\NoData.png";
		if(constraintList == null || constraintList.size() == 0)
			return noDataChartPath;
		try {
			long begin = System.currentTimeMillis();	
			
			String y1Label = "";
			String y2Label = "";
			List<TimeSeriesCollection> datasetList = new ArrayList<TimeSeriesCollection>();
			if(constraintList.size() == 2){
				y1Label = constraintList.get(0).getParamName() + ": 单位( " + constraintList.get(0).getUnits() + " )";
				y2Label = constraintList.get(1).getParamName() + ": 单位( " + constraintList.get(1).getUnits() + " )";
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
					Set<String> unitsSet = new HashSet<String>();
					for (ConstraintDto constraintDto : constraintList) {
						unitsSet.add(constraintDto.getUnits());
					}
					//当参数单位只有一个的时候添加
					if(unitsSet.size() == 1)
						y1Label = constraintList.get(0).getParamName() + ": 单位( " + constraintList.get(0).getUnits() + " )";
				}
			}
			
			String title = "";
			String categoryAxisLabel = "";
			String valueAxisLabel = "";
			
			Map<String,String> configMap = new HashMap<String,String>();
			configMap.put("title", title);
			configMap.put("categoryAxisLabel", categoryAxisLabel);
			configMap.put("valueAxisLabel", valueAxisLabel);
			configMap.put("y1Label", y1Label);
			configMap.put("y2Label", y2Label);

			int itemCount = 0;
    		for (TimeSeriesCollection dataset : datasetList) {
    			for (int i = 0; i < dataset.getSeriesCount(); i++) {
    				if(dataset.getSeries(i).getItemCount() > itemCount){
    					itemCount = dataset.getSeries(i).getItemCount();
    				}
    			}
			}
    		
			if(itemCount > 0){
				JFreeChart chart = ChartFactory.createTimeSeriesChart(datasetList, beginDate, endDate, configMap);
				
				chartName = chartName+"_lineChart.png";
				File file = new File(cachePath, chartName);
				if(chart != null){
					int width = 1024;
					int height = 620;
					// ChartUtilities.saveChartAsJPEG(file, chart, width, height);
					ChartUtilities.saveChartAsPNG(file, chart, width, height);
					return file.getAbsolutePath();
				}
				long end_getData = System.currentTimeMillis();
				System.out.println(chartName + "chart time: " + (end_getData - begin));
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		}				
		return noDataChartPath;
	}

}
