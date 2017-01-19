package DataAn.reportManager.dao.impl;


import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import DataAn.common.dao.BaseDaoImpl;
import DataAn.common.dao.Pager;
import DataAn.reportManager.dao.IStarParamDao;
import DataAn.reportManager.domain.StarParam;

@Repository
public class StarParamDao extends BaseDaoImpl<StarParam> implements IStarParamDao {

	@SuppressWarnings("unchecked")
	@Override
	public Pager<StarParam> selectByOption(int pageIndex, int pageSize, String series, String star,
			String partsType,String paramCode) {
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
		if(StringUtils.isNotBlank(partsType)){
			hql += " and u.partsType = :partsType";
			countHql += " and u.partsType = :partsType";
		}
		if(StringUtils.isNotBlank(paramCode)){
			hql += " and u.paramCode = :paramCode";
			countHql += " and u.paramCode = :paramCode";
		}
			hql += " order by u.createDate desc";
		
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
		if(StringUtils.isNotBlank(partsType)){
			query.setParameter("partsType", partsType);
			countQuery.setParameter("partsType", partsType);
		}
		if(StringUtils.isNotBlank(paramCode)){
			query.setParameter("paramCode", paramCode);
			countQuery.setParameter("paramCode", paramCode);
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
	public boolean cherkStarParam(String series,String star,String partsType,String paramCode) {
		
		String hql = "select count(*) from  StarParam sp where "
				+ "  sp.series = :series and "
				+ "  sp.star = :star and"
				+ "  sp.partsType = :partsType  and"
				+ "  sp.paramCode = :paramCode";
		Query countQuery = this.getSession().createQuery(hql);
		countQuery.setParameter("series", series);
		countQuery.setParameter("star", star);
		countQuery.setParameter("partsType", partsType);
		countQuery.setParameter("paramCode", paramCode);
		
		Long totalCount = 0L;
		Object obj = countQuery.uniqueResult();
		if(obj != null){
			totalCount = (Long) obj;
		}
		return  totalCount == 0L ? Boolean.FALSE : Boolean.TRUE;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<StarParam>  getStarParamForReport(String seriesId, String starId, String partsType) {
		String hql = "from StarParam u where 1=1";
		if(StringUtils.isNotBlank(seriesId)){
			hql += " and u.series = :series";
		}
		if(StringUtils.isNotBlank(starId)){
			hql += " and u.star = :star";
		}
		if(StringUtils.isNotBlank(partsType)){
			hql += " and u.partsType = :partsType";
		}
		hql += " order by u.paramName";
		
		Query query = this.getSession().createQuery(hql);
		if(StringUtils.isNotBlank(seriesId)){
			query.setParameter("series", seriesId);
		}
		if(StringUtils.isNotBlank(starId)){
			query.setParameter("star", starId);
		}
		if(StringUtils.isNotBlank(partsType)){
			query.setParameter("partsType", partsType);
		}
		List<StarParam> starParamList =   query.list();
		return starParamList;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<StarParam>  getStarParamByParts() {
		String hql = "select distinct  series,star,partsType from StarParam";
		Query query = this.getSession().createQuery(hql);
		List<Object[]> list = query.list();    
		List<StarParam> starParamList =new ArrayList<StarParam>();
        for(Object[] obj : list){
        	StarParam sp = new StarParam();
        	sp.setSeries(obj[0].toString());
        	sp.setStar(obj[1].toString());
        	sp.setPartsType(obj[2].toString());
        	starParamList.add(sp);
        }
		
//		List<StarParam> starParamList =   query.list();
		return starParamList;
	}
	
	
}
