package DataAn.prewarning.dto;

/**
 * @author wj
 *
 */
public class QueryLogDTO implements java.io.Serializable {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;
	private String logId; // 记录Id

	private String series; // 星系

	private String star; // 星

	private String parameter; // 参数

	private String parameterType; // 设备

	private double paramValue; // 参数值

	private String warningType; // 预警类型（0特殊工况，1异常）

	private String hadRead; // 是否已读（0未读，1已读）

	private String timeValue; // 时间点

	private String warnRemark; // 预警备注

	public String getWarnRemark() {
		return warnRemark;
	}

	public void setWarnRemark(String warnRemark) {
		this.warnRemark = warnRemark;
	}

	public String getStar() {
		return star;
	}

	public void setStar(String star) {
		this.star = star;
	}

	public String getLogId() {
		return logId;
	}

	public void setLogId(String logId) {
		this.logId = logId;
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

	public double getParamValue() {
		return paramValue;
	}

	public void setParamValue(double paramValue) {
		this.paramValue = paramValue;
	}

	public String getWarningType() {
		return warningType;
	}

	public void setWarningType(String warningType) {
		this.warningType = warningType;
	}

	public String getSeries() {
		return series;
	}

	public void setSeries(String series) {
		this.series = series;
	}

	public String getHadRead() {
		return hadRead;
	}

	public void setHadRead(String hadRead) {
		this.hadRead = hadRead;
	}

	public String getTimeValue() {
		return timeValue;
	}

	public void setTimeValue(String timeValue) {
		this.timeValue = timeValue;
	}

}
