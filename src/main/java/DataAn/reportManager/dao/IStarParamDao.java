package DataAn.reportManager.dao;

import java.util.List;

import DataAn.common.dao.IBaseDao;
import DataAn.common.dao.Pager;
import DataAn.reportManager.domain.StarParam;
import DataAn.reportManager.dto.StarParamDto;

public interface IStarParamDao  extends IBaseDao<StarParam> {

	Pager<StarParam> selectByOption(int pageIndex, int pageSize, String series, String star, String parameterType);

	boolean cherkStarParam(StarParamDto starParamDto);

	List<StarParam> getStarParamForReport(String seriesId, String starId, String partsType);

	public List<StarParam>  getStarParamByParts();
}
