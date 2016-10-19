package DataAn.jfreechart.dto;

/**
 *	约束条件描述
 */
public class ConstraintDto {

	private String name; //中文键
	private String value; //英文值
	private double max; //最大值区间
	private double min; //最小值区间
	
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
	public double getMax() {
		return max;
	}
	public void setMax(double max) {
		this.max = max;
	}
	public double getMin() {
		return min;
	}
	public void setMin(double min) {
		this.min = min;
	}
	
	
}
