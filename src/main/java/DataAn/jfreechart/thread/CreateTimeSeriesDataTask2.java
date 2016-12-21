package DataAn.jfreechart.thread;

import java.util.Date;
import java.util.concurrent.RecursiveTask;

import org.jfree.data.time.Millisecond;
import org.jfree.data.time.Second;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesDataItem;

import DataAn.jfreechart.chart.ChartUtils;
import DataAn.jfreechart.dto.LineTimeSeriesDto2;

/**
 * 多线程生成：TimeSeries
 *
 */
public class CreateTimeSeriesDataTask2 extends RecursiveTask<TimeSeries>{

	private static final long serialVersionUID = 1L;
	
	private TimeSeriesDataItem[] arrayData;
	private String paramCode;
	private String paramName;
	
	
	public CreateTimeSeriesDataTask2(TimeSeriesDataItem[] arrayData,
			String paramCode, String paramName) {
		super();
		this.arrayData = arrayData;
		this.paramCode = paramCode;
		this.paramName = paramName;
	}


	@Override
	protected TimeSeries compute() {
		TimeSeries timeseries = ChartUtils.createTimeseries(paramName);
		for (TimeSeriesDataItem item : arrayData) {
			if(item != null){
				// 往序列里面添加数据
//				timeseries.addOrUpdate(new Millisecond(lineTimeSeriesDto.getDatetime()), lineTimeSeriesDto.getParamValue());
				timeseries.addOrUpdate(item);
				
			}
		}
		return timeseries;
	}

}
