package DataAn.galaxyManager.dto;

public class ParameterDto {

	private Long id;
	
	private String series; //系列 j9
	
	private String star; //星 01、02、03...
	
	private String parameterType; //参数类型 flywheel、top
	
	private String fullName; //参数全称如， F10W111:飞轮电流Xa(00814)
	
	private String simplyName; //参数简称 如， 飞轮电流Xa(00814)
	
	private String code; //码： sequence_00814

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

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getSimplyName() {
		return simplyName;
	}

	public void setSimplyName(String simplyName) {
		this.simplyName = simplyName;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Override
	public String toString() {
		return "ParameterDto [id=" + id + ", series=" + series + ", star="
				+ star + ", parameterType=" + parameterType + ", fullName="
				+ fullName + ", simplyName=" + simplyName + ", code=" + code
				+ "]";
	}
	
	
}
