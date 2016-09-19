package DataAn.sys.domain;

import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * RoleAuth entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_role_auth")
public class RoleAuth implements java.io.Serializable {

	/** serialVersionUID*/
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "roleId", column = @Column(name = "roleId", nullable = false)),
			@AttributeOverride(name = "authId", column = @Column(name = "authId", nullable = false)) })
	private RoleAuthId id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "authId", nullable = false, insertable = false, updatable = false)
	private Auth auth;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "roleId", nullable = false, insertable = false, updatable = false)
	private Role role;
	
	@Column(name = "roleAuthId", nullable = true, length = 32)
	private String roleAuthId;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "createDate", nullable = true)
	private Date createDate;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updateDate", nullable = true)
	private Date updateDate;
	
	@Column(name = "version", nullable = true)
	private Integer version;

	
	public RoleAuthId getId() {
		return id;
	}

	public void setId(RoleAuthId id) {
		this.id = id;
	}

	public Auth getAuth() {
		return auth;
	}

	public void setAuth(Auth auth) {
		this.auth = auth;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String getRoleAuthId() {
		return roleAuthId;
	}

	public void setRoleAuthId(String roleAuthId) {
		this.roleAuthId = roleAuthId;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	
}