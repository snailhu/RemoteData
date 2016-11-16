package DataAn.galaxyManager.service;

import java.util.List;

import DataAn.common.pageModel.Combo;
import DataAn.galaxyManager.dto.StarDto;

/**
* Title: IStarService
* @Description: 编辑星的信息的服务类
* @author  Shewp
* @date 2016年7月27日
*/
public interface IStarService {

	public boolean saveStar(StarDto starDto);
	
	public boolean deleteStar(long starId);
	
	public boolean updateStar(StarDto starDto);
	
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
	
	public StarDto getStarDto(long starId);
	
	public boolean isExistStarByName(StarDto starDto);
	
	public List<Combo> getStarComboData(String seriesCode, String starCode);
}
