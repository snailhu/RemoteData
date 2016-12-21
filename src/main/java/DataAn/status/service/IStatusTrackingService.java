package DataAn.status.service;

import java.util.List;

import DataAn.common.dao.Pager;
import DataAn.status.domain.StatusTracking;
import DataAn.status.dto.StatusTrackingDTO;
import DataAn.status.dto.StatusYstepDTO;

public interface IStatusTrackingService {

	public void addStatusTracking(StatusTrackingDTO statusTrackingDTO) throws Exception;

	public void updateStatusTracking(String fileName, String statusType, String userType, String exceptionInfo)
			throws Exception;

	public void deleteStatusTracking(long trackingId) throws Exception;

	public Pager<StatusTrackingDTO> pageQueryStatusTracking(int pageIndex, int pageSize, String fileName,
			String userType, String statusType, String createdatetimeStart, String createdatetimeEnd) throws Exception;

	public StatusTracking getStatusTrackingById(long trackingId) throws Exception;

	public List<StatusYstepDTO> getSatusYstepList(String userType) throws Exception;
}
