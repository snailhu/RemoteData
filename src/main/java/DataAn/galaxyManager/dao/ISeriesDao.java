package DataAn.galaxyManager.dao;

import DataAn.common.dao.IBaseDao;
import DataAn.galaxyManager.domain.Series;

public interface ISeriesDao extends IBaseDao<Series>{

	String getSeriesName(String seriesId);

}
