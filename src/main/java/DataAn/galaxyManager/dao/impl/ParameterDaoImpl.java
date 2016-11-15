package DataAn.galaxyManager.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import DataAn.common.dao.BaseDaoImpl;
import DataAn.common.dao.Pager;
import DataAn.galaxyManager.dao.IParameterDao;
import DataAn.galaxyManager.domain.Parameter;
import DataAn.sys.domain.Role;

@Repository
public class ParameterDaoImpl extends BaseDaoImpl<Parameter>
implements IParameterDao{

	@SuppressWarnings("unchecked")
	@Override
	public Pager<Parameter> selectByPager(String series, int pageIndex, int pageSize) {
		
		String hql = "from Parameter param where param.series=?";
		String countHQL = "select count(*) from Parameter param where param.series=?";
		List<Parameter> list = this.getSession().createQuery(hql).setParameter(0, series)
										   .setMaxResults(pageSize)
										   .setFirstResult(pageSize * (pageIndex -1)).list();
		Long totalCount = 0l; 
		Object obj = this.getSession().createQuery(countHQL).setParameter(0, series).uniqueResult();
		if(obj != null){
			totalCount = (Long) obj;
		}
		Pager<Parameter> pager = new Pager<Parameter>(pageIndex,pageSize,totalCount,list);
		return pager;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Parameter selectBySeriesAndStarAndFullName(String series, String star,
			String param_zh) {
		String hql = "from Parameter param where param.series=? and param.star=? and param.fullName=?";
		List<Parameter> list = this.getSession().createQuery(hql)
												.setParameter(0, series)
												.setParameter(1, star)
												.setParameter(2, param_zh).list();
		if(list != null && list.size() > 0){
			return list.get(0);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Parameter selectBySeriesAndStarAndSimplyName(String series,
			String star, String param_zh) {
		String hql = "from Parameter param where param.series=? and param.star=? and param.simplyName=?";
		List<Parameter> list = this.getSession().createQuery(hql)
												.setParameter(0, series)
												.setParameter(1, star)
												.setParameter(2, param_zh).list();
		if(list != null && list.size() > 0){
			return list.get(0);
		}
		return null;
	}
	@SuppressWarnings("unchecked")
	@Override
	public Parameter selectBySeriesAndName(String series, String param_zh) {
		String hql = "from Parameter param where param.series=? and param.fullName=?";
		List<Parameter> list = this.getSession().createQuery(hql)
												.setParameter(0, series)
												.setParameter(1, param_zh).list();
		if(list != null && list.size() > 0){
			return list.get(0);
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Parameter selectBySeriesAndStarAndCode(String series, String star,
			String param_en) {
		String hql = "from Parameter param where param.series=? and param.star=? and param.code=?";
		List<Parameter> list = this.getSession().createQuery(hql)
												.setParameter(0, series)
												.setParameter(1, star)
												.setParameter(2, param_en).list();
		if(list != null && list.size() > 0){
			return list.get(0);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Parameter selectBySeriesAndCode(String series, String param_en) {
		String hql = "from Parameter param where param.series=? and param.code=?";
		List<Parameter> list = this.getSession().createQuery(hql)
												.setParameter(0, series)
												.setParameter(1, param_en).list();
		if(list != null && list.size() > 0){
			return list.get(0);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Parameter> selectBySeriesAndParameterType(String series,
			String parameterType) {
		String hql = "from Parameter param where param.series=? and param.parameterType=? and param.simplyName is not null";
		return this.getSession().createQuery(hql)
								.setParameter(0, series)
								.setParameter(1, parameterType).list();
	}


}
