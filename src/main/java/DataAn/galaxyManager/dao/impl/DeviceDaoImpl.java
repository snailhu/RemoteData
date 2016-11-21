package DataAn.galaxyManager.dao.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import DataAn.common.dao.BaseDaoImpl;
import DataAn.common.dao.Pager;
import DataAn.common.utils.DateUtil;
import DataAn.galaxyManager.dao.IDeviceDao;
import DataAn.galaxyManager.domain.Device;

@Repository
public class DeviceDaoImpl extends BaseDaoImpl<Device> implements IDeviceDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<Device> getDevicesBySeriesAndStar(String series, String star, String deviceType) {
		String hql = "from Device d where 1=1";
		if (StringUtils.isNoneBlank(series)) {
			hql += " and d.seriersId = " + series;
		}
		if (StringUtils.isNoneBlank(star)) {
			hql += " and d.starId = " + star;
		}
		if (StringUtils.isNoneBlank(deviceType)) {
			hql += " and d.deviceType = " + deviceType;
		}
		hql += " order by d.createDate";
		return this.getSession().createQuery(hql).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Pager<Device> selectByPager(String series, String star, int pageIndex, int pageSize) {
		String hql = "from Device d where 1=1";
		String countHQL = "select count(*) from Device d where 1=1";
		if (StringUtils.isNoneBlank(series)) {
			hql += " and d.seriersId = " + series;
			countHQL += " and d.seriersId = " + series;
		}
		if (StringUtils.isNoneBlank(star)) {
			hql += " and d.starId = " + star;
			countHQL += " and d.starId = " + star;
		}
		hql += " order by d.editDate desc";
		Query query = this.getSession().createQuery(hql);
		Query countQuery = this.getSession().createQuery(countHQL);
		List<Device> list = query.setMaxResults(pageSize).setFirstResult(pageSize * (pageIndex - 1)).list();
		Long totalCount = 0l;
		Object obj = countQuery.uniqueResult();
		if (obj != null) {
			totalCount = (Long) obj;
		}
		Pager<Device> pager = new Pager<Device>(pageIndex, pageSize, totalCount, list);
		return pager;
	}

	@Override
	public int getTotalRuntimeByDeviceType(String series, String star, String deviceType) {
		String hql = "from Device d where d.seriersId = " + series + " and d.starId=" + star + " and deviceType = "
				+ deviceType;
		List<Device> devices = this.getSession().createQuery(hql).list();
		int totalDays = 0;
		for (Device device : devices) {
			int days = DateUtil.daysOfTwo(device.getStartDate(), new Date());
			if (device.getEndDate() != null) {
				days = DateUtil.daysOfTwo(device.getStartDate(), device.getEndDate());
			}
			totalDays += days;
		}
		return totalDays;
	}

	@Override
	public int getTotalRuntimeByStar(String series, String star) {
		String hql = "from Device d where d.seriersId = " + series + " and d.starId=" + star;
		List<Device> devices = this.getSession().createQuery(hql).list();
		int totalDays = 0;
		for (Device device : devices) {
			int days = DateUtil.daysOfTwo(device.getStartDate(), new Date());
			if (device.getEndDate() != null) {
				days = DateUtil.daysOfTwo(device.getStartDate(), device.getEndDate());
			}
			totalDays += days;
		}
		return totalDays;
	}

	@Override
	public int getTotalRuntimeBySeries(String series) {
		String hql = "from Device d where d.seriersId = " + series;
		List<Device> devices = this.getSession().createQuery(hql).list();
		int totalDays = 0;
		for (Device device : devices) {
			int days = DateUtil.daysOfTwo(device.getStartDate(), new Date());
			if (device.getEndDate() != null) {
				days = DateUtil.daysOfTwo(device.getStartDate(), device.getEndDate());
			}
			totalDays += days;
		}
		return totalDays;
	}

	@Override
	public boolean checkDevice(String series, String star, String deviceType, String deviceName) {
		String hql = "select count(*) from Device where 1=1";
		if (StringUtils.isNotBlank(series)) {
			hql += " and seriersId = " + series;
		}
		if (StringUtils.isNotBlank(star)) {
			hql += " and starId = " + star;
		}
		if (StringUtils.isNotBlank(deviceType)) {
			hql += " and deviceType = " + deviceType;
		}
		if (StringUtils.isNotBlank(deviceName)) {
			hql += " and deviceName = '" + deviceName + "'";
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
