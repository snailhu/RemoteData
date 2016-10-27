package DataAn.status.domain;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author wj
 *
 */
@Entity
@Table(name = "t_statustracking")
public class StatusTracking implements java.io.Serializable {
	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "trackingId", unique = true, nullable = false)
	private Long trackingId; // 跟踪记录Id

	@Column(name = "fileName", nullable = true)
	private String fileName; // 文件名

	@Column(name = "userType", nullable = true)
	private String userType; // 所属用户

	@Column(name = "statusType", nullable = true)
	private String statusType; // 所处状态

	@Column(name = "exceptionInfo", nullable = true)
	private String exceptionInfo; // 异常详情

	@Column(name = "createDate", nullable = true)
	private Date createDate;

	public String getExceptionInfo() {
		return exceptionInfo;
	}

	public void setExceptionInfo(String exceptionInfo) {
		this.exceptionInfo = exceptionInfo;
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

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

}
