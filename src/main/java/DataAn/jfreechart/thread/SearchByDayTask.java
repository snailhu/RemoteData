package DataAn.jfreechart.thread;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.RecursiveTask;

import org.jfree.data.time.Millisecond;
import org.jfree.data.time.TimeSeries;

import DataAn.common.config.CommonConfig;
import DataAn.common.utils.DateUtil;
import DataAn.jfreechart.chart.ChartUtils;
import DataAn.jfreechart.dto.ConstraintDto;
import DataAn.jfreechart.dto.LineChartDto;
import DataAn.jfreechart.dto.LineMapDto;
import DataAn.jfreechart.dto.LineTimeSeriesDto;
import DataAn.mongo.client.MongodbUtil;
import DataAn.mongo.init.InitMongo;

public class SearchByDayTask extends RecursiveTask<LineChartDto>{

	private static final long serialVersionUID = 1L;
	
	private String series;
	private String star;
	private String paramType;
	private Date beginDate;
	private Date endDate;
	private Map<String, List<ConstraintDto>> constraintsMap;
	
	
	public SearchByDayTask(String series, String star, String paramType,
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
		String databaseName = InitMongo.getDataBaseNameBySeriesAndStar(series, star);
		//1s 等级数据集
		String collectionName =  paramType + "1s";
		int count = (int) mg.countByDate(databaseName, collectionName, beginDate, endDate);
		
		System.out.println(DateUtil.format(beginDate) + " 到 "+ DateUtil.format(endDate) + " index: " + count);
		
		LineTimeSeriesDto[] arrayData = new LineTimeSeriesDto[count];
		
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
				}
			}
		}
		
		long begin = System.currentTimeMillis();
		System.out.println("begin get mongodb data...");
		//获取数据
		List<SearchByDayDoneTask> forks = new LinkedList<SearchByDayDoneTask>();
		Calendar cal = Calendar.getInstance();
		cal.setTime(beginDate);
		while(cal.getTime().before(endDate)){
			Date tempDate = cal.getTime();
//			cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE)+1);
			cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE),cal.get(Calendar.HOUR_OF_DAY)+12,0);
			SearchByDayDoneTask task = new SearchByDayDoneTask(arrayData, databaseName, collectionName, 
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
		Map<String, Double> tempMaxMap = null;	
		for (SearchByDayDoneTask task : forks) {
			lineMapDto = task.join();
			System.out.println(lineMapDto);
			// 获取最小值
			tempMinMap = lineMapDto.getMinMap();
			if(tempMinMap != null){
				for (String paramCode : en_params) {
					Double min = minMap.get(paramCode);
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
					Double max = maxMap.get(paramCode);
					if (max == null) {
						max = tempMaxMap.get(paramCode);
					}
					if(tempMaxMap.get(paramCode) != null){
						maxMap.put(paramCode, this.getMax(max, tempMaxMap.get(paramCode)));							
					}
				}
			}
		}
		
		long end = System.currentTimeMillis();
		System.out.println("获取数据总时间： " + (end-begin));
		
		String cachePath = CommonConfig.getChartCachePath();
		File parentDir = new File(cachePath);
		if (!parentDir.exists()) {
			parentDir.mkdirs();
		}
		//画图
		Map<String, CreateTimeSeriesChartTask> chartForksMap = new HashMap<String, CreateTimeSeriesChartTask>();
		for (String constraintsKey : constraintsKeys) {
			List<ConstraintDto> constraintList = constraintsMap.get(constraintsKey);
			CreateTimeSeriesChartTask task = new CreateTimeSeriesChartTask(constraintList, arrayData, 
					beginDate, endDate, cachePath,constraintsKey);
			chartForksMap.put(constraintsKey, task);
			task.fork();
		}
		Map<String, String> chartMap = new HashMap<String, String>();
		for (String key : chartForksMap.keySet()) {
			String chartPath = chartForksMap.get(key).join();
			chartMap.put(key, chartPath);
		}
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
