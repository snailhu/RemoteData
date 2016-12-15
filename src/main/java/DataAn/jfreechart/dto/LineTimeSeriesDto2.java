package DataAn.jfreechart.dto;

import java.util.Date;

public class LineTimeSeriesDto2 {

	private Date datetime;
	
	private String paramCode;
	
	private double paramValue;

	public Date getDatetime() {
		return datetime;
	}

	public void setDatetime(Date datetime) {
		this.datetime = datetime;
	}

	public String getParamCode() {
		return paramCode;
	}

	public void setParamCode(String paramCode) {
		this.paramCode = paramCode;
	}

	public double getParamValue() {
		return paramValue;
	}

	public void setParamValue(double paramValue) {
		this.paramValue = paramValue;
	}
	
	
	
}
