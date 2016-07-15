package DataAn.fileSystem.service;

import java.util.List;

import DataAn.Analysis.dto.ConstraintDto;


/**
* Title: IFlyWheelService
* @Description: 飞轮数据处理
* @author  Shewp
* @date 2016年7月15日
*/
public interface IFlyWheelService {

	public List<ConstraintDto> getFlyWheelParameterList() throws Exception;
}
