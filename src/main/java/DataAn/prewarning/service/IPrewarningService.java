package DataAn.prewarning.service;

import java.util.List;

import DataAn.common.dao.Pager;
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

	public Pager<QueryValueDTO> pageQueryWarningValue(int pageIndex, int pageSize, String series, String star,
			String parameter, String parameterType, String warningType) throws Exception;

	public WarningValue getWarningValueById(long valueId) throws Exception;

	public void addWarningLog(WarningLogDTO warningLogDTO) throws Exception;

	public void updateWarningLog(WarningLogDTO warningLogDTO) throws Exception;

	public void deleteWarningLog(long logId) throws Exception;

	public Pager<QueryLogDTO> pageQueryWarningLog(int pageIndex, int pageSize, String series, String star,
			String parameterType, String createdatetimeStart, String createdatetimeEnd, String warningType,
			String hadRead) throws Exception;

	public WarningLog getWarningLogById(long logId) throws Exception;

	public Long getNotReadCount(String series, String star, String parameterType, String parameter, String warningType)
			throws Exception;

	public SelectOptionDTO getSelectOption(String series, String paramaterType) throws Exception;

	public boolean cherkWarningValue(String series, String star, String parameter, String parameterType,
			String warningType) throws Exception;

	public List<WarningValue> getWarningValueByParams(String series, String star, String parameter,
			String parameterType, String warningType);
}
