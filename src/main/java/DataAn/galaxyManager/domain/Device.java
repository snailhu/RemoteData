package DataAn.galaxyManager.domain;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Title: Star
 * 
 * @Description: 一个设备的实体对象
 * @author wj
 * @date 2016年11月15日
 */
@Entity
@Table(name = "t_device")
public class Device {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "deviceId", unique = true, nullable = false)
	private Long deviceId;

	@Column(name = "deviceName", nullable = true)
	private String deviceName;

	@Column(name = "starId", nullable = true)
	private Long starId;

	@Column(name = "seriersId", nullable = true)
	private Long seriersId;

	@Column(name = "deviceType", nullable = true)
	private Long deviceType;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "startDate", nullable = true)
	private Date startDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "endDate", nullable = true)
	private Date endDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "createDate", nullable = true)
	private Date createDate = new Date();

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "editDate", nullable = true)
	private Date editDate;

	public Long getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(Long deviceId) {
		this.deviceId = deviceId;
	}

	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	public Long getStarId() {
		return starId;
	}

	public void setStarId(Long starId) {
		this.starId = starId;
	}

	public Long getSeriersId() {
		return seriersId;
	}

	public void setSeriersId(Long seriersId) {
		this.seriersId = seriersId;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Long getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(Long deviceType) {
		this.deviceType = deviceType;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getEditDate() {
		return editDate;
	}

	public void setEditDate(Date editDate) {
		this.editDate = editDate;
	}
}
