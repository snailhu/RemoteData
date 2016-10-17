package DataAn.status.dto;

/**
 * @author wj
 *
 */
public class StatusYstepDTO implements java.io.Serializable {
	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	private String fileName; // 文件名

	private String statusType; // 所处状态

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

}
