package DataAn.reportManager.dao;

import java.util.List;

import DataAn.common.dao.IBaseDao;
import DataAn.common.dao.Pager;
import DataAn.reportManager.domain.StarParam;

public interface IStarParamDao  extends IBaseDao<StarParam> {

	Pager<StarParam> selectByOption(int pageIndex, int pageSize, String series, String star, String parameterType,String paramCode);

	boolean cherkStarParam(String series,String star,String partsType,String paramCode);

	List<StarParam> getStarParamForReport(String seriesId, String starId, String partsType);
	
	long getStarParamForReportCount(String seriesId, String starId, String partsType);

	public List<StarParam>  getStarParamByParts();
}
