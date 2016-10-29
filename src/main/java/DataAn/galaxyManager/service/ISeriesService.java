package DataAn.galaxyManager.service;

import java.util.List;

import DataAn.common.dao.Pager;
import DataAn.common.pageModel.Combo;
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
	
	public Pager<SeriesDto> getSeriesList(int pageIndex, int pageSize);
	
	public List<SeriesDto> getAllSeries();
	
	public SeriesDto getSeriesDto(long seriesId);

	public List<Combo> getSeriesComboData(long seriesId);
	
	public boolean checkSeriesAndStar(String series, String star);
	
	public void initJ9Series();
}
