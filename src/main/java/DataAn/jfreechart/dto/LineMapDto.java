package DataAn.jfreechart.dto;

import java.util.Map;

import org.jfree.data.time.TimeSeries;

public class LineMapDto {

	private Map<String, TimeSeries> lineMap;
	private Map<String, Double> minMap;
	private Map<String, Double> maxMap;
	public Map<String, TimeSeries> getLineMap() {
		return lineMap;
	}
	public void setLineMap(Map<String, TimeSeries> lineMap) {
		this.lineMap = lineMap;
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
