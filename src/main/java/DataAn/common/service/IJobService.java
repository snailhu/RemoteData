package DataAn.common.service;

/**
* Title: IJobService
* @Description: 作业调度服务类接口
* @author  Shewp
* @date 2016年8月3日
*/
public interface IJobService {

	
	/**
	* Description: 删除mongodb中的无效值
	* @author Shenwp
	* @date 2016年8月3日
	* @version 1.0
	*/
	public void delMongoDBInvalidValueJob();
	
	/**
	* Description: 删除缓存文件
	* @author Shenwp
	* @date 2016年8月5日
	* @version 1.0
	*/
	public void delCacheFileJob();
	
	/**
	* Description: 通过定时任务来离线上传csv原文件
	* @author Shenwp
	* @date 2016年8月15日
	* @version 1.0
	 * @throws Exception 
	*/
	public void saveFileOfCSV() throws Exception;
	
	//每日定时生成报告
	public void createReport() throws Exception;
	
	public void createReport2() throws Exception;
}
