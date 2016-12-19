package DataAn.jfreechart.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ForkJoinPool;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.bson.Document;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.data.time.Millisecond;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.springframework.stereotype.Service;

import com.mongodb.client.MongoCursor;

import DataAn.common.config.CommonConfig;
import DataAn.common.utils.DateUtil;
import DataAn.common.utils.Log4jUtil;
import DataAn.jfreechart.chart.ChartFactory;
import DataAn.jfreechart.chart.ChartUtils;
import DataAn.jfreechart.dto.ConstraintDto;
import DataAn.jfreechart.dto.LineChartDto;
import DataAn.jfreechart.service.IJfreechartServcie;
import DataAn.jfreechart.thread.SearchByDayTask2;
import DataAn.jfreechart.thread.SearchByDayTask3;
import DataAn.jfreechart.thread.SearchByDayTask4;
import DataAn.jfreechart.thread.SearchByDayTask6;
import DataAn.jfreechart.thread.SearchByDayTask7;
import DataAn.mongo.service.IMongoService;
import DataAn.wordManager.config.OptionConfig;

@Service
public class JfreechartServiceImpl implements IJfreechartServcie {
	@Resource
	private IMongoService mongoService;

	private Logger logger = Log4jUtil.getInstance().getLogger(JfreechartServiceImpl.class);
	
	private final static ForkJoinPool forkJoinPool = new ForkJoinPool();
	
	@Override
	public LineChartDto createLineChart(String series, String star,
			String paramType, Date beginDate, Date endDate,
			Map<String, List<ConstraintDto>> constraintsMap) throws Exception {
		
		StringBuffer sb = new StringBuffer();
		sb.append(DateUtil.format(new Date())+" come in createLineChart.."+"\n");
		sb.append("series: " + series+"\n");
		sb.append("star: " + star+"\n");
		sb.append("paramType: " + paramType+"\n");
		sb.append("beginDate: " + DateUtil.format(beginDate)+"\n");
		sb.append("endDate: " + DateUtil.format(endDate)+"\n");
		sb.append("\n");
		for (String key : constraintsMap.keySet()) {
			sb.append(key+"\n");
			for (ConstraintDto constraintDto : constraintsMap.get(key)) {
				sb.append(constraintDto+"\n");
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());
//		logger.info(sb.toString());
		
		return this.createTimeSeriesChart3(series, star, paramType, beginDate,
				endDate, constraintsMap);
	}
	protected LineChartDto createTimeSeriesChart3(String series, String star,
			String paramType, Date beginDate, Date endDate,
			Map<String, List<ConstraintDto>> constraintsMap) throws Exception {
//		ForkJoinPool forkJoinPool = new ForkJoinPool(15);
		
		LineChartDto lineChartDto = forkJoinPool.invoke(new SearchByDayTask6(series, star, paramType, beginDate, endDate, constraintsMap));
		return lineChartDto;
	}
	protected LineChartDto createTimeSeriesChart2(String series, String star,
			String paramType, Date beginDate, Date endDate,
			Map<String, List<ConstraintDto>> constraintsMap) throws Exception {
		
		Map<String, String> params = new HashMap<String, String>();
		Map<String, Double> paramMin = new HashMap<String, Double>();
		Map<String, Double> paramMax = new HashMap<String, Double>();
		Set<String> constraintsKeys = constraintsMap.keySet();
		for (String key : constraintsKeys) {
			List<ConstraintDto> constraintList = constraintsMap.get(key);
			for (ConstraintDto constraintDto : constraintList) {
				if (!params.containsKey(key)) {
					params.put(constraintDto.getParamCode(),constraintDto.getParamName());
					paramMin.put(constraintDto.getParamCode(),constraintDto.getMin());
					paramMax.put(constraintDto.getParamCode(),constraintDto.getMax());
				}
			}
		}
		
		// 多条线数据
		Map<String, TimeSeries> lineMap = new HashMap<String, TimeSeries>();
		Map<String, Double> minMap = new HashMap<String, Double>();
		Map<String, Double> maxMap = new HashMap<String, Double>();
		//参数集
		Set<String> en_params = params.keySet();
		TimeSeries timeseries = null;
		Double min = null;
		Double max = null;
		
		long begin = System.currentTimeMillis();
		
		//查询mongodb数据集
		MongoCursor<Document> cursor = mongoService.findByDate(series, star,
				paramType, beginDate, endDate);
		if(cursor == null){
			throw new RuntimeException(DateUtil.format(beginDate) + " 到 "+ DateUtil.format(endDate) +" 未找到报告数据！");
		}
		Document doc = null;
		Date datetime = null;
		int count = 0;//计数器
		long lastTime = 0; //上一个时间截
		long nextTime = 0; //下一个时间截
		int second_count = 0; //秒级数据集的个数
		while (cursor.hasNext()) {
			
			count++;
			
			doc = cursor.next();
			nextTime = doc.getDate("datetime").getTime();
			//如果这次的时间截跟上次的时间截不相等
			if(nextTime != lastTime){
				second_count = 0;
				lastTime = nextTime;
			}else{
				nextTime = nextTime + (second_count * 100);
				second_count ++;
			}
			datetime = new Date(nextTime);
			
			for (String key : en_params) {
				timeseries = lineMap.get(key);
				if (timeseries == null) {
					timeseries = ChartUtils.createTimeseries(params.get(key));
				}
				String strValue = doc.getString(key);
				if(StringUtils.isNotBlank(strValue)){
					
					// 转换为double 类型
					double dValue = Double.parseDouble(strValue.trim());
					
					//在有效值区间之内
					if(paramMax.get(key) != null && dValue > paramMax.get(key))
						continue;
					
					if(paramMin.get(key) != null && dValue < paramMin.get(key))
						continue;
					
					// 往序列里面添加数据
					timeseries.addOrUpdate(new Millisecond(datetime), dValue);
					lineMap.put(key, timeseries);
					// 获取最小值
					min = minMap.get(key);
					if (min == null) {
						min = dValue;
					}
					minMap.put(key, this.getMin(min, dValue));
					// 获取最大值
					max = maxMap.get(key);
					if (max == null) {
						max = dValue;
					}
					maxMap.put(key, this.getMax(max, dValue));
				}
				else{
//					throw new RuntimeException(DateUtil.format(beginDate) + " 到 "+ 
//							DateUtil.format(endDate) +" " + params.get(key) + " 未找到报告数据！1");
				}
			}
		}
		long end_getData = System.currentTimeMillis();
		System.out.println("get mongodb data count: " + count);
		System.out.println("getData time: " + (end_getData - begin));
		if(count == 0){
			throw new RuntimeException(DateUtil.format(beginDate) + " 到 "+ DateUtil.format(endDate) + " 报告数据记录数为：" + count);
		}
		
		for (String line : lineMap.keySet()) {
			System.out.println(line + " count: " + lineMap.get(line).getItemCount());
		}
		
		String cachePath = CommonConfig.getChartCachePath();
		File parentDir = new File(cachePath);
		if (!parentDir.exists()) {
			parentDir.mkdirs();
		}
		Map<String, String> chartMap = new HashMap<String, String>();
		for (String key : constraintsKeys) {
			long begin_chart = System.currentTimeMillis();
			System.out.println(key + " chart begin... ");
			List<ConstraintDto> constraintList = constraintsMap.get(key);
			List<TimeSeriesCollection> datasetList = new ArrayList<TimeSeriesCollection>();
			if(constraintList.size() == 2){
				//双Y轴
				for (ConstraintDto constraintDto : constraintList) {
					TimeSeriesCollection dataset = new TimeSeriesCollection();
					TimeSeries timeSeries = lineMap.get(constraintDto.getParamCode());
//					if(timeSeries == null){
//						throw new RuntimeException(DateUtil.format(beginDate) + " 到 "+ 
//								DateUtil.format(endDate) +" " + constraintDto.getName()+" 未找到报告数据！2");
//					}
//					datasetList.add(dataset);
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
//					if(timeSeries == null){
//						throw new RuntimeException(DateUtil.format(beginDate) + " 到 "+ 
//								DateUtil.format(endDate) +" " + constraintDto.getName()+" 未找到报告数据！3");
//					}
//					dataset.addSeries(timeSeries);		
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
			if(chart != null){
				String chartName = key+"_lineChart.png";
				File file = new File(cachePath, chartName);
				int width = 1024;
				int height = 620;
				// ChartUtilities.saveChartAsJPEG(file, chart, width, height);
				ChartUtilities.saveChartAsPNG(file, chart, width, height);
				chartMap.put(key, file.getAbsolutePath());				
			}
			long end_chart = System.currentTimeMillis();
			System.out.println(key + " chart time: " + (end_chart-begin_chart));
		}

		LineChartDto lineChartDto = new LineChartDto();
		lineChartDto.setChartMap(chartMap);
		lineChartDto.setMinMap(minMap);
		lineChartDto.setMaxMap(maxMap);
		
		System.out.println(lineChartDto);
		
		return lineChartDto;
	}

	protected LineChartDto createTimeSeriesChart(String series, String star,
			String paramType, Date beginDate, Date endDate,
			Map<String, List<ConstraintDto>> constraintsMap)
			throws Exception {

		Map<String, String> params = new HashMap<String, String>();
		Map<String, Double> paramMin = new HashMap<String, Double>();
		Map<String, Double> paramMax = new HashMap<String, Double>();
		Set<String> constraintsKeys = constraintsMap.keySet();
		for (String key : constraintsKeys) {
			List<ConstraintDto> constraintList = constraintsMap.get(key);
			for (ConstraintDto constraintDto : constraintList) {
				if (!params.containsKey(key)) {
					params.put(constraintDto.getParamCode(),constraintDto.getParamName());
					paramMin.put(constraintDto.getParamCode(),constraintDto.getMin());
					paramMax.put(constraintDto.getParamCode(),constraintDto.getMax());
				}
			}
		}
		
		Map<Date, List<Document>> tempMap = this.getEverySecondData(series, star, paramType, beginDate, endDate, 0);
		// 多条线数据
		Map<String, TimeSeries> lineMap = new HashMap<String, TimeSeries>();
		Map<String, Double> minMap = new HashMap<String, Double>();
		Map<String, Double> maxMap = new HashMap<String, Double>();
		TimeSeries timeseries = null;
		Double min = null;
		Double max = null;
		List<Document> tempDocList = null;
		Document doc = null;
		Set<Date> dateSet = tempMap.keySet();
		Set<String> en_params = params.keySet();
		for (Date datetime : dateSet) {
			tempDocList = tempMap.get(datetime);
			for (int i = 0; i < tempDocList.size(); i++) {
				doc = tempDocList.get(i);
				long time = doc.getDate("datetime").getTime() + (i * 100);
				datetime = new Date(time);
				for (String key : en_params) {
					timeseries = lineMap.get(key);
					if (timeseries == null) {
						timeseries = ChartUtils.createTimeseries(params.get(key));
					}
					String strValue = doc.getString(key);
					if(StringUtils.isNotBlank(strValue)){
						
						// 转换为double 类型
						double dValue = Double.parseDouble(strValue.trim());
						
						//在有效值区间之内
						if(paramMax.get(key) != null && dValue > paramMax.get(key))
							continue;
						
						if(paramMin.get(key) != null && dValue < paramMin.get(key))
							continue;
						
						// 往序列里面添加数据
						timeseries.addOrUpdate(new Millisecond(datetime), dValue);
						lineMap.put(key, timeseries);
						// 获取最小值
						min = minMap.get(key);
						if (min == null) {
							min = dValue;
						}
						minMap.put(key, this.getMin(min, dValue));
						// 获取最大值
						max = maxMap.get(key);
						if (max == null) {
							max = dValue;
						}
						maxMap.put(key, this.getMax(max, dValue));
					}
					else{
//						throw new RuntimeException(DateUtil.format(beginDate) + " 到 "+ 
//								DateUtil.format(endDate) +" " + params.get(key) + " 未找到报告数据！1");
					}
				}
			}
		}

		for (String line : lineMap.keySet()) {
			System.out.println(line + " count: " + lineMap.get(line).getItemCount());
		}
		
		Map<String, String> chartMap = new HashMap<String, String>();
		for (String key : constraintsKeys) {
			List<ConstraintDto> constraintList = constraintsMap.get(key);
			List<TimeSeriesCollection> datasetList = new ArrayList<TimeSeriesCollection>();
			if(constraintList.size() == 2){
				//双Y轴
				for (ConstraintDto constraintDto : constraintList) {
					TimeSeriesCollection dataset = new TimeSeriesCollection();
					TimeSeries timeSeries = lineMap.get(constraintDto.getParamCode());
//					if(timeSeries == null){
//						throw new RuntimeException(DateUtil.format(beginDate) + " 到 "+ 
//								DateUtil.format(endDate) +" " + constraintDto.getName()+" 未找到报告数据！2");
//					}
//					datasetList.add(dataset);
					if(timeSeries != null && timeSeries.getItemCount() > 24){
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
//					if(timeSeries == null){
//						throw new RuntimeException(DateUtil.format(beginDate) + " 到 "+ 
//								DateUtil.format(endDate) +" " + constraintDto.getName()+" 未找到报告数据！3");
//					}
//					dataset.addSeries(timeSeries);		
					if(timeSeries != null && timeSeries.getItemCount() > 24){
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
			if(chart != null){
				String cachePath = CommonConfig.getChartCachePath();
				File parentDir = new File(cachePath);
				if (!parentDir.exists()) {
					parentDir.mkdirs();
				}
				File file = new File(cachePath, "lineChart.png");
				int width = 1024;
				int height = 620;
				// ChartUtilities.saveChartAsJPEG(file, chart, width, height);
				ChartUtilities.saveChartAsPNG(file, chart, width, height);
				chartMap.put(key, file.getAbsolutePath());				
			}
		}

		LineChartDto lineChartDto = new LineChartDto();
		lineChartDto.setChartMap(chartMap);
		lineChartDto.setMinMap(minMap);
		lineChartDto.setMaxMap(maxMap);
		
		System.out.println(lineChartDto);
		
		return lineChartDto;
		
		
	}

	/** 按每一秒获取数据集
	 */
	protected Map<Date, List<Document>> getEverySecondData(String series, String star,
			String paramType, Date beginDate, Date endDate, int totalNumber){
		
		MongoCursor<Document> cursor = mongoService.findByDate(series, star,
				paramType, beginDate, endDate);
		if(cursor == null){
			throw new RuntimeException(DateUtil.format(beginDate) + " 到 "+ DateUtil.format(endDate) +" 未找到报告数据！");
		}
		Document doc = null;
		int count = 0;
		Map<Date, List<Document>> tempMap = new LinkedHashMap<Date, List<Document>>();
		List<Document> tempDocList = null;
		while (cursor.hasNext()) {
			// 设置总数
			if (totalNumber != 0 && count == totalNumber) {
				break;
			}
			count++;

			doc = cursor.next();
			Date datetime = doc.getDate("datetime");
			tempDocList = tempMap.get(datetime);
			if (tempDocList == null) {
				tempDocList = new ArrayList<Document>();
			}
			tempDocList.add(doc);
			tempMap.put(datetime, tempDocList);
		}
		System.out.println("get mongodb data count: " + count);
		if(count == 0){
			throw new RuntimeException(DateUtil.format(beginDate) + " 到 "+ DateUtil.format(endDate) +" 未找到报告数据！");
		}
		return tempMap;
	}
	protected double getMax(double data1, double data2) {
		if (data1 >= data2)
			return data1;
		return data2;
	}

	protected double getMin(double data1, double data2) {
		if (data1 <= data2)
			return data1;
		return data2;
	}

	@Override
	public LineChartDto createLineChartMock(String series, String star,
			String paramType, Date beginDate, Date endDate,
			Map<String, List<ConstraintDto>> constraintsMap) throws Exception {
		LineChartDto lineChartDto = new LineChartDto();

		Map<String, String> chartMap = new HashMap<String, String>();

		Map<String, Double> minMap = new HashMap<String, Double>();

		Map<String, Double> maxMap = new HashMap<String, Double>();

		minMap.put("sequence_00814", 2.1);
		minMap.put("sequence_00815", 2.2);
		minMap.put("sequence_00423", 2.3);
		minMap.put("sequence_00820", 2.4);
		minMap.put("sequence_00821", 2.5);
		minMap.put("sequence_00426", 2.6);

		maxMap.put("sequence_00814", 9.1);
		maxMap.put("sequence_00815", 9.2);
		maxMap.put("sequence_00423", 9.3);
		maxMap.put("sequence_00820", 9.4);
		maxMap.put("sequence_00821", 9.5);
		maxMap.put("sequence_00426", 9.6);

		String imgUrl = OptionConfig.getWebPath()
				+ "\\report\\wordtemplate\\satellite.jpg";

		chartMap.put("转速", imgUrl);
		chartMap.put("飞轮Xb转速,电流", imgUrl);
		chartMap.put("飞轮Xa转速,电流", imgUrl);
		chartMap.put("温度", imgUrl);
		chartMap.put("飞轮Xa温度", imgUrl);
		chartMap.put("电流", imgUrl);
		chartMap.put("飞轮Xb温度", imgUrl);

		lineChartDto.setChartMap(chartMap);
		lineChartDto.setMaxMap(maxMap);
		lineChartDto.setMinMap(minMap);

		return lineChartDto;
	}

}
