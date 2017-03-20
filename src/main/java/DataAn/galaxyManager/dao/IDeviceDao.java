package DataAn.galaxyManager.dao;

import java.util.List;

import DataAn.common.dao.IBaseDao;
import DataAn.common.dao.Pager;
import DataAn.galaxyManager.domain.Device;
import DataAn.galaxyManager.dto.DeviceViewDTO;

public interface IDeviceDao extends IBaseDao<Device> {
	public List<Device> getDevicesBySeriesAndStar(String series, String star, String deviceType, String model);

	public Pager<Device> selectByPager(String series, String star, int pageIndex, int pageSize);

	public int getTotalRuntimeByDeviceType(String series, String star, String deviceType);

	public int getTotalRuntimeByStar(String series, String star);

	public int getTotalRuntimeByStarAndModel(String series, String star, String model, String deviceType);

	public int getTotalRuntimeBySeries(String series);

	public Long getTotalNumByStarAndModel(String series, String star, String model, String deviceType);

	public boolean checkDevice(String series, String star, String deviceType, String deviceName);

	public Pager<DeviceViewDTO> selectViewDTObyPager(String deviceType, String model, int pageIndex, int pageSize);
	
	public void deleteBySeriesId(long seriesId);
	
	public void deleteByStarId(long starId);

}
