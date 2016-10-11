package DataAn.reportManager.dto;

public class ParamDto {

	private String paramName;//参数名称
	
	private String paramNumMax;//参数最大值
	
	private String paramNumMin;//参数最小值

	public String getParamName() {
		return paramName;
	}

	public void setParamName(String paramName) {
		this.paramName = paramName;
	}

	public String getParamNumMax() {
		return paramNumMax;
	}

	public void setParamNumMax(String paramNumMax) {
		this.paramNumMax = paramNumMax;
	}

	public String getParamNumMin() {
		return paramNumMin;
	}

	public void setParamNumMin(String paramNumMin) {
		this.paramNumMin = paramNumMin;
	}
}
