package DataAn.fileSystem.service;

import java.util.List;
import java.util.Map;

import DataAn.Analysis.dto.ConstraintDto;

/**
* Title: IJ9Series_Star_Service
* @Description: j9系列的参数配置 服务类
* @author  Shewp
* @date 2016年7月26日
*/
public interface IJ9Series_Star_Service {
	
	
	public List<ConstraintDto> getAllParameterListFromBeginDateToEndDate(String beginDate,String endDate) throws Exception;
	
	/**
	* Description: 获取飞轮类型的参数列表
	* @return 
	* @throws Exception
	* @author Shenwp
	* @date 2016年7月26日
	* @version 1.0
	*/
	public List<ConstraintDto> getFlyWheelParameterList() throws Exception;
	
	/**
	* Description: 获取陀螺类型的参数列表
	* @return 
	* @throws Exception
	* @author Shenwp
	* @date 2016年7月28日
	* @version 1.0
	*/
	public List<ConstraintDto> getTopParameterList() throws Exception;
	
	/**
	* Description: 获取所有的 英-简写中文 参数列表 如：flywheel_d_speed -- 飞轮D转速(16110)
	* @return key 英文； value 中文
	* @throws Exception
	* @author Shenwp
	* @date 2016年7月28日
	* @version 1.0
	*/
	public Map<String,String> getAllParameterList_en_and_simplyZh() throws Exception;


	/**
	* Description: 获取所有的 英-全称中文 参数列表  如：flywheel_d_speed -- 采集数据110:飞轮D转速(16110)
	* @return key 英文； value 中文
	* @throws Exception
	* @author Shenwp
	* @date 2016年7月28日
	* @version 1.0
	*/
	public Map<String,String> getAllParameterList_en_and_allZh() throws Exception;
	
	/**
	* Description: 获取所有的 简写中文-英  参数列表  如：飞轮D转速(16110)--flywheel_d_speed
	* @return key 中文； value 英文
	* @throws Exception
	* @author Shenwp
	* @date 2016年7月28日
	* @version 1.0
	*/
	public Map<String,String> getAllParameterList_simplyZh_and_en() throws Exception;
	
	/**
	* Description: 获取所有的 全称中文-英  参数列表  如：采集数据110:飞轮D转速(16110)--flywheel_d_speed
	* @return key 中文； value 英文
	* @throws Exception
	* @author Shenwp
	* @date 2016年7月28日
	* @version 1.0
	*/
	public Map<String,String> getAllParameterList_allZh_and_en() throws Exception;
	
	/**
	* Description: 
	* @param type 飞轮/陀螺
	* @param params 参数
	* @return
	* @throws Exception
	* @author Shenwp
	* @date 2016年8月2日
	* @version 1.0
	*/
	public Map<String,String> getAllParameterList_allZh_and_enByOption(String type, List<String> params) throws Exception;

}
