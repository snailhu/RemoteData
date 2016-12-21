package DataAn.prewarning.dao.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import DataAn.common.dao.BaseDaoImpl;
import DataAn.common.dao.Pager;
import DataAn.prewarning.dao.IWarningLogDao;
import DataAn.prewarning.domain.WarningLog;

@Repository
public class WarningLogDaoImpl extends BaseDaoImpl<WarningLog> implements IWarningLogDao {

	@Override
	public List<WarningLog> getWarningLogByParams(String series, String star, String parameterType, String parameter,
			String warningType, String hadRead) {
		String hql = "from WarningLog where 1=1";
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
		if (StringUtils.isNotBlank(hadRead)) {
			hql += " and hadRead = " + hadRead;
		}
		hql += "order by createDate desc";
		List<WarningLog> warningLogs = this.getSession().createQuery(hql).list();
		return warningLogs;
	}

	@Override
	public Pager<WarningLog> selectByOption(int pageIndex, int pageSize, String series, String star,
			String parameterType, String parameter, String createdatetimeStart, String createdatetimeEnd,
			String warningType, String hadRead) {
		String hql = "from WarningLog where 1=1";
		String countHQL = "select count(*) from WarningLog where 1=1";
		if (StringUtils.isNotBlank(series)) {
			hql += " and series = " + series;
			countHQL += " and series = " + series;
		}
		if (StringUtils.isNotBlank(star)) {
			hql += " and star = " + star;
			countHQL += " and star = " + star;
		}
		if (StringUtils.isNotBlank(createdatetimeStart)) {
			hql += " and timeValue >= '" + createdatetimeStart + "'";
			countHQL += " and timeValue >= '" + createdatetimeStart + "'";
		}
		if (StringUtils.isNotBlank(createdatetimeEnd)) {
			hql += " and timeValue <= '" + createdatetimeEnd + "'";
			countHQL += " and timeValue <= '" + createdatetimeEnd + "'";
		}
		if (StringUtils.isNotBlank(parameterType)) {
			hql += " and parameterType = '" + parameterType + "'";
			countHQL += " and parameterType = '" + parameterType + "'";
		}
		if (StringUtils.isNotBlank(parameter)) {
			hql += " and parameter = '" + parameter + "'";
			countHQL += " and parameter = '" + parameter + "'";
		}
		if (StringUtils.isNotBlank(warningType)) {
			hql += " and warningType = " + warningType;
			countHQL += " and warningType = " + warningType;
		}
		if (StringUtils.isNotBlank(hadRead)) {
			hql += " and hadRead = " + hadRead;
			countHQL += " and hadRead = " + hadRead;
		}
		hql += "order by timeValue desc";

		List<WarningLog> list = this.getSession().createQuery(hql).setMaxResults(pageSize)
				.setFirstResult(pageSize * (pageIndex - 1)).list();
		Long totalCount = 0l;
		Object obj = this.getSession().createQuery(countHQL).uniqueResult();
		if (obj != null) {
			totalCount = (Long) obj;
		}
		Pager<WarningLog> pager = new Pager<WarningLog>(pageSize, pageIndex, totalCount, list);
		return pager;
	}

	@Override
	public Long getNotReadCount(String series, String star, String parameterType, String parameter,
			String warningType) {
		String hql = "select count(*) from WarningLog where hadRead=0";
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
		Object obj = this.getSession().createQuery(hql).uniqueResult();
		Long totalCount = 0l;
		if (obj != null) {
			totalCount = (Long) obj;
		}
		return totalCount;
	}

}
