package DataAn.galaxyManager.dto;

public class ParameterDto {

	private Long id;
	
	private String series;
	
	private String star;
	
	private String parameterType;
	
	private String fullName;
	
	private String simplyName;
	
	private String code;

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
				+ star + ", fullName=" + fullName + ", simplyName="
				+ simplyName + ", code=" + code + "]";
	}

	
	
}
