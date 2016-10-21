package DataAn.mongo.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.bson.Document;

import com.mongodb.client.MongoCursor;

public interface IMongoService {


	/**
	* Description: 保存数据到mongodb中
	* @param series 系列 如：j9
	* @param star 星号 如：02
	* @param paramType 参数类型 如：飞轮（flywheel)
	* @param date 时间
	* @param documents 文档集合
	* @param versions 此次上传的版本号
	* @throws Exception
	* @author Shenwp
	* @date 2016年9月2日
	* @version 1.0
	*/
	public void saveCSVData(String series, String star, String paramType,
			String date, List<Document> documents, String versions)
			throws Exception;
	
	public void saveCSVData(String series, String star, String paramType,
			String date, Map<String,List<Document>> map, String versions)
			throws Exception;

	/**
	* Description: 更新mongodb数据
	* @param series 系列 如：j9
	* @param star 星号 如：02
	* @param paramType 参数类型 如：飞轮（flywheel)
	* @param date 时间
	* @param versions
	* @author Shenwp
	* @date 2016年9月2日
	* @version 1.0
	*/
	public void updateCSVDataByVersions(String series, String star, String paramType,
			String date, String versions);

	public void updateCSVDataByVersions(String series, String star, String paramType,
			String versions);
	
	public void find();
	
	public MongoCursor<Document> findByYear_month_day(String series,
			String star, String paramType, String date);
	
	public MongoCursor<Document> findByWeek_of_year(String series,
			String star, String paramType, int week_of_year);
	
	public MongoCursor<Document> findByDate(String series,
			String star, String paramType, Date beginDate, Date endDate);
	
	public long findMovableNumByParamCode(String series,String star , 
			String collectionName, String paramCode,Date beginDate, Date endDate);
}
