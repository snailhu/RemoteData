package DataAn.galaxyManager.dao;

import DataAn.common.dao.IBaseDao;
import DataAn.common.dao.Pager;
import DataAn.galaxyManager.domain.Parameter;

public interface IParameterDao extends IBaseDao<Parameter>{

	Pager<Parameter> selectByPager(int pageIndex, int pageSize);

}
