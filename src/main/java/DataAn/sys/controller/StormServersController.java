package DataAn.sys.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import DataAn.common.controller.BaseController;
import DataAn.common.dao.Pager;
import DataAn.common.pageModel.EasyuiDataGridJson;
import DataAn.common.pageModel.JsonMessage;
import DataAn.sys.dto.RoleDto;
import DataAn.sys.dto.StormServerDto;
import DataAn.sys.service.IStormServerService;

@Controller
@RequestMapping(value = "/admin/stormServers")
public class StormServersController extends BaseController{

	@Resource
	private IStormServerService stormServerService;
	
	@RequestMapping("index")
	public String stormServersIndex(){
		
		return "admin/stormServers/index";
	}
	@RequestMapping(value = "/getList", method = RequestMethod.POST)
	@ResponseBody
	public EasyuiDataGridJson getList(int page, int rows) {
		System.out.println("getList..");
		System.out.println("pageIndex: " + page);
		System.out.println("pageSize: " + rows);
		EasyuiDataGridJson json = new EasyuiDataGridJson();
		Pager<StormServerDto> pager = stormServerService.getStormServerList(page, rows);
		json.setRows(pager.getDatas());
		json.setTotal(pager.getTotalCount());
		return json;
	}

	@RequestMapping(value="/createServer")
	@ResponseBody
	public JsonMessage createServer(StormServerDto server,HttpServletRequest request,HttpServletResponse response){
		System.out.println("come in createServer");
		System.out.println(server);
		JsonMessage jsonMsg = new JsonMessage();
		boolean flag = stormServerService.existServer(server);
		if(flag){
			String msg = "服务器已存在";
			jsonMsg.setSuccess(false);
			jsonMsg.setMsg(msg);
			jsonMsg.setObj(msg);
			return jsonMsg;
		}
		try {
			stormServerService.create(server);
		} catch (Exception e) {
			e.printStackTrace();
			jsonMsg.setSuccess(false);
			jsonMsg.setMsg("创建服务器出错！");
			jsonMsg.setObj(e.getMessage());
			return jsonMsg;
		}
		jsonMsg.setSuccess(true);
		jsonMsg.setMsg("创建服务器成功！");
	    return jsonMsg;
	}
	@RequestMapping(value="/editServer", method = RequestMethod.POST)
	@ResponseBody
	public JsonMessage editServer(@RequestParam(value = "id", required = true) long id,
			  					@RequestParam(value = "serverIp", required = true) String serverIp){
		StormServerDto server = new StormServerDto();
		server.setId(id);
		server.setServerIp(serverIp);
		System.out.println("come in editServer");
		System.out.println(server);
		JsonMessage jsonMsg = new JsonMessage();
		boolean flag = stormServerService.existServer(server);
		if(flag){
			String msg = "服务器已存在";
			jsonMsg.setSuccess(false);
			jsonMsg.setMsg(msg);
			jsonMsg.setObj(msg);
			return jsonMsg;
		}
		try {
			stormServerService.update(server);
		} catch (Exception e) {
			e.printStackTrace();
			jsonMsg.setSuccess(false);
			jsonMsg.setMsg("编辑服务器出错！");
			jsonMsg.setObj(e.getMessage());
			return jsonMsg;
		}
		jsonMsg.setSuccess(true);
		jsonMsg.setMsg("编辑服务器成功！");
	    return jsonMsg;
	}
	// 删除服务器
	@RequestMapping("/deleteServer")
	@ResponseBody
	public JsonMessage deleteServer(String ServerIds) {
		JsonMessage jsonMsg = new JsonMessage();
		try {
			stormServerService.delete(ServerIds);
		} catch (Exception e) {
			e.printStackTrace();
			jsonMsg.setSuccess(false);
			jsonMsg.setMsg("删除服务器失败!");
			jsonMsg.setObj(e.getMessage());
			return jsonMsg;
		}
		jsonMsg.setSuccess(true);
		jsonMsg.setMsg("删除服务器成功!");
		return jsonMsg;
	}
}
