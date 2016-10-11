package DataAn.jfreechart.service.impl;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import javax.annotation.Resource;

import org.bson.Document;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.springframework.stereotype.Service;

import com.mongodb.client.MongoCursor;

import DataAn.common.config.CommonConfig;
import DataAn.common.utils.DateUtil;
import DataAn.jfreechart.chart.ChartFactory;
import DataAn.jfreechart.chart.Serie;
import DataAn.jfreechart.service.IJfreechartServcie;
import DataAn.mongo.service.IMongoService;

@Service
public class JfreechartServiceImpl implements IJfreechartServcie{

	@Resource
	private IMongoService mongoService;
	
	@Override
	public String createLineChart(String series, String star, String paramType,
			String date, Map<String,String> params) throws Exception {
		//多条线数据
		Vector<Serie> lines = new Vector<Serie>();
		//x轴数据
		Vector<String> categories = new Vector<String>();
		
		Map<String,Vector<Object>> map = new HashMap<String,Vector<Object>>();
		Vector<Object> lineList = null;
		Set<String> en_params = params.keySet();
		MongoCursor<Document> cursor = mongoService.findByYear_month_day(series, star, paramType, date);
		Document doc = null;
//		int count = 0;
		while(cursor.hasNext()){
			
//			count ++;
//			if(count == 1000)
//				break;
			
			doc = cursor.next();
			categories.add(DateUtil.format(doc.getDate("datetime")));
			for (String key : en_params) {
				lineList = map.get(key);
				if(lineList == null){
					lineList = new Vector<Object>();
				}
				lineList.add(doc.get(key));
				map.put(key,lineList);
			}
		}
		Serie line = null;
		for (String key : en_params) {
			line = new Serie();
			line.setName(params.get(key));
			line.setData(map.get(key));
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
		int height = 420;
		//ChartUtilities.saveChartAsJPEG(file, chart, width, height);
		ChartUtilities.saveChartAsPNG(file, chart, width, height);
		return file.getAbsolutePath();
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



	
}
