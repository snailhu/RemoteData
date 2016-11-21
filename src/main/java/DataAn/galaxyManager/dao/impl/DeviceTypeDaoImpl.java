package DataAn.galaxyManager.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import DataAn.common.dao.BaseDaoImpl;
import DataAn.common.dao.Pager;
import DataAn.galaxyManager.dao.IDeviceTypeDao;
import DataAn.galaxyManager.domain.DeviceType;

@Repository
public class DeviceTypeDaoImpl extends BaseDaoImpl<DeviceType> implements IDeviceTypeDao {

	@Override
	public Pager<DeviceType> selectByPager(int pageIndex, int pageSize) {
		String hql = "from DeviceType d where 1=1";
		String countHQL = "select count(*) from DeviceType d where 1=1";
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

}
