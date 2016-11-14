package DataAn.galaxyManager.dao;

import java.util.List;

import DataAn.common.dao.IBaseDao;
import DataAn.galaxyManager.domain.Star;

public interface IStarDao extends IBaseDao<Star> {

	public void deleteBySeriesId(long seriesId);

	public List<Star> getStarList(String seriesId);

	public List<Star> getStarListBySeriesId(String seriesId);

	public String getStarName(String starId);

	public String getStarIdByName(String starName);
	
	public List<Star> getStarBySeriesIdAndName(long seriesId,String name);
}
