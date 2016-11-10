package DataAn.reportManager.service;

import java.util.List;

import DataAn.Analysis.dto.ConstraintDto;
import DataAn.common.dao.Pager;
import DataAn.galaxyManager.domain.Series;
import DataAn.galaxyManager.domain.Star;
import DataAn.reportManager.domain.StarParam;
import DataAn.reportManager.dto.StarParamDto;

public interface IStarParamService {

	public Pager<StarParamDto> getStarParamList(int pageIndex, int pageSize, String series, String star, String parameterType,String paramCode) throws Exception;

	public void save(StarParamDto starParamDto) throws Exception ;

	public boolean cherkStarParam(String series,String star,String partsType,String paramCode);

	public void update(StarParamDto starParamDto)  throws Exception;

	public void delete(String[] userIdArray);

	public List<Series> getSeriesList();

	public List<Star> getStarList(String seriesId);
	
	public List<StarParam> getStarParamForReport(String seriesId,String starId,String partsType);

	public List<ConstraintDto> getConstraintList(String series,String star,String paramType) throws Exception;

	public StarParam getParamById(Long id);
}
