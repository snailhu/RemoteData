package DataAn.galaxyManager.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import DataAn.common.dao.Pager;
import DataAn.common.pageModel.Combo;
import DataAn.common.pageModel.EasyuiDataGridJson;
import DataAn.common.pageModel.JsonMessage;
import DataAn.common.utils.DateUtil;
import DataAn.galaxyManager.domain.DeviceType;
import DataAn.galaxyManager.dto.DeviceDto;
import DataAn.galaxyManager.dto.DeviceViewDTO;
import DataAn.galaxyManager.dto.QueryDeviceDTO;
import DataAn.galaxyManager.dto.QueryDeviceTypeDTO;
import DataAn.galaxyManager.dto.SeriesDto;
import DataAn.galaxyManager.dto.StarDto;
import DataAn.galaxyManager.option.J9Series_Star_ParameterType;
import DataAn.galaxyManager.service.IDeviceService;
import DataAn.galaxyManager.service.ISeriesService;
import DataAn.galaxyManager.service.IStarService;
import DataAn.sys.dto.ActiveUserDto;

/**
 * Title: DeviceController
 * 
 * @Description: 设备信息的 Controller
 */
@Controller
@RequestMapping("/admin/device")
public class DeviceListController {

	@Resource
	private IDeviceService deviceService;
	@Resource
	private ISeriesService seriesService;
	@Resource
	private IStarService starService;

	//设备管理列表
	@RequestMapping("/index/{series}/{star}/")
	public String indexOfDeviceType(@PathVariable String series, @PathVariable String star, Model model) {
		// 当前所在系列
		SeriesDto seriesDto = seriesService.getSeriesDtoById(Long.parseLong(series));
		StarDto starDto = starService.getStarDtoById(Long.parseLong(star));

		model.addAttribute("nowSeriesId", seriesDto.getId());
		model.addAttribute("nowSeriesCode", seriesDto.getCode());
		model.addAttribute("nowSeriesName", seriesDto.getName());
		
		model.addAttribute("nowStarId", starDto.getId());
		model.addAttribute("nowStarCode", starDto.getCode());
		model.addAttribute("nowStarName", starDto.getName());
		return "admin/galaxy/deviceList";
	}
	
	//获取设备类型
	@RequestMapping(value = "getDeviceTypePager", method = RequestMethod.POST)
	@ResponseBody
	public EasyuiDataGridJson getDeviceTypePager(int page, int rows, HttpServletRequest request,
			HttpServletResponse response) {
		String series = request.getParameter("series");
		String star = request.getParameter("star");
		String userType = getUserType(null, request);
		EasyuiDataGridJson json = new EasyuiDataGridJson();
		System.out.println("come in getDeviceTypePager...");
		System.out.println("pageIndex: " + page);
		System.out.println("pageSize: " + rows);
		System.out.println("series: " + series);
		System.out.println("star: " + star);
		try {
			Pager<QueryDeviceTypeDTO> pager = deviceService.pageQueryDeviceType(page, rows, series, star, userType);
			json.setRows(pager.getDatas());
			json.setTotal(pager.getTotalCount());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return json;
	}

	//通过 系列、星、设备类型、设备型号 获取设备
	@RequestMapping("/getDevicesByParam")
	@ResponseBody
	public List<QueryDeviceDTO> getDevicesByParam(String series, String star, String deviceType, String model) {
		List<QueryDeviceDTO> list = null;
		try {
			list = deviceService.getDeviceByParam(series, star, deviceType, model);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	@RequestMapping(value = "/createDevice")
	@ResponseBody
	public JsonMessage createDevice(DeviceDto deviceDto, HttpServletRequest request, HttpServletResponse response) {
		System.out.println("come in createDevice ");
		System.out.println(deviceDto);
		JsonMessage jsonMsg = new JsonMessage();
		try {
			boolean falg = deviceService.checkDevice(deviceDto.getSeriersId().toString(),
					deviceDto.getStarId().toString(), deviceDto.getDeviceType().toString(), deviceDto.getDeviceName());
			if (falg) {
				jsonMsg.setSuccess(false);
				jsonMsg.setMsg("设备已存在！");
				jsonMsg.setObj("设备已存在！");
				return jsonMsg;
			}
			deviceService.addDevice(deviceDto);
			jsonMsg.setSuccess(true);
			jsonMsg.setMsg("新增设备成功！");
			return jsonMsg;
		} catch (Exception e) {
			e.printStackTrace();
			jsonMsg.setSuccess(false);
			jsonMsg.setMsg("新增设备出错！");
			jsonMsg.setObj(e.getMessage());
			return jsonMsg;
		}
	}

	@RequestMapping(value = "/getDeviceById")
	@ResponseBody
	public QueryDeviceDTO getDeviceById(long deviceId) {
		QueryDeviceDTO queryDeviceDTO = null;
		try {
			queryDeviceDTO = deviceService.getDeviceById(deviceId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return queryDeviceDTO;
	}

	// 编辑特殊工况参数
	@RequestMapping(value = "/editDevice")
	@ResponseBody
	public JsonMessage editDevice(DeviceDto deviceDto, HttpServletRequest request, HttpServletResponse response) {
		JsonMessage jsonMsg = new JsonMessage();
		try {
			QueryDeviceDTO device = deviceService.getDeviceById(deviceDto.getDeviceId());
			if (StringUtils.isNotBlank(deviceDto.getStartDate())) {
				if (deviceDto.getRunStatus() == 0) {
					if (StringUtils.isNotBlank(device.getEndDate())) {
						if (DateUtil.format(deviceDto.getStartDate(), "yyyy-MM-dd")
								.after(DateUtil.format(device.getEndDate(), "yyyy-MM-dd"))) {
							jsonMsg.setSuccess(false);
							jsonMsg.setMsg("开始时间不能大于结束时间！");
							jsonMsg.setObj("开始时间不能大于结束时间！");
							return jsonMsg;
						}
					}
				}
				if (deviceDto.getRunStatus() == 1) {
					if (StringUtils.isNotBlank(device.getEndDate())) {
						if (DateUtil.format(deviceDto.getStartDate(), "yyyy-MM-dd")
								.before(DateUtil.format(device.getEndDate(), "yyyy-MM-dd"))) {
							jsonMsg.setSuccess(false);
							jsonMsg.setMsg("开始时间不能小于结束时间！");
							jsonMsg.setObj("开始时间不能小于结束时间！");
							return jsonMsg;
						}
					}
				}
			}
			if (StringUtils.isNotBlank(deviceDto.getEndDate())) {
				if (deviceDto.getRunStatus() == 0) {
					if (DateUtil.format(deviceDto.getEndDate(), "yyyy-MM-dd")
							.before(DateUtil.format(device.getStartDate(), "yyyy-MM-dd"))) {
						jsonMsg.setSuccess(false);
						jsonMsg.setMsg("结束时间不能小于开始时间！");
						jsonMsg.setObj("结束时间不能小于开始时间！");
						return jsonMsg;
					}
				}
				if (deviceDto.getRunStatus() == 1) {
					if (DateUtil.format(deviceDto.getEndDate(), "yyyy-MM-dd")
							.after(DateUtil.format(device.getStartDate(), "yyyy-MM-dd"))) {
						jsonMsg.setSuccess(false);
						jsonMsg.setMsg("结束时间不能大于开始时间！");
						jsonMsg.setObj("结束时间不能大于开始时间！");
						return jsonMsg;
					}
				}
			}
			deviceService.updateDevice(deviceDto);
		} catch (Exception e) {
			e.printStackTrace();
			jsonMsg.setSuccess(false);
			jsonMsg.setMsg("编辑设备失败！");
			jsonMsg.setObj(e.getMessage());
			return jsonMsg;
		}
		jsonMsg.setSuccess(true);
		jsonMsg.setMsg("编辑设备成功！");
		return jsonMsg;
	}

	// 批量删除参数
	@RequestMapping(value = "/deleteDevice")
	@ResponseBody
	public JsonMessage deleteDevice(HttpServletRequest request, String deviceIds) {
		System.out.println("come in deleteDevice");
		System.out.println(deviceIds);
		String[] deviceIdArray = deviceIds.split(",");
		JsonMessage jsonMsg = new JsonMessage();
		try {
			for (String deviceId : deviceIdArray) {
				deviceService.deleteDevice(Long.parseLong(deviceId));
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

	//获取设备类型
	@RequestMapping(value = "/getDeviceTypeList")
	@ResponseBody
	public List<DeviceType> getDeviceTypeList(HttpServletRequest request) {
		List<DeviceType> deviceTypes = null;
		List<DeviceType> deviceTypesNew = new ArrayList<DeviceType>();
		try {
			deviceTypes = deviceService.getDeviceTypeList();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String userType = getUserType(null, request);
		if (StringUtils.isNotBlank(userType)) {
			for (DeviceType deviceType : deviceTypes) {
				if (userType.equals(deviceType.getDeviceCode())) {
					deviceTypesNew.add(deviceType);
				}
			}
		} else {
			deviceTypesNew.addAll(deviceTypes);
		}
		return deviceTypesNew;
	}

	@RequestMapping("/getDeviceTypeComboData")
	@ResponseBody
	public List<Combo> getDeviceTypeComboData(String deviceTypeCode) {
		// System.out.println("getDeviceTypeComboData..");
		// System.out.println("deviceTypeCode: " + deviceTypeCode);
		List<Combo> list = deviceService.getDeviceTypeComboData(deviceTypeCode);
		// for (Combo combo : list) {
		// System.out.println(combo);
		// }
		return list;
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
