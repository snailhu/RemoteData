package DataAn.reportManager.dao;

import java.util.List;

import DataAn.common.dao.IBaseDao;
import DataAn.common.pageModel.Pager;
import DataAn.reportManager.domain.ReportFileSystem;

public interface IReportFileSystemDao extends IBaseDao<ReportFileSystem> {

	public ReportFileSystem selectByFileName(String fileName);

	public ReportFileSystem selectByParentIdisNullAndFileName(String fileName);

	public ReportFileSystem selectByParentIdAndFileName(long parentId,
			String fileName);

	public ReportFileSystem selectByParentIdAndFileNameAndParameterType(
			long parentId, String fileName, String parameterType);

	public ReportFileSystem selectByyear_month_day(String year_month_day);

	public List<ReportFileSystem> selectByParentIdisNullAndOrder(String order);

	public Pager<ReportFileSystem> selectBySeriesAndStarAndParameterTypeAndParentIdisNullAndOrder(
			String series, String star, String partsType, String order, int pageIndex,
			int pageSize);

	public Pager<ReportFileSystem> selectBySeriesAndStarAndParameterTypeAndParentIdAndOrder(
			String series, String star, String partsType, long parentId, String order,
			int pageIndex, int pageSize);

	public Pager<ReportFileSystem> selectByOption(String series, String star, String partsType,
			long parentId, String beginTime, String endTime,
			String order, int pageIndex, int pageSize);

	public List<ReportFileSystem> selectByFileTypeIsFileAndCachePathISNotNull();
}
