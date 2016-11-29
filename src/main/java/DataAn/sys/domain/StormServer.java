package DataAn.sys.domain;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import DataAn.sys.dto.StormSysStatus;

@Entity
@Table(name="t_stormServer")
public class StormServer implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "userId", unique = true, nullable = false)
	private long id;
	
	@Column(name = "serverIp", nullable = true,length=64)
	private String serverIp;//服务器IP
	
	@Enumerated(EnumType.STRING)
	@Column(name = "status", nullable = true,length=16)
	private StormSysStatus status; // 服务器状态

	@Column(name = "description", nullable = true,length=256)
	private String description;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "createDate", nullable = true)
	private Date createDate = new Date();
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getServerIp() {
		return serverIp;
	}

	public void setServerIp(String serverIp) {
		this.serverIp = serverIp;
	}
	
	public StormSysStatus getStatus() {
		return status;
	}

	public void setStatus(StormSysStatus status) {
		this.status = status;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	
}
