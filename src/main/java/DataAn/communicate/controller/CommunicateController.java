package DataAn.communicate.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import DataAn.common.controller.BaseController;
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

	// 获取所有特殊工况参数配置信息
	@RequestMapping(value = "/getAllWarnValue")
	@ResponseBody
	public String getAllWarnValue() {
		JSONArray jsonArray = new JSONArray();
		List<WarningValue> warningValues = null;
		try {
			warningValues = prewarningService.getWarningValueByParams("", "", "", "0");
			for (WarningValue value : warningValues) {
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("series", value.getSeries());
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
			warningValues = prewarningService.getWarningValueByParams("", "", "", "1");
			for (WarningValue value : warningValues) {
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("series", value.getSeries());
				jsonObject.put("parameter", value.getParameter());
				jsonObject.put("parameterType", value.getParameterType());
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
}
