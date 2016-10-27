package DataAn.galaxyManager.dao;

import DataAn.common.dao.IBaseDao;
import DataAn.common.dao.Pager;
import DataAn.galaxyManager.domain.Series;

public interface ISeriesDao extends IBaseDao<Series> {

	String getSeriesName(String seriesId);

	public String getSeriesIdByName(String seriesName);
	
	Series selectByName(String name);
	
	Pager<Series> selectByPager(int pageIndex, int pageSize);
}
