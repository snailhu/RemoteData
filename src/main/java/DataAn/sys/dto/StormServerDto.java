package DataAn.sys.dto;

public class StormServerDto {

	private long id;
	
	private String serverIp;
	
	private String status;
	
	private String description;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getServerIp() {
		return serverIp;
	}

	public void setServerIp(String serverIp) {
		this.serverIp = serverIp;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "StormServerDto [id=" + id + ", serverIp=" + serverIp
				+ ", status=" + status + ", description=" + description + "]";
	}
	
	
}
