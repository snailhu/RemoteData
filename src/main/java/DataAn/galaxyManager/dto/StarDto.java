package DataAn.galaxyManager.dto;

public class StarDto {

	private long id;
	
	private String name;
	
	private String description;
	
	private String startRunDate;

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStartRunDate() {
		return startRunDate;
	}

	public void setStartRunDate(String startRunDate) {
		this.startRunDate = startRunDate;
	}
	
	
}
