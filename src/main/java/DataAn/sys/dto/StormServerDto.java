package DataAn.sys.dto;

public class StormServerDto {

	private long id;
	
	private String serverIp;
	
	private String statusName;
	
	private String statusValue;
	
	private String createDate;

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

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public String getStatusValue() {
		return statusValue;
	}

	public void setStatusValue(String statusValue) {
		this.statusValue = statusValue;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	@Override
	public String toString() {
		return "StormServerDto [id=" + id + ", serverIp=" + serverIp
				+ ", statusName=" + statusName + ", statusValue=" + statusValue
				+ ", createDate=" + createDate + "]";
	}

}
