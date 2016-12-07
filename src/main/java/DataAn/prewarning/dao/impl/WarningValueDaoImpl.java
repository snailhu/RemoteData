package DataAn.prewarning.dao.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import DataAn.common.dao.BaseDaoImpl;
import DataAn.common.dao.Pager;
import DataAn.prewarning.dao.IWarningValueDao;
import DataAn.prewarning.domain.WarningValue;

@Repository
public class WarningValueDaoImpl extends BaseDaoImpl<WarningValue> implements IWarningValueDao {

	@Override
	@SuppressWarnings("unchecked")
	public List<WarningValue> getWarningValueByParams(String series, String star, String parameter,
			String parameterType, String warningType) {
		String hql = "from WarningValue where 1=1";
		if (StringUtils.isNotBlank(series)) {
			hql += " and series = " + series;
		}
		if (StringUtils.isNotBlank(star)) {
			hql += " and star = " + star;
		}
		if (StringUtils.isNotBlank(parameter)) {
			hql += " and parameter = '" + parameter + "'";
		}
		if (StringUtils.isNotBlank(parameterType)) {
			hql += " and parameterType = '" + parameterType + "'";
		}
		if (StringUtils.isNotBlank(warningType)) {
			hql += " and warningType = " + warningType;
		}
		hql += "order by createDate desc";
		List<WarningValue> warningValues = this.getSession().createQuery(hql).list();
		return warningValues;
	}

	@Override
	@SuppressWarnings("unchecked")
	public Pager<WarningValue> selectByOption(int pageIndex, int pageSize, String sort, String order, String series,
			String star, String parameter, String parameterType, String warningType) {
		String hql = "from WarningValue where 1=1";
		String countHQL = "select count(*) from WarningValue where 1=1";
		if (StringUtils.isNotBlank(series)) {
			hql += " and series = " + series;
			countHQL += " and series = " + series;
		}
		if (StringUtils.isNotBlank(star)) {
			hql += " and star = " + star;
			countHQL += " and star = " + star;
		}
		if (StringUtils.isNotBlank(parameter)) {
			hql += " and parameter = '" + parameter + "'";
			countHQL += " and parameter = '" + parameter + "'";
		}
		if (StringUtils.isNotBlank(parameterType)) {
			hql += " and parameterType = '" + parameterType + "'";
			countHQL += " and parameterType = '" + parameterType + "'";
		}
		if (StringUtils.isNotBlank(warningType)) {
			hql += " and warningType = " + warningType;
			countHQL += " and warningType = " + warningType;
		}
		if (StringUtils.isNotBlank(sort) && StringUtils.isNotBlank(order)) {
			hql += "order by " + sort + " " + order;
		} else {
			hql += "order by createDate desc";
		}

		List<WarningValue> list = this.getSession().createQuery(hql).setMaxResults(pageSize)
				.setFirstResult(pageSize * (pageIndex - 1)).list();
		Long totalCount = 0l;
		Object obj = this.getSession().createQuery(countHQL).uniqueResult();
		if (obj != null) {
			totalCount = (Long) obj;
		}
		Pager<WarningValue> pager = new Pager<WarningValue>(pageSize, pageIndex, totalCount, list);
		return pager;
	}

	@Override
	public boolean cherkWarningValue(String series, String star, String parameter, String parameterType,
			String warningType) {
		String hql = "select count(*) from WarningValue where 1=1";
		if (StringUtils.isNotBlank(series)) {
			hql += " and series = " + series;
		}
		if (StringUtils.isNotBlank(star)) {
			hql += " and star = " + star;
		}
		if (StringUtils.isNotBlank(parameter)) {
			hql += " and parameter = '" + parameter + "'";
		}
		if (StringUtils.isNotBlank(parameterType)) {
			hql += " and parameterType = '" + parameterType + "'";
		}
		if (StringUtils.isNotBlank(warningType)) {
			hql += " and warningType = " + warningType;
		}
		Query countQuery = this.getSession().createQuery(hql);
		Long totalCount = 0L;
		Object obj = countQuery.uniqueResult();
		if (obj != null) {
			totalCount = (Long) obj;
		}
		return totalCount == 0L ? Boolean.FALSE : Boolean.TRUE;
	}

}
