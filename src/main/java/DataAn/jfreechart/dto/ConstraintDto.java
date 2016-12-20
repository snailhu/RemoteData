package DataAn.jfreechart.dto;

/**
 *	约束条件描述
 */
public class ConstraintDto {

	private String paramName; //中文键
	private String paramCode; //英文值
	private double max; //最大值区间
	private double min; //最小值区间
	private String units; //单位
	
	public String getParamName() {
		return paramName;
	}
	public void setParamName(String paramName) {
		this.paramName = paramName;
	}
	public String getParamCode() {
		return paramCode;
	}
	public void setParamCode(String paramCode) {
		this.paramCode = paramCode;
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
	
	public String getUnits() {
		return units;
	}
	public void setUnits(String units) {
		this.units = units;
	}
	@Override
	public String toString() {
		return "ConstraintDto [paramName=" + paramName + ", paramCode=" + paramCode + ", max=" + max + ", min=" + min
				+ ", units=" + units + "]";
	}
	
}
