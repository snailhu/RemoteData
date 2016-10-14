package DataAn.prewarning.dto;

/**
 * @author wj
 *
 */
public class WarnValueDTO implements java.io.Serializable {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	private Long valueId; // 参数配置Id

	private Long series; // 星系

	private String parameter; // 参数

	private String parameterType; // 参数类型

	private Double maxVal; // 最大值

	private Double minVal; // 最小值

	private Integer timeZone; // 时间跨度（单位min）

	private Integer limitTimes; // 限定次数

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

	public Double getMaxVal() {
		return maxVal;
	}

	public void setMaxVal(Double maxVal) {
		this.maxVal = maxVal;
	}

	public Double getMinVal() {
		return minVal;
	}

	public void setMinVal(Double minVal) {
		this.minVal = minVal;
	}

	public Integer getTimeZone() {
		return timeZone;
	}

	public void setTimeZone(Integer timeZone) {
		this.timeZone = timeZone;
	}

	public Integer getLimitTimes() {
		return limitTimes;
	}

	public void setLimitTimes(Integer limitTimes) {
		this.limitTimes = limitTimes;
	}

}
