package DataAn.galaxyManager.dao.impl;

import java.util.List;

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

	@SuppressWarnings("unchecked")
	@Override
	public List<Star> getStarList(long seriesId) {
		String hql = "from Star star where star.series.id=?";
		return this.getSession().createQuery(hql).setParameter(0, seriesId).list();
	}

	@Override
	public String getStarName(String starId) {
		String hql = "select star.name from Star star where star.id=?";
		return (String)getSession().createQuery(hql).setParameter(0,  Long.parseLong(starId)).uniqueResult();
	}

	
}
