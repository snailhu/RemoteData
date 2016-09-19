package DataAn.sys.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class UserRoleId implements java.io.Serializable{

	/** serialVersionUID*/
	private static final long serialVersionUID = 1L;

	@Column(name = "userId", nullable = false)
	private Long userId;
	
	@Column(name = "roleId", nullable = false)
	private Long roleId;

	// Constructors

	/** default constructor */
	public UserRoleId() {
	}

	/** full constructor */
	public UserRoleId(Long userId, Long roleId) {
		this.userId = userId;
		this.roleId = roleId;
	}

	// Property accessors

	
	public Long getUserId() {
		return this.userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	
	public Long getRoleId() {
		return this.roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof UserRoleId))
			return false;
		UserRoleId castOther = (UserRoleId) other;

		return ((this.getUserId() == castOther.getUserId()) || (this
				.getUserId() != null && castOther.getUserId() != null && this
				.getUserId().equals(castOther.getUserId())))
				&& ((this.getRoleId() == castOther.getRoleId()) || (this
						.getRoleId() != null && castOther.getRoleId() != null && this
						.getRoleId().equals(castOther.getRoleId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getUserId() == null ? 0 : this.getUserId().hashCode());
		result = 37 * result
				+ (getRoleId() == null ? 0 : this.getRoleId().hashCode());
		return result;
	}

}