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
	* Description: 针对无效文件，更新文件上传状态
	* @author Shenwp
	* @date 2017年1月10日
	* @version 1.0
	*/
	public void updateFileStatusJob();
	
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
	
	/**
	 * Description: 通过定时任务来删除系统日志，保证日志表中不超过一定数量的记录
	 * @author hanz
	 * @date 2017年2月7号
	 * @throws Exception
	 */
	public void deletesystemlog() throws Exception;
}
