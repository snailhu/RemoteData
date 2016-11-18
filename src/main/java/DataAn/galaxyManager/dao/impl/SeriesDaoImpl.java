package DataAn.galaxyManager.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import DataAn.common.dao.BaseDaoImpl;
import DataAn.common.dao.Pager;
import DataAn.galaxyManager.dao.ISeriesDao;
import DataAn.galaxyManager.domain.Parameter;
import DataAn.galaxyManager.domain.Series;

@Repository
public class SeriesDaoImpl extends BaseDaoImpl<Series> implements ISeriesDao {

	@Override
	public String getSeriesName(String seriesId) {
		String hql = "select s.name from Series s where s.code= ? ";
		return (String) getSession().createQuery(hql).setParameter(0, seriesId).uniqueResult();
	}

	@Override
	public String getSeriesIdByName(String seriesName) {
		String hql = "select s.id from Series s where s.code= ? ";
		return getSession().createQuery(hql).setParameter(0, seriesName).uniqueResult().toString();
	}

	@Override
	public Series selectByName(String name) {
		String hql = "from Series s where s.name=?";
		return (Series) this.getSession().createQuery(hql).setParameter(0, name).uniqueResult();
	}

	@Override
	public Series selectByCode(String code) {
		String hql = "from Series s where s.code=?";
		return (Series) this.getSession().createQuery(hql).setParameter(0, code).uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Pager<Series> selectByPager(int pageIndex, int pageSize) {
		String hql = "from Series";
		String countHQL = "select count(*) from Series";
		List<Series> list = this.getSession().createQuery(hql)
										   .setMaxResults(pageSize)
										   .setFirstResult(pageSize * (pageIndex -1)).list();
		Long totalCount = 0l; 
		Object obj = this.getSession().createQuery(countHQL).uniqueResult();
		if(obj != null){
			totalCount = (Long) obj;
		}
		Pager<Series> pager = new Pager<Series>(pageIndex,pageSize,totalCount,list);
		return pager;
	}

}
