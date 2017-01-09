package DataAn.status.dao.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import DataAn.common.dao.BaseDaoImpl;
import DataAn.common.dao.Pager;
import DataAn.status.dao.IStatusTrackingDao;
import DataAn.status.domain.StatusTracking;
import DataAn.status.option.StatusTrackingType;

@Repository
public class StatusTrackingDaoImpl extends BaseDaoImpl<StatusTracking> implements IStatusTrackingDao {

	@Override
	@SuppressWarnings("unchecked")
	public List<StatusTracking> getStatusTrackingByParams(String userType) {
		String hql = "from StatusTracking where statusType != '5' and statusType not like '%e%'";
		if (StringUtils.isNotBlank(userType)) {
			hql += " and userType = '" + userType + "'";
		}
		hql += " order by createDate desc";
		List<StatusTracking> statusTrackings = this.getSession().createQuery(hql).list();
		return statusTrackings;
	}

	@Override
	@SuppressWarnings("unchecked")
	public Pager<StatusTracking> selectByOption(int pageIndex, int pageSize, String fileName, String userType,
			String statusType, String createdatetimeStart, String createdatetimeEnd) {
		String hql = "from StatusTracking where (statusType='" + StatusTrackingType.END.getValue()
				+ "' or statusType like('%e%'))";
		String countHQL = "select count(*) from StatusTracking where (statusType='" + StatusTrackingType.END.getValue()
				+ "' or statusType like('%e%'))";
		if (StringUtils.isNotBlank(fileName)) {
			hql += " and fileName like '%" + fileName + "%'";
			countHQL += " and fileName like '%" + fileName + "%'";
		}
		if (StringUtils.isNotBlank(userType)) {
			hql += " and userType = '" + userType + "'";
			countHQL += " and userType = '" + userType + "'";
		}
		if (StringUtils.isNotBlank(statusType)) {
			if (statusType.equals("0")) {
				hql += " and statusType = '" + StatusTrackingType.END.getValue() + "'";
				countHQL += " and statusType = '" + StatusTrackingType.END.getValue() + "'";
			} else {
				hql += " and statusType like '%e%'";
				countHQL += " and statusType like '%e%'";
			}

		}
		if (StringUtils.isNotBlank(createdatetimeStart)) {
			hql += " and createDate >= '" + createdatetimeStart + "'";
			countHQL += " and createDate >= '" + createdatetimeStart + "'";
		}
		if (StringUtils.isNotBlank(createdatetimeEnd)) {
			hql += " and createDate <= '" + createdatetimeEnd + "'";
			countHQL += " and createDate <= '" + createdatetimeEnd + "'";
		}
		hql += "order by createDate desc";

		List<StatusTracking> list = this.getSession().createQuery(hql).setMaxResults(pageSize)
				.setFirstResult(pageSize * (pageIndex - 1)).list();
		Long totalCount = 0l;
		Object obj = this.getSession().createQuery(countHQL).uniqueResult();
		if (obj != null) {
			totalCount = (Long) obj;
		}
		Pager<StatusTracking> pager = new Pager<StatusTracking>(pageSize, pageIndex, totalCount, list);
		return pager;
	}

	@Override
	public boolean checkStatusTrackingByParams(String fileName, String userType) {
		String hql = "select count(*) from StatusTracking where 1=1";
		if (StringUtils.isNotBlank(fileName)) {
			hql += " and fileName = '" + fileName + "'";
		}
		if (StringUtils.isNotBlank(userType)) {
			hql += " and userType = '" + userType + "'";
		}
		Query countQuery = this.getSession().createQuery(hql);
		Long totalCount = 0L;
		Object obj = countQuery.uniqueResult();
		if (obj != null) {
			totalCount = (Long) obj;
		}
		return totalCount == 0L ? Boolean.FALSE : Boolean.TRUE;
	}

	@Override
	public StatusTracking getStatusTrackingByParams(String fileName, String userType) {
		String hql = "from StatusTracking where 1=1";
		if (StringUtils.isNotBlank(fileName)) {
			hql += " and fileName = '" + fileName + "'";
		}
		if (StringUtils.isNotBlank(userType)) {
			hql += " and userType = '" + userType + "'";
		}
		StatusTracking statusTracking = (StatusTracking) this.getSession().createQuery(hql).uniqueResult();
		return statusTracking;
	}

}
