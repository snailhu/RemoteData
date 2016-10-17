package DataAn.status.dao;

import java.util.List;

import DataAn.common.dao.IBaseDao;
import DataAn.common.dao.Pager;
import DataAn.status.domain.StatusTracking;

public interface IStatusTrackingDao extends IBaseDao<StatusTracking> {

	public List<StatusTracking> getStatusTrackingByParams(String userType);

	public Pager<StatusTracking> selectByOption(int pageIndex, int pageSize, String fileName, String userType,
			String statusType, String createdatetimeStart, String createdatetimeEnd);

}
