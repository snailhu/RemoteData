package DataAn.galaxyManager.dto;

public class DeviceDto {

	private Long deviceId;
	private String deviceName;
	private Long starId;
	private Long seriersId;
	private Long deviceType;
	private String endDate;

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

	public Long getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(Long deviceType) {
		this.deviceType = deviceType;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

}
