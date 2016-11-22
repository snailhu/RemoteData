package DataAn.galaxyManager.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import DataAn.common.pageModel.Combo;
import DataAn.common.utils.DateUtil;
import DataAn.galaxyManager.dao.IDeviceDao;
import DataAn.galaxyManager.dao.ISeriesDao;
import DataAn.galaxyManager.dao.IStarDao;
import DataAn.galaxyManager.domain.Series;
import DataAn.galaxyManager.domain.Star;
import DataAn.galaxyManager.dto.StarDto;
import DataAn.galaxyManager.service.IStarService;

@Service
public class StarServiceImpl implements IStarService {

	@Resource
	private IStarDao starDao;
	@Resource
	private ISeriesDao seriesDao;
	@Resource
	private IDeviceDao deviceDao;

	@Override
	@Transactional
	public boolean saveStar(StarDto starDto) {
		Series series = seriesDao.get(starDto.getSeriesId());
		if (series == null) {
			return false;
		}
		Star star = new Star();
		star.setSeries(series);
		star.setName(starDto.getName());
		star.setCode(starDto.getCode());
		star.setDescription(starDto.getDescription());
		star.setStartRunDate(DateUtil.format(starDto.getBeginDate()));
		starDao.add(star);
		return true;
	}

	@Override
	@Transactional
	public boolean deleteStar(long starId) {
		starDao.delete(starId);
		return true;
	}

	@Override
	@Transactional
	public boolean updateStar(StarDto starDto) {
		Star star = starDao.get(starDto.getId());
		if (StringUtils.isNotBlank(starDto.getName())) {
			star.setName(starDto.getName());
		}
		if (StringUtils.isNotBlank(starDto.getCode())) {
			star.setCode(starDto.getCode());
		}
		if (StringUtils.isNotBlank(starDto.getDescription())) {
			star.setDescription(starDto.getDescription());
		}
		if (StringUtils.isNotBlank(starDto.getBeginDate())) {
			star.setStartRunDate(DateUtil.format(starDto.getBeginDate()));
		}
		starDao.update(star);
		return false;
	}

	@Override
	public List<StarDto> getStarsBySeriesId(long seriesId) {
		List<StarDto> starDtoList = new ArrayList<StarDto>();
		List<Star> list = starDao.findByParam("series.id", seriesId);
		if (list != null && list.size() > 0) {
			for (Star star : list) {
				starDtoList.add(this.pojoToDto(star));
			}
		}
		return starDtoList;
	}

	@Override
	public StarDto getStarDto(long starId) {
		Star star = starDao.get(starId);
		return this.pojoToDto(star);
	}

	@Override
	public boolean isExistStarByName(StarDto starDto) {
		List<Star> list = starDao.getStarBySeriesIdAndName(starDto.getSeriesId(), starDto.getName());
		if(list != null && list.size() > 0){
			if(starDto.getId() == 0){
				return true;				
			}else{
				if(starDto.getId() != list.get(0).getId()){
					return true;
				}
			}
		}
		return false;
	}

	private StarDto pojoToDto(Star star) {
		StarDto dto = new StarDto();
		dto.setId(star.getId());
		dto.setName(star.getName());
		dto.setCode(star.getCode());
		dto.setBeginDate(DateUtil.format(star.getStartRunDate()));
		dto.setDescription(star.getDescription());
		dto.setRunDays(deviceDao.getTotalRuntimeByStar(star.getSeries().getId().toString(), star.getId().toString()));
		return dto;
	}

	@Override
	public List<Combo> getStarComboData(String seriesCode, String starCode) {
		List<Combo> comboList = new ArrayList<Combo>();
		Series series = seriesDao.selectByCode(seriesCode);
		if (series != null) {
			List<Star> list = starDao.getStarListBySeriesId(series.getId());
			if (list != null && list.size() > 0) {
				Combo combo = null;
				for (Star star : list) {
					combo = new Combo();
					combo.setText(star.getName());
					combo.setValue(star.getCode());
					if (star.getCode().equals(starCode)) {
						combo.setSelected(true);
					}
					comboList.add(combo);
				}
			}
		}
		return comboList;
	}

}
