package DataAn.log.dto;


public class SystemLogDto {
		
	private Integer UserId;
	
	private String userName;
	
	private String loginIp;	

	private String loginTime;
	
	private String logoutTime;

	private String operateJob;

	public Integer getUserId() {
		return UserId;
	}

	public void setUserId(Integer userId) {
		UserId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getLoginIp() {
		return loginIp;
	}

	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}

	public String getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(String loginTime) {
		this.loginTime = loginTime;
	}

	public String getLogoutTime() {
		return logoutTime;
	}

	public void setLogoutTime(String logoutTime) {
		this.logoutTime = logoutTime;
	}

	public String getOperateJob() {
		return operateJob;
	}

	public void setOperateJob(String operateJob) {
		this.operateJob = operateJob;
	}
	
	
}
