package DataAn.galaxyManager.dao;

import java.util.List;

import DataAn.common.dao.IBaseDao;
import DataAn.common.dao.Pager;
import DataAn.galaxyManager.domain.DeviceType;

public interface IDeviceTypeDao extends IBaseDao<DeviceType> {
	public Pager<DeviceType> selectByPager(int pageIndex, int pageSize);

	public List<DeviceType> getDeviceTypeList();
}
