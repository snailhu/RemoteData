package DataAn.fileSystem.service;

import java.util.List;

import DataAn.Analysis.dto.ConstraintDto;

/**
* Title: IJ9Series_Star_Service
* @Description: j9系列的星星 服务类
* @author  Shewp
* @date 2016年7月26日
*/
public interface IJ9Series_Star_Service {

	/**
	* @Title: getFlyWheelParameterList
	* @Description: 获取飞轮类型的参数列表
	* @return
	* @throws Exception
	* @author Shenwp
	* @date 2016年7月26日
	* @version 1.0
	*/
	public List<ConstraintDto> getFlyWheelParameterList() throws Exception;

}
