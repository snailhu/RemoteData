package DataAn.galaxyManager.dao.impl;

import org.springframework.stereotype.Repository;

import DataAn.common.dao.BaseDaoImpl;
import DataAn.galaxyManager.dao.IStarDao;
import DataAn.galaxyManager.domain.Star;

@Repository
public class StarDaoImpl extends BaseDaoImpl<Star>
implements IStarDao{

	@Override
	public void deleteBySeriesId(long seriesId) {
		String hql = "delete from Star star where star.series.id=?";
		this.getSession().createQuery(hql).setParameter(0, seriesId).executeUpdate();
	}

}
