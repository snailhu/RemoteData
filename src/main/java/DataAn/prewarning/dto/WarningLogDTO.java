package DataAn.prewarning.dto;

import java.util.Date;

/**
 * @author wj
 *
 */
public class WarningLogDTO implements java.io.Serializable {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;
	private String logId; // 记录Id

	private Long series; // 星系

	private Long star; // 星

	private String parameter; // 参数

	private String parameterType; // 设备

	private double paramValue; // 参数值

	private int warningType; // 预警类型（0特殊工况，1异常）

	private int hadRead; // 是否已读（0未读，1已读）

	private Date timeValue; // 时间点

	public Long getStar() {
		return star;
	}

	public void setStar(Long star) {
		this.star = star;
	}

	public Date getTimeValue() {
		return timeValue;
	}

	public void setTimeValue(Date timeValue) {
		this.timeValue = timeValue;
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

	public Long getSeries() {
		return series;
	}

	public void setSeries(Long series) {
		this.series = series;
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

	public int getWarningType() {
		return warningType;
	}

	public void setWarningType(int warningType) {
		this.warningType = warningType;
	}

	public int getHadRead() {
		return hadRead;
	}

	public void setHadRead(int hadRead) {
		this.hadRead = hadRead;
	}

}
