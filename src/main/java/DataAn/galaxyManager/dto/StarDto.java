package DataAn.galaxyManager.dto;

public class StarDto {

	private long id;
	
	private String name;
	
	private String code;
	
	private String description;
	
	private String beginDate;
	
	private long seriesId;

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

	public String getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}

	public long getSeriesId() {
		return seriesId;
	}

	public void setSeriesId(long seriesId) {
		this.seriesId = seriesId;
	}

	@Override
	public String toString() {
		return "StarDto [id=" + id + ", name=" + name + ", code=" + code
				+ ", description=" + description + ", beginDate=" + beginDate
				+ ", seriesId=" + seriesId + "]";
	}
	
	
}
