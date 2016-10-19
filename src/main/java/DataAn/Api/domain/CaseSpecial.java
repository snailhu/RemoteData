package DataAn.Api.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 特殊工况
 * @author snailHU
 *
 */
public class CaseSpecial {
	
	@Id
	@GeneratedValue
	private int  id;
	
	//特殊工况出现的次数
	@Column(nullable = true)
	private int count;
	
	//特殊工况的起始时间
	@Temporal(TemporalType.TIMESTAMP )
	private Date  startTime;
	
	//特殊工况的结束时间
	@Temporal(TemporalType.TIMESTAMP )
	private  Date  endTime;
	
	//特殊工况所针对的参数
	@Column(length = 32,nullable = true)
	private  String paramName;
	
	// 特殊工况的限定值
	private  float limitValue;
	
	//限定值出现的频次计为一次特殊工况
	@Column(nullable = true)
	private  int  frequency;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	
	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getParamName() {
		return paramName;
	}

	public void setParamName(String paramName) {
		this.paramName = paramName;
	}

	public float getLimitValue() {
		return limitValue;
	}

	public void setLimitValue(float limitValue) {
		this.limitValue = limitValue;
	}

	public int getFrequency() {
		return frequency;
	}

	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}
	
	
	
	
	
}
