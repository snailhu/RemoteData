package DataAn.Api.dto;

public class ParamExceptionDto {
	
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

	public String getParamName() {
		return paramName;
	}

	public void setParamName(String paramName) {
		this.paramName = paramName;
	}

	public float getMax() {
		return max;
	}

	public void setMax(float max) {
		this.max = max;
	}

	public float getMin() {
		return min;
	}

	public void setMin(float min) {
		this.min = min;
	}

	public float getValue() {
		return value;
	}

	public void setValue(float value) {
		this.value = value;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}
		
	
}
