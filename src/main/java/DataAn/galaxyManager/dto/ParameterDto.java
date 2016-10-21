package DataAn.galaxyManager.dto;

public class ParameterDto {

	private Long id;
	
	private String series;
	
	private String star;
	
	private String name;
	
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
				+ star + ", name=" + name + ", code=" + code + "]";
	}
	
	
}
