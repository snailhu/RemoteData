package DataAn.galaxyManager.dto;

/**
 * @author wj
 *
 */
public class QueryDeviceTypeDTO implements java.io.Serializable {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	private Long deviceTypeId;
	private String deviceCode;
	private String deviceName;
	private int runDays;

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

	public int getRunDays() {
		return runDays;
	}

	public void setRunDays(int runDays) {
		this.runDays = runDays;
	}

}
