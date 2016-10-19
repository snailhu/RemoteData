package DataAn.jfreechart.dto;

import java.util.Map;

public class LineChartDto {

//	private String chartPath;
	
	private Map<String,String> chartMap;
	
	private Map<String,Double> minMap;
	
	private Map<String,Double> maxMap;

//	public String getChartPath() {
//		return chartPath;
//	}
//
//	public void setChartPath(String chartPath) {
//		this.chartPath = chartPath;
//	}
	
	public Map<String, String> getChartMap() {
		return chartMap;
	}

	public void setChartMap(Map<String, String> chartMap) {
		this.chartMap = chartMap;
	}

	public Map<String, Double> getMinMap() {
		return minMap;
	}

	public void setMinMap(Map<String, Double> minMap) {
		this.minMap = minMap;
	}

	public Map<String, Double> getMaxMap() {
		return maxMap;
	}

	public void setMaxMap(Map<String, Double> maxMap) {
		this.maxMap = maxMap;
	}

	
	
	
}
