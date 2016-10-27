package DataAn.galaxyManager.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import DataAn.common.dao.Pager;
import DataAn.common.pageModel.EasyuiDataGridJson;
import DataAn.common.pageModel.JsonMessage;
import DataAn.galaxyManager.dto.ParameterDto;
import DataAn.galaxyManager.service.IParameterService;

@Controller
@RequestMapping("/admin/parameter")
public class ParameterController {

	@Resource
	private IParameterService parameterService;
	
	// 返回参数管理主页
	@RequestMapping("/index")
	public String index(Model model) {
		//当前所在系列
		model.addAttribute("nowSeries", "j9");
		
		return "admin/galaxy/parameterList";
	}
	
	@RequestMapping("/index/{series}")
	public String indexOfSeries(@PathVariable String series, Model model) {
		//当前所在系列
		model.addAttribute("nowSeries", series);
		
		return "admin/galaxy/parameterList";
	}
	
	@RequestMapping(value = "/getList/{series}", method = RequestMethod.POST)
	@ResponseBody
	public EasyuiDataGridJson getParamList(@PathVariable String series,
										int page, int rows) {
		System.out.println("getParamList..");
		System.out.println("series: " + series);
		System.out.println("pageIndex: " + page);
		System.out.println("pageSize: " + rows);
		EasyuiDataGridJson json = new EasyuiDataGridJson();
		Pager<ParameterDto> pager = parameterService.getParameterList(page, rows);
		json.setRows(pager.getDatas());
		json.setTotal(pager.getTotalCount());
		return json;
	}

	@RequestMapping(value="/createParam")
	@ResponseBody
	public JsonMessage createParam(ParameterDto param,HttpServletRequest request,HttpServletResponse response){
		System.out.println("come in createParam");
		System.out.println(param);
		JsonMessage jsonMsg = new JsonMessage();
		try {
			parameterService.saveOne(param.getSeries(),param.getStar(),param.getParameterType(), param.getFullName());
		} catch (Exception e) {
			e.printStackTrace();
			jsonMsg.setSuccess(false);
			jsonMsg.setMsg("创建参数出错！");
			jsonMsg.setObj(e.getMessage());
			return jsonMsg;
		}
		jsonMsg.setSuccess(true);
		jsonMsg.setMsg("创建参数成功！");
	    return jsonMsg;
	}
	@RequestMapping(value="/editParam", method = RequestMethod.POST)
	@ResponseBody
	public JsonMessage editParam(@RequestParam(value = "id", required = true) long id,
			  					@RequestParam(value = "name", required = true) String name,
			  					@RequestParam(value = "description", required = false) String description){
		ParameterDto Param = new ParameterDto();
		Param.setId(id);
		Param.setFullName(name);
		System.out.println("come in editParam");
		System.out.println(Param);
		JsonMessage jsonMsg = new JsonMessage();

		try {
			
		} catch (Exception e) {
			e.printStackTrace();
			jsonMsg.setSuccess(false);
			jsonMsg.setMsg("编辑参数出错！");
			jsonMsg.setObj(e.getMessage());
			return jsonMsg;
		}
		jsonMsg.setSuccess(true);
		jsonMsg.setMsg("编辑参数成功！");
	    return jsonMsg;
	}
	// 删除参数
	@RequestMapping("/deleteParam")
	@ResponseBody
	public JsonMessage deleteParam(String paramIds) {
		String[] paramIdArray = paramIds.split(",");
		JsonMessage jsonMsg = new JsonMessage();
		try {
		} catch (Exception e) {
			e.printStackTrace();
			jsonMsg.setSuccess(false);
			jsonMsg.setMsg("删除参数失败!");
			jsonMsg.setObj(e.getMessage());
			return jsonMsg;
		}
		jsonMsg.setSuccess(true);
		jsonMsg.setMsg("删除参数成功!");
		return jsonMsg;
	}
}
