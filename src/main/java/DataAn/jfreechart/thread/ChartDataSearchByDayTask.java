package DataAn.jfreechart.thread;

import java.util.Date;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.RecursiveTask;
import org.apache.commons.lang3.StringUtils;
import org.bson.Document;
import org.jfree.data.time.Millisecond;
import org.jfree.data.time.TimeSeries;
import com.mongodb.client.MongoCursor;
import DataAn.jfreechart.dto.LineMapDto;
import DataAn.mongo.client.MongodbUtil;

public class ChartDataSearchByDayTask extends RecursiveTask<LineMapDto>{

	private static final long serialVersionUID = 1L;
	
	private LineMapDto lineMapDto;
	private String databaseName;
	private String collectionName;
	private String year_month_day;
	private Map<String, Double> paramMin;
	private Map<String, Double> paramMax;

	
	public ChartDataSearchByDayTask(LineMapDto lineMapDto,
			String databaseName, String collectionName, String year_month_day,
			Map<String, Double> paramMin, Map<String, Double> paramMax){
		this.lineMapDto = lineMapDto;
		this.databaseName = databaseName;
		this.collectionName = collectionName;
		this.year_month_day = year_month_day;
		this.paramMin = paramMin;
		this.paramMax = paramMax;
	}
	

	@Override
	protected LineMapDto compute() {
		Map<String, TimeSeries> lineMap = lineMapDto.getLineMap();
		Map<String, Double> minMap = lineMapDto.getMaxMap();
		Map<String, Double> maxMap = lineMapDto.getMinMap();
		//参数集
		Set<String> en_params = lineMap.keySet();
		TimeSeries timeseries = null;
		Double min = null;
		Double max = null;
		
		//查询mongodb数据集
		MongodbUtil mg = MongodbUtil.getInstance();
		MongoCursor<Document> cursor = mg.find(databaseName, collectionName, "year_month_day", year_month_day);
		if(cursor == null){
			throw new RuntimeException(year_month_day +" 未找到报告数据！");
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
			}
		}
		if(count == 0){
			throw new RuntimeException(year_month_day + " 报告数据记录数为：" + count);
		}
		//重设
		lineMapDto.setLineMap(lineMap);
		lineMapDto.setMaxMap(maxMap);
		lineMapDto.setMinMap(minMap);
		return lineMapDto;
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
