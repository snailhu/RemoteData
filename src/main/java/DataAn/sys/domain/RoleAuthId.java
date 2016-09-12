package DataAn.sys.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class RoleAuthId implements java.io.Serializable {

	/** serialVersionUID*/
	private static final long serialVersionUID = 1L;

	@Column(name = "roleId", nullable = false)
	private Long roleId;
	
	@Column(name = "authId", nullable = false)
	private Long authId;

	// Constructors

	/** default constructor */
	public RoleAuthId() {
	}

	/** full constructor */
	public RoleAuthId(Long roleId, Long authId) {
		this.roleId = roleId;
		this.authId = authId;
	}

	// Property accessors

	
	public Long getRoleId() {
		return this.roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	
	public Long getAuthId() {
		return this.authId;
	}

	public void setAuthId(Long authId) {
		this.authId = authId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof RoleAuthId))
			return false;
		RoleAuthId castOther = (RoleAuthId) other;

		return ((this.getRoleId() == castOther.getRoleId()) || (this
				.getRoleId() != null && castOther.getRoleId() != null && this
				.getRoleId().equals(castOther.getRoleId())))
				&& ((this.getAuthId() == castOther.getAuthId()) || (this
						.getAuthId() != null && castOther.getAuthId() != null && this
						.getAuthId().equals(castOther.getAuthId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getRoleId() == null ? 0 : this.getRoleId().hashCode());
		result = 37 * result
				+ (getAuthId() == null ? 0 : this.getAuthId().hashCode());
		return result;
	}

}