package DataAn.prewarning.controller;

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

import DataAn.common.controller.BaseController;
import DataAn.common.dao.Pager;
import DataAn.common.pageModel.EasyuiDataGridJson;
import DataAn.common.pageModel.JsonMessage;
import DataAn.galaxyManager.option.J9Series_Star_ParameterType;
import DataAn.galaxyManager.domain.Star;
import DataAn.galaxyManager.dto.StarDto;
import DataAn.prewarning.domain.WarningValue;
import DataAn.prewarning.dto.ErrorValueDTO;
import DataAn.prewarning.dto.QueryLogDTO;
import DataAn.prewarning.dto.QueryValueDTO;
import DataAn.prewarning.dto.SelectOptionDTO;
import DataAn.prewarning.dto.WarnValueDTO;
import DataAn.prewarning.service.IPrewarningService;
import DataAn.reportManager.util.CommonsConstant;
import DataAn.reportManager.util.ResultJSON;
import DataAn.sys.dto.ActiveUserDto;

@Controller
@RequestMapping(value = "/admin/prewarning")
public class PrewarningController extends BaseController {

	@Resource
	private IPrewarningService prewarningService;

	@RequestMapping("/logIndex")
	public String logIndex(Model model, HttpServletRequest request, HttpServletResponse response) {
		String hadReadFlag = request.getParameter("hadReadFlag");
		model.addAttribute("hadReadFlag", hadReadFlag);
		return "admin/prewarning/logIndex";
	}

	@RequestMapping("/errorvalueIndex")
	public String errorvalueIndex() {
		return "admin/prewarning/errorvalueIndex";
	}

	@RequestMapping("/warnvalueIndex")
	public String warnvalueIndex() {
		return "admin/prewarning/warnvalueIndex";
	}

	@RequestMapping(value = "/getParamList")
	@ResponseBody
	public SelectOptionDTO getParamList(HttpServletRequest request, String series, String parameterType, String star) {
		SelectOptionDTO selectOptionDTO = null;
		try {
			selectOptionDTO = prewarningService.getSelectOption(series, parameterType, star);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return selectOptionDTO;
	}

	// 获取参数列表
	@RequestMapping(value = "/getValueList", method = RequestMethod.POST)
	@ResponseBody
	public EasyuiDataGridJson getValueList(int page, int rows, String sort, String order, HttpServletRequest request,
			HttpServletResponse response) {
		EasyuiDataGridJson json = new EasyuiDataGridJson();
		String series = request.getParameter("series");
		String star = request.getParameter("star");
		String parameter = request.getParameter("parameter");
		String parameterType = getUserType(request.getParameter("parameterType"), request);
		String warningType = request.getParameter("warningType");
		System.out.println("come in getValueList...");
		System.out.println("pageIndex: " + page);
		System.out.println("pageSize: " + rows);
		System.out.println("series: " + series);
		System.out.println("star: " + star);
		System.out.println("parameter: " + parameter);
		System.out.println("parameterType: " + parameterType);
		System.out.println("warningType: " + warningType);
		System.out.println("sort: " + sort);
		System.out.println("order: " + order);
		Pager<QueryValueDTO> pager = null;
		try {
			pager = prewarningService.pageQueryWarningValue(page, rows, sort, order, series, star, parameter,
					parameterType, warningType);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (pager != null) {
			json.setTotal(pager.getTotalCount());
			json.setRows(pager.getDatas());
		} else {
			System.out.println("pager is null");
		}
		return json;
	}

	// 获取预警列表
	@RequestMapping(value = "/getLogList", method = RequestMethod.POST)
	@ResponseBody
	public EasyuiDataGridJson getLogList(int page, int rows, HttpServletRequest request, HttpServletResponse response) {
		EasyuiDataGridJson json = new EasyuiDataGridJson();
		String series = request.getParameter("series");
		String star = request.getParameter("star");
		String parameter = request.getParameter("parameter");
		String parameterType = getUserType(request.getParameter("parameterType"), request);
		String warningType = request.getParameter("warningType");
		String createdatetimeStart = request.getParameter("createdatetimeStart");
		String createdatetimeEnd = request.getParameter("createdatetimeEnd");
		String hadRead = request.getParameter("hadRead");
		String clickCount = request.getParameter("clickCount");
		System.out.println("come in getLogList...");
		System.out.println("pageIndex: " + page);
		System.out.println("pageSize: " + rows);
		System.out.println("series: " + series);
		System.out.println("star: " + star);
		System.out.println("parameterType: " + parameterType);
		System.out.println("parameter: " + parameter);
		System.out.println("createdatetimeStart: " + createdatetimeStart);
		System.out.println("createdatetimeEnd: " + createdatetimeEnd);
		System.out.println("warningType: " + warningType);
		System.out.println("hadRead: " + hadRead);
		System.out.println("clickCount: " + clickCount);
		Pager<QueryLogDTO> pager = null;
		try {
			if ("1".equals(clickCount)) {
				pager = prewarningService.pageQueryWarningLog(page, rows, series, star, parameterType, parameter,
						createdatetimeStart, createdatetimeEnd, warningType, hadRead);
			}
			if ("0".equals(hadRead)) {
				pager = prewarningService.pageQueryWarningLog(page, rows, series, star, parameterType, parameter,
						createdatetimeStart, createdatetimeEnd, warningType, hadRead);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (pager != null) {
			json.setTotal(pager.getTotalCount());
			json.setRows(pager.getDatas());
		} else {
			System.out.println("pager is null");
		}
		Long warnCount = 0l;
		try {
			warnCount = prewarningService.getNotReadCount("", "", "", "", "");
			request.getSession().setAttribute("warnCount", warnCount);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return json;
	}

	// 创建特殊工况参数
	@RequestMapping(value = "/createWarnValue")
	@ResponseBody
	public JsonMessage createWarnValue(WarnValueDTO warnValue, HttpServletRequest request,
			HttpServletResponse response) {
		System.out.println("come in createValue ");
		System.out.println(warnValue);
		System.out.println(warnValue.getMaxVal());
		if(warnValue.getMinVal()==null){
			warnValue.setMinVal(0.0);
		}
		JsonMessage jsonMsg = new JsonMessage();
		try {
			boolean falg = prewarningService.cherkWarningValue(warnValue.getSeries().toString(),
					warnValue.getStar().toString(), warnValue.getParameter(), warnValue.getParameterType(), "0");
			if (falg) {
				jsonMsg.setSuccess(false);
				jsonMsg.setMsg("参数已存在！");
				jsonMsg.setObj("参数已存在！");
				return jsonMsg;
			}
			prewarningService.addWarnValue(warnValue);
			jsonMsg.setSuccess(true);
			jsonMsg.setMsg("新增参数成功！");
			return jsonMsg;
		} catch (Exception e) {
			e.printStackTrace();
			jsonMsg.setSuccess(false);
			jsonMsg.setMsg("新增参数出错！");
			jsonMsg.setObj(e.getMessage());
			return jsonMsg;
		}
	}

	// 创建异常参数
	@RequestMapping(value = "/createErrorValue")
	@ResponseBody
	public JsonMessage createErrorValue(ErrorValueDTO errorValue, HttpServletRequest request,
			HttpServletResponse response) {
		System.out.println("come in createValue ");
		System.out.println(errorValue);
		JsonMessage jsonMsg = new JsonMessage();
		try {
			boolean falg = prewarningService.cherkWarningValue(errorValue.getSeries().toString(),
					errorValue.getStar().toString(), errorValue.getParameter(), errorValue.getParameterType(), "1");
			if (falg) {
				jsonMsg.setSuccess(false);
				jsonMsg.setMsg("参数已存在！");
				jsonMsg.setObj("参数已存在！");
				return jsonMsg;
			}
			prewarningService.addErrorValue(errorValue);
			jsonMsg.setSuccess(true);
			jsonMsg.setMsg("新增参数成功！");
			return jsonMsg;
		} catch (Exception e) {
			e.printStackTrace();
			jsonMsg.setSuccess(false);
			jsonMsg.setMsg("新增参数出错！");
			jsonMsg.setObj(e.getMessage());
			return jsonMsg;
		}
	}

	// 返回编辑参数数据
	@RequestMapping(value = "/getValueById")
	@ResponseBody
	public WarningValue getValueById(long valueId) {
		WarningValue warningValue = null;
		try {
			warningValue = prewarningService.getWarningValueById(valueId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return warningValue;
	}

	// 编辑特殊工况参数
	@RequestMapping(value = "/editWarnValue")
	@ResponseBody
	public JsonMessage editWarnValue(WarnValueDTO warnValue, HttpServletRequest request, HttpServletResponse response) {
		JsonMessage jsonMsg = new JsonMessage();
		try {
			WarningValue value = prewarningService.getWarningValueById(warnValue.getValueId().longValue());
			if (!(value.getParameter().equals(warnValue.getParameter())
					&& value.getParameterType().equals(warnValue.getParameterType())
					&& value.getSeries().equals(warnValue.getSeries())
					&& value.getStar().equals(warnValue.getStar()))) {
				System.out.println("编辑特殊工况：参数名称"+warnValue.getParameter());
				boolean falg = prewarningService.cherkWarningValue(warnValue.getSeries().toString(),
						warnValue.getStar().toString(), warnValue.getParameter(), warnValue.getParameterType(), "0");
				if (falg) {
					jsonMsg.setSuccess(false);
					jsonMsg.setMsg("参数已存在！");
					jsonMsg.setObj("参数已存在！");
					return jsonMsg;
				}
			}
			if(warnValue.getMinVal()==null){
				warnValue.setMinVal(0.0);
			}
			prewarningService.updateWarnValue(warnValue);
		} catch (Exception e) {
			e.printStackTrace();
			jsonMsg.setSuccess(false);
			jsonMsg.setMsg("编辑参数失败！");
			jsonMsg.setObj(e.getMessage());
			return jsonMsg;
		}
		jsonMsg.setSuccess(true);
		jsonMsg.setMsg("编辑参数成功！");
		return jsonMsg;
	}

	// 编辑异常参数
	@RequestMapping(value = "/editErrorValue")
	@ResponseBody
	public JsonMessage editErrorValue(ErrorValueDTO errorValue, HttpServletRequest request,
			HttpServletResponse response) {
		JsonMessage jsonMsg = new JsonMessage();
		try {
			WarningValue value = prewarningService.getWarningValueById((errorValue.getValueId().longValue()));
			if (!(value.getParameter().equals(errorValue.getParameter())
					&& value.getParameterType().equals(errorValue.getParameterType())
					&& value.getSeries().equals(errorValue.getSeries())
					&& value.getStar().equals(errorValue.getStar()))) {
				boolean falg = prewarningService.cherkWarningValue(errorValue.getSeries().toString(),
						errorValue.getStar().toString(), errorValue.getParameter(), errorValue.getParameterType(), "1");
				if (falg) {
					jsonMsg.setSuccess(false);
					jsonMsg.setMsg("参数已存在！");
					jsonMsg.setObj("参数已存在！");
					return jsonMsg;
				}
			}
			prewarningService.updateErrorValue(errorValue);
		} catch (Exception e) {
			e.printStackTrace();
			jsonMsg.setSuccess(false);
			jsonMsg.setMsg("编辑参数失败！");
			jsonMsg.setObj(e.getMessage());
			return jsonMsg;
		}
		jsonMsg.setSuccess(true);
		jsonMsg.setMsg("编辑参数成功！");
		return jsonMsg;
	}

	// 批量删除参数
	@RequestMapping(value = "/deleteValue")
	@ResponseBody
	public JsonMessage deleteValue(HttpServletRequest request, String valueIds) {
		System.out.println("come in deleteValue");
		System.out.println(valueIds);
		String[] valueIdArray = valueIds.split(",");
		JsonMessage jsonMsg = new JsonMessage();
		try {
			for (String valueId : valueIdArray) {
				prewarningService.deleteWarningValue(Long.parseLong(valueId));
			}
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

	// 批量删除预警
	@RequestMapping(value = "/deleteLog")
	@ResponseBody
	public JsonMessage deleteLog(HttpServletRequest request, String logIds, String series, String star,
			String parameterType, String warningType,String hadRead) {
		System.out.println("come in deleteLog，删除按钮所在页面："+hadRead);
		System.out.println(logIds);
		String[] logIdArray = logIds.split(",");
		JsonMessage jsonMsg = new JsonMessage();
		try {
			for (String logId : logIdArray) {
				prewarningService.deleteWarningLog(logId, series, star, parameterType, warningType,hadRead);
			}
		} catch (Exception e) {
			e.printStackTrace();
			jsonMsg.setSuccess(false);
			jsonMsg.setMsg("删除预警失败!");
			jsonMsg.setObj(e.getMessage());
			return jsonMsg;
		}
		jsonMsg.setSuccess(true);
		jsonMsg.setMsg("删除预警成功!");
		return jsonMsg;
	}

	@RequestMapping(value = "/getStarList")
	@ResponseBody
	public ResultJSON getStarList(HttpServletRequest request, String seriesId) {
		ResultJSON res = ResultJSON.getSuccessResultJSON();
		try {
			if (StringUtils.isNotBlank(seriesId)) {
				List<Star> starList = prewarningService.getStarList(seriesId);
				List<StarDto> starDtoList = new ArrayList<StarDto>();
				for (Star star : starList) {
					StarDto starDto = new StarDto();
					starDto.setName(star.getName());
					starDto.setDescription(star.getDescription());
					starDto.setId(star.getId());
					starDto.setSeriesId(star.getSeries().getId());
					starDtoList.add(starDto);
				}
				Map<String, Object> data = new HashMap<String, Object>();
				data.put("data", starDtoList);
				res.setData(data);
			}
		} catch (Exception ex) {
			res.setMsg("获取星失败！");
			res.setResult(CommonsConstant.RESULT_FALSE);
		}
		return res;
	}

	private String getUserType(String parameterType, HttpServletRequest request) {
		if (StringUtils.isNotBlank(parameterType)) {
			return parameterType;
		} else {
			HttpSession session = request.getSession();
			ActiveUserDto acticeUser = (ActiveUserDto) session.getAttribute("activeUser");
			String flywheel = J9Series_Star_ParameterType.FLYWHEEL.getValue();
			String top = J9Series_Star_ParameterType.TOP.getValue();
			String userType = "";
			int i = 0;
			if (acticeUser == null) {
				return null;
			}
			if (StringUtils.isNotBlank(acticeUser.getPermissionItems().get(flywheel))) {
				userType = J9Series_Star_ParameterType.FLYWHEEL.getValue();
				i++;
			}
			if (StringUtils.isNotBlank(acticeUser.getPermissionItems().get(top))) {
				userType = J9Series_Star_ParameterType.TOP.getValue();
				i++;
			}
			if (i > 1) {
				userType = "";
			}
			return userType;
		}
	}

}
