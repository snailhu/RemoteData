package DataAn.reportManager.dto;

import java.util.List;

public class DataToDocDto {
	
	private String reporttitle;
	
	private String parts;
	
	private String healthcondition;
	
	private String PhotoBLOB;
	
	private List<ProductDto> products;
	
	private List<ParamDto> params;
	
	private List<ParamImgDataDto> threeParamImg;
	
	private List<ParamImgDataDto> twoParamImg;
	
	private List<ParamImgDataDto> oneParamImg;
	
	
	public List<ParamImgDataDto> getThreeParamImg() {
		return threeParamImg;
	}

	public void setThreeParamImg(List<ParamImgDataDto> threeParamImg) {
		this.threeParamImg = threeParamImg;
	}

	public List<ParamImgDataDto> getTwoParamImg() {
		return twoParamImg;
	}

	public void setTwoParamImg(List<ParamImgDataDto> twoParamImg) {
		this.twoParamImg = twoParamImg;
	}

	public List<ParamImgDataDto> getOneParamImg() {
		return oneParamImg;
	}

	public void setOneParamImg(List<ParamImgDataDto> oneParamImg) {
		this.oneParamImg = oneParamImg;
	}

	public List<ParamDto> getParams() {
		return params;
	}

	public void setParams(List<ParamDto> params) {
		this.params = params;
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
