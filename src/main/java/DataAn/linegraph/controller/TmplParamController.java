package DataAn.linegraph.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import DataAn.Util.JsonStringToObj;
import DataAn.linegraph.dto.LineGraphTemplateDto;
import DataAn.linegraph.dto.TemplateParameterDto;
import DataAn.linegraph.service.ILineTmplService;
import DataAn.linegraph.service.ITmplParamService;

@Controller
public class TmplParamController {
	@Resource
	private ITmplParamService templparamService;
	@Resource
	private ILineTmplService linegraphTemplateSrtvice;
	@RequestMapping("/getParamsByTemplateId")
	@ResponseBody
	public List<TemplateParameterDto> getParamsByTemplateId(long templateId){
		List<TemplateParameterDto> list = templparamService.getTmplparamDtoByTmplId(templateId);
		return list;
	}
	@RequestMapping("/getAllTemplate")
	@ResponseBody
	public List<TemplateParameterDto> getAllLineGraphTemplate() {
		List<TemplateParameterDto> list = templparamService.getAllTemplate();
		return list;
	}
	@RequestMapping("/getTemplateList")
	@ResponseBody
	public List<LineGraphTemplateDto> getTemplateList(){
		List<LineGraphTemplateDto> list = linegraphTemplateSrtvice.getTemplateList();
		return list;
	}
	
	@RequestMapping(value = "/saveTotemplate", method = RequestMethod.POST)
	@ResponseBody
	public void savetoTemplate(
		   @RequestParam(value = "templateNmae", required = true) String templateNmae,
		   @RequestParam(value = "templateDescription", required = true) String templateDescription,
		   @RequestParam(value = "JsonParams",required = true) String JsonParams) throws Exception{
		LineGraphTemplateDto templateDto =new LineGraphTemplateDto();
		templateDto.setName(templateNmae);
		templateDto.setDescription(templateDescription);
		Map<String,Class<TemplateParameterDto>> classMap = new HashMap<String,Class<TemplateParameterDto>>();
		classMap.put("param", TemplateParameterDto.class);
		List<TemplateParameterDto> params_list = JsonStringToObj.jsonArrayToListObject(JsonParams, TemplateParameterDto.class, classMap);
		//List<TemplateParameterDto> params_list = JsonStringToObj.jsonToObject(JsonParams,TemplateParameterDto.class,classMap);
		templparamService.saveTemplateParam(templateDto, params_list);
	}
	
}
