package DataAn.prewarning.domain;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author wj
 *
 */
@Entity
@Table(name = "t_warningvalue")
public class WarningValue implements java.io.Serializable {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "valueId", unique = true, nullable = false)
	private Long valueId; // 参数配置Id

	@Column(name = "series", nullable = true)
	private Long series; // 星系

	@Column(name = "parameter", nullable = true)
	private String parameter; // 参数

	@Column(name = "parameterType", nullable = true)
	private String parameterType; // 参数类型

	@Column(name = "maxVal", nullable = true)
	private double maxVal; // 最大值

	@Column(name = "minVal", nullable = true)
	private double minVal; // 最小值

	@Column(name = "timeZone", nullable = true)
	private int timeZone; // 时间跨度（单位min）

	@Column(name = "limitTimes", nullable = true)
	private int limitTimes; // 限定次数

	@Column(name = "warningType", nullable = true)
	private int warningType; // 预警类型（0特殊工况，1异常）

	@Column(name = "createDate", nullable = true)
	private Date createDate;

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
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

	public int getTimeZone() {
		return timeZone;
	}

	public void setTimeZone(int timeZone) {
		this.timeZone = timeZone;
	}

	public int getWarningType() {
		return warningType;
	}

	public void setWarningType(int warningType) {
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

	public int getLimitTimes() {
		return limitTimes;
	}

	public void setLimitTimes(int limitTimes) {
		this.limitTimes = limitTimes;
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
