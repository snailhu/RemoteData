package DataAn.jfreechart.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;
import javax.annotation.Resource;
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
import DataAn.jfreechart.chart.ChartFactory;
import DataAn.jfreechart.chart.ChartUtils;
import DataAn.jfreechart.chart.Serie;
import DataAn.jfreechart.dto.ConstraintDto;
import DataAn.jfreechart.dto.LineChartDto;
import DataAn.jfreechart.service.IJfreechartServcie;
import DataAn.mongo.service.IMongoService;

@Service
public class JfreechartServiceImpl implements IJfreechartServcie{

	@Resource
	private IMongoService mongoService;
	
	@Override
	public LineChartDto createLineChart(String series,String star, String paramType, 
			Date beginDate, Date endDate, Map<String,List<ConstraintDto>> constraintsMap) throws Exception{
		
		
		
		return this.createTimeSeriesChart(series, star, paramType, beginDate, endDate, constraintsMap, 0);
	}
	
	
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

	protected LineChartDto createLineChart(String series, String star, String paramType,
			String date, Map<String,String> params, int totalNumber) throws Exception {
		//多条线数据
		Vector<Serie> lines = new Vector<Serie>();
		//x轴数据
		Vector<String> categories = new Vector<String>();
		
		Map<String,Vector<Object>> lineMap = new HashMap<String,Vector<Object>>();
		Map<String,Double> minMap = new HashMap<String,Double>();
		Map<String,Double> maxMap = new HashMap<String,Double>();
		Vector<Object> lineList = null;
		Double min = null;
		Double max = null;
		Set<String> en_params = params.keySet();
		MongoCursor<Document> cursor = mongoService.findByYear_month_day(series, star, paramType, date);
		Document doc = null;
		int count = 0;
		while(cursor.hasNext()){
			//设置总数
			if(totalNumber !=0 && count == totalNumber){
				break;
			}
			count ++;
			
			doc = cursor.next();
			categories.add(DateUtil.format(doc.getDate("datetime")));
			for (String key : en_params) {
				lineList = lineMap.get(key);
				if(lineList == null){
					lineList = new Vector<Object>();
				}
				lineList.add(doc.get(key));
				lineMap.put(key,lineList);
				//获取最小值
				min = minMap.get(key);
				if(min == null){
					min = Double.parseDouble(doc.getString(key));
				}
				minMap.put(key, this.getMin(min, Double.parseDouble(doc.getString(key))));
				//获取最大值
				max = maxMap.get(key);
				if(max == null){
					max = Double.parseDouble(doc.getString(key));
				}
				maxMap.put(key, this.getMax(max, Double.parseDouble(doc.getString(key))));
			}
		}
		
		Serie line = null;
		for (String key : en_params) {
			line = new Serie();
			line.setName(params.get(key));
			line.setData(lineMap.get(key));
			lines.add(line);
		}
		
		String title = "";
		String categoryAxisLabel = "";
		String valueAxisLabel = "";
		
		JFreeChart chart = ChartFactory.createLineChartOneY(title,
				categoryAxisLabel, valueAxisLabel, lines, categories);
		
		String cachePath = CommonConfig.getChartCachePath();
		File parentDir = new File(cachePath);
		if (!parentDir.exists()) {
			parentDir.mkdirs();
		}
		
		File file = new File(cachePath,"lineChart.png");
		int width = 1024;
		int height = 620;
		//ChartUtilities.saveChartAsJPEG(file, chart, width, height);
		ChartUtilities.saveChartAsPNG(file, chart, width, height);
		LineChartDto lineChartDto = new LineChartDto();
		//lineChartDto.setChartPath(file.getAbsolutePath());
		lineChartDto.setMinMap(minMap);
		lineChartDto.setMaxMap(maxMap);
		return lineChartDto;
	}
	

	protected LineChartDto createTimeSeriesChart(String series, String star, String paramType,
			Date beginDate, Date endDate, Map<String,List<ConstraintDto>> constraintsMap, int totalNumber) throws Exception {
		
		Map<String,String> params = new HashMap<String,String>();
		Map<String,Double> paramMin = new HashMap<String,Double>();
		Map<String,Double> paramMax = new HashMap<String,Double>();
		Set<String> constraintsKeys = constraintsMap.keySet();
		for (String key : constraintsKeys) {
			List<ConstraintDto> constraintList = constraintsMap.get(key);
			for (ConstraintDto constraintDto : constraintList) {
				if(!params.containsKey(key)){
					params.put(constraintDto.getValue(), constraintDto.getName());
					paramMin.put(constraintDto.getValue(), constraintDto.getMin());
					paramMax.put(constraintDto.getValue(), constraintDto.getMax());					
				}
			}
		}
		
		MongoCursor<Document> cursor = mongoService.findByDate(series, star, paramType, beginDate, endDate);
		Document doc = null;
		int count = 0;
		Map<Date,List<Document>> tempMap = new LinkedHashMap<Date,List<Document>>();
		List<Document> tempDocList = null;
		while(cursor.hasNext()){
			//设置总数
			if(totalNumber !=0 && count == totalNumber){
				break;
			}
			count ++;
			
			doc = cursor.next();
			Date datetime = doc.getDate("datetime");
			tempDocList = tempMap.get(datetime);
			if(tempDocList == null){
				tempDocList = new ArrayList<Document>();
			}
			tempDocList.add(doc);
			tempMap.put(datetime, tempDocList);
		}
		System.out.println("count: " + count);
		//多条线数据
		Map<String,TimeSeries> lineMap = new HashMap<String,TimeSeries>();
		Map<String,Double> minMap = new HashMap<String,Double>();
		Map<String,Double> maxMap = new HashMap<String,Double>();
		TimeSeries timeseries = null;
		Double min = null;
		Double max = null;
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
					if(timeseries == null){
						timeseries = ChartUtils.createTimeseries(params.get(key));
					}
					//转换为double 类型
					double dValue = Double.parseDouble(doc.getString(key));
					//往序列里面添加数据
					timeseries.addOrUpdate(new Millisecond(datetime), dValue);
					lineMap.put(key,timeseries);
					//获取最小值
					min = minMap.get(key);
					if(min == null){
						min = dValue;
					}
					minMap.put(key, this.getMin(min, dValue));
					//获取最大值
					max = maxMap.get(key);
					if(max == null){
						max = dValue;
					}
					maxMap.put(key, this.getMax(max, dValue));
				}
			}
		}
		
		Map<String,String> chartMap = new HashMap<String,String>();
		for (String key : constraintsKeys) {
			List<ConstraintDto> constraintList = constraintsMap.get(key);
			//多条线图表数据
			TimeSeriesCollection dataset = new TimeSeriesCollection();
			for (ConstraintDto constraintDto : constraintList) {
				dataset.addSeries(lineMap.get(constraintDto.getValue()));
				System.out.println("constraintDto.getValue(): " + lineMap.get(constraintDto.getValue()).getItemCount());
			}
			
			String title = "";
			String categoryAxisLabel = "";
			String valueAxisLabel = "";
	        
			JFreeChart chart = ChartFactory.createTimeSeriesChart(title, categoryAxisLabel, valueAxisLabel, dataset);
			
			String cachePath = CommonConfig.getChartCachePath();
			File parentDir = new File(cachePath);
			if (!parentDir.exists()) {
				parentDir.mkdirs();
			}
			File file = new File(cachePath,"lineChart.png");
			int width = 1024;
			int height = 620;
			//ChartUtilities.saveChartAsJPEG(file, chart, width, height);
			ChartUtilities.saveChartAsPNG(file, chart, width, height);
			chartMap.put(key, file.getAbsolutePath());
		}
		
		LineChartDto lineChartDto = new LineChartDto();
		lineChartDto.setChartMap(chartMap);
		lineChartDto.setMinMap(minMap);
		lineChartDto.setMaxMap(maxMap);
		return lineChartDto;
	}
	protected double getMax(double data1,double data2){
		if(data1 >= data2)
			return data1;
		return data2;
	}
	protected double getMin(double data1,double data2){
		if(data1 <= data2)
			return data1;
		return data2;
	}

}
