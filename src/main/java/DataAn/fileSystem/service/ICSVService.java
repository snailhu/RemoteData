package DataAn.fileSystem.service;

import java.io.InputStream;
import org.bson.Document;
import DataAn.fileSystem.dto.CSVFileDataResultDto;

/**
* Title: ICSVService
* @Description: 自己手动解析csv文件服务类
* @author  Shewp
* @date 2016年7月28日
*/
public interface ICSVService {

	/**
	* Description: 以读取流的方式读取cav文件并将它转化为document类型，并保存临时文件内容
	* @param fileName
	* @param in
	* @param versions
	* @return
	* @throws Exception
	* @author Shenwp
	* @date 2016年8月19日
	* @version 1.0
	*/
	public CSVFileDataResultDto<Document> readCSVFileToDocAndgetTitle(String filePath) throws Exception;
	
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
	public CSVFileDataResultDto<Document> readCSVFileToDoc(String filePath, String versions) throws Exception;
	
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
	public CSVFileDataResultDto<Document> readCSVFileToDoc(InputStream in, String versions) throws Exception;
	

//	public CSVFileDataResultDto<Document> readCSVFileToDoc_delFrontAndBack_arithmetic1(InputStream in, String versions, int delNumber, int totalNumber) throws Exception;

//	public List<Document> readCSVFileToDoc_delFrontAndBack_arithmetic2(InputStream in, String versions, int delNumber, int totalNumber) throws Exception;
	
//	public List<Document> readCSVFileToDoc_delOneItem(InputStream in, String versions, int totalNumber) throws Exception;
	
//	public List<Document> readCSVFileToDoc_delOneSecondItems(InputStream in, String versions, int totalNumber) throws Exception;
	

}
