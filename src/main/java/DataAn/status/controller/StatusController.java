package DataAn.status.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

import com.alibaba.fastjson.JSON;

import DataAn.common.dao.Pager;
import DataAn.common.pageModel.EasyuiDataGridJson;
import DataAn.common.pageModel.JsonMessage;
import DataAn.status.dto.StatusTrackingDTO;
import DataAn.status.dto.StatusYstepDTO;
import DataAn.status.service.IStatusTrackingService;
import DataAn.sys.dto.ActiveUserDto;

@Controller
@RequestMapping("/admin/status")
public class StatusController {

	@Resource
	private IStatusTrackingService statusTrackingService;

	@RequestMapping("/endIndex")
	public String endIndex() {
		return "/admin/status/endIndex";
	}

	@RequestMapping("/startingIndex")
	public String startingIndex(Model model, HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		ActiveUserDto acticeUser = (ActiveUserDto) session.getAttribute("activeUser");
		String userType = "";
		try {
			List<StatusYstepDTO> statusYstepList = statusTrackingService.getSatusYstepList(userType);
			System.out.println(JSON.toJSONString(statusYstepList));
			String jsonStatusList = JSON.toJSONString(statusYstepList);
			model.addAttribute("statusYstepList", jsonStatusList);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "/admin/status/startingIndex";
	}

	@RequestMapping(value = "/getStatusList", method = RequestMethod.POST)
	@ResponseBody
	public EasyuiDataGridJson getStatusList(int page, int rows, WebRequest request) {
		EasyuiDataGridJson json = new EasyuiDataGridJson();
		String userType = "";
		String statusType = request.getParameter("statusType");
		String fileName = request.getParameter("fileName");
		String createdateStart = request.getParameter("createdatetimeStart");
		String createdateEnd = request.getParameter("createdatetimeEnd");
		System.out.println("come in getStatusList...");
		System.out.println("fileName: " + fileName);
		System.out.println("createdateStart: " + createdateStart);
		System.out.println("createdateEnd: " + createdateEnd);
		// TODO 获取流程信息
		Pager<StatusTrackingDTO> pager = null;
		try {
			pager = statusTrackingService.pageQueryStatusTracking(page, rows, fileName, userType, statusType,
					createdateStart, createdateEnd);
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

	// 批量删除参数
	@RequestMapping(value = "/deleteStatus", method = RequestMethod.POST)
	@ResponseBody
	public JsonMessage deleteStatus(HttpServletRequest request, String trackingIds) {
		System.out.println("come in deleteStatus");
		System.out.println(trackingIds);
		String[] trackingIdArray = trackingIds.split(",");
		JsonMessage jsonMsg = new JsonMessage();
		try {
			for (String trackingId : trackingIdArray) {
				statusTrackingService.deleteStatusTracking(Long.parseLong(trackingId));
			}
		} catch (Exception e) {
			e.printStackTrace();
			jsonMsg.setSuccess(false);
			jsonMsg.setMsg("删除失败!");
			jsonMsg.setObj(e.getMessage());
			return jsonMsg;
		}
		jsonMsg.setSuccess(true);
		jsonMsg.setMsg("删除成功!");
		return jsonMsg;
	}
}
