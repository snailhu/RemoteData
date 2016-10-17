package DataAn.prewarning.dao;

import java.util.List;

import DataAn.common.dao.IBaseDao;
import DataAn.common.dao.Pager;
import DataAn.prewarning.domain.WarningLog;

public interface IWarningLogDao extends IBaseDao<WarningLog> {

	public List<WarningLog> getWarningLogByParams(String series, String parameterType, String parameter,
			String warningType, String hadRead);

	public Pager<WarningLog> selectByOption(int pageIndex, int pageSize, String series, String parameterType,
			String createdatetimeStart, String createdatetimeEnd, String warningType, String hadRead);

	public Long getNotReadCount(String series, String parameterType, String parameter, String warningType);

}
