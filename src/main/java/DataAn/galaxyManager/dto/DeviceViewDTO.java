package DataAn.galaxyManager.dto;

public class DeviceViewDTO {
	private Long seriersId;
	private Long starId;
	private Long deviceTypeId;
	private String seriesName;
	private String starName;
	private String deviceTypeName;
	private Long deviceNum;
	private int runDays;

	public String getDeviceTypeName() {
		return deviceTypeName;
	}

	public void setDeviceTypeName(String deviceTypeName) {
		this.deviceTypeName = deviceTypeName;
	}

	public Long getSeriersId() {
		return seriersId;
	}

	public void setSeriersId(Long seriersId) {
		this.seriersId = seriersId;
	}

	public Long getStarId() {
		return starId;
	}

	public void setStarId(Long starId) {
		this.starId = starId;
	}

	public Long getDeviceTypeId() {
		return deviceTypeId;
	}

	public void setDeviceTypeId(Long deviceTypeId) {
		this.deviceTypeId = deviceTypeId;
	}

	public String getSeriesName() {
		return seriesName;
	}

	public void setSeriesName(String seriesName) {
		this.seriesName = seriesName;
	}

	public String getStarName() {
		return starName;
	}

	public void setStarName(String starName) {
		this.starName = starName;
	}

	public Long getDeviceNum() {
		return deviceNum;
	}

	public void setDeviceNum(Long deviceNum) {
		this.deviceNum = deviceNum;
	}

	public int getRunDays() {
		return runDays;
	}

	public void setRunDays(int runDays) {
		this.runDays = runDays;
	}

}
