package DataAn.mongo.fs;

import java.io.InputStream;
/**
* Title: IDfsDb
* @Description: 文件服务器的接口
* @author  Shewp
* @date 2016年7月28日
*/
public interface IDfsDb {

	/**
	* @Title: upload
	* @Description: 通过本地文件目录上传文件
	* @param mongoFSUUId 跟数据库对应的唯一表示UUID
	* @param filePath 本地文件路径
	* @throws Exception
	* @author Shenwp
	* @date 2016年6月17日
	* @version 1.0
	*/
	public abstract boolean upload(String mongoFSUUId,String filePath) throws Exception ;
	public abstract boolean upload(String databaseName, String mongoFSUUId,String filePath) throws Exception ;
	
	/**
	* @Title: upload
	* @Description: 已流形式上传
	* @param fileName 文件名称
	* @param mongoFSUUId 跟数据库对应的唯一表示UUID
	* @param in 二进制流
	* @author Shenwp
	* @date 2016年6月17日
	* @version 1.0
	*/
	public abstract boolean upload(String fileName,String mongoFSUUId, InputStream in);
	public abstract boolean upload(String databaseName, String fileName,String mongoFSUUId, InputStream in);
	/**
	* @Title: downLoad
	* @Description: 下载文件
	* @param mongoFSUUId 跟数据库对应的唯一表示UUID
	* @param local 本地文件路径
	* @return
	* @throws Exception
	* @author Shenwp
	* @date 2016年6月17日
	* @version 1.0
	*/
	public abstract boolean downLoadToLocal(String mongoFSUUId, String localPath) throws Exception;
	public abstract boolean downLoadToLocal(String databaseName, String mongoFSUUId, String localPath) throws Exception;

	public abstract InputStream downLoadToStream(String mongoFSUUId);
	public abstract InputStream downLoadToStream(String databaseName, String mongoFSUUId);

	/**
	 * 删除文件
	 * @param mongoFSUUId
	 * @throws Exception
	 */
	public abstract boolean delete(String mongoFSUUId);
	public abstract boolean delete(String databaseName, String mongoFSUUId);

	/**
	 * 重命名文件
	 * @param src
	 * @param dst
	 * @throws Exception
	 */
	public abstract boolean rename(String src, String dst) throws Exception;

	/**
	 * 创建文件夹
	 * @param dir
	 * @throws Exception
	 */
	public abstract boolean mkdir(String dir) throws Exception;
	
	/**
	 * 移动或复制文件
	 * @param path
	 * @param dst
	 * @param flag true 移动文件;false 复制文件
	 * @throws Exception
	 */
	public abstract boolean copy(String[] path, String dst, boolean flag)
			throws Exception;

	public abstract void showList();
}