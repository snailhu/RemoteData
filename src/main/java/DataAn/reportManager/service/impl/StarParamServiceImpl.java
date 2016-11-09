package DataAn.reportManager.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import DataAn.Analysis.dto.ConstraintDto;
import DataAn.common.dao.Pager;
import DataAn.galaxyManager.dao.ISeriesDao;
import DataAn.galaxyManager.dao.IStarDao;
import DataAn.galaxyManager.domain.Series;
import DataAn.galaxyManager.domain.Star;
import DataAn.galaxyManager.option.J9Series_Star_ParameterType;
import DataAn.galaxyManager.service.IJ9Series_Star_Service;
import DataAn.reportManager.dao.IStarParamDao;
import DataAn.reportManager.domain.StarParam;
import DataAn.reportManager.dto.StarParamDto;
import DataAn.reportManager.service.IStarParamService;

@Service
public class StarParamServiceImpl implements IStarParamService {

	@Resource
	private IStarParamDao starParamDao;
	
	@Resource
	private ISeriesDao seriesDao;
	
	@Resource
	private IStarDao starDao;
	
	@Resource
	private IJ9Series_Star_Service j9Series_Star_Service;
	
	
	@Override
	public Pager<StarParamDto> getStarParamList(int pageIndex, int pageSize, String series, String star, String partsType,String paramCode) throws Exception {

		if(pageIndex == 0){
			pageIndex = 1;
		}
		List<StarParamDto>  starParamModelList = new ArrayList<StarParamDto>();
		Pager<StarParam> paramPager = starParamDao.selectByOption(pageIndex, pageSize, series, 
				star, partsType,paramCode); 
		List<StarParam> userList = paramPager.getDatas();
		for (StarParam starParam : userList) {
			starParamModelList.add(pojoToDto(starParam));
		}
		Pager<StarParamDto> pager = new Pager<StarParamDto>(pageIndex, pageSize, paramPager.getTotalCount(), starParamModelList);
		return pager;			
	}
	
	private StarParamDto pojoToDto(StarParam starParam) throws Exception {
		StarParamDto starParamDto = new StarParamDto();
		starParamDto.setId(starParam.getId());
		starParamDto.setParamCode(starParam.getParamCode());
		starParamDto.setParamName(getParamCNname(starParamDto.getSeries(),starParamDto.getStar(),starParamDto.getParamCode()));
		starParamDto.setEffeMin(starParam.getEffeMin());
		starParamDto.setEffeMax(starParam.getEffeMax());
		starParamDto.setParameterType(starParam.getParameterType());
		starParamDto.setProductName(starParam.getProductName());
		starParamDto.setPartsType(J9Series_Star_ParameterType.getJ9SeriesStarParameterType(starParam.getPartsType()).getName());
		starParamDto.setSeries(getSeriesName(starParam.getSeries()));
		starParamDto.setStar(getStarName(starParam.getStar()));
		starParamDto.setCreater(starParam.getCreater());
		starParamDto.setCreateDate(starParam.getCreateDate());
		return starParamDto;
	}

	private String getStarName(String star) {
		return starDao.getStarName(star);
	}

	private String getSeriesName(String series) {
		return seriesDao.getSeriesName(series);
	}

	@Override
	public void save(StarParamDto starParamDto) throws Exception {
		StarParam starParam = new StarParam();
		starParam.setCreater(starParamDto.getCreater());
		starParam.setSeries(starParamDto.getSeries());
		starParam.setStar(starParamDto.getStar());
		starParam.setPartsType(starParamDto.getPartsType());
		starParam.setParameterType(j9Series_Star_Service.getFlyWheelParameterType(starParamDto.getSeries(),starParamDto.getStar(),starParamDto.getParamCode()));
		starParam.setProductName(j9Series_Star_Service.getFlyWheelName(starParamDto.getSeries(),starParamDto.getStar(),starParamDto.getParamCode()));
		starParam.setParamCode(starParamDto.getParamCode());
		starParam.setParamName(getParamCNname(starParamDto.getSeries(),starParamDto.getStar(),starParamDto.getParamCode()));
		starParam.setEffeMin(starParamDto.getEffeMin());
		starParam.setEffeMax(starParamDto.getEffeMax());
		starParam.setCreateDate(new Date());
		starParamDao.add(starParam);
	}
	
	private String getParamCNname(String series ,String star,String EnName) throws Exception {
		String cnName = "";
		for (ConstraintDto constraintDto : j9Series_Star_Service.getFlyWheelParameterList(series,star)) {
			if (EnName.equals(constraintDto.getValue())) {
				cnName = constraintDto.getName();
				break;
			}
		}
		for (ConstraintDto constraintDto : j9Series_Star_Service.getTopParameterList(series,star)) {
			if (EnName.equals(constraintDto.getValue())) {
				cnName = constraintDto.getName();
				break;
			}
		}
		return cnName;
	}

	@Override
	public boolean cherkStarParam(String series,String star,String partsType,String paramCode) {
		return starParamDao.cherkStarParam( series, star, partsType, paramCode);
	}

	@Override
	public void update(StarParamDto starParamDto) throws Exception {
		
		StarParam starParam = starParamDao.get(starParamDto.getId());
		if (StringUtils.isNotBlank(starParamDto.getSeries())) {
			starParam.setSeries(starParamDto.getSeries());
		}
		if (StringUtils.isNotBlank(starParamDto.getStar())) {
			starParam.setStar(starParamDto.getStar());
		}
		if (StringUtils.isNotBlank(starParamDto.getPartsType())) {
			starParam.setPartsType(starParamDto.getPartsType());
		}
		if (StringUtils.isNotBlank(starParamDto.getParamCode())) {
			starParam.setParamCode(starParamDto.getParamCode());
			starParam.setParamName(getParamCNname(starParamDto.getSeries(),starParamDto.getStar(),starParamDto.getParamCode()));
			starParam.setParameterType(j9Series_Star_Service.getFlyWheelParameterType(starParamDto.getSeries(),starParamDto.getStar(),starParamDto.getParamCode()));
			starParam.setProductName(j9Series_Star_Service.getFlyWheelName(starParamDto.getSeries(),starParamDto.getStar(),starParamDto.getParamCode()));
		}
		starParam.setEffeMax(starParamDto.getEffeMax());
		starParam.setEffeMin(starParamDto.getEffeMin());
		starParamDao.update(starParam);
	}

	@Override
	public void delete(String[] starParamIdArray) {
		for (String starParamId : starParamIdArray) {
			starParamDao.delete(Long.parseLong(starParamId)); 
		}
	}

	@Override
	public List<Series> getSeriesList() { 
		return seriesDao.list("from Series s");
	}

	@Override
	public List<Star> getStarList(String seriesId) {
		return starDao.getStarList(seriesId);
	}

	@Override
	public List<StarParam> getStarParamForReport(String seriesId, String starId, String partsType) {
		return starParamDao.getStarParamForReport(seriesId,starId,partsType);
	}
	@Override
	public List<ConstraintDto> getConstraintList(String series,String star,String paramType) throws Exception{
		List<ConstraintDto> constraintDtoList = new ArrayList<ConstraintDto>();
		
		List<ConstraintDto> list = j9Series_Star_Service.getAllParameterList("", "", series, star, paramType);
		for (ConstraintDto constraintDto : list) {
			if(constraintDto.getParentId() != 0) {
				constraintDtoList.add(constraintDto);
			}
		}
		return constraintDtoList;
	}
}
