package DataAn.galaxyManager.service;

import java.util.List;

import DataAn.common.pageModel.Combo;
import DataAn.common.pageModel.Pager;
import DataAn.galaxyManager.dto.SeriesDto;

/**
* Title: ISeriesService
* @Description: 编辑系列信息的服务类
* @author  Shewp
* @date 2016年7月27日
*/
public interface ISeriesService {
	
	public void saveSeries(SeriesDto dto);
	
	public void deleteSeries(String seriesIds);
	
	public void updateSeries(SeriesDto dto);
	
	public Pager<SeriesDto> getRoleList(int pageIndex, int pageSize);
	
	public SeriesDto getSeriesDto(long seriesId);

	public void initSeries();

	public List<Combo> getSeriesComboData(long seriesId);
}
