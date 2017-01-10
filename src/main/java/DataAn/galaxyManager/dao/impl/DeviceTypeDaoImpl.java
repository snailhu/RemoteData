package DataAn.galaxyManager.dao.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import DataAn.common.dao.BaseDaoImpl;
import DataAn.common.dao.Pager;
import DataAn.galaxyManager.dao.IDeviceTypeDao;
import DataAn.galaxyManager.domain.DeviceType;

@Repository
public class DeviceTypeDaoImpl extends BaseDaoImpl<DeviceType> implements IDeviceTypeDao {

	@Override
	public Pager<DeviceType> selectByPager(int pageIndex, int pageSize, String userType) {
		String hql = "from DeviceType d where 1=1";
		String countHQL = "select count(*) from DeviceType d where 1=1";
		if (StringUtils.isNotBlank(userType)) {
			hql += " and d.deviceCode = '" + userType + "'";
			countHQL += " and d.deviceCode = '" + userType + "'";
		}
		Query query = this.getSession().createQuery(hql);
		Query countQuery = this.getSession().createQuery(countHQL);
		List<DeviceType> list = query.setMaxResults(pageSize).setFirstResult(pageSize * (pageIndex - 1)).list();
		Long totalCount = 0l;
		Object obj = countQuery.uniqueResult();
		if (obj != null) {
			totalCount = (Long) obj;
		}
		Pager<DeviceType> pager = new Pager<DeviceType>(pageIndex, pageSize, totalCount, list);
		return pager;
	}

	@Override
	public List<DeviceType> getDeviceTypeList() {
		String hql = "from DeviceType d where 1=1";
		Query query = this.getSession().createQuery(hql);
		List<DeviceType> list = query.list();
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public DeviceType selectByDeviceCode(String deviceCode) {
		String hql = "from DeviceType d where d.deviceCode=?";
		List<DeviceType> list = this.getSession().createQuery(hql).setParameter(0, deviceCode).list();
		if(list != null && list.size() > 0)
			return list.get(0);
		return null;
	}

}
