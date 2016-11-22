package DataAn.galaxyManager.dao;

import java.util.List;

import DataAn.common.dao.IBaseDao;
import DataAn.common.dao.Pager;
import DataAn.galaxyManager.domain.Device;

public interface IDeviceDao extends IBaseDao<Device> {
	public List<Device> getDevicesBySeriesAndStar(String series, String star, String deviceType);

	public Pager<Device> selectByPager(String series, String star, int pageIndex, int pageSize);

	public int getTotalRuntimeByDeviceType(String series, String star, String deviceType);

	public int getTotalRuntimeByStar(String series, String star);

	public int getTotalRuntimeBySeries(String series);

	public boolean checkDevice(String series, String star, String deviceType, String deviceName);

}
