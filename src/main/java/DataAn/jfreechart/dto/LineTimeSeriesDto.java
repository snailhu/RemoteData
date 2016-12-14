package DataAn.jfreechart.dto;

import java.util.Date;
import java.util.Map;

public class LineTimeSeriesDto {

	private Date datetime;
	
	private Map<String, Double> param;
	

	public Date getDatetime() {
		return datetime;
	}

	public void setDatetime(Date datetime) {
		this.datetime = datetime;
	}

	public Map<String, Double> getParam() {
		return param;
	}

	public void setParam(Map<String, Double> param) {
		this.param = param;
	}

	@Override
	public String toString() {
		return "LineTimeSeriesDto [datetime=" + datetime + ", param=" + param
				+ "]";
	}
	
	
	
}
