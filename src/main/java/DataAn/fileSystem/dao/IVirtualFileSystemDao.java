package DataAn.fileSystem.dao;

import java.util.List;

import DataAn.common.dao.IBaseDao;
import DataAn.fileSystem.domain.VirtualFileSystem;


public interface IVirtualFileSystemDao extends IBaseDao<VirtualFileSystem>{

	public VirtualFileSystem selectByParentIdisNullAndFileName(String fileName);
	
	public VirtualFileSystem selectByParentIdAndFileName(long parentId,String fileName);
	
	public VirtualFileSystem selectByyear_month_day(String year_month_day);
	
	public List<VirtualFileSystem> selectByParentIdisNullAndOrder(String order);
	
	public List<VirtualFileSystem> selectBySeriesAndStarAndParentIdisNullAndOrder(String series, String star, String order);
	
	public List<VirtualFileSystem> selectBySeriesAndStarAndParentIdAndOrder(String series, String star, long parentId, String order);

	public List<VirtualFileSystem> selectByOption(String series, String star, long parentId,
			String beginTime,String endTime,String dataTypes, String order);
}
