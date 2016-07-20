package DataAn.mongo.fs;

import java.io.InputStream;
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
	public abstract boolean downLoad(String mongoFSUUId, String local) throws Exception;

	public abstract InputStream downLoad(String mongoFSUUId);
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
	 * 删除文件
	 * @param mongoFSUUId
	 * @throws Exception
	 */
	public abstract boolean delete(String mongoFSUUId);

	/**
	 * 查询文件夹
	 * @param dir
	 * @return
	 * @throws Exception
	 */
//	public abstract List<FileSystemVo> queryAll(String dir) throws Exception;

	/**
	 * 移动或复制文件
	 * @param path
	 * @param dst
	 * @param flag true 移动文件;false 复制文件
	 * @throws Exception
	 */
	public abstract boolean copy(String[] path, String dst, boolean flag)
			throws Exception;

//	public abstract List<Menu> tree(String dir) throws Exception;

	public abstract void showList();
}