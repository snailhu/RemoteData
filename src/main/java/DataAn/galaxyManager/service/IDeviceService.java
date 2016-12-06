package DataAn.galaxyManager.service;

import java.util.List;

import DataAn.common.dao.Pager;
import DataAn.common.pageModel.Combo;
import DataAn.galaxyManager.domain.DeviceType;
import DataAn.galaxyManager.dto.DeviceDto;
import DataAn.galaxyManager.dto.QueryDeviceDTO;
import DataAn.galaxyManager.dto.QueryDeviceTypeDTO;

public interface IDeviceService {
	public void addDevice(DeviceDto deviceDto) throws Exception;

	public void updateDevice(DeviceDto deviceDto) throws Exception;

	public Pager<QueryDeviceTypeDTO> pageQueryDeviceType(int pageIndex, int pageSize, String series, String star,
			String userType) throws Exception;

	public List<QueryDeviceDTO> getDeviceByParam(String series, String star, String deviceType) throws Exception;

	public List<DeviceType> getDeviceTypeList() throws Exception;

	public boolean checkDevice(String series, String star, String deviceType, String deviceName) throws Exception;

	public QueryDeviceDTO getDeviceById(Long deviceId) throws Exception;

	public void deleteDevice(Long deviceId) throws Exception;

	public List<Combo> getDeviceTypeComboData(String deviceTypeCode);

}
