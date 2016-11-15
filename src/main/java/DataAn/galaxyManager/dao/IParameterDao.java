package DataAn.galaxyManager.dao;

import java.util.List;

import DataAn.common.dao.IBaseDao;
import DataAn.common.dao.Pager;
import DataAn.galaxyManager.domain.Parameter;

public interface IParameterDao extends IBaseDao<Parameter>{

	Pager<Parameter> selectByPager(String series, int pageIndex, int pageSize);

	Parameter selectBySeriesAndStarAndFullName(String series, String star, String param_zh);
	
	Parameter selectBySeriesAndStarAndSimplyName(String series, String star, String param_zh);
	
	Parameter selectBySeriesAndName(String series, String param_zh);
	
	Parameter selectBySeriesAndStarAndCode(String series, String star, String param_en);
	
	Parameter selectBySeriesAndCode(String series, String param_en);
	
	List<Parameter> selectBySeriesAndParameterType(String series, String parameterType);
}
