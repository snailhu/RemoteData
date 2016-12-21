package DataAn.sys.dao;

import DataAn.common.dao.IBaseDao;
import DataAn.common.dao.Pager;
import DataAn.sys.domain.StormServer;

public interface IStormServerDao extends IBaseDao<StormServer>{

	Pager<StormServer> selectByPager(int pageIndex, int pageSize);

}
