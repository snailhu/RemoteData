package DataAn.mongo.service;

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
}
