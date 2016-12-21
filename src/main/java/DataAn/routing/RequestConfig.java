package DataAn.routing;

import java.io.Serializable;

import DataAn.Analysis.dto.ParamAttributeDto;
import DataAn.Analysis.dto.SingleParamDto;

public class RequestConfig implements Serializable {
	
	/**
	 * 星系
	 */
	private String series;
	
	/**
	 * 星
	 */
	private String star;
	
	/**
	 * 设备(部件)
	 */
	private String device;

	/**
	 * 参数
	 */
	//private String[] properties;
	private ParamAttributeDto properties[];
	
	private String timeStart;
	
	private String timeEnd;
	
	/**
	 * value same as {@link #properties.length}
	 */
	private int propertyCount;

	public String getDevice() {
		return device;
	}

	public void setDevice(String device) {
		this.device = device;
	}

	public ParamAttributeDto[] getProperties() {
		return properties;
	}

	public void setProperties(ParamAttributeDto[] properties) {
		this.properties = properties;
	}

	public String getTimeStart() {
		return timeStart;
	}

	public void setTimeStart(String timeStart) {
		this.timeStart = timeStart;
	}

	public String getTimeEnd() {
		return timeEnd;
	}

	public void setTimeEnd(String timeEnd) {
		this.timeEnd = timeEnd;
	}

	public String getSeries() {
		return series;
	}

	public void setSeries(String series) {
		this.series = series;
	}

	public String getStar() {
		return star;
	}

	public void setStar(String star) {
		this.star = star;
	}

	public int getPropertyCount() {
		return propertyCount;
	}

	public void setPropertyCount(int propertyCount) {
		this.propertyCount = propertyCount;
	}

	
}
