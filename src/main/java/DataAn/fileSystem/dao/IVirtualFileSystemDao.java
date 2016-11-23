package DataAn.fileSystem.dao;

import java.util.List;

import DataAn.common.dao.IBaseDao;
import DataAn.common.pageModel.Pager;
import DataAn.fileSystem.domain.VirtualFileSystem;

public interface IVirtualFileSystemDao extends IBaseDao<VirtualFileSystem> {

	public VirtualFileSystem selectByFileTypeIsFileAndMongoFSUUId(String uuId);
	
	public VirtualFileSystem selectByFileName(String fileName);

	public VirtualFileSystem selectByParentIdisNullAndFileName(String fileName);

	public VirtualFileSystem selectByParentIdAndFileName(long parentId,
			String fileName);

	public VirtualFileSystem selectByParentIdAndFileNameAndParameterType(
			long parentId, String fileName, String parameterType);

	public VirtualFileSystem selectBySeriesAndStarAndParameterTypeAndParentIdAndFileName(String series, 
			String star, String parameterType,long parentId,String fileName);

	public Pager<VirtualFileSystem> selectBySeriesAndStarAndParameterTypeAndParentIdAndOrder(
			String series, String star, String parameterType, long parentId, String order,
			int pageIndex, int pageSize);

	public Pager<VirtualFileSystem> selectByOption(String series, String star, String parameterType,
			Long parentId, String beginTime, String endTime, String dataTypes,
			String order, int pageIndex, int pageSize);

	public List<VirtualFileSystem> selectByFileTypeIsFileAndCachePathISNotNull();
}
