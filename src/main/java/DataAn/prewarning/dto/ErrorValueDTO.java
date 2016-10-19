package DataAn.prewarning.dto;

/**
 * @author wj
 *
 */
public class ErrorValueDTO implements java.io.Serializable {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	private Long valueId; // 参数配置Id

	private Long series; // 星系

	private Long star; // 星

	private String parameter; // 参数

	private String parameterType; // 参数类型

	private double maxVal; // 最大值

	private double minVal; // 最小值

	public Long getStar() {
		return star;
	}

	public void setStar(Long star) {
		this.star = star;
	}

	public Long getValueId() {
		return valueId;
	}

	public void setValueId(Long valueId) {
		this.valueId = valueId;
	}

	public Long getSeries() {
		return series;
	}

	public void setSeries(Long series) {
		this.series = series;
	}

	public String getParameter() {
		return parameter;
	}

	public void setParameter(String parameter) {
		this.parameter = parameter;
	}

	public String getParameterType() {
		return parameterType;
	}

	public void setParameterType(String parameterType) {
		this.parameterType = parameterType;
	}

	public double getMaxVal() {
		return maxVal;
	}

	public void setMaxVal(double maxVal) {
		this.maxVal = maxVal;
	}

	public double getMinVal() {
		return minVal;
	}

	public void setMinVal(double minVal) {
		this.minVal = minVal;
	}

}
