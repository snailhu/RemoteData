package DataAn.communicate.controller;

import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import DataAn.common.controller.BaseController;
import DataAn.communicate.service.ICommunicateService;


@Controller
@RequestMapping("/Communicate")
public class CommunicateController extends BaseController {

	@Resource
	private ICommunicateService communicateService;
	// 获取星系设备:特殊工况参数配置信息
	@RequestMapping(value = "/getExceptionJobConfigList")
	@ResponseBody
	public String getExceptionJobConfigList(String series, String star, String parameterType) {
		
		return communicateService.getExceptionJobConfigList(series, star, parameterType);
	}
	
	// 获取星系设备:异常参数配置信息
	@RequestMapping(value = "/getExceptionPointConfigList")
	@ResponseBody
	public String getExceptionPointConfigList(String series, String star, String parameterType) {
		
		return communicateService.getExceptionPointConfigList(series, star, parameterType);
	}
		
	// 获取所有特殊工况参数配置信息
	@RequestMapping(value = "/getAllWarnValue")
	@ResponseBody
	public String getAllWarnValue() {
		
		return communicateService.getAllWarnValue();
	}

	// 返回所有异常参数
	@RequestMapping(value = "/getAllErrorValue")
	@ResponseBody
	public String getAllErrorValue() {
		
		return communicateService.getAllErrorValue();
	}

	// 根据查询条件获取特殊工况参数配置信息
	@RequestMapping(value = "/getWarnValueByParam")
	@ResponseBody
	public String getWarnValueByParam(String series, String star, String parameterType,
			String parameter) {
		return communicateService.getWarnValueByParam(series, star, parameterType, parameter);
	}

	// 根据查询条件查询异常参数
	@RequestMapping(value = "/getErrorValueByParam")
	@ResponseBody
	public String getErrorValueByParam(String series, String star, String parameterType,
			String parameter) {
		return communicateService.getErrorValueByParam(series, star, parameterType, parameter);
	}

	// 修改状态信息
	@RequestMapping(value = "/updateStatus", method = RequestMethod.POST)
	@ResponseBody
	public String updateStatus(String version, String statusType, String exceptionInfo) {
		
		return communicateService.updateStatus(version, statusType, exceptionInfo);
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
	

	
	//storm 服务器报错更行服务器状态
	@RequestMapping(value="/updateServerStatus")
	@ResponseBody
	public boolean updateServerStatus(String workerId){
		
		return communicateService.updateServerStatus(workerId);
	
	}
}
