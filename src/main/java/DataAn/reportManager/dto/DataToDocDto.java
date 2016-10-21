package DataAn.reportManager.dto;

import java.util.List;

public class DataToDocDto {
	
	private String series;
	
	private String star;
	
	private String beginDate;
	
	private String endDate;
	
	private String createDate;
	
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

	public String getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public List<ProductDto> getProducts() {
		return products;
	}

	public void setProducts(List<ProductDto> products) {
		this.products = products;
	}
}
