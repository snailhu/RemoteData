package DataAn.log.domain;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "t_systemlog", catalog = "mongoyp")
public class SystemLog {
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;
	
	private Integer UserId;
	
	@Column(length = 32,nullable = true)
	private String userName;
	
	@Column(length = 32,nullable = true)
	private String loginIp;
	
	@Column(length = 32,nullable = true)
	@Temporal(TemporalType.TIMESTAMP) 
	private Date loginTime;
	
	@Column(length = 32,nullable = true)
	@Temporal(TemporalType.TIMESTAMP) 
	private Date logoutTime;
	
	@Column(length = 250,nullable = true)
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

	
	public Date getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}

	public Date getLogoutTime() {
		return logoutTime;
	}

	public void setLogoutTime(Date logoutTime) {
		this.logoutTime = logoutTime;
	}

	public String getOperateJob() {
		return operateJob;
	}

	public void setOperateJob(String operateJob) {
		this.operateJob = operateJob;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
			
}
