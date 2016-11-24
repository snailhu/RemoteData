package DataAn.galaxyManager.controller;

import javax.annotation.Resource;

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
import DataAn.galaxyManager.service.ISeriesService;
import DataAn.galaxyManager.service.IStarService;

@Controller
@RequestMapping("/admin/parameter")
public class ParameterController {

	@Resource
	private IParameterService parameterService;
	@Resource
	private ISeriesService seriesService;
	@Resource
	private IStarService starService;
	
	// 返回参数管理主页
	@RequestMapping("/index")
	public String index(Model model) {
		//当前所在系列
		model.addAttribute("nowSeries", "j9");
		
		return "admin/galaxy/parameterList";
	}
	@RequestMapping("/index/{series}/{star}/")
	public String indexOfSeries(@PathVariable String series,@PathVariable String star, Model model) {
		//当前所在系列
		model.addAttribute("nowSeries", series);
		model.addAttribute("nowSeriesName", seriesService.getSeriesDtoByCode(series).getName());
		model.addAttribute("nowStar", star);
		model.addAttribute("nowStarName", starService.getStarDtoBySeriesCodeAndStarCode(series, star).getName());
		return "admin/galaxy/parameterList";
	}
	
	@RequestMapping(value = "/getList/{series}/{star}", method = RequestMethod.POST)
	@ResponseBody
	public EasyuiDataGridJson getParamList(@PathVariable String series,@PathVariable String star,
										int page, int rows) {
		System.out.println("getParamList..");
		System.out.println("series: " + series);
		System.out.println("star: " + star);
		System.out.println("pageIndex: " + page);
		System.out.println("pageSize: " + rows);
		EasyuiDataGridJson json = new EasyuiDataGridJson();
		Pager<ParameterDto> pager = parameterService.getParameterListByPager(series, star, page, rows);
		json.setRows(pager.getDatas());
		json.setTotal(pager.getTotalCount());
		return json;
	}

	@RequestMapping(value="/createParam")
	@ResponseBody
	public JsonMessage createParam(@RequestParam(value = "series", required = true) String series,
								   @RequestParam(value = "star", required = true) String star,
								   @RequestParam(value = "name", required = true) String name){
		System.out.println("come in createParam");
		System.out.println("series: " + series);
		System.out.println("star: " + star);
		System.out.println("name: " + name);
		JsonMessage jsonMsg = new JsonMessage();
		if (parameterService.isExistParameter(0, series, star, name)) {
			jsonMsg.setSuccess(false);
			jsonMsg.setMsg("参数名已存在！");
			jsonMsg.setObj("参数名已存在！");
			return jsonMsg;
		} 
		try {
			parameterService.saveOne(series,star,null, name);
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
								@RequestParam(value = "series", required = true) String series,
								@RequestParam(value = "star", required = true) String star,
			  					@RequestParam(value = "name", required = true) String name){

		System.out.println("come in editParam");
		System.out.println("id: " + id);
		System.out.println("series: " + series);
		System.out.println("star: " + star);
		System.out.println("name: " + name);
		JsonMessage jsonMsg = new JsonMessage();
		boolean flag = parameterService.isExistParameter(0, series, star, name);
		System.out.println("flag: " + flag);
		if (flag) {
			jsonMsg.setSuccess(false);
			jsonMsg.setMsg("参数名已存在！");
			jsonMsg.setObj("参数名已存在！");
			return jsonMsg;
		} 
		try {
			parameterService.updateParamter(id, name);
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
		System.out.println("come in deleteParam");
		System.out.println(paramIds);
		JsonMessage jsonMsg = new JsonMessage();
		try {
			parameterService.deleteParamter(paramIds);
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
