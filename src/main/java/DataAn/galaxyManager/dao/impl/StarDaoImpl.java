package DataAn.galaxyManager.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import DataAn.common.dao.BaseDaoImpl;
import DataAn.galaxyManager.dao.IStarDao;
import DataAn.galaxyManager.domain.Star;

@Repository
public class StarDaoImpl extends BaseDaoImpl<Star> implements IStarDao {

	@Override
	public void deleteBySeriesId(long seriesId) {
		String hql = "delete from Star star where star.series.id=?";
		this.getSession().createQuery(hql).setParameter(0, seriesId).executeUpdate();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Star> getStarList(String seriesId) {
		String hql = "from Star star where star.series.code=?";
		return this.getSession().createQuery(hql).setParameter(0, seriesId).list();
	}

	@Override
	public String getStarName(String starId) {
		String hql = "select star.name from Star star where star.code=?";
		return (String) getSession().createQuery(hql).setParameter(0, starId).uniqueResult();
	}

	@Override
	public String getStarIdByName(String starName) {
		String hql = "select star.id from Star star where star.code=?";
		return getSession().createQuery(hql).setParameter(0, starName).uniqueResult().toString();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Star> getStarListBySeriesId(String seriesId) {
		String hql = "from Star star where star.series.id=?";
		return this.getSession().createQuery(hql).setParameter(0, Long.parseLong(seriesId)).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Star> getStarBySeriesIdAndName(long seriesId, String name) {
		String hql = "from Star star where star.series.id=? and star.name=?";
		return this.getSession().createQuery(hql).setParameter(0, seriesId).setParameter(1, name).list();
	}

}
