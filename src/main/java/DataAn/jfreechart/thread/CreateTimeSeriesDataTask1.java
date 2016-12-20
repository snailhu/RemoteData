package DataAn.jfreechart.thread;

import java.util.Date;
import java.util.concurrent.RecursiveTask;

import org.jfree.data.time.Millisecond;
import org.jfree.data.time.Second;
import org.jfree.data.time.TimeSeries;

import DataAn.jfreechart.chart.ChartUtils;
import DataAn.jfreechart.dto.LineTimeSeriesDto2;

/**
 * 多线程生成：TimeSeries
 *
 */
public class CreateTimeSeriesDataTask1 extends RecursiveTask<TimeSeries>{

	private static final long serialVersionUID = 1L;
	
	private LineTimeSeriesDto2[] arrayData;
	private String paramCode;
	private String paramName;
	
	
	public CreateTimeSeriesDataTask1(LineTimeSeriesDto2[] arrayData,
			String paramCode, String paramName) {
		super();
		this.arrayData = arrayData;
		this.paramCode = paramCode;
		this.paramName = paramName;
	}


	@Override
	protected TimeSeries compute() {
		TimeSeries timeseries = ChartUtils.createTimeseries(paramName);
		for (LineTimeSeriesDto2 lineTimeSeriesDto : arrayData) {
			if(lineTimeSeriesDto != null){
				// 往序列里面添加数据
//				timeseries.addOrUpdate(new Millisecond(lineTimeSeriesDto.getDatetime()), lineTimeSeriesDto.getParamValue());
				timeseries.addOrUpdate(new Second(lineTimeSeriesDto.getDatetime()), lineTimeSeriesDto.getParamValue());
				
			}
		}
		return timeseries;
	}

}
