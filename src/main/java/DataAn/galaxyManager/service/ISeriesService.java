package DataAn.galaxyManager.service;

import DataAn.common.pageModel.Pager;
import DataAn.galaxyManager.domain.Series;
import DataAn.galaxyManager.dto.SeriesDto;

public interface ISeriesService {

	public void saveSeries(SeriesDto dto);
	
	public Pager<SeriesDto> getRoleList(int pageIndex, int pageSize);
	
	public SeriesDto getSeriesDto(long seriesId);

}
