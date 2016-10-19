package DataAn.galaxyManager.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import DataAn.common.pageModel.Combo;
import DataAn.common.pageModel.Pager;
import DataAn.common.utils.DateUtil;
import DataAn.galaxyManager.dao.ISeriesDao;
import DataAn.galaxyManager.dao.IStarDao;
import DataAn.galaxyManager.domain.Series;
import DataAn.galaxyManager.domain.Star;
import DataAn.galaxyManager.dto.SeriesDto;
import DataAn.galaxyManager.service.ISeriesService;

@Service
public class SeriesServiceImpl implements ISeriesService{

	@Resource
	private ISeriesDao seriesDao;
	@Resource
	private IStarDao starDao;
	
	@Override
	@Transactional
	public void saveSeries(SeriesDto dto) {
		Series series = new Series();
		series.setName(dto.getName());
		series.setDescription(dto.getDescription());
		seriesDao.add(series);
		
	}
	
	@Override
	@Transactional
	public void deleteSeries(String seriesIds) {
		String[] ids = seriesIds.split(",");
		for (String strId : ids) {
			long seriesId = Long.parseLong(strId);
			starDao.deleteBySeriesId(seriesId);
			seriesDao.delete(seriesId);
		}
	}

	@Override
	@Transactional
	public void updateSeries(SeriesDto dto) {
		Series series = seriesDao.get(dto.getId());
		if(StringUtils.isNotBlank(dto.getName())){
			series.setName(dto.getName());			
		}
		if(StringUtils.isNotBlank(dto.getDescription())){
			series.setDescription(dto.getDescription());
		}
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
	
	@Override
	public void initSeries() {
		List<Series> seriesList = seriesDao.findAll();
		if(seriesList == null || seriesList.size() == 0){
			Series series = new Series();
			series.setName("j9");
			series.setDescription("j9系列");
			seriesDao.add(series);
			List<Star> list = new ArrayList<Star>();
			Star star1 = new Star();
			star1.setSeries(series);
			star1.setName("01");
			star1.setDescription("01星");
			star1.setStartRunDate(new Date());
			list.add(star1);
			Star star2 = new Star();
			star2.setSeries(series);
			star2.setName("02");
			star2.setDescription("02星");
			star2.setStartRunDate(new Date());
			list.add(star2);
			Star star3 = new Star();
			star3.setSeries(series);
			star3.setName("03");
			star3.setDescription("03星");
			star3.setStartRunDate(new Date());
			list.add(star3);
			Star star4 = new Star();
			star4.setSeries(series);
			star4.setName("04");
			star4.setDescription("04星");
			star4.setStartRunDate(new Date());
			list.add(star4);
			Star star5 = new Star();
			star5.setSeries(series);
			star5.setName("05");
			star5.setDescription("05星");
			star5.setStartRunDate(new Date());
			list.add(star5);
			starDao.add(list);
		}
	}

	@Override
	public List<Combo> getSeriesComboData(long seriesId) {
		List<Combo> comboList = new ArrayList<Combo>();
		List<Series> list = seriesDao.findAll();
		if(list != null && list.size() > 0){
			Combo combo = null;
			for (Series series : list) {
				combo = new Combo();
				combo.setText(series.getName());
				combo.setValue(series.getName());
				if(series.getId() == seriesId){
					combo.setSelected(true);
				}
				comboList.add(combo);
			}
		}
		return comboList;
	}


}
