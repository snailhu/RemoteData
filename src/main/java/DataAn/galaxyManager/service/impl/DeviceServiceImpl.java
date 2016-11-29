package DataAn.galaxyManager.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import DataAn.common.dao.Pager;
import DataAn.common.pageModel.Combo;
import DataAn.common.utils.DateUtil;
import DataAn.galaxyManager.dao.IDeviceDao;
import DataAn.galaxyManager.dao.IDeviceTypeDao;
import DataAn.galaxyManager.dao.IStarDao;
import DataAn.galaxyManager.domain.Device;
import DataAn.galaxyManager.domain.DeviceType;
import DataAn.galaxyManager.domain.Star;
import DataAn.galaxyManager.dto.DeviceDto;
import DataAn.galaxyManager.dto.QueryDeviceDTO;
import DataAn.galaxyManager.dto.QueryDeviceTypeDTO;
import DataAn.galaxyManager.service.IDeviceService;

@Service
public class DeviceServiceImpl implements IDeviceService {
	@Resource
	private IDeviceDao deviceDao;
	@Resource
	private IDeviceTypeDao deviceTypeDao;
	@Resource
	private IStarDao starDao;

	@Override
	public void addDevice(DeviceDto deviceDto) throws Exception {
		Device device = new Device();
		device.setCreateDate(new Date());
		device.setDeviceName(deviceDto.getDeviceName());
		device.setEditDate(new Date());
		// device.setEndDate(DateUtil.format(deviceDto.getEndDate(),
		// "yyyy-MM-dd"));
		device.setSeriersId(deviceDto.getSeriersId());
		device.setStarId(deviceDto.getStarId());
		Star star = starDao.get(deviceDto.getStarId());
		device.setStartDate(star.getStartRunDate());
		device.setDeviceType(deviceDto.getDeviceType());
		deviceDao.add(device);
	}

	@Override
	public void updateDevice(DeviceDto deviceDto) throws Exception {
		Device device = deviceDao.get(deviceDto.getDeviceId());
		if (StringUtils.isNotBlank(deviceDto.getEndDate())) {
			device.setEndDate(DateUtil.format(deviceDto.getEndDate(), "yyyy-MM-dd"));
		}
		if (StringUtils.isNotBlank(deviceDto.getDeviceName())) {
			device.setDeviceName(deviceDto.getDeviceName());
		}
		deviceDao.update(device);
	}

	@Override
	public Pager<QueryDeviceTypeDTO> pageQueryDeviceType(int pageIndex, int pageSize, String series, String star)
			throws Exception {
		List<QueryDeviceTypeDTO> deviceTypeDTOs = new ArrayList<QueryDeviceTypeDTO>();
		Pager<DeviceType> devicePager = deviceTypeDao.selectByPager(pageIndex, pageSize);
		List<DeviceType> deviceTypes = devicePager.getDatas();
		if (deviceTypes != null && deviceTypes.size() > 0) {
			for (DeviceType deviceType : deviceTypes) {
				QueryDeviceTypeDTO deviceTypeDTO = new QueryDeviceTypeDTO();
				deviceTypeDTO.setDeviceCode(deviceType.getDeviceCode());
				deviceTypeDTO.setDeviceName(deviceType.getDeviceName());
				deviceTypeDTO.setDeviceTypeId(deviceType.getDeviceTypeId());
				int rundays = deviceDao.getTotalRuntimeByDeviceType(series, star,
						deviceType.getDeviceTypeId().toString());
				deviceTypeDTO.setRunDays(rundays);
				deviceTypeDTOs.add(deviceTypeDTO);
			}
		}
		Pager<QueryDeviceTypeDTO> pager = new Pager<QueryDeviceTypeDTO>(pageSize, pageIndex,
				devicePager.getTotalCount(), deviceTypeDTOs);
		return pager;
	}

	@Override
	public List<QueryDeviceDTO> getDeviceByParam(String series, String star, String deviceType) throws Exception {
		List<QueryDeviceDTO> deviceDtos = new ArrayList<QueryDeviceDTO>();
		List<Device> devices = deviceDao.getDevicesBySeriesAndStar(series, star, deviceType);
		for (Device device : devices) {
			QueryDeviceDTO deviceDto = new QueryDeviceDTO();
			deviceDto.setDeviceId(device.getDeviceId());
			deviceDto.setDeviceName(device.getDeviceName());
			deviceDto.setDeviceType(device.getDeviceType());
			int rundays = DateUtil.daysOfTwo(device.getStartDate(), new Date());
			if (device.getEndDate() != null) {
				deviceDto.setEndDate(DateUtil.format(device.getEndDate(), "yyyy-MM-dd"));
				rundays = DateUtil.daysOfTwo(device.getStartDate(), device.getEndDate());
			}
			deviceDto.setSeriersId(device.getSeriersId());
			deviceDto.setStarId(device.getStarId());
			deviceDto.setStartDate(DateUtil.format(device.getStartDate(), "yyyy-MM-dd"));
			deviceDto.setRunDays(rundays);
			deviceDtos.add(deviceDto);
		}
		return deviceDtos;
	}

	@Override
	public List<DeviceType> getDeviceTypeList() throws Exception {
		List<DeviceType> deviceTypes = deviceTypeDao.getDeviceTypeList();
		return deviceTypes;
	}

	@Override
	public boolean checkDevice(String series, String star, String deviceType, String deviceName) throws Exception {
		return deviceDao.checkDevice(series, star, deviceType, deviceName);
	}

	@Override
	public QueryDeviceDTO getDeviceById(Long deviceId) throws Exception {
		Device device = deviceDao.get(deviceId);
		QueryDeviceDTO deviceDto = new QueryDeviceDTO();
		deviceDto.setDeviceId(device.getDeviceId());
		deviceDto.setDeviceName(device.getDeviceName());
		deviceDto.setDeviceType(device.getDeviceType());
		int rundays = DateUtil.daysOfTwo(device.getStartDate(), new Date());
		if (device.getEndDate() != null) {
			deviceDto.setEndDate(DateUtil.format(device.getEndDate(), "yyyy-MM-dd"));
			rundays = DateUtil.daysOfTwo(device.getStartDate(), device.getEndDate());
		}
		deviceDto.setSeriersId(device.getSeriersId());
		deviceDto.setStarId(device.getStarId());
		deviceDto.setStartDate(DateUtil.format(device.getStartDate(), "yyyy-MM-dd"));
		deviceDto.setRunDays(rundays);
		return deviceDto;
	}

	@Override
	public void deleteDevice(Long deviceId) throws Exception {
		deviceDao.delete(deviceId);
	}

	@Override
	public List<Combo> getDeviceTypeComboData(String deviceTypeCode) {
		List<Combo> comboList = new ArrayList<Combo>();
		List<DeviceType> list = deviceTypeDao.findAll();
		if (list != null && list.size() > 0) {
			Combo combo = null;
			for (DeviceType deviceType : list) {
				combo = new Combo();
				combo.setText(deviceType.getDeviceName());
				combo.setValue(deviceType.getDeviceCode());
				if (deviceType.getDeviceCode().equals(deviceTypeCode)) {
					combo.setSelected(true);
				}
				comboList.add(combo);
			}
		}
		return comboList;
	}

}
