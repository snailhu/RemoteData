package DataAn.fileSystem.service;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

import org.bson.Document;

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
