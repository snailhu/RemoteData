package DataAn.sys.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;


@Embeddable
public class DepartmentUserId implements java.io.Serializable{


	/** serialVersionUID*/
	private static final long serialVersionUID = 1L;

	@Column(name = "userId", nullable = false)
	private Long userId;
	
	@Column(name = "departmentId", nullable = false)
	private Long departmentId;

	// Constructors

	/** default constructor */
	public DepartmentUserId() {
	}

	/** full constructor */
	public DepartmentUserId(Long userId, Long departmentId) {
		this.userId = userId;
		this.departmentId = departmentId;
	}

	// Property accessors

	
	public Long getUserId() {
		return this.userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	
	public Long getDepartmentId() {
		return this.departmentId;
	}

	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof DepartmentUserId))
			return false;
		DepartmentUserId castOther = (DepartmentUserId) other;

		return ((this.getUserId() == castOther.getUserId()) || (this
				.getUserId() != null && castOther.getUserId() != null && this
				.getUserId().equals(castOther.getUserId())))
				&& ((this.getDepartmentId() == castOther.getDepartmentId()) || (this
						.getDepartmentId() != null
						&& castOther.getDepartmentId() != null && this
						.getDepartmentId().equals(castOther.getDepartmentId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getUserId() == null ? 0 : this.getUserId().hashCode());
		result = 37
				* result
				+ (getDepartmentId() == null ? 0 : this.getDepartmentId()
						.hashCode());
		return result;
	}

}