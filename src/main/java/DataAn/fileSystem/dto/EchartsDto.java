package DataAn.fileSystem.dto;

import java.util.List;
import java.util.Map;

public class EchartsDto {

	private List<String> xAxis;
	
	private List<Float> series;
	
	private Map<String,List<Float>> seriesMap;

	private List<String> series2;
	
	private Map<String,List<String>> seriesMap2;

	public List<String> getxAxis() {
		return xAxis;
	}

	public void setxAxis(List<String> xAxis) {
		this.xAxis = xAxis;
	}

	public List<Float> getSeries() {
		return series;
	}

	public void setSeries(List<Float> series) {
		this.series = series;
	}

	public Map<String, List<Float>> getSeriesMap() {
		return seriesMap;
	}

	public void setSeriesMap(Map<String, List<Float>> seriesMap) {
		this.seriesMap = seriesMap;
	}

	public List<String> getSeries2() {
		return series2;
	}

	public void setSeries2(List<String> series2) {
		this.series2 = series2;
	}

	public Map<String, List<String>> getSeriesMap2() {
		return seriesMap2;
	}

	public void setSeriesMap2(Map<String, List<String>> seriesMap2) {
		this.seriesMap2 = seriesMap2;
	}
	
	
}
