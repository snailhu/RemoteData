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
import DataAn.common.pageModel.JsonMessage;
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
	
	//在没有指定卫星系列、卫星型号的情况下(网址中没有系列、星)
	@RequestMapping("/getTemplateList")
	@ResponseBody
	public List<LineGraphTemplateDto> getTemplateList(){
		List<LineGraphTemplateDto> list = linegraphTemplateSrtvice.getTemplateList();
		return list;
	}
	
	//指定了系列、星的情况下获取模板列表
	
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
	
	@RequestMapping(value="/deleteTemplates", method = RequestMethod.POST)
	@ResponseBody
	public void deleteTemplates(
			@RequestParam(value = "templateIds", required = true) String templateIds){
		JsonMessage jsonMsg = new JsonMessage();
		try {
			linegraphTemplateSrtvice.deleteTemplate(templateIds);
		} catch (Exception e) {
			//e.printStackTrace();
			jsonMsg.setSuccess(false);
			jsonMsg.setMsg("删除模板出错！");
			jsonMsg.setObj(e.getMessage());
			//return jsonMsg;
		}
		jsonMsg.setSuccess(true);
		jsonMsg.setMsg("删除模板成功！");
	    //return jsonMsg;
	}
	
}
