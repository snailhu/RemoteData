package DataAn.galaxyManager.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import DataAn.common.utils.DateUtil;
import DataAn.galaxyManager.dao.ISeriesDao;
import DataAn.galaxyManager.dao.IStarDao;
import DataAn.galaxyManager.domain.Series;
import DataAn.galaxyManager.domain.Star;
import DataAn.galaxyManager.dto.StarDto;
import DataAn.galaxyManager.service.IStarService;

@Service
public class StarServiceImpl implements IStarService{

	@Resource
	private IStarDao starDao;
	@Resource
	private ISeriesDao seriesDao;
	
	@Override
	@Transactional
	public boolean saveStar(StarDto starDto) {
		Series series = seriesDao.get(starDto.getSeriesId());
		if(series == null){
			return false;
		}
		Star star = new Star();
		star.setSeries(series);
		star.setName(starDto.getName());
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
		if(StringUtils.isNotBlank(starDto.getName())){
			star.setName(starDto.getName());			
		}
		if(StringUtils.isNotBlank(starDto.getDescription())){
			star.setDescription(starDto.getDescription());
		}
		if(StringUtils.isNotBlank(starDto.getBeginDate())){
			star.setStartRunDate(DateUtil.format(starDto.getBeginDate()));
		}
		starDao.update(star);
		return false;
	}
	
	@Override
	public List<StarDto> getStarsBySeriesId(long seriesId) {
		List<StarDto> starDtoList = new ArrayList<StarDto>();
		List<Star> list = starDao.findByParam("series.id", seriesId);
		if(list != null && list.size() > 0){
			StarDto dto = null;
			for (Star star : list) {
				dto = new StarDto();
				dto.setId(star.getId());
				dto.setName(star.getName());
				dto.setBeginDate(DateUtil.format(star.getStartRunDate()));
				dto.setDescription(star.getDescription());
				starDtoList.add(dto);
			}
		}
		return starDtoList;
	}

	@Override
	public StarDto getStarDto(long starId) {
		Star star = starDao.get(starId);
		StarDto dto = new StarDto();
		dto.setId(star.getId());
		dto.setName(star.getName());
		dto.setBeginDate(DateUtil.format(star.getStartRunDate()));
		dto.setDescription(star.getDescription());
		return dto;
	}




}
