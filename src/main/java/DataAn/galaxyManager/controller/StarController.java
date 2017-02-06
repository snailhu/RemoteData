package DataAn.galaxyManager.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import DataAn.common.pageModel.Combo;
import DataAn.common.pageModel.JsonMessage;
import DataAn.galaxyManager.dto.StarDto;
import DataAn.galaxyManager.service.IStarService;

/**
* Title: StarController
* @Description: 星信息的Contrller
* @author  Shewp
* @date 2016年7月28日
*/
@Controller
@RequestMapping("/admin/star")
public class StarController {

	@Resource
	private IStarService starService;
	
	@RequestMapping("/getStars")
	@ResponseBody
	public List<StarDto> getStarsBySeriesId(long seriesId){
		List<StarDto> list = starService.getStarsBySeriesId(seriesId);
		return list;
	}
	
	@RequestMapping(value="/createStar", method = RequestMethod.POST)
	@ResponseBody
	public JsonMessage createStar(@RequestParam(value = "seriesId", required = true) long seriesId,
								  @RequestParam(value = "name", required = true) String name,
								  @RequestParam(value = "code", required = true) String code,
								  @RequestParam(value = "beginDate", required = true) String beginDate,
								  @RequestParam(value = "description", required = false) String description){
		StarDto starDto = new StarDto();
		starDto.setSeriesId(seriesId);
		starDto.setName(name);
		starDto.setCode(code);
		starDto.setDescription(description);
		starDto.setBeginDate(beginDate);
//		System.out.println("come in createStar..");
//		System.out.println(starDto);
		JsonMessage jsonMsg = new JsonMessage();
		if (starService.isExistStarByName(starDto)) {
			jsonMsg.setSuccess(false);
			jsonMsg.setMsg("在此星系下的星名称已存在！");
			jsonMsg.setObj("在此星系下的星名称已存在！");
			return jsonMsg;
		}
		if (starService.isExistStarByCode(starDto)) {
			jsonMsg.setSuccess(false);
			jsonMsg.setMsg("在此星系下的星编码已存在！");
			jsonMsg.setObj("在此星系下的星编码已存在！");
			return jsonMsg;
		} 
		try {
			starService.saveStar(starDto);
		} catch (Exception e) {
			e.printStackTrace();
			jsonMsg.setSuccess(false);
			jsonMsg.setMsg("创建星出错！");
			jsonMsg.setObj(e.getMessage());
			return jsonMsg;
		}
		jsonMsg.setSuccess(true);
		jsonMsg.setMsg("创建星成功！");
	    return jsonMsg;
	}
	
	@RequestMapping(value="/getStarForm", method = RequestMethod.POST)
	@ResponseBody
	public StarDto getStarForm(long starId){
		
		return starService.getStarDtoById(starId);
	}
	
	@RequestMapping(value="/editStar", method = RequestMethod.POST)
	@ResponseBody
	public JsonMessage editStar(@RequestParam(value = "seriesId", required = true) long seriesId,
								@RequestParam(value = "id", required = true) long id,
								@RequestParam(value = "name", required = true) String name,
								@RequestParam(value = "code", required = true) String code,
								@RequestParam(value = "beginDate", required = true) String beginDate,
								@RequestParam(value = "description", required = false) String description){
		StarDto starDto = new StarDto();
		starDto.setSeriesId(seriesId);
		starDto.setId(id);
		starDto.setName(name);
		starDto.setCode(code);
		starDto.setDescription(description);
		starDto.setBeginDate(beginDate);
//		System.out.println("come in editStar..");
//		System.out.println(starDto);
		JsonMessage jsonMsg = new JsonMessage();
		if (starService.isExistStarByName(starDto)) {
			jsonMsg.setSuccess(false);
			jsonMsg.setMsg("在此星系下的星名称已存在！");
			jsonMsg.setObj("在此星系下的星名称已存在！");
			return jsonMsg;
		} 
		if (starService.isExistStarByCode(starDto)) {
			jsonMsg.setSuccess(false);
			jsonMsg.setMsg("在此星系下的星编码已存在！");
			jsonMsg.setObj("在此星系下的星编码已存在！");
			return jsonMsg;
		} 
		try {
			starService.updateStar(starDto);
		} catch (Exception e) {
			e.printStackTrace();
			jsonMsg.setSuccess(false);
			jsonMsg.setMsg("编辑星出错！");
			jsonMsg.setObj(e.getMessage());
			return jsonMsg;
		}
		jsonMsg.setSuccess(true);
		jsonMsg.setMsg("编辑星成功！");
	    return jsonMsg;
	}
	@RequestMapping(value="/deleteStar", method = RequestMethod.POST)
	@ResponseBody
	public JsonMessage deleteStar(@RequestParam(value = "starId", required = true) long starId){
//		System.out.println("come in deleteStar..");
//		System.out.println("starId: " + starId);
//		System.out.println();
		JsonMessage jsonMsg = new JsonMessage();
		try {
			starService.deleteStar(starId);
		} catch (Exception e) {
			e.printStackTrace();
			jsonMsg.setSuccess(false);
			jsonMsg.setMsg("删除星出错！");
			jsonMsg.setObj(e.getMessage());
			return jsonMsg;
		}
		jsonMsg.setSuccess(true);
		jsonMsg.setMsg("删除星成功！");
	    return jsonMsg;
	}
	
	@RequestMapping("/getStarComboData")
	@ResponseBody
	public List<Combo> getStarComboData(String seriesCode, String starCode) {
//		System.out.println("getStarComboData..");
//		System.out.println("seriesId: " + seriesCode);
//		System.out.println("starId: " + starCode);
		List<Combo> list = starService.getStarComboData(seriesCode, starCode);
//		for (Combo combo : list) {
//			System.out.println(combo);
//		}
		return list;
	}
}
