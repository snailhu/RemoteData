package DataAn.Api.domain;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

public class ParamException {
	
	//参数名称
	private  String paramName;
	
	//参数允许的最大值	
	private  float  max;
	
	//允许最小值
	private  float min;
	
	//异常点的值
	private  float value;
	
	//异常点时间
	private  String time;
	
	//异常的起始时间
	@Temporal(TemporalType.TIMESTAMP )
	private Date  startTime;
	
	//特殊工况的结束时间
	@Temporal(TemporalType.TIMESTAMP )
	private  Date  endTime;
	
}
