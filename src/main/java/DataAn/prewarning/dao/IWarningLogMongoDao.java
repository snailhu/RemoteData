package DataAn.prewarning.dao;

import java.util.List;

import DataAn.common.dao.Pager;
import DataAn.prewarning.dto.QueryLogDTO;

public interface IWarningLogMongoDao {

	public void deleteWainingById(String logId, String series, String star, String parameterType, String warningType,String HadRead);

	public Long getNotReadCount(String series, String star, String parameterType, String parameter, String warningType);

	public List<QueryLogDTO> getQueryLogDTOs();

	public Pager<QueryLogDTO> selectByOption(int pageIndex, int pageSize, String series, String star,
			String parameterType, String parameter, String createdatetimeStart, String createdatetimeEnd,
			String warningType, String hadRead);

	public void readAllWarningLog();

}
