package DataAn.fileSystem.service;

import java.util.Map;

import DataAn.common.pageModel.Pager;
import DataAn.fileSystem.dto.FileDto;
import DataAn.fileSystem.dto.MongoFSDto;

/**
* Title: IVirtualFileSystemService
* @Description: 虚拟文件目录信息服务类
* @author  Shewp
* @date 2016年7月28日
*/
public interface IVirtualFileSystemService {

	
	/**
	* @Title: saveFile
	* @Description: 保存一组组文件 *.dat和 *.csv
	* @param map key：csv Or dat 
	* @throws Exception
	* @author Shenwp
	* @date 2016年7月8日
	* @version 1.0
	*/
	public void saveFile(Map<String,FileDto> map) throws Exception;
	
	/**
	* Description: 删除一个或多个文件和目录
	* @param ids 如："1/dir,2/file";
	* @author Shenwp
	* @date 2016年8月9日
	* @version 1.0
	*/
	public void deleteFile(String ids);
	
	/**
	* @Title: downloadFile
	* @Description: 下载单个文件
	* @param fileId  文件Id
	* @return
	* @author Shenwp
	* @date 2016年7月8日
	* @version 1.0
	 * @throws Exception 
	*/
	public FileDto downloadFile(long fileId) throws Exception;
	
	/**
	* @Title: downloadFiles
	* @Description: 下载多个文件或者目录 先缓存到缓冲目录下在读取这个目录
	* @param ids 如："1/dir,2/file";
	* @return
	* @throws Exception
	* @author Shenwp
	* @date 2016年7月8日
	* @version 1.0
	*/
	public FileDto downloadFiles(String ids) throws Exception;
	
	/**
	* @Title: downloadFiles
	* @Description: 下载目录下的文件此目录无子目录
	* @param dirId 文件目录Id
	* @return
	* @throws Exception
	* @author Shenwp
	* @date 2016年7月8日
	* @version 1.0
	*/
	public FileDto downloadFiles(long dirId) throws Exception;
	
	/**
	* @Title: isExistFile
	* @Description: 判断当前文件时候存在
	* @param fileName *.csv 或  *.DAT 文件名
	* @return
	* @author Shenwp
	* @date 2016年7月5日
	* @version 1.0
	*/
	public boolean isExistFile(String fileName);
	
	/**
	* @Title: getMongoFSList
	* @Description: 获取文件列表
	* @param pageIndex
	* @param pageSize
	* @param dirId
	* @return
	* @author Shenwp
	* @date 2016年7月8日
	* @version 1.0
	*/
	public Pager<MongoFSDto> getMongoFSList(int pageIndex, int pageSize,long dirId);
	
	public Pager<MongoFSDto> getMongoFSList(int pageIndex, int pageSize,String series, String star, String parameterType, long dirId);
	
	public Pager<MongoFSDto> getMongoFSList(int pageIndex, int pageSize,String series, String star, String parameterType, long dirId,
			String beginTime,String endTime,String dataTypes);
	
	/**
	* Description: 获取当前目录的父级目录列表
	* @param dirId
	* @return
	* @author Shenwp
	* @date 2016年7月28日
	* @version 1.0
	*/
	public String getParentFSCatalog(long dirId);

}
