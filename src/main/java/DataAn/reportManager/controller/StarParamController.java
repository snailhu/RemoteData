package DataAn.reportManager.controller;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import DataAn.Analysis.dto.ConstraintDto;
import DataAn.common.controller.BaseController;
import DataAn.common.dao.Pager;
import DataAn.common.pageModel.EasyuiDataGridJson;
import DataAn.common.pageModel.JsonMessage;
import DataAn.galaxyManager.domain.Series;
import DataAn.galaxyManager.domain.Star;
import DataAn.galaxyManager.dto.SeriesDto;
import DataAn.galaxyManager.dto.StarDto;
import DataAn.galaxyManager.option.J9Series_Star_ParameterType;
import DataAn.galaxyManager.service.IJ9Series_Star_Service;
import DataAn.reportManager.dto.StarParamDto;
import DataAn.reportManager.service.IStarParamService;
import DataAn.reportManager.util.CommonsConstant;
import DataAn.reportManager.util.ResultJSON;
import DataAn.sys.dto.ActiveUserDto;

@Controller
@RequestMapping("/starParam")
public class StarParamController  extends BaseController {
	@Resource
	private IStarParamService starParamService;
	
	@Resource
	private IJ9Series_Star_Service j9Series_Star_Service;
	
	@RequestMapping("/index")
	public String reportIndex(Model model,HttpServletRequest request,HttpServletResponse response) {
		
		return "/admin/reportManager/paramManager";
	}
	@RequestMapping(value = "/getStarParamList", method = RequestMethod.POST)
	@ResponseBody
	public EasyuiDataGridJson getStarParamList(int page, int rows, HttpServletRequest request) {
		EasyuiDataGridJson json = new EasyuiDataGridJson();
		String series = request.getParameter("series");
		String star = request.getParameter("star");
		String partsType = request.getParameter("partsType");
		String paramCode = request.getParameter("paramCode");
		HttpSession session = request.getSession();
		ActiveUserDto acticeUser = (ActiveUserDto) session.getAttribute("activeUser");
		String flywheel = J9Series_Star_ParameterType.FLYWHEEL.getValue();
		String flywheelRole = acticeUser.getPermissionItems().get(flywheel);
		String top = J9Series_Star_ParameterType.TOP.getValue();
		String topRole = acticeUser.getPermissionItems().get(top);
		
		String value = "";
		if (StringUtils.isNotBlank(flywheelRole) && StringUtils.isBlank(topRole)) {
			value = J9Series_Star_ParameterType.FLYWHEEL.getValue();
		}
		if (StringUtils.isNotBlank(topRole)&& StringUtils.isBlank(flywheelRole) ) {
			value = J9Series_Star_ParameterType.TOP.getValue();
		}
		partsType = value;
		Pager<StarParamDto> pager;
		try {
			pager = starParamService.getStarParamList(page, rows, series,star, 
					partsType,paramCode);
			if(pager != null){
				json.setTotal(pager.getTotalCount());
				json.setRows(pager.getDatas());	
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return json;
	}
	
	@RequestMapping(value = "/checkParam")
	@ResponseBody
	public ResultJSON checkParam(String series,String star,String partsType,String paramCode,HttpServletRequest request) {
		ResultJSON res = ResultJSON.getSuccessResultJSON();
		try {
			boolean falg = starParamService.cherkStarParam(series,star,partsType,paramCode);
			 if(falg) {
				 res.setMsg("参数已存在！");
				 res.setResult(CommonsConstant.RESULT_FALSE);
				 return res;
			 } 
		 } catch (Exception ex) {
			 res.setMsg("参数已存在！");
			 res.setResult(CommonsConstant.RESULT_FALSE);
		 }
		 return res;
	}
	
	@RequestMapping(value = "/createStarParam")
	@ResponseBody
	public JsonMessage createStarParam(StarParamDto starParamDto,HttpServletRequest request,HttpServletResponse response) {
		JsonMessage jsonMsg = new JsonMessage();
		try {
			String currentUserName = getCurrentUserName(request);
			starParamDto.setCreater(currentUserName);
			starParamService.save(starParamDto);
		} catch (Exception e) {
			jsonMsg.setSuccess(false);
			jsonMsg.setMsg("添加参数出错！");
			jsonMsg.setObj(e.getMessage());
			return jsonMsg;
		}
		jsonMsg.setSuccess(true);
		jsonMsg.setMsg("添加参数成功！");
		
		return jsonMsg;
	}
	
	@RequestMapping(value = "/editStarParam")
	@ResponseBody
	public JsonMessage editStarParam(StarParamDto starParamDto, HttpServletRequest request, HttpServletResponse response) {
		
		JsonMessage jsonMsg = new JsonMessage();
		try {
			String currentUserName = getCurrentUserName(request);
			starParamDto.setCreater(currentUserName);
			starParamService.update(starParamDto);
		} catch (Exception e) {
			jsonMsg.setSuccess(false);
			jsonMsg.setMsg("编辑参数失败！");
			jsonMsg.setObj(e.getMessage());
			return jsonMsg;
		}
		jsonMsg.setSuccess(true);
		jsonMsg.setMsg("编辑参数成功！");
		return jsonMsg;
	}
	@RequestMapping(value = "/deleteStarParam")
	@ResponseBody
	public JsonMessage deleteStarParam(HttpServletRequest request, String starParamIds) {
		String[] starParamIdArray = starParamIds.split(",");
		JsonMessage jsonMsg = new JsonMessage();
		try {
			starParamService.delete(starParamIdArray);
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
	@RequestMapping(value = "/getSeriesList")
	@ResponseBody
	public ResultJSON getSeriesList(HttpServletRequest request) {
		ResultJSON res = ResultJSON.getSuccessResultJSON();
		try {
			 List<Series> seriesList = starParamService.getSeriesList();
			 
			 List<SeriesDto> seriesDtoList = new ArrayList<SeriesDto>();
			 for (Series series : seriesList) {
				 SeriesDto seriesDto = new SeriesDto();
				 seriesDto.setName(series.getName());
				 seriesDto.setCode(series.getCode());
				 seriesDto.setDescription(series.getDescription());
				 seriesDto.setId(series.getId());
				 seriesDtoList.add(seriesDto);
			}
			 Map<String, Object> data = new HashMap<String, Object>();
			 data.put("data", seriesDtoList);
			 res.setData(data);
		 } catch (Exception ex) {
			 res.setMsg("查询星系失败！");
			 res.setResult(CommonsConstant.RESULT_FALSE);
		 }
		 return res;
	}
	
	@RequestMapping(value = "/getStarList")
	@ResponseBody
	public ResultJSON getStarList(HttpServletRequest request,String seriesId) {
		ResultJSON res = ResultJSON.getSuccessResultJSON();
		try {
			 List<Star> starList = starParamService.getStarList(seriesId); 
			 List<StarDto> starDtoList = new ArrayList<StarDto>();
			 for (Star star : starList) {
				 StarDto starDto = new StarDto();
				 starDto.setName(star.getName());
				 starDto.setDescription(star.getDescription());
				 starDto.setId(star.getId());
				 starDto.setCode(star.getCode());
				 starDto.setSeriesId(star.getSeries().getId());
				 starDtoList.add(starDto);
			}
			 Map<String, Object> data = new HashMap<String, Object>();
			 data.put("data", starDtoList);
			 res.setData(data);
		 } catch (Exception ex) {
			 res.setMsg("获取星失败！");
			 res.setResult(CommonsConstant.RESULT_FALSE);
		 }
		 return res;
	}
	
	@RequestMapping(value = "/getConstraintList")
	@ResponseBody
	public ResultJSON getConstraintList(HttpServletRequest request,String seriesId,String starId,String partstype) {
		ResultJSON res = ResultJSON.getSuccessResultJSON();
		try {

			 List<ConstraintDto> starList = starParamService.getConstraintList(seriesId,starId,partstype); 
			 Map<String, Object> data = new HashMap<String, Object>();
			 data.put("data", starList);
			 res.setData(data);
		 } catch (Exception ex) {
			 res.setMsg("获取参数失败！");
			 res.setResult(CommonsConstant.RESULT_FALSE);
		 }
		 return res;
	}
}
