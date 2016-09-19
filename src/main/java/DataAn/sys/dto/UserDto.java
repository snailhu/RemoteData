package DataAn.sys.dto;


public class UserDto {
	
	private long id;
	
	private String userName;

	// 用户密码(大写) SHA1(pwd+salt)
	private String passWord;

	// 密码盐度(大写)
	// public String PasswordSalt;

	// 性别，0女，1男
	private String gender;
	
	private long departmentId;

	// 真实姓名
	private String realName;

	// 电子邮箱
	private String email;

	// 手机号码
	private String mobile;

	// 用户状态，0正常，1禁用，N待扩展
	private String status ;

	// 创建人
	private String createUser;

	// 创建日期
	private String createDate;

	// 修改人
	private String updateUser;

	// 修改日期
	private String updateDate;
	
	private String userRole = "个人角色";

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public long getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(long departmentId) {
		this.departmentId = departmentId;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	public String getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}

	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

	@Override
	public String toString() {
		return "UserDto [id=" + id + ", userName=" + userName + ", passWord="
				+ passWord + ", gender=" + gender + ", departmentId="
				+ departmentId + ", realName=" + realName + ", email=" + email
				+ ", mobile=" + mobile + ", status=" + status + ", createUser="
				+ createUser + ", createDate=" + createDate + ", updateUser="
				+ updateUser + ", updateDate=" + updateDate + ", userRole="
				+ userRole + "]";
	}
	
}
