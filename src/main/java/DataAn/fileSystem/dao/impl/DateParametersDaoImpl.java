package DataAn.fileSystem.dao.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import DataAn.common.dao.BaseDaoImpl;
import DataAn.common.utils.DateUtil;
import DataAn.fileSystem.dao.IDateParametersDao;
import DataAn.fileSystem.domain.DateParameters;

@Repository
public class DateParametersDaoImpl extends BaseDaoImpl<DateParameters>
implements IDateParametersDao{

	@SuppressWarnings("unchecked")
	@Override
	public List<DateParameters> selectByYear_month_dayAndParameterType(String beginDate,
			String endDate, String parameterType) {
		String hql = "from DateParameters dp where dp.parameterType=:parameterType";
		if(StringUtils.isNotBlank(beginDate)){
			hql += " and dp.year_month_day >= :beginDate";
		}
		if(StringUtils.isNotBlank(endDate)){
			hql += " and dp.year_month_day <= :endDate";
		}
		Query query = this.getSession().createQuery(hql).setParameter("parameterType", parameterType);
		if(StringUtils.isNotBlank(beginDate)){
			beginDate = DateUtil.formatString(beginDate, "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd");	
			query.setParameter("beginDate", beginDate);
		}
		if(StringUtils.isNotBlank(endDate)){
			endDate = DateUtil.formatString(endDate, "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd");	
			query.setParameter("endDate", endDate);
		}
		return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DateParameters> selectByYear_month_dayAndParameterType(
			String beginDate, String endDate, String series, String star,
			String parameterType) {
		
		String hql = "from DateParameters dp where dp.series=:series and "
												+ "dp.star=:star and "
												+ "dp.parameterType=:parameterType";
		
		if(StringUtils.isNotBlank(beginDate)){
			hql += " and dp.year_month_day >= :beginDate";
		}
		if(StringUtils.isNotBlank(endDate)){
			hql += " and dp.year_month_day <= :endDate";
		}
		Query query = this.getSession().createQuery(hql)
										.setParameter("series", series)
										.setParameter("star", star)
										.setParameter("parameterType", parameterType);
		
		if(StringUtils.isNotBlank(beginDate)){
			beginDate = DateUtil.formatString(beginDate, "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd");	
			query.setParameter("beginDate", beginDate);
		}
		if(StringUtils.isNotBlank(endDate)){
			endDate = DateUtil.formatString(endDate, "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd");	
			query.setParameter("endDate", endDate);
		}
		return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DateParameters> selectByOption(String series, String star, String parameterType, String year_month_day) {
		String hql = "from DateParameters dp where dp.series=:series and "
				+ "dp.star=:star and "
				+ "dp.parameterType=:parameterType and "
				+ "dp.year_month_day=:year_month_day";
		Query query = this.getSession().createQuery(hql)
				.setParameter("series", series)
				.setParameter("star", star)
				.setParameter("parameterType", parameterType)
				.setParameter("year_month_day", year_month_day);
		
		return query.list();
	}

	@Override
	public void deleteByIds(List<Long> ids) {
		String hql = "delete from DateParameters dp where dp.id in (:ids)";
		this.getSession().createQuery(hql).setParameterList("ids", ids).executeUpdate();
		
	}

}
