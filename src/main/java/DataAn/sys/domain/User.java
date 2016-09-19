package DataAn.sys.domain;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * @author Shenwp
 *
 */
@Entity
@Table(name="t_user")
public class User implements java.io.Serializable {

	/** serialVersionUID*/
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "userId", unique = true, nullable = false)
	private Long userId;	//用户Id
	
	@Column(name = "userName", nullable = true,length=64)
	private String  userName;	//用户名
	
	@Column(name = "realName", nullable = true,length=64)
	private String realName; //真实名字
	
	@Column(name = "gender", nullable = true,length=4)
	private String  gender; //性别
	
	@Column(name = "email", nullable = true,length=128)	
	private String email; //邮箱
	
	@Column(name = "passWord", nullable = true,length=256)
	private String passWord; //登录密码
	
	@Column(name = "pwdSalt", nullable = true,length=128)
    private String pwdSalt;
	
	@Column(name = "pwsHash", nullable = true,length=128)
    private String pwsHash;
	
	@Column(name = "mobile", nullable = true,length = 11)
	private String mobile; //手机号码
	
	@Column(name = "description", nullable = true,length = 500)
	private String description;
	
	@Column(name = "createUser", nullable = true,length=64)
	private String createUser;
	
	@Column(name = "updateUser", nullable = true,length=64)
	private String updateUser;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "createDate", nullable = true)
	private Date createDate;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updateDate", nullable = true)
	private Date updateDate;
	
	@Column(name = "version", nullable = true)
	private Integer version;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
	private Set<DepartmentUser> departUsers = new HashSet<DepartmentUser>(0);
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
	private Set<UserRole> userRoles = new HashSet<UserRole>(0);

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public String getPwdSalt() {
		return pwdSalt;
	}

	public void setPwdSalt(String pwdSalt) {
		this.pwdSalt = pwdSalt;
	}

	public String getPwsHash() {
		return pwsHash;
	}

	public void setPwsHash(String pwsHash) {
		this.pwsHash = pwsHash;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public String getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
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

	public Set<DepartmentUser> getDepartUsers() {
		return departUsers;
	}

	public void setDepartUsers(Set<DepartmentUser> departUsers) {
		this.departUsers = departUsers;
	}

	public Set<UserRole> getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(Set<UserRole> userRoles) {
		this.userRoles = userRoles;
	}
	
	
}
