package DataAn.jfreechart.thread;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.RecursiveTask;

import org.jfree.data.time.Second;
import org.jfree.data.time.TimeSeries;

import DataAn.common.config.CommonConfig;
import DataAn.common.utils.DateUtil;
import DataAn.jfreechart.chart.ChartUtils;
import DataAn.jfreechart.dto.ConstraintDto;
import DataAn.jfreechart.dto.LineChartDto;
import DataAn.jfreechart.dto.LineMapDto;
import DataAn.jfreechart.dto.LineTimeSeriesDto2;
import DataAn.mongo.client.MongodbUtil;
import DataAn.mongo.init.InitMongo;
/**
 * 多线程获取mongodb数据：Map<String,LineTimeSeriesDto2[]> arrayDataMap(每个参数一个数组)
 * 主线程中使用 嵌套List 通过实践先小后大判断，生成TimeSeries
 * 多线程生成图片
 *
 */
public class SearchByDayTask4 extends RecursiveTask<LineChartDto>{

	private static final long serialVersionUID = 1L;
	
	private String series;
	private String star;
	private String paramType;
	private Date beginDate;
	private Date endDate;
	private Map<String, List<ConstraintDto>> constraintsMap;
	
	
	public SearchByDayTask4(String series, String star, String paramType,
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
		//1s 等级数据集
		String collectionName =  paramType + "1s";
		int index = (int) mg.countByDate(databaseName, collectionName, beginDate, endDate);
		
		System.out.println(DateUtil.format(beginDate) + " 到 "+ DateUtil.format(endDate) + " index: " + index);
		
		Map<String,LineTimeSeriesDto2[]> arrayDataMap = new HashMap<String,LineTimeSeriesDto2[]>();
		
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
					arrayDataMap.put(constraintDto.getParamCode(), new LineTimeSeriesDto2[index]);
				}
			}
		}
		
		long begin = System.currentTimeMillis();
		System.out.println("begin get mongodb data...");
		
		//获取数据
		List<Integer> taskIntervalList = new ArrayList<Integer>();
		for (int j = 1; j <= 10; j++) {
			taskIntervalList.add(getFibo(j));
		}
		Date tempBeginDate = beginDate;
		Date tempEndDate = null;
		List<List<SearchByDayDoneTask2>> forksList = new ArrayList<List<SearchByDayDoneTask2>>();
		List<SearchByDayDoneTask2> forks = null;
		boolean flag = false;
		for (Integer taskInterval : taskIntervalList) {
			forks = new LinkedList<SearchByDayDoneTask2>();
			for (int i = 0; i < taskInterval; i++) {
				long tempTime = tempBeginDate.getTime() + 1000 * 60 * 60 * 12;
				if(tempTime <= endDate.getTime()){
					tempEndDate = new Date(tempTime);
					
					SearchByDayDoneTask2 task = new SearchByDayDoneTask2(arrayDataMap, databaseName, collectionName, 
							beginDate, tempBeginDate, tempEndDate, paramMinMap, paramMaxMap, paramsMap);
					forks.add(task);
					task.fork();
					
					tempBeginDate = tempEndDate;
				}else{
					flag = true;
					break;
				}
			}
			forksList.add(forks);
			if(flag)
				break;
		}
		
		//参数集
		Set<String> en_params = paramsMap.keySet();
		// 多条线数据
		Map<String, TimeSeries> lineMap = new HashMap<String, TimeSeries>();
		TimeSeries timeseries = null;
		Map<String, Double> minMap = new HashMap<String, Double>();
		Map<String, Double> maxMap = new HashMap<String, Double>();
		
		LineMapDto lineMapDto = null;
		Map<String, Double> tempMinMap = null;
		Double min = null;
		Map<String, Double> tempMaxMap = null;
		Double max = null;
		LineTimeSeriesDto2[] arrayData = null;
		LineTimeSeriesDto2 lineTimeSeriesDto = null;
		for (int j = 0; j < forksList.size(); j++) {
			forks = forksList.get(j);
			for (SearchByDayDoneTask2 task : forks) {
				lineMapDto = task.join();
				int task_index = lineMapDto.getIndex();
				int task_count = lineMapDto.getCount();
				System.out.println(lineMapDto);	
				
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
				
				////遍历数据
				for (String paramCode : en_params) {
					timeseries = lineMap.get(paramCode);
					if (timeseries == null) {
						timeseries = ChartUtils.createTimeseries(paramsMap.get(paramCode));
					}
					arrayData = arrayDataMap.get(paramCode);
					for (int i = 0; i < task_count; i++) {
						lineTimeSeriesDto = arrayData[task_index+i];
						if(lineTimeSeriesDto != null){
							// 往序列里面添加数据
							timeseries.addOrUpdate(new Second(lineTimeSeriesDto.getDatetime()), lineTimeSeriesDto.getParamValue());
						}
					}
					lineMap.put(paramCode, timeseries);
				}
			}
		}
		
		long end = System.currentTimeMillis();
		System.out.println("获取 mongodb 数据总时间： " + (end-begin));		
		
		long beginChart = System.currentTimeMillis();
		System.out.println("begin Chart...");
		
		for (String line : lineMap.keySet()) {
			System.out.println("code: "+line + "-name: " + paramsMap.get(line) + 
					" count: " + lineMap.get(line).getItemCount());
		}
		
		//画图
		String cachePath = CommonConfig.getChartCachePath();
		File parentDir = new File(cachePath);
		if (!parentDir.exists()) {
			parentDir.mkdirs();
		}
		Map<String, CreateTimeSeriesChartTask2> chartForksMap = new HashMap<String, CreateTimeSeriesChartTask2>();
		for (String constraintsKey : constraintsKeys) {
			List<ConstraintDto> constraintList = constraintsMap.get(constraintsKey);
			CreateTimeSeriesChartTask2 task = new CreateTimeSeriesChartTask2(constraintList, paramsMap, 
					lineMap, beginDate, endDate, cachePath, constraintsKey);
			chartForksMap.put(constraintsKey, task);
			task.fork();
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
	
	protected int getFibo(int i) {
		if (i == 1 || i == 2)
			return 1;
		else
		return getFibo(i - 1) + getFibo(i - 2);
	}
}
