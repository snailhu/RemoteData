package DataAn.prewarning.dao;

import DataAn.common.dao.Pager;
import DataAn.prewarning.domain.WarningLog;

public interface IWarningLogMongoDao {

	public void deleteWainingById(String logId);

	public Pager<WarningLog> selectByOption(int pageIndex, int pageSize, String series, String star,
			String parameterType, String createdatetimeStart, String createdatetimeEnd, String warningType,
			String hadRead);

	public Long getNotReadCount(String series, String star, String parameterType, String parameter, String warningType);

}
