package DataAn.galaxyManager.service;

import java.util.List;

import DataAn.galaxyManager.dto.StarDto;

public interface IStarService {

	/**
	* @Title: getStarsBySeriesId
	* @Description: 通过系列获取次系类下的所有星星
	* @param seriesId
	* @return
	* @author Shenwp
	* @date 2016年7月26日
	* @version 1.0
	*/
	public List<StarDto> getStarsBySeriesId(long seriesId);

}
