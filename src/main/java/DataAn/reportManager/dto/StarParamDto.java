package DataAn.reportManager.dto;

import java.util.Date;


public class StarParamDto {

	private Long id;
	
	private String paramCode;
	
	private String paramName;
	
	private String creater;
	
	private String series;
	
	private String star;
	
	private String parameterType;
	
	private String productName;
		
	private String partsType;
	
	private double effeMin;
	
	private double effeMax;
	
	private Date createDate = new Date();
	

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public double getEffeMin() {
		return effeMin;
	}

	public void setEffeMin(double effeMin) {
		this.effeMin = effeMin;
	}

	public double getEffeMax() {
		return effeMax;
	}

	public void setEffeMax(double effeMax) {
		this.effeMax = effeMax;
	}

	public String getPartsType() {
		return partsType;
	}

	public void setPartsType(String partsType) {
		this.partsType = partsType;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getParameterType() {
		return parameterType;
	}

	public void setParameterType(String parameterType) {
		this.parameterType = parameterType;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getParamCode() {
		return paramCode;
	}

	public void setParamCode(String paramCode) {
		this.paramCode = paramCode;
	}

	public String getParamName() {
		return paramName;
	}

	public void setParamName(String paramName) {
		this.paramName = paramName;
	}

	public String getCreater() {
		return creater;
	}

	public void setCreater(String creater) {
		this.creater = creater;
	}
}
