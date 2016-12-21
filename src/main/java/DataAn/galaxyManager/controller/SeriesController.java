package DataAn.galaxyManager.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import DataAn.common.dao.Pager;
import DataAn.common.pageModel.Combo;
import DataAn.common.pageModel.EasyuiDataGridJson;
import DataAn.common.pageModel.JsonMessage;
import DataAn.galaxyManager.dto.SeriesDto;
import DataAn.galaxyManager.service.ISeriesService;


/**
* Title: SeriesController
* @Description: 系列信息的 Controller
* @author  Shewp
* @date 2016年7月28日
*/
@Controller
@RequestMapping("/admin/series")
public class SeriesController {

	@Resource
	private ISeriesService seriesService;
	
	//获取系列列表
	@RequestMapping(value = "getList", method = RequestMethod.POST)
	@ResponseBody
	public EasyuiDataGridJson getRoleList(int page, int rows,
			HttpServletRequest request,HttpServletResponse response) {
//		System.out.println("come in getroleList..");
		EasyuiDataGridJson json = new EasyuiDataGridJson();
		Pager<SeriesDto> pager = seriesService.getSeriesList(page, rows);
		json.setRows(pager.getDatas());
		json.setTotal(pager.getTotalCount());			
		return json;
	}
	@RequestMapping(value="/createSeries", method = RequestMethod.POST)
	@ResponseBody
	public JsonMessage createSeries(@RequestParam(value = "name", required = true) String name,
									@RequestParam(value = "code", required = true) String code,
									@RequestParam(value = "description", required = false) String description){
		SeriesDto dto = new SeriesDto();
		dto.setName(name);
		dto.setCode(code);
		dto.setDescription(description);
		System.out.println("come in createSeries..");
		System.out.println(dto);
		JsonMessage jsonMsg = new JsonMessage();
		if (seriesService.isExistSeries(dto)) {
			jsonMsg.setSuccess(false);
			jsonMsg.setMsg("在系列名称已存在！");
			jsonMsg.setObj("在系列名称已存在！");
			return jsonMsg;
		} 
		try {
			seriesService.saveSeries(dto);
		} catch (Exception e) {
			e.printStackTrace();
			jsonMsg.setSuccess(false);
			jsonMsg.setMsg("创建系列出错！");
			jsonMsg.setObj(e.getMessage());
			return jsonMsg;
		}
		jsonMsg.setSuccess(true);
		jsonMsg.setMsg("创建系列成功！");
	    return jsonMsg;
	}
	
	@RequestMapping(value="/getSeriesForm", method = RequestMethod.POST)
	public SeriesDto getSeriesForm(long seriesId){
		
		return seriesService.getSeriesDtoById(seriesId);
	}
	
	@RequestMapping(value="/editSeries", method = RequestMethod.POST)
	@ResponseBody
	public JsonMessage editSeries(@RequestParam(value = "id", required = true) long id,
								  @RequestParam(value = "name", required = true) String name,
								  @RequestParam(value = "code", required = true) String code,
								  @RequestParam(value = "description", required = false) String description){
		SeriesDto dto = new SeriesDto();
		dto.setId(id);
		dto.setName(name);
		dto.setCode(code);
		dto.setDescription(description);
		System.out.println("come in editSeries...");
		System.out.println(dto);
		
		JsonMessage jsonMsg = new JsonMessage();
		if (seriesService.isExistSeries(dto)) {
			jsonMsg.setSuccess(false);
			jsonMsg.setMsg("在系列名称已存在！");
			jsonMsg.setObj("在系列名称已存在！");
			return jsonMsg;
		} 
		try {
			seriesService.updateSeries(dto);
		} catch (Exception e) {
			e.printStackTrace();
			jsonMsg.setSuccess(false);
			jsonMsg.setMsg("编辑系列出错！");
			jsonMsg.setObj(e.getMessage());
			return jsonMsg;
		}
		jsonMsg.setSuccess(true);
		jsonMsg.setMsg("编辑系列成功！");
	    return jsonMsg;
	}
	@RequestMapping(value="/deleteSeries", method = RequestMethod.POST)
	@ResponseBody
	public JsonMessage deleteSeries(@RequestParam(value = "seriesIds", required = true) String seriesIds){
		System.out.println("come in deleteSeries..");
		System.out.println("seriesIds: " + seriesIds);
		System.out.println();
		JsonMessage jsonMsg = new JsonMessage();
		try {
			seriesService.deleteSeries(seriesIds);
		} catch (Exception e) {
			e.printStackTrace();
			jsonMsg.setSuccess(false);
			jsonMsg.setMsg("删除系列出错！");
			jsonMsg.setObj(e.getMessage());
			return jsonMsg;
		}
		jsonMsg.setSuccess(true);
		jsonMsg.setMsg("删除系列成功！");
	    return jsonMsg;
	}
	
	@RequestMapping("/getSeriesComboData")
	@ResponseBody
	public List<Combo> getSeriesComboData(String seriesCode) {
//		System.out.println("getSeriesComboData..");
//		System.out.println("seriesId: " + seriesId);
		List<Combo> list = seriesService.getSeriesComboData(seriesCode);
//		for (Combo combo : list) {
//			System.out.println(combo);
//		}
		return list;
	}

}
