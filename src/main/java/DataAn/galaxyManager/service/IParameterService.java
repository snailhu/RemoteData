package DataAn.galaxyManager.service;

import java.util.List;
import java.util.Set;

import DataAn.common.dao.Pager;
import DataAn.galaxyManager.domain.Parameter;
import DataAn.galaxyManager.dto.ParameterDto;

public interface IParameterService {

	public Parameter saveOne(String series, String star, String deviceTypeCode, String param_zh);
	
	public void saveMany(String series, String star, String paramType, String param_zhs);
	
	public void saveMany(String series, String star, String paramType, Set<String> paramNames);
	
	public void deleteParamter(String paramIds);
	
	public void updateParamter(long paramId, String param_zh);
	
	public boolean isExistParameter(long paramId, String series, String star, String param_zh);
	
	public List<ParameterDto> getParameterList(String series, String star, String paramType);
	
	public Pager<ParameterDto> getParameterListByPager(String series, String star, int pageIndex, int pageSize);

	public String getParameter_en_by_allZh(String series, String star, String paramType, String param_zh);

	public String getParameter_en_by_simpleZh(String series, String star, String paramType, String param_zh);

	public String getParameter_allZh_by_en(String series, String star, String paramType, String param_en);
	
	public String getParameter_simpleZh_by_en(String series, String star, String paramType, String param_en);
	
	/** 通过参数的英文返回参数的类型
	 * @param series 系列
	 * @param star 星
	 * @param paramType 参数类型
	 * @param param_en 参数英文值
	 * @return  "电流","转速","温度","指令","供电状态","角动量"
	 * @author Shenwp
	 * @date 2016年10月26日
	 */
	public String getParameter_dataType_by_en(String series, String star, String paramType, String param_en);
	
	/** 通过参数的英文返回飞轮名称
	 * @param series 系列
	 * @param star 星
	 * @param paramType 参数类型
	 * @param param_en 参数英文值
	 * @return Xa、Ya、Za、Xb、Yb、Zb
	 * @author Shenwp
	 * @date 2016年10月26日
	 */
	public String getParameter_deviceName_by_en(String series, String star, String paramType, String param_en);
}
