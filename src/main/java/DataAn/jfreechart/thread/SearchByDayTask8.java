package DataAn.jfreechart.thread;

import java.io.File;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.RecursiveTask;

import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesDataItem;

import DataAn.common.config.CommonConfig;
import DataAn.common.utils.DateUtil;
import DataAn.jfreechart.chart.ChartUtils;
import DataAn.jfreechart.dto.ConstraintDto;
import DataAn.jfreechart.dto.LineChartDto;
import DataAn.jfreechart.dto.LineMapDto;
import DataAn.mongo.client.MongodbUtil;
import DataAn.mongo.init.InitMongo;

/**
 * 多线程获取mongodb数据：Map<String,TimeSeriesDataItem[]> arrayDataMap(每个参数一个数组)
 * 主线程中使用 LinkedList 循环是否有值，生成TimeSeries
 * 多线程生成图片
 *
 */
public class SearchByDayTask8 extends RecursiveTask<LineChartDto>{

	private static final long serialVersionUID = 1L;
	
	private String series;
	private String star;
	private String paramType;
	private Date beginDate;
	private Date endDate;
	private Map<String, List<ConstraintDto>> constraintsMap;
	
	
	public SearchByDayTask8(String series, String star, String paramType,
			Date beginDate, Date endDate,
			Map<String, List<ConstraintDto>> constraintsMap) {
		super();
		this.series = series;
		this.star = star;
		this.paramType = paramType;
		this.beginDate = beginDate;
		this.endDate = endDate;
		this.constraintsMap = constraintsMap;
	}


	@Override
	protected LineChartDto compute() {
		MongodbUtil mg = MongodbUtil.getInstance();
		String databaseName = InitMongo.getDataDBBySeriesAndStar(series, star);
		//1s 等级数据集 或原数据集
		String collectionName = paramType;
		int index = (int) mg.countByDate(databaseName, collectionName, beginDate, endDate);
		
		System.out.println(DateUtil.format(beginDate) + " 到 "+ DateUtil.format(endDate) + " count==index: " + index);
		if(index == 0){
			throw new RuntimeException(DateUtil.format(beginDate) + " 到 "+ DateUtil.format(endDate) +" 获取数据失败！数据count: " + index);
		}
		
		Map<String,TimeSeriesDataItem[]> arrayDataMap = new HashMap<String,TimeSeriesDataItem[]>();
		
		Map<String, String> paramsMap = new HashMap<String, String>();
		Map<String, Double> paramMinMap = new HashMap<String, Double>();
		Map<String, Double> paramMaxMap = new HashMap<String, Double>();
		Set<String> constraintsKeys = constraintsMap.keySet();
		for (String key : constraintsKeys) {
			List<ConstraintDto> constraintList = constraintsMap.get(key);
			for (ConstraintDto constraintDto : constraintList) {
				if (!paramsMap.containsKey(key)) {
					paramsMap.put(constraintDto.getParamCode(),constraintDto.getParamName());
					paramMinMap.put(constraintDto.getParamCode(),constraintDto.getMin());
					paramMaxMap.put(constraintDto.getParamCode(),constraintDto.getMax());
					//初始化每条参数的数组对象
					arrayDataMap.put(constraintDto.getParamCode(), new TimeSeriesDataItem[index + 20]);
				}
			}
		}
		
		long begin = System.currentTimeMillis();
		System.out.println("begin get mongodb data...");
		
		//获取数据
		LinkedList<SearchByDayDoneTask3> forks = new LinkedList<SearchByDayDoneTask3>();
		Calendar cal = Calendar.getInstance();
		cal.setTime(beginDate);
		while(cal.getTime().before(endDate)){
			Date tempDate = cal.getTime();
			cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE)+1);
//			cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE),cal.get(Calendar.HOUR_OF_DAY)+12,0);
			SearchByDayDoneTask3 task = new SearchByDayDoneTask3(arrayDataMap, databaseName, collectionName, 
					beginDate, tempDate, cal.getTime(), paramMinMap, paramMaxMap, paramsMap);
			forks.add(task);
			task.fork();
		}
		
		//参数集
		Set<String> en_params = paramsMap.keySet();
		Map<String, Double> minMap = new HashMap<String, Double>();
		Map<String, Double> maxMap = new HashMap<String, Double>();
		LineMapDto lineMapDto = null;
		Map<String, Double> tempMinMap = null;
		Double min = null;
		Map<String, Double> tempMaxMap = null;
		Double max = null;
		for (SearchByDayDoneTask3 task : forks) {
			if(task.isCompletedAbnormally())
				throw new RuntimeException(DateUtil.format(beginDate) + " 到 "+ DateUtil.format(endDate) +" 获取数据失败！\n"+task.getException());
			lineMapDto = task.join();
			if(lineMapDto != null){
				
//				System.out.println(lineMapDto);	
				
				// 获取最小值
				tempMinMap = lineMapDto.getMinMap();
				if(tempMinMap != null){
					for (String paramCode : en_params) {
						min = minMap.get(paramCode);
						if (min == null) {
							min = tempMinMap.get(paramCode);
						}
						if(tempMinMap.get(paramCode) != null){
							minMap.put(paramCode, this.getMin(min, tempMinMap.get(paramCode)));							
						}
					}
				}
				//获取最大值
				tempMaxMap = lineMapDto.getMaxMap();
				if(tempMaxMap != null){
					for (String paramCode : en_params) {
						max = maxMap.get(paramCode);
						if (max == null) {
							max = tempMaxMap.get(paramCode);
						}
						if(tempMaxMap.get(paramCode) != null){
							maxMap.put(paramCode, this.getMax(max, tempMaxMap.get(paramCode)));							
						}
					}
				}
				
			}
		}
		
		long end = System.currentTimeMillis();
		System.out.println("获取 mongodb 数据总时间： " + (end-begin));
		
		long beginChart = System.currentTimeMillis();
		System.out.println("begin Chart...");
		
		long beginTimeSeries = System.currentTimeMillis();
		System.out.println("begin TimeSeries...");
		//遍历数据
		Map<String,CreateTimeSeriesDataTask2> dataForksMap = new HashMap<String,CreateTimeSeriesDataTask2>();
		for (String paramCode : en_params) {
			CreateTimeSeriesDataTask2 task = new CreateTimeSeriesDataTask2(arrayDataMap.get(paramCode), 
					paramCode, paramsMap.get(paramCode));
			dataForksMap.put(paramCode, task);
			task.fork();
		}
		// 多条线数据
		Map<String, TimeSeries> lineMap = new HashMap<String, TimeSeries>();
		TimeSeries timeseries = null;
		for (String key : dataForksMap.keySet()) {
			timeseries = dataForksMap.get(key).join();
			lineMap.put(key, timeseries);
		}
		
		for (String line : lineMap.keySet()) {
			timeseries = lineMap.get(line);
			System.out.println("code: "+line + " <->name: " + paramsMap.get(line) + 
					" count: " + timeseries.getItemCount());
//			for (int i = 0; i < timeseries.getItemCount(); i++) {
//				System.out.println(timeseries.getDataItem(i).getPeriod() + " : " + timeseries.getDataItem(i).getValue());
//			}
		}
		long endTimeSeries = System.currentTimeMillis();
		System.out.println("获取 TimeSeries 数据总时间： " + (endTimeSeries-beginTimeSeries));
		
		//画图
		String cachePath = CommonConfig.getChartCachePath();
		File parentDir = new File(cachePath);
		if (!parentDir.exists()) {
			parentDir.mkdirs();
		}
		Map<String, CreateTimeSeriesChartTask2> chartForksMap = new HashMap<String, CreateTimeSeriesChartTask2>();
		for (String constraintsKey : constraintsKeys) {
			List<ConstraintDto> constraintList = constraintsMap.get(constraintsKey);
			CreateTimeSeriesChartTask2 createChartTask = new CreateTimeSeriesChartTask2(constraintList, paramsMap, 
					lineMap, beginDate, endDate, cachePath, constraintsKey);
			chartForksMap.put(constraintsKey, createChartTask);
			createChartTask.fork();
		}
		Map<String, String> chartMap = new HashMap<String, String>();
		for (String key : chartForksMap.keySet()) {
			String chartPath = chartForksMap.get(key).join();
			chartMap.put(key, chartPath);
		}
		
		long endChart = System.currentTimeMillis();
		System.out.println("获取 Chart 数据总时间： " + (endChart-beginChart));
		
		LineChartDto lineChartDto = new LineChartDto();
		lineChartDto.setChartMap(chartMap);
		lineChartDto.setMinMap(minMap);
		lineChartDto.setMaxMap(maxMap);
		
		return lineChartDto;
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
}
