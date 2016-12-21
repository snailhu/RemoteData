package DataAn.status.dto;

/**
 * @author wj
 *
 */
public class StatusTrackingDTO implements java.io.Serializable {
	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	private Long trackingId; // 跟踪记录Id

	private String fileName; // 文件名

	private String userType; // 用户类型

	private String createDate;

	private String statusType; // 所处状态（00 文件上传成功 01 过滤噪点成功 02.。。。。 ）

	private String exceptionInfo;

	public String getExceptionInfo() {
		return exceptionInfo;
	}

	public void setExceptionInfo(String exceptionInfo) {
		this.exceptionInfo = exceptionInfo;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public Long getTrackingId() {
		return trackingId;
	}

	public void setTrackingId(Long trackingId) {
		this.trackingId = trackingId;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getStatusType() {
		return statusType;
	}

	public void setStatusType(String statusType) {
		this.statusType = statusType;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

}
