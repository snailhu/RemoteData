package DataAn.galaxyManager.dao.impl;

import org.springframework.stereotype.Repository;

import DataAn.common.dao.BaseDaoImpl;
import DataAn.galaxyManager.dao.ISeriesDao;
import DataAn.galaxyManager.domain.Series;

@Repository
public class SeriesDaoImpl extends BaseDaoImpl<Series>
implements ISeriesDao{

	@Override
	public String getSeriesName(String seriesId) {
		String hql = "select s.name from Series s where s.id= ? ";
		return  (String)getSession().createQuery(hql).setParameter(0, Long.parseLong(seriesId)).uniqueResult();
	}
}
