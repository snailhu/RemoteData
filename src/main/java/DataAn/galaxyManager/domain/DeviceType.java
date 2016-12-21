package DataAn.galaxyManager.domain;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Title: DeviceType
 * 
 * @Description: 一个设备类型的实体对象
 * @author wj
 * @date 2016年11月17日
 */
@Entity
@Table(name = "t_deviceType")
public class DeviceType {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "deviceTypeId", unique = true, nullable = false)
	private Long deviceTypeId;

	@Column(name = "deviceCode", nullable = true)
	private String deviceCode;

	@Column(name = "deviceName", nullable = true)
	private String deviceName;

	public Long getDeviceTypeId() {
		return deviceTypeId;
	}

	public void setDeviceTypeId(Long deviceTypeId) {
		this.deviceTypeId = deviceTypeId;
	}

	public String getDeviceCode() {
		return deviceCode;
	}

	public void setDeviceCode(String deviceCode) {
		this.deviceCode = deviceCode;
	}

	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

}
