package DataAn.fileSystem.service;

import java.io.InputStream;
import java.util.List;
import java.util.Map;
import org.bson.Document;

/**
* Title: ICSVService
* @Description: 自己手动解析csv文件服务类
* @author  Shewp
* @date 2016年7月28日
*/
public interface ICSVService {

	/**
	* @Title: readCSVFileToDoc
	* @Description: 以系统路径的方式读取cav文件并将它转化为document类型
	* @param filePath 系统路径
	* @param versions 标志某一次上传的一个版本号 方便数据库事务会滚 可以是UUID
	* @return
	* @throws Exception
	* @author Shenwp
	* @date 2016年7月8日
	* @version 1.0
	*/
	public List<Document> readCSVFileToDoc(String filePath, String versions) throws Exception;
	
	/**
	* @Title: readCSVFileToDoc
	* @Description: 以读取流的方式读取cav文件并将它转化为document类型
	* @param versions 标志某一次上传的一个版本号 方便数据库事务会滚 可以是UUID
	* @param in 二进制流
	* @return
	* @throws Exception
	* @author Shenwp
	* @date 2016年7月8日
	* @version 1.0
	*/
	public List<Document> readCSVFileToDoc(InputStream in, String versions) throws Exception;
	
	/**
	* Description: 通过算法1 删除前后记录
	* @param in 输入流
	* @param versions 标志某一次上传的一个版本号 方便数据库事务会滚 可以是UUID
	* @param delNumber 删除前后记录数
	* @param totalNumber 获取总记录数 0为全部
	* @return
	* @throws Exception
	* @author Shenwp
	* @date 2016年7月29日
	* @version 1.0
	*/
	public List<Document> readCSVFileToDoc_delFrontAndBack_arithmetic1(InputStream in, String versions, int delNumber, int totalNumber) throws Exception;
	
	/**
	* Description: 通过算法2 删除前后记录
	* @param in 输入流
	* @param versions 标志某一次上传的一个版本号 方便数据库事务会滚 可以是UUID
	* @param delNumber 删除前后记录数
	* @param totalNumber 获取总记录数 0为全部
	* @return
	* @throws Exception
	* @author Shenwp
	* @date 2016年7月29日
	* @version 1.0
	*/
	public List<Document> readCSVFileToDoc_delFrontAndBack_arithmetic2(InputStream in, String versions, int delNumber, int totalNumber) throws Exception;
	
	/**
	* Description: 删除某一条无效值
	* @param in 输入流
	* @param versions 标志某一次上传的一个版本号 方便数据库事务会滚 可以是UUID
	* @param totalNumber 获取总记录数 0为全部
	* @return
	* @throws Exception
	* @author Shenwp
	* @date 2016年7月29日
	* @version 1.0
	*/
	public List<Document> readCSVFileToDoc_delOneItem(InputStream in, String versions, int totalNumber) throws Exception;
	
	/**
	* Description: 删除某一秒无效值
	* @param in 输入流
	* @param versions 标志某一次上传的一个版本号 方便数据库事务会滚 可以是UUID
	* @param totalNumber 获取总记录数 0为全部
	* @return
	* @throws Exception
	* @author Shenwp
	* @date 2016年7月29日
	* @version 1.0
	*/
	public List<Document> readCSVFileToDoc_delOneSecondItems(InputStream in, String versions, int totalNumber) throws Exception;
	
	/**
	* @Title: readCSVFileOfJsonToDoc
	* @Description: 以系统路径的方式读取cav文件并将它以json拼接，再转化为document类型
	* @param filePath 系统路径
	* @return
	* @throws Exception
	* @author Shenwp
	* @date 2016年7月8日
	* @version 1.0
	*/
	public List<Document> readCSVFileOfJsonToDoc(String filePath) throws Exception;
	
	/**
	* @Title: readCSVFileOfJsonToDoc
	* @Description: 以流的方式读取cav文件并将它以json拼接，再转化为document类型
	* @param in 二进制流
	* @return
	* @throws Exception
	* @author Shenwp
	* @date 2016年7月8日
	* @version 1.0
	*/
	public List<Document> readCSVFileOfJsonToDoc(InputStream in) throws Exception;
	
	@Deprecated
	public List<Map<String,String>> readCSVFile(String filePath) throws Exception;
	
	@Deprecated
	public List<Map<String,String>> readCSVFile(InputStream in) throws Exception;
	
	@Deprecated
	public String readCSVFileOfJson(String filePath) throws Exception;
	
	@Deprecated
	public String readCSVFileOfJson(InputStream in) throws Exception;
}
