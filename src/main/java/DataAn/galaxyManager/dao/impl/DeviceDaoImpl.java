package DataAn.galaxyManager.dao.impl;

import java.math.BigInteger;
import java.util.ArrayList;
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
import DataAn.galaxyManager.domain.Star;
import DataAn.galaxyManager.dto.DeviceViewDTO;

@Repository
public class DeviceDaoImpl extends BaseDaoImpl<Device> implements IDeviceDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<Device> getDevicesBySeriesAndStar(String series, String star, String deviceType, String model) {
		String hql = "from Device d where 1=1";
		if (StringUtils.isNotBlank(series)) {
			hql += " and d.seriersId = " + series;
		}
		if (StringUtils.isNotBlank(star)) {
			hql += " and d.starId = " + star;
		}
		if (StringUtils.isNotBlank(deviceType)) {
			hql += " and d.deviceType = " + deviceType;
		}
		if (StringUtils.isNotBlank(model)) {
			hql += " and d.model like '%" + model + "%'";
		}
		hql += " order by d.seriersId,d.starId,d.editDate desc";
		return this.getSession().createQuery(hql).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Pager<Device> selectByPager(String series, String star, int pageIndex, int pageSize) {
		String hql = "from Device d where 1=1";
		String countHQL = "select count(*) from Device d where 1=1";
		if (StringUtils.isNotBlank(series)) {
			hql += " and d.seriersId = " + series;
			countHQL += " and d.seriersId = " + series;
		}
		if (StringUtils.isNotBlank(star)) {
			hql += " and d.starId = " + star;
			countHQL += " and d.starId = " + star;
		}
		hql += " order by d.seriersId,d.starId,d.editDate desc";
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
			if (device.getRunStatus() == 0) {
				totalDays += device.getRunDays();
			} else if (device.getRunStatus() == 1) {
				int days = DateUtil.daysOfTwo(device.getStartDate(), new Date());
				days += device.getRunDays();
				totalDays += days;
			}
		}
		return totalDays;
	}

	@Override
	public int getTotalRuntimeByStar(String series, String star) {
		String hql = "from Star d where d.series.id= " + series + " and d.id=" + star;
		List<Star> stars = this.getSession().createQuery(hql).list();
		int totalDays = 0;
		for (Star starDomain : stars) {
			int days = DateUtil.daysOfTwo(starDomain.getStartRunDate(), new Date());
			totalDays += days;
		}
		return totalDays;
	}

	@Override
	public int getTotalRuntimeBySeries(String series) {
		String hql = "from Star d where d.series.id = " + series;
		List<Star> stars = this.getSession().createQuery(hql).list();
		int totalDays = 0;
		for (Star starDomain : stars) {
			int days = DateUtil.daysOfTwo(starDomain.getStartRunDate(), new Date());
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

	@Override
	public Pager<DeviceViewDTO> selectViewDTObyPager(String deviceType, String model, int pageIndex, int pageSize) {
		String sql = "select distinct seriersid,starid,deviceType from t_device where 1=1";
		String countSQL = "select count(*) from (select distinct seriersid,starid,deviceType from t_device where 1=1";
		if (StringUtils.isNotBlank(deviceType)) {
			sql += " and deviceType = " + deviceType;
			countSQL += " and deviceType = " + deviceType;
		}
		if (StringUtils.isNotBlank(model)) {
			sql += " and model like '%" + model + "%'";
			countSQL += " and model like '%" + model + "%'";
		}
		sql += " order by seriersid,starId,deviceType";
		countSQL += ") ttt";
		Query query = this.getSession().createSQLQuery(sql);
		query.setFirstResult((pageIndex - 1) * pageSize);
		query.setMaxResults(pageSize);
		List<Object[]> objs = query.list();
		List<DeviceViewDTO> deviceViewDTOs = new ArrayList<DeviceViewDTO>();
		for (Object[] obj : objs) {
			DeviceViewDTO viewDTO = new DeviceViewDTO();
			if (obj[0] != null) {
				viewDTO.setSeriersId(((BigInteger) obj[0]).longValue());
			}
			if (obj[1] != null) {
				viewDTO.setStarId(((BigInteger) obj[1]).longValue());
			}
			deviceViewDTOs.add(viewDTO);
		}

		Query countQuery = this.getSession().createSQLQuery(countSQL);
		Long totalCount = 0L;
		Object obj = countQuery.uniqueResult();
		if (obj != null) {
			totalCount = ((BigInteger) obj).longValue();
		}
		Pager<DeviceViewDTO> pager = new Pager<DeviceViewDTO>(pageSize, pageIndex, totalCount, deviceViewDTOs);
		return pager;
	}

	@Override
	public int getTotalRuntimeByStarAndModel(String series, String star, String model, String deviceType) {
		String hql = "from Device d where d.seriersId = " + series + " and d.starId=" + star + " and d.deviceType = "
				+ deviceType;
		if (StringUtils.isNotBlank(model)) {
			hql += " and d.model = '" + model + "'";
		}
		List<Device> devices = this.getSession().createQuery(hql).list();
		int totalDays = 0;
		for (Device device : devices) {
			if (device.getRunStatus() == 0) {
				totalDays += device.getRunDays();
			} else if (device.getRunStatus() == 1) {
				int days = DateUtil.daysOfTwo(device.getStartDate(), new Date());
				days += device.getRunDays();
				totalDays += days;
			}
		}
		return totalDays;
	}

	@Override
	public Long getTotalNumByStarAndModel(String series, String star, String model, String deviceType) {
		String hql = "select count(*) from Device d where d.seriersId = " + series + " and d.starId=" + star
				+ " and d.deviceType = " + deviceType;
		if (StringUtils.isNotBlank(model)) {
			hql += " and d.model = '" + model + "'";
		}
		Query countQuery = this.getSession().createQuery(hql);
		Long totalCount = 0L;
		Object obj = countQuery.uniqueResult();
		if (obj != null) {
			totalCount = (Long) obj;
		}
		return totalCount;
	}

}
