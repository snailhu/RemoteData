package DataAn.galaxyManager.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import DataAn.common.dao.Pager;
import DataAn.common.pageModel.Combo;
import DataAn.common.utils.DateUtil;
import DataAn.galaxyManager.dao.IDeviceDao;
import DataAn.galaxyManager.dao.ISeriesDao;
import DataAn.galaxyManager.dao.IStarDao;
import DataAn.galaxyManager.domain.Series;
import DataAn.galaxyManager.domain.Star;
import DataAn.galaxyManager.dto.SeriesDto;
import DataAn.galaxyManager.option.J9SeriesType;
import DataAn.galaxyManager.option.SeriesType;
import DataAn.galaxyManager.service.ISeriesService;
import DataAn.sys.domain.User;

@Service
public class SeriesServiceImpl implements ISeriesService {

	@Resource
	private ISeriesDao seriesDao;
	@Resource
	private IStarDao starDao;
	@Resource
	private IDeviceDao deviceDao;

	@Override
	@Transactional
	public void saveSeries(SeriesDto dto) {
		Series series = new Series();
		series.setName(dto.getName());
		series.setCode(dto.getCode());
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
		if (StringUtils.isNotBlank(dto.getName())) {
			series.setName(dto.getName());
		}
		if (StringUtils.isNotBlank(dto.getCode())) {
			series.setCode(dto.getCode());
		}
		if (StringUtils.isNotBlank(dto.getDescription())) {
			series.setDescription(dto.getDescription());
		}
		seriesDao.update(series);
	}

	@Override
	public Pager<SeriesDto> getSeriesList(int pageIndex, int pageSize) {
		List<SeriesDto> seriesDtoList = new ArrayList<SeriesDto>();
		Pager<Series> seriesPager = seriesDao.selectByPager(pageIndex, pageSize);
		List<Series> list = seriesPager.getDatas();
		if (list != null && list.size() > 0) {
			for (Series series : list) {
				SeriesDto dto = new SeriesDto();
				dto.setId(series.getId());
				dto.setName(series.getName());
				dto.setCode(series.getCode());
				dto.setCreateDate(DateUtil.format(series.getCreateDate()));
				dto.setDescription(series.getDescription());
				int rundays = deviceDao.getTotalRuntimeBySeries(series.getId().toString());
				dto.setRunDays(rundays);
				seriesDtoList.add(dto);
			}
		}
		Pager<SeriesDto> pager = new Pager<SeriesDto>(pageIndex, pageSize, seriesPager.getTotalCount(), seriesDtoList);
		return pager;
	}

	@Override
	public List<SeriesDto> getAllSeries() {
		List<SeriesDto> seriesDtoList = new ArrayList<SeriesDto>();
		List<Series> list = seriesDao.findAll();
		if (list != null && list.size() > 0) {
			for (Series series : list) {
				seriesDtoList.add(this.pojoToDto(series));
			}
		}
		return seriesDtoList;
	}

	@Override
	public SeriesDto getSeriesDtoById(long seriesId) {
		Series series = seriesDao.get(seriesId);
		return this.pojoToDto(series);
	}
	@Override
	public SeriesDto getSeriesDtoByCode(String code){
		Series series = seriesDao.selectByCode(code);
		return this.pojoToDto(series);
	}
	@Override
	public List<Combo> getSeriesComboData(String seriesCode) {
		List<Combo> comboList = new ArrayList<Combo>();
		List<Series> list = seriesDao.findAll();
		if (list != null && list.size() > 0) {
			Combo combo = null;
			for (Series series : list) {
				combo = new Combo();
				combo.setText(series.getName());
				combo.setValue(series.getCode());
				if (series.getCode().equals(seriesCode)) {
					combo.setSelected(true);
				}
				comboList.add(combo);
			}
		}
		return comboList;
	}

	@Override
	public boolean checkSeriesAndStar(String series, String star) {
		List<Series> seriesList = seriesDao.findByParam("code", series);
		if (seriesList != null && seriesList.size() > 0) {
			List<Star> starList = starDao.getStarBySeriesIdAndCode(seriesList.get(0).getId(), star);
			if (starList != null && starList.size() > 0) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean isExistSeriesByName(SeriesDto dto) {
		List<Series> list = seriesDao.findByParam("name", dto.getName());
		if(list != null && list.size() > 0){
			if(dto.getId() == 0){
				return true;				
			}else{
				if(dto.getId() != list.get(0).getId()){
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public boolean isExistSeriesByCode(SeriesDto dto) {
		List<Series> list = seriesDao.findByParam("code", dto.getCode());
		if(list != null && list.size() > 0){
			if(dto.getId() == 0){
				return true;				
			}else{
				if(dto.getId() != list.get(0).getId()){
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public void initJ9Series() {
		List<Series> seriesList = seriesDao.findAll();
		if (seriesList == null || seriesList.size() == 0) {
			Series series = new Series();
			series.setName(SeriesType.J9_SERIES.getName());
			series.setCode(SeriesType.J9_SERIES.getValue());
			series.setDescription(SeriesType.J9_SERIES.getName() + "系列");
			seriesDao.add(series);
			List<Star> list = new ArrayList<Star>();
			J9SeriesType[] types = J9SeriesType.values();
			for (J9SeriesType j9SeriesType : types) {
				Star star = new Star();
				star.setSeries(series);
				star.setName(j9SeriesType.getValue());
				star.setCode(j9SeriesType.getValue());
				star.setDescription(j9SeriesType.getValue() + "星");
				star.setStartRunDate(j9SeriesType.getStartRunDate());
				list.add(star);
			}
			starDao.add(list);
		}
	}

	private SeriesDto pojoToDto(Series series) {
		SeriesDto dto = new SeriesDto();
		dto.setId(series.getId());
		dto.setName(series.getName());
		dto.setCode(series.getCode());
		dto.setCreateDate(DateUtil.format(series.getCreateDate()));
		dto.setDescription(series.getDescription());
		return dto;
	}

}
