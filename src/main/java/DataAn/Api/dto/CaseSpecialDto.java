package DataAn.Api.dto;

public class CaseSpecialDto {

	// 特殊工况的限定值
	private  float limitValue;
	
	//限定值出现的频次计为一次特殊工况
	private  int  frequency;
	
	//特殊工况所针对的参数
	private  String paramName;
	
	//特殊工况所针对的时间
	private  String  dateTime;
		
	private String series;
	
	private String star;
	
	private String deviceName;
	
	public String getSeries() {
		return series;
	}

	public void setSeries(String series) {
		this.series = series;
	}

	public String getStar() {
		return star;
	}

	public void setStar(String star) {
		this.star = star;
	}

	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
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

	public String getParamName() {
		return paramName;
	}

	public void setParamName(String paramName) {
		this.paramName = paramName;
	}

	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}
	
	
	
}
