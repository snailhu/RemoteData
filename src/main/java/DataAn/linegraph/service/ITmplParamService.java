package DataAn.linegraph.service;

import java.util.List;

import DataAn.linegraph.dto.LineGraphTemplateDto;
import DataAn.linegraph.dto.TemplateParameterDto;

public interface ITmplParamService {
public boolean saveTmplparam(TemplateParameterDto tmplparamDto);
	
	public boolean deleteTmplparam(long parameterid);
	
	public boolean updateTmplparam(TemplateParameterDto tmplparamDto);
	
	/**
	* @Title:根据模板id获取模板下的所有参数
	* @Description: 根据模板id获取模板下的所有参数
	* @param LineGraphTemplateid
	* @return
	* @author hanz
	* @date 2016年8月3日
	* @version 1.0
	*/
	public List<TemplateParameterDto> getTmplparamDtoByTmplId(long LineGraphTemplateid);
	
	public List<TemplateParameterDto> getAllTemplate();
	public List<TemplateParameterDto> getTemplateByUser(long Userid);
	public List<TemplateParameterDto> getAllTemlparamDto();
	
	public TemplateParameterDto getTmplparamDto(long parameterid);

	boolean saveTemplateParam(LineGraphTemplateDto templateDto,
			List<TemplateParameterDto> params);

}
