package DataAn.fileSystem.dao.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import DataAn.common.dao.BaseDaoImpl;
import DataAn.fileSystem.dao.IDateParametersDao;
import DataAn.fileSystem.domain.DateParameters;
import DataAn.fileSystem.option.FileType;

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
			query.setParameter("beginDate", beginDate);
		}
		if(StringUtils.isNotBlank(endDate)){
			query.setParameter("endDate", endDate);
		}
		return query.list();
	}

}
