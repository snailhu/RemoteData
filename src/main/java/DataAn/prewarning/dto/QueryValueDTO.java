package DataAn.prewarning.dto;

/**
 * @author wj
 *
 */
public class QueryValueDTO implements java.io.Serializable {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	private Long valueId; // 参数配置Id

	private String series; // 星系

	private String star; // 星

	private String parameter; // 参数

	private String parameterType; // 参数类型

	private double maxVal; // 最大值

	private double minVal; // 最小值

	private int timeZone; // 时间跨度（单位min）

	private int limitTimes; // 限定次数

	private String warningType; // 预警类型（0特殊工况，1异常）

	public String getStar() {
		return star;
	}

	public void setStar(String star) {
		this.star = star;
	}

	public int getLimitTimes() {
		return limitTimes;
	}

	public void setLimitTimes(int limitTimes) {
		this.limitTimes = limitTimes;
	}

	public Long getValueId() {
		return valueId;
	}

	public void setValueId(Long valueId) {
		this.valueId = valueId;
	}

	public int getTimeZone() {
		return timeZone;
	}

	public void setTimeZone(int timeZone) {
		this.timeZone = timeZone;
	}

	public String getSeries() {
		return series;
	}

	public void setSeries(String series) {
		this.series = series;
	}

	public String getWarningType() {
		return warningType;
	}

	public void setWarningType(String warningType) {
		this.warningType = warningType;
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
