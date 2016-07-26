package DataAn.galaxyManager.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import DataAn.common.pageModel.Pager;
import DataAn.common.utils.DateUtil;
import DataAn.galaxyManager.dao.ISeriesDao;
import DataAn.galaxyManager.domain.Series;
import DataAn.galaxyManager.dto.SeriesDto;
import DataAn.galaxyManager.service.ISeriesService;

@Service
public class SeriesServiceImpl implements ISeriesService{

	@Resource
	private ISeriesDao seriesDao;
	
	@Override
	@Transactional
	public void saveSeries(SeriesDto dto) {
		Series series = new Series();
		series.setName(dto.getName());
		series.setDescription(dto.getDescription());
		seriesDao.add(series);
		
	}
	
	@Override
	public Pager<SeriesDto> getRoleList(int pageIndex, int pageSize) {
		List<Series> list = seriesDao.findAll();
		List<SeriesDto> seriesDtoList = new ArrayList<SeriesDto>();
		Long totalCount = 0l;
		if(list != null && list.size() > 0){
			SeriesDto dto = null;
			for (Series series : list) {
				dto = new SeriesDto();
				dto.setId(series.getId());
				dto.setName(series.getName());
				dto.setCreateDate(DateUtil.format(series.getCreateDate()));
				dto.setDescription(series.getDescription());
				seriesDtoList.add(dto);
			}
			totalCount = (long) list.size();
		}
		Pager<SeriesDto> pager = new Pager<SeriesDto>(pageIndex, pageSize, totalCount , seriesDtoList);
		return pager;
	}

	@Override
	public SeriesDto getSeriesDto(long seriesId) {
		Series series = seriesDao.get(seriesId);
		SeriesDto dto = new SeriesDto();
		dto.setId(series.getId());
		dto.setName(series.getName());
		dto.setCreateDate(DateUtil.format(series.getCreateDate()));
		dto.setDescription(series.getDescription());
		return dto;
	}



}
