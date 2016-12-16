package DataAn.galaxyManager.dto;

/**
 * @author wj
 *
 */
public class QueryDeviceDTO implements java.io.Serializable {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	private Long deviceId;
	private String deviceName;
	private Long starId;
	private Long seriersId;
	private Long deviceType;
	private String startDate;
	private String endDate;
	private int runDays;
	private int runStatus;
	private String model;

	public int getRunStatus() {
		return runStatus;
	}

	public void setRunStatus(int runStatus) {
		this.runStatus = runStatus;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public int getRunDays() {
		return runDays;
	}

	public void setRunDays(int runDays) {
		this.runDays = runDays;
	}

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

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

}
