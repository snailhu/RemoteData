package DataAn.jfreechart.dto;

import java.util.Map;

public class LineChartDto {

	private String chartPath;
	
	private Map<String,Double> minMap;
	
	private Map<String,Double> maxMap;

	public String getChartPath() {
		return chartPath;
	}

	public void setChartPath(String chartPath) {
		this.chartPath = chartPath;
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

	@Override
	public String toString() {
		return "LineChartDto [chartPath=" + chartPath + ", minMap=" + minMap
				+ ", maxMap=" + maxMap + "]";
	}
	
	
}
