package DataAn.prewarning.service;

import java.util.List;

import DataAn.common.dao.Pager;
import DataAn.galaxyManager.domain.Star;
import DataAn.prewarning.domain.WarningLog;
import DataAn.prewarning.domain.WarningValue;
import DataAn.prewarning.dto.ErrorValueDTO;
import DataAn.prewarning.dto.QueryLogDTO;
import DataAn.prewarning.dto.QueryValueDTO;
import DataAn.prewarning.dto.SelectOptionDTO;
import DataAn.prewarning.dto.WarnValueDTO;
import DataAn.prewarning.dto.WarningLogDTO;

public interface IPrewarningService {

	public void addErrorValue(ErrorValueDTO errorValueDTO) throws Exception;

	public void addWarnValue(WarnValueDTO warnValueDTO) throws Exception;

	public void updateErrorValue(ErrorValueDTO errorValueDTO) throws Exception;

	public void updateWarnValue(WarnValueDTO warnValueDTO) throws Exception;

	public void deleteWarningValue(long valueId) throws Exception;
	
	//将未读预警信息全部标记为已读
	public void readAllWarningLog(String hadRead) throws Exception;

	public Pager<QueryValueDTO> pageQueryWarningValue(int pageIndex, int pageSize, String sort, String order,
			String series, String star, String parameter, String parameterType, String warningType) throws Exception;

	public WarningValue getWarningValueById(long valueId) throws Exception;

	public void addWarningLog(WarningLogDTO warningLogDTO) throws Exception;

	public void addWarningLogFromMongo() throws Exception;

	public void updateWarningLog(WarningLogDTO warningLogDTO) throws Exception;

	public void deleteWarningLog(String logId, String series, String star, String parameterType, String warningType,String hadRead)
			throws Exception;

	public Pager<QueryLogDTO> pageQueryWarningLog(int pageIndex, int pageSize, String series, String star,
			String parameterType, String parameter, String createdatetimeStart, String createdatetimeEnd,
			String warningType, String hadRead) throws Exception;

	public WarningLog getWarningLogById(long logId) throws Exception;

	public Long getNotReadCount(String series, String star, String parameterType, String parameter, String warningType)
			throws Exception;

	public SelectOptionDTO getSelectOption(String series, String paramaterType, String star) throws Exception;

	public boolean cherkWarningValue(String series, String star, String parameter, String parameterType,
			String warningType) throws Exception;

	public List<WarningValue> getWarningValueByParams(String series, String star, String parameter,
			String parameterType, String warningType);

	public List<Star> getStarList(String seriesId);

	
}
