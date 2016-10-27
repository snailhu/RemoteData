package DataAn.Analysis.dto;

import java.io.Serializable;
import java.util.List;

public class YearAndParamDataDto implements Serializable{
	
	private float paramCount;
	
	private List<String> paramValue;
	
	private List<String> YearValue;

		
	public float getParamCount() {
		return paramCount;
	}

	public void setParamCount(float paramCount) {
		this.paramCount = paramCount;
	}

	public List<String> getParamValue() {
		return paramValue;
	}

	public void setParamValue(List<String> paramValue) {
		this.paramValue = paramValue;
	}

	public List<String> getYearValue() {
		return YearValue;
	}

	public void setYearValue(List<String> yearValue) {
		YearValue = yearValue;
	}
	
	
}
