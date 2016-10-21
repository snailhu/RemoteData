package DataAn.prewarning.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author wj
 *
 */
@Entity
@Table(name = "t_warninglog")
public class WarningLog implements java.io.Serializable {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "logId", nullable = false)
	private String logId; // 记录Id

	@Column(name = "series", nullable = true)
	private Long series; // 星系

	@Column(name = "star", nullable = true)
	private Long star; // 星

	@Column(name = "parameter", nullable = true)
	private String parameter; // 参数

	@Column(name = "parameterType", nullable = true)
	private String parameterType; // 设备

	@Column(name = "timeValue", nullable = true)
	private Date timeValue; // 时间点

	@Column(name = "warnValue", nullable = true)
	private double paramValue; // 参数值

	@Column(name = "warningType", nullable = true)
	private int warningType; // 预警类型（0特殊工况，1异常）

	@Column(name = "hadRead", nullable = true)
	private int hadRead; // 是否已读（0未读，1已读）

	@Column(name = "createDate", nullable = true)
	private Date createDate;

	public Long getStar() {
		return star;
	}

	public void setStar(Long star) {
		this.star = star;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
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

	public String getLogId() {
		return logId;
	}

	public void setLogId(String logId) {
		this.logId = logId;
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

	public Date getTimeValue() {
		return timeValue;
	}

	public void setTimeValue(Date timeValue) {
		this.timeValue = timeValue;
	}

}
