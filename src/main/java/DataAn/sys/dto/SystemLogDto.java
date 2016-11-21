package DataAn.sys.dto;


public class SystemLogDto {
		
	private String userName;
	
	private String loginIp;	
	
	private String operateTime;

	private String operateJob;

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

	public String getOperateTime() {
		return operateTime;
	}

	public void setOperateTime(String operateTime) {
		this.operateTime = operateTime;
	}

	public String getOperateJob() {
		return operateJob;
	}

	public void setOperateJob(String operateJob) {
		this.operateJob = operateJob;
	}
	
}
