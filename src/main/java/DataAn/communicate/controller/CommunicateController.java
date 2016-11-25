package DataAn.communicate.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import DataAn.common.controller.BaseController;
import DataAn.fileSystem.dao.IVirtualFileSystemDao;
import DataAn.fileSystem.domain.VirtualFileSystem;
import DataAn.prewarning.domain.WarningValue;
import DataAn.prewarning.service.IPrewarningService;
import DataAn.status.service.IStatusTrackingService;

@Controller
@RequestMapping("/Communicate")
public class CommunicateController extends BaseController {
	@Resource
	private IPrewarningService prewarningService;
	@Resource
	private IStatusTrackingService statusTrackingService;
	@Resource
	private IVirtualFileSystemDao fileDao;

	// 获取所有特殊工况参数配置信息
	@RequestMapping(value = "/getAllWarnValue")
	@ResponseBody
	public String getAllWarnValue() {
		JSONArray jsonArray = new JSONArray();
		List<WarningValue> warningValues = null;
		try {
			warningValues = prewarningService.getWarningValueByParams("", "", "", "", "0");
			for (WarningValue value : warningValues) {
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("series", value.getSeries());
				jsonObject.put("star", value.getStar());
				jsonObject.put("parameter", value.getParameter());
				jsonObject.put("parameterType", value.getParameterType());
				jsonObject.put("maxVal", value.getMaxVal());
				jsonObject.put("minVal", value.getMinVal());
				jsonObject.put("timeZone", value.getTimeZone());
				jsonObject.put("limitTimes", value.getLimitTimes());
				jsonArray.add(jsonObject);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonArray.toJSONString();
	}

	// 返回所有异常参数
	@RequestMapping(value = "/getAllErrorValue")
	@ResponseBody
	public String getAllErrorValue() {
		JSONArray jsonArray = new JSONArray();
		List<WarningValue> warningValues = null;
		try {
			warningValues = prewarningService.getWarningValueByParams("", "", "", "", "1");
			for (WarningValue value : warningValues) {
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("series", value.getSeries());
				jsonObject.put("star", value.getStar());
				jsonObject.put("parameter", value.getParameter());
				jsonObject.put("parameterType", value.getParameterType());
				jsonObject.put("timeZone", value.getTimeZone());
				jsonObject.put("maxVal", value.getMaxVal());
				jsonObject.put("minVal", value.getMinVal());
				jsonArray.add(jsonObject);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonArray.toJSONString();
	}

	// 根据查询条件获取特殊工况参数配置信息
	@RequestMapping(value = "/getWarnValueByParam")
	@ResponseBody
	public String getWarnValueByParam(HttpServletRequest request, String series, String star, String parameterType,
			String parameter) {
		if (StringUtils.isBlank("series") || StringUtils.isBlank("star") || StringUtils.isBlank("parameterType")) {
			return "请求参数：星系，星，设备类型 不能为空！！！";
		}
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("series", series);
		jsonObject.put("star", star);
		jsonObject.put("device", parameterType);

		JSONArray jsonArray = new JSONArray();
		List<WarningValue> warningValues = null;
		try {
			warningValues = prewarningService.getWarningValueByParams(series, star, parameter, parameterType, "0");
			for (WarningValue value : warningValues) {
				JSONObject jO = new JSONObject();
				jO.put("paramName", value.getParameter());
				jO.put("jobMax", value.getMaxVal());
				jO.put("jobMin", value.getMinVal());
				jO.put("delayTime", value.getTimeZone());
				jO.put("count", value.getLimitTimes());
				jsonArray.add(jO);
			}
			jsonObject.put("parameterInfos", jsonArray);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonObject.toJSONString();
	}

	// 根据查询条件查询异常参数
	@RequestMapping(value = "/getErrorValueByParam")
	@ResponseBody
	public String getErrorValueByParam(HttpServletRequest request, String series, String star, String parameterType,
			String parameter) {
		if (StringUtils.isBlank("series") || StringUtils.isBlank("star") || StringUtils.isBlank("parameterType")) {
			return "请求参数：星系，星，设备类型 不能为空！！！";
		}
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("series", series);
		jsonObject.put("star", star);
		jsonObject.put("device", parameterType);

		JSONArray jsonArray = new JSONArray();
		List<WarningValue> warningValues = null;
		try {
			warningValues = prewarningService.getWarningValueByParams(series, star, parameter, parameterType, "1");
			for (WarningValue value : warningValues) {
				JSONObject jO = new JSONObject();
				jO.put("paramName", value.getParameter());
				jO.put("delayTime", value.getTimeZone());
				jO.put("exceptionMax", value.getMaxVal());
				jO.put("exceptionMin", value.getMinVal());
				jsonArray.add(jO);
			}
			jsonObject.put("parameterInfos", jsonArray);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonObject.toJSONString();
	}

	// 修改状态信息
	@RequestMapping(value = "/updateStatus", method = RequestMethod.POST)
	@ResponseBody
	public String updateStatus(HttpServletRequest request, String version, String statusType, String exceptionInfo) {
		try {
			VirtualFileSystem file = fileDao.selectByFileTypeIsFileAndMongoFSUUId(version);
			if (file != null) {
				statusTrackingService.updateStatusTracking(file.getFileName(), statusType, file.getParameterType(),
						exceptionInfo);
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("sucFlag", true);
				return jsonObject.toJSONString();
			} else {
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("sucFlag", false);
				return jsonObject.toJSONString();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("sucFlag", false);
			return jsonObject.toJSONString();
		}
	}

	// // 同步预警数据
	// @RequestMapping(value = "/updatePreWarn", method = RequestMethod.POST)
	// @ResponseBody
	// public String updatePreWarn() {
	// try {
	// UpdatePrewarnThread prewarnThread = new
	// UpdatePrewarnThread(prewarningService);
	// prewarnThread.start();
	// JSONObject jsonObject = new JSONObject();
	// jsonObject.put("sucFlag", true);
	// return jsonObject.toJSONString();
	// } catch (Exception e) {
	// e.printStackTrace();
	// JSONObject jsonObject = new JSONObject();
	// jsonObject.put("sucFlag", false);
	// return jsonObject.toJSONString();
	// }
	// }
}
