package DataAn.reportManager.dto;

public class MongoFSDto {

	private long id;
	
	private String name;
	
	private String type;  // dir\file
	
	private String fileType;
	
	private String fileSize;
	
	private String createDate;
	
	private String mongoFSUUId;

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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public String getFileSize() {
		return fileSize;
	}

	public void setFileSize(String fileSize) {
		this.fileSize = fileSize;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getMongoFSUUId() {
		return mongoFSUUId;
	}

	public void setMongoFSUUId(String mongoFSUUId) {
		this.mongoFSUUId = mongoFSUUId;
	}

	@Override
	public String toString() {
		return "MongoFSDto [id=" + id + ", name=" + name + ", type=" + type
				+ ", fileType=" + fileType + ", fileSize=" + fileSize
				+ ", createDate=" + createDate + ", mongoFSUUId=" + mongoFSUUId
				+ "]";
	}
	
	
}
