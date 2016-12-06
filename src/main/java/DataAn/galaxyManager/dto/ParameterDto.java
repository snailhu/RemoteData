package DataAn.galaxyManager.dto;

import java.io.Serializable;

public class ParameterDto implements Serializable {

	/**
	 * serialVersionUID:TODO(用一句话描述这个变量表示什么).
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	private String series; // 系列 j9

	private String star; // 星 01、02、03...

	private String fullName; // 参数全称如， F10W111:飞轮电流Xa(00814)

	private String simplyName; // 参数简称 如， 飞轮电流Xa(00814)

	private String code; // 码： sequence_00814
	
	private String deviceTypeCode;// 设备类型Code flywheel、top
	
	private String deviceTypeName;// 设备类型名称 飞轮、陀螺

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSeries() {
		return series;
	}

	public void setSeries(String series) {
		this.series = series;
	}

	public String getStar() {
		return star;
	}

	public void setStar(String star) {
		this.star = star;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getSimplyName() {
		return simplyName;
	}

	public void setSimplyName(String simplyName) {
		this.simplyName = simplyName;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDeviceTypeCode() {
		return deviceTypeCode;
	}

	public void setDeviceTypeCode(String deviceTypeCode) {
		this.deviceTypeCode = deviceTypeCode;
	}

	public String getDeviceTypeName() {
		return deviceTypeName;
	}

	public void setDeviceTypeName(String deviceTypeName) {
		this.deviceTypeName = deviceTypeName;
	}

	@Override
	public String toString() {
		return "ParameterDto [id=" + id + ", series=" + series + ", star="
				+ star + ", fullName="
				+ fullName + ", simplyName=" + simplyName + ", code=" + code
				+ ", deviceTypeCode=" + deviceTypeCode + ", deviceTypeName="
				+ deviceTypeName + "]";
	}

	

}
