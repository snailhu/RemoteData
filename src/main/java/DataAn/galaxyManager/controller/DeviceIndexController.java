package DataAn.galaxyManager.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import DataAn.common.dao.Pager;
import DataAn.common.pageModel.EasyuiDataGridJson;
import DataAn.galaxyManager.domain.DeviceType;
import DataAn.galaxyManager.dto.DeviceViewDTO;
import DataAn.galaxyManager.service.IDeviceService;
import DataAn.galaxyManager.service.ISeriesService;
import DataAn.galaxyManager.service.IStarService;

@Controller
@RequestMapping("/admin/deviceIndex")
public class DeviceIndexController {

	@Resource
	private IDeviceService deviceService;
	@Resource
	private ISeriesService seriesService;
	@Resource
	private IStarService starService;
	
	//设备查看、设备搜索
	@RequestMapping("/deviceIndex")
	public String indexOfDevice(Model model) {
		return "admin/galaxy/deviceIndex";
	}
	//设备查看、设备搜索 通过设备类型、设备型号搜索
	@RequestMapping(value = "getDeviceIndexPager", method = RequestMethod.POST)
	@ResponseBody
	public EasyuiDataGridJson getDeviceIndexPager(int page, int rows, HttpServletRequest request,
			HttpServletResponse response) {
		String deviceType = request.getParameter("parameterType");
		String model = request.getParameter("model");
		EasyuiDataGridJson json = new EasyuiDataGridJson();
		System.out.println("come in getDeviceTypePager...");
		System.out.println("pageIndex: " + page);
		System.out.println("pageSize: " + rows);
		System.out.println("deviceType: " + deviceType);
		System.out.println("model: " + model);
		try {
			Pager<DeviceViewDTO> pager = deviceService.pageDeviceViewDTO(page, rows, deviceType, model);
			json.setRows(pager.getDatas());
			json.setTotal(pager.getTotalCount());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return json;
	}

}
