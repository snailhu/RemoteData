package DataAn.reportManager.dto;

import java.util.List;

public class DataToDocDto {
	
	private String reporttitle;
	
	private String parts;
	
	private String healthcondition;
	
	private String PhotoBLOB;
	
	private List<ProductDto> products;
	
	private List<ParamDto> params;
	
	private List<ParamImgDataDto> paramImgData;
	
	private List<ParamImgDataDto> paramImgDataAll;

	
	
	public List<ParamImgDataDto> getParamImgDataAll() {
		return paramImgDataAll;
	}

	public void setParamImgDataAll(List<ParamImgDataDto> paramImgDataAll) {
		this.paramImgDataAll = paramImgDataAll;
	}

	public List<ParamDto> getParams() {
		return params;
	}

	public void setParams(List<ParamDto> params) {
		this.params = params;
	}

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

	public List<ProductDto> getProducts() {
		return products;
	}

	public void setProducts(List<ProductDto> products) {
		this.products = products;
	}
}
