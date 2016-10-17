package DataAn.prewarning.dao;

import java.util.List;

import DataAn.common.dao.IBaseDao;
import DataAn.common.dao.Pager;
import DataAn.prewarning.domain.WarningValue;

public interface IWarningValueDao extends IBaseDao<WarningValue> {

	public List<WarningValue> getWarningValueByParams(String series, String parameter, String parameterType,
			String warningType);

	public Pager<WarningValue> selectByOption(int pageIndex, int pageSize, String series, String parameter,
			String parameterType, String warningType);

	public boolean cherkWarningValue(String series, String parameter, String parameterType, String warningType);
}
