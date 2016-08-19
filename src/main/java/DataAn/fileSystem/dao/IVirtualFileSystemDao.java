package DataAn.fileSystem.dao;

import java.util.List;

import DataAn.common.dao.IBaseDao;
import DataAn.common.pageModel.Pager;
import DataAn.fileSystem.domain.VirtualFileSystem;


public interface IVirtualFileSystemDao extends IBaseDao<VirtualFileSystem>{
	
	public VirtualFileSystem selectByFileName(String fileName);

	public VirtualFileSystem selectByParentIdisNullAndFileName(String fileName);
	
	public VirtualFileSystem selectByParentIdAndFileName(long parentId,String fileName);
	
	public VirtualFileSystem selectByyear_month_day(String year_month_day);
	
	public List<VirtualFileSystem> selectByParentIdisNullAndOrder(String order);
	
	public Pager<VirtualFileSystem> selectBySeriesAndStarAndParentIdisNullAndOrder(String series, String star, String order, int pageIndex, int pageSize);
	
	public Pager<VirtualFileSystem> selectBySeriesAndStarAndParentIdAndOrder(String series, String star, long parentId, String order, int pageIndex, int pageSize);

	public Pager<VirtualFileSystem> selectByOption(String series, String star, long parentId,
			String beginTime,String endTime,String dataTypes, String order, int pageIndex, int pageSize);
	
	public List<VirtualFileSystem> selectByFileTypeIsFileAndCachePathISNotNull();
}
