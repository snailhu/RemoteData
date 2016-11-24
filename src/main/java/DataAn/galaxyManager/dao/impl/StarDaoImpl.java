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
	public String getStarName(String series,String star) {
		String hql = "select star.name from Star star where star.code=? and star.series.code = ?";
		return (String) getSession().createQuery(hql).setParameter(0, star).setParameter(1, series).uniqueResult();
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
	public List<Star> getStarListBySeriesId(long seriesId) {
		String hql = "from Star star where star.series.id=?";
		return this.getSession().createQuery(hql).setParameter(0,seriesId).list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Star> getStarBySeriesIdAndName(long seriesId, String name) {
		String hql = "from Star star where star.series.id=? and star.name=?";
		return this.getSession().createQuery(hql).setParameter(0, seriesId).setParameter(1, name).list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Star> getStarBySeriesIdAndCode(long seriesId,String code){
		String hql = "from Star star where star.series.id=? and star.code=?";
		return this.getSession().createQuery(hql).setParameter(0, seriesId).setParameter(1, code).list();
	}
}
