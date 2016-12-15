package DataAn.jfreechart.dto;

import java.util.Map;

public class LineMapDto {

	private Map<String, Double> minMap;
	private Map<String, Double> maxMap;
	private int index;
	private int count;
	
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
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	@Override
	public String toString() {
		return "LineMapDto [minMap=" + minMap + ", maxMap=" + maxMap
				+ ", index=" + index + ", count=" + count + "]";
	}
	
	
}
