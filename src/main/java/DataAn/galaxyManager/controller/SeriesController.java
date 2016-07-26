package DataAn.galaxyManager.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

import DataAn.common.pageModel.EasyuiDataGridJson;
import DataAn.common.pageModel.JsonMessage;
import DataAn.common.pageModel.Pager;
import DataAn.galaxyManager.dto.SeriesDto;
import DataAn.galaxyManager.service.ISeriesService;


@Controller
@RequestMapping("/admin/series")
public class SeriesController {

	@Resource
	private ISeriesService seriesService;
	
	//获取系列列表
	@RequestMapping(value = "getList", method = RequestMethod.POST)
	@ResponseBody
	public EasyuiDataGridJson getRoleList(int page, int rows) {
//		System.out.println("come in getroleList..");
		EasyuiDataGridJson json = new EasyuiDataGridJson();
		Pager<SeriesDto> pager = seriesService.getRoleList(page, rows);
		json.setRows(pager.getRows());
		json.setTotal(pager.getTotalCount());			
		return json;
	}
	@RequestMapping(value="/createSeries", method = RequestMethod.POST)
	@ResponseBody
	public JsonMessage createSeries(@RequestParam(value = "name", required = true) String name,
									@RequestParam(value = "description", required = false) String description){
		JsonMessage jsonMsg = new JsonMessage();
		try {
			System.out.println("name: " + name);
			System.out.println("description: " + description);
			SeriesDto dto = new SeriesDto();
			dto.setName(name);
			dto.setDescription(description);
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
	
	@RequestMapping(value="/createSeries", method = RequestMethod.POST)
	@ResponseBody
	public SeriesDto getSeriesForm(long seriesId){
		
		return seriesService.getSeriesDto(seriesId);
	}
	
}
