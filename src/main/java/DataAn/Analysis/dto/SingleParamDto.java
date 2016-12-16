package DataAn.Analysis.dto;

import java.io.Serializable;


public class SingleParamDto implements Serializable{
	private int id;
	
	private String name;//中文键
	
	private String value;//英文值
	
	private String max;
	
	private String min;
	
	private String unit;
	
	private String yname;
	
	private String startDate;
	
	private String endDate;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getYname() {
		return yname;
	}

	public void setYname(String yname) {
		this.yname = yname;
	}

			
	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	@Override
	public String toString() {
		return "SingleParamDto [id=" + id + ", name=" + name + ", value="
				+ value + ", max=" + max + ", min=" + min + ", unit=" + unit + ", yname=" + yname
				+ "]";
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getMax() {
		return max;
	}

	public void setMax(String max) {
		this.max = max;
	}

	public String getMin() {
		return min;
	}

	public void setMin(String min) {
		this.min = min;
	}	
}		
