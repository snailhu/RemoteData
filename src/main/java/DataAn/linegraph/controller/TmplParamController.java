package DataAn.linegraph.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
		System.out.println(templateId);
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
}
