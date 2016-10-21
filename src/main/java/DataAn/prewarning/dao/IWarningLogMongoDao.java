package DataAn.prewarning.dao;

import java.util.List;

import DataAn.prewarning.dto.QueryLogDTO;

public interface IWarningLogMongoDao {

	public void deleteWainingById(String logId, String series, String star, String parameterType, String warningType);

	public Long getNotReadCount(String series, String star, String parameterType, String parameter, String warningType);

	public List<QueryLogDTO> getQueryLogDTOs();

}
