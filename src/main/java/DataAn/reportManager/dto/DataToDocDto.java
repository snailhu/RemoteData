package DataAn.reportManager.dto;

import java.util.List;

public class DataToDocDto {
	
	private String reporttitle;
	
	private String parts;
	
	private String healthcondition;
	
	private String PhotoBLOB;
	
	private List<ParamDto> params;
	
	private List<ParamImgDataDto> paramImgData;

	public List<ParamImgDataDto> getParamImgData() {
		return paramImgData;
	}

	public void setParamImgData(List<ParamImgDataDto> paramImgData) {
		this.paramImgData = paramImgData;
	}

	public String getReporttitle() {
		return reporttitle;
	}

	public void setReporttitle(String reporttitle) {
		this.reporttitle = reporttitle;
	}

	public String getParts() {
		return parts;
	}

	public void setParts(String parts) {
		this.parts = parts;
	}

	public String getHealthcondition() {
		return healthcondition;
	}

	public void setHealthcondition(String healthcondition) {
		this.healthcondition = healthcondition;
	}

	public String getPhotoBLOB() {
		return PhotoBLOB;
	}

	public void setPhotoBLOB(String photoBLOB) {
		PhotoBLOB = photoBLOB;
	}

	public List<ParamDto> getParams() {
		return params;
	}

	public void setParams(List<ParamDto> params) {
		this.params = params;
	}
}
