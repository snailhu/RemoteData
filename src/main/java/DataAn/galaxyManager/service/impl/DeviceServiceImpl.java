package DataAn.galaxyManager.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import DataAn.common.dao.Pager;
import DataAn.common.pageModel.Combo;
import DataAn.common.utils.DateUtil;
import DataAn.galaxyManager.dao.IDeviceDao;
import DataAn.galaxyManager.dao.IDeviceTypeDao;
import DataAn.galaxyManager.dao.ISeriesDao;
import DataAn.galaxyManager.dao.IStarDao;
import DataAn.galaxyManager.domain.Device;
import DataAn.galaxyManager.domain.DeviceType;
import DataAn.galaxyManager.domain.Star;
import DataAn.galaxyManager.dto.DeviceDto;
import DataAn.galaxyManager.dto.DeviceViewDTO;
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
	@Resource
	private ISeriesDao seriesDao;

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
		device.setModel(deviceDto.getModel());
		device.setRunDays(0);
		device.setRunStatus(1);
		deviceDao.add(device);
	}

	@Override
	public void updateDevice(DeviceDto deviceDto) throws Exception {
		Device device = deviceDao.get(deviceDto.getDeviceId());
		if (StringUtils.isNotBlank(deviceDto.getModel())) {
			device.setModel(deviceDto.getModel());
		}
		if (StringUtils.isNotBlank(deviceDto.getStartDate())) {
			device.setStartDate(DateUtil.format(deviceDto.getStartDate(), "yyyy-MM-dd"));
		}
		if (StringUtils.isNotBlank(deviceDto.getEndDate())) {
			device.setEndDate(DateUtil.format(deviceDto.getEndDate(), "yyyy-MM-dd"));
		}
		if (deviceDto.getRunStatus() != device.getRunStatus()) {
			device.setRunStatus(deviceDto.getRunStatus());
			if (device.getRunStatus() == 0) {
				int rundays = DateUtil.daysOfTwo(device.getStartDate(), device.getEndDate());
				int beforeRundays = device.getRunDays();
				device.setRunDays(beforeRundays + (rundays - device.getLastDays()));
				device.setLastDays(rundays);
			} else {
				device.setLastDays(0);
			}
		} else {
			if (device.getRunStatus() == 0) {
				int rundays = DateUtil.daysOfTwo(device.getStartDate(), device.getEndDate());
				int beforeRundays = device.getRunDays();
				device.setRunDays(beforeRundays + (rundays - device.getLastDays()));
				device.setLastDays(rundays);
			}
		}
		if (StringUtils.isNotBlank(deviceDto.getDeviceName())) {
			device.setDeviceName(deviceDto.getDeviceName());
		}
		deviceDao.update(device);
	}

	@Override
	public Pager<QueryDeviceTypeDTO> pageQueryDeviceType(int pageIndex, int pageSize, String series, String star,
			String userType) throws Exception {
		List<QueryDeviceTypeDTO> deviceTypeDTOs = new ArrayList<QueryDeviceTypeDTO>();
		Pager<DeviceType> devicePager = deviceTypeDao.selectByPager(pageIndex, pageSize, userType);
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
	public List<QueryDeviceDTO> getDeviceByParam(String series, String star, String deviceType, String model)
			throws Exception {
		List<QueryDeviceDTO> deviceDtos = new ArrayList<QueryDeviceDTO>();
		List<Device> devices = deviceDao.getDevicesBySeriesAndStar(series, star, deviceType, model);
		for (Device device : devices) {
			QueryDeviceDTO deviceDto = new QueryDeviceDTO();
			deviceDto.setDeviceId(device.getDeviceId());
			deviceDto.setDeviceName(device.getDeviceName());
			deviceDto.setDeviceType(device.getDeviceType());
			int totalDays = 0;
			if (device.getRunStatus() == 0) {
				totalDays += device.getRunDays();
			} else if (device.getRunStatus() == 1) {
				int days = DateUtil.daysOfTwo(device.getStartDate(), new Date());
				days += device.getRunDays();
				totalDays += days;
			}
			if (device.getEndDate() != null) {
				deviceDto.setEndDate(DateUtil.format(device.getEndDate(), "yyyy-MM-dd"));
			}
			deviceDto.setSeriersId(device.getSeriersId());
			deviceDto.setStarId(device.getStarId());
			deviceDto.setStartDate(DateUtil.format(device.getStartDate(), "yyyy-MM-dd"));
			deviceDto.setRunDays(totalDays);
			deviceDto.setModel(device.getModel());
			deviceDto.setRunStatus(device.getRunStatus());
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
		int totalDays = 0;
		if (device.getRunStatus() == 0) {
			totalDays += device.getRunDays();
		} else if (device.getRunStatus() == 1) {
			int days = DateUtil.daysOfTwo(device.getStartDate(), new Date());
			days += device.getRunDays();
			totalDays += days;
		}
		if (device.getEndDate() != null) {
			deviceDto.setEndDate(DateUtil.format(device.getEndDate(), "yyyy-MM-dd"));
		}
		deviceDto.setSeriersId(device.getSeriersId());
		deviceDto.setStarId(device.getStarId());
		deviceDto.setStartDate(DateUtil.format(device.getStartDate(), "yyyy-MM-dd"));
		deviceDto.setRunDays(totalDays);
		deviceDto.setModel(device.getModel());
		deviceDto.setRunStatus(device.getRunStatus());
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

	@Override
	public Map<String, String> getAllDeviceTypeMap() {
		Map<String, String> map = new HashMap<String, String>();
		List<DeviceType> list = deviceTypeDao.findAll();
		if (list != null && list.size() > 0) {
			for (DeviceType deviceType : list) {
				map.put(deviceType.getDeviceCode(), deviceType.getDeviceName());
			}
		}
		return map;
	}

	@Override
	public Pager<DeviceViewDTO> pageDeviceViewDTO(int pageIndex, int pageSize, String deviceType, String model)
			throws Exception {
		Pager<DeviceViewDTO> pager = deviceDao.selectViewDTObyPager(deviceType, model, pageIndex, pageSize);
		for (DeviceViewDTO deviceViewDTO : pager.getDatas()) {
			Long seriesId = deviceViewDTO.getSeriersId();
			Long starId = deviceViewDTO.getStarId();
			deviceViewDTO.setDeviceNum(
					deviceDao.getTotalNumByStarAndModel(seriesId.toString(), starId.toString(), model, deviceType));
			deviceViewDTO.setDeviceTypeId(Long.parseLong(deviceType));
			deviceViewDTO.setRunDays(
					deviceDao.getTotalRuntimeByStarAndModel(seriesId.toString(), starId.toString(), model, deviceType));
			deviceViewDTO.setSeriesName(seriesDao.get(seriesId).getName());
			deviceViewDTO.setStarName(starDao.get(starId).getName());
			deviceViewDTO.setDeviceTypeName(deviceTypeDao.get(Long.parseLong(deviceType)).getDeviceName());
		}
		return pager;
	}

}
