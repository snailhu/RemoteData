package DataAn.common.service;

public interface IInitDataService {

	/**
	* @Title: initDataBase
	* @Description: 初始化数据库
	* @author Shenwp
	* @date 2016年5月17日
	* @version 1.0
	 * @throws Exception 
	*/
	public void initDataBase();
	
	public void initServerConfig();
	
	public void initMongodbConfig();
	
	public void initTopjobConfig();
	
	public void initTopDenoiseConfig();
	
}
