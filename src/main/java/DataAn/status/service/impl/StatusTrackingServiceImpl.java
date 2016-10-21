package DataAn.status.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import DataAn.common.dao.Pager;
import DataAn.common.utils.DateUtil;
import DataAn.status.dao.IStatusTrackingDao;
import DataAn.status.domain.StatusTracking;
import DataAn.status.dto.StatusTrackingDTO;
import DataAn.status.dto.StatusYstepDTO;
import DataAn.status.option.StatusTrackingType;
import DataAn.status.service.IStatusTrackingService;

@Service
public class StatusTrackingServiceImpl implements IStatusTrackingService {
	@Resource
	private IStatusTrackingDao statusTrackingDao;

	@Override
	public void addStatusTracking(StatusTrackingDTO statusTrackingDTO) throws Exception {
		StatusTracking statusTracking = new StatusTracking();
		statusTracking.setFileName(statusTrackingDTO.getFileName());
		statusTracking.setStatusType(statusTrackingDTO.getStatusType());
		statusTracking.setUserType(statusTrackingDTO.getUserType());
		statusTracking.setCreateDate(new Date());
		statusTrackingDao.add(statusTracking);
	}

	@Override
	public void updateStatusTracking(String fileName, String statusType, String userType) throws Exception {
		// 判断是否已存在
		boolean checkStatusTracking = statusTrackingDao.checkStatusTrackingByParams(fileName, userType);
		if (checkStatusTracking) {
			// 存在则更新
			StatusTracking statusTracking = statusTrackingDao.getStatusTrackingByParams(fileName, userType);
			statusTracking.setFileName(fileName);
			statusTracking.setStatusType(statusType);
			statusTracking.setUserType(userType);
			statusTrackingDao.update(statusTracking);
		} else {
			// 不存在则新增
			StatusTrackingDTO statusTrackingDTO = new StatusTrackingDTO();
			statusTrackingDTO.setFileName(fileName);
			statusTrackingDTO.setUserType(userType);
			statusTrackingDTO.setStatusType(statusType);
			addStatusTracking(statusTrackingDTO);
		}
	}

	@Override
	public void deleteStatusTracking(long trackingId) throws Exception {
		statusTrackingDao.delete(trackingId);
	}

	@Override
	public Pager<StatusTrackingDTO> pageQueryStatusTracking(int pageIndex, int pageSize, String fileName,
			String userType, String statusType, String createdatetimeStart, String createdatetimeEnd) throws Exception {
		List<StatusTrackingDTO> statusTrackingDTOs = new ArrayList<StatusTrackingDTO>();
		Pager<StatusTracking> stPager = statusTrackingDao.selectByOption(pageIndex, pageSize, fileName, userType,
				statusType, createdatetimeStart, createdatetimeEnd);
		List<StatusTracking> statusTrackings = stPager.getDatas();
		if (statusTrackings != null && statusTrackings.size() > 0) {
			for (StatusTracking statusTracking : statusTrackings) {
				StatusTrackingDTO statusTrackingDTO = new StatusTrackingDTO();
				statusTrackingDTO.setCreateDate(DateUtil.format(statusTracking.getCreateDate()));
				statusTrackingDTO.setFileName(statusTracking.getFileName());
				statusTrackingDTO.setStatusType(
						StatusTrackingType.getStatusTrackingType(statusTracking.getStatusType()).getName());
				statusTrackingDTO.setTrackingId(statusTracking.getTrackingId());
				statusTrackingDTO.setUserType(statusTracking.getUserType());
				statusTrackingDTOs.add(statusTrackingDTO);
			}
		}
		Pager<StatusTrackingDTO> pager = new Pager<StatusTrackingDTO>(pageSize, pageIndex, stPager.getTotalCount(),
				statusTrackingDTOs);
		return pager;
	}

	@Override
	public StatusTracking getStatusTrackingById(long trackingId) throws Exception {
		return statusTrackingDao.get(trackingId);
	}

	@Override
	public List<StatusYstepDTO> getSatusYstepList(String userType) throws Exception {
		List<StatusTracking> statusTrackings = statusTrackingDao.getStatusTrackingByParams(userType);
		List<StatusYstepDTO> statusYstepDTOs = new ArrayList<StatusYstepDTO>();
		for (StatusTracking statusTracking : statusTrackings) {
			StatusYstepDTO statusYstepDTO = new StatusYstepDTO();
			statusYstepDTO.setFileName(statusTracking.getFileName());
			statusYstepDTO.setStatusType(statusTracking.getStatusType());
			statusYstepDTOs.add(statusYstepDTO);
		}
		return statusYstepDTOs;
	}

}
