package DataAn.galaxyManager.dto;


public class SeriesDto{

	private long id;
	
	private String name;
	
	private String code;
	
	private String description;
	
	private String createDate;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	

	@Override
	public String toString() {
		return "SeriesDto [id=" + id + ", name=" + name + ", code=" + code
				+ ", description=" + description + ", createDate=" + createDate
				+ "]";
	}

}
