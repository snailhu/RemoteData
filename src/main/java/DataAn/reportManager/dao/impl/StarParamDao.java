package DataAn.reportManager.dao.impl;


import org.apache.commons.lang3.StringUtils;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import DataAn.common.dao.BaseDaoImpl;
import DataAn.common.dao.Pager;
import DataAn.reportManager.dao.IStarParamDao;
import DataAn.reportManager.domain.StarParam;
import DataAn.reportManager.dto.StarParamDto;

@Repository
public class StarParamDao extends BaseDaoImpl<StarParam> implements IStarParamDao {

	@SuppressWarnings("unchecked")
	@Override
	public Pager<StarParam> selectByOption(int pageIndex, int pageSize, String series, String star,
			String parameterType) {
		String hql = "from StarParam u where 1=1";
		String countHql = "select count(*) from StarParam u where 1=1";
		if(StringUtils.isNotBlank(series)){
			hql += " and u.series = :series";
			countHql += " and u.series = :series";
		}
		if(StringUtils.isNotBlank(star)){
			hql += " and u.star = :star";
			countHql += " and u.star = :star";
		}
		if(StringUtils.isNotBlank(parameterType)){
			hql += " and u.parameterType = :parameterType";
			countHql += " and u.parameterType = :parameterType";
		}
			hql += " order by u.createDate";
		
		Query query = this.getSession().createQuery(hql);
		Query countQuery = this.getSession().createQuery(countHql);
		if(StringUtils.isNotBlank(series)){
			query.setParameter("series", series);
			countQuery.setParameter("series", series);
		}
		if(StringUtils.isNotBlank(star)){
			query.setParameter("star", star);
			countQuery.setParameter("star", star);
		}
		if(StringUtils.isNotBlank(parameterType)){
			query.setParameter("parameterType", parameterType);
			countQuery.setParameter("parameterType", parameterType);
		}
		Long totalCount = 0l;
		Object obj = countQuery.uniqueResult();
		if(obj != null){
			totalCount = (Long) obj;
		}
		//设置每页显示多少个，设置最大结果
		query.setMaxResults(pageSize);
		//设置起点
		query.setFirstResult(pageSize * (pageIndex - 1));
		Pager<StarParam> pager = new Pager<StarParam>(pageIndex, pageSize, totalCount, query.list());
		return pager;
	}

	@Override
	public boolean cherkStarParam(StarParamDto starParamDto) {
		
		String hql = "select count(*) from  StarParam sp where "
				+ "  sp.series = :series and "
				+ "  sp.star = :star and"
				+ "  sp.parameterType = :parameterType  and"
				+ "  sp.paramCode = :paramCode";
		Query countQuery = this.getSession().createQuery(hql);
		countQuery.setParameter("series", starParamDto.getSeries());
		countQuery.setParameter("star", starParamDto.getStar());
		countQuery.setParameter("parameterType", starParamDto.getParameterType());
		countQuery.setParameter("paramCode", starParamDto.getParamCode());
		
		Long totalCount = 0L;
		Object obj = countQuery.uniqueResult();
		if(obj != null){
			totalCount = (Long) obj;
		}
		return  totalCount == 0L ? Boolean.FALSE : Boolean.TRUE;
	}
}
