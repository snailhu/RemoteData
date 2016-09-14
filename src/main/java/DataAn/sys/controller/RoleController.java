package DataAn.sys.controller;

import java.util.Date;
import java.util.List;

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
import DataAn.common.pageModel.Combo;
import DataAn.common.pageModel.EasyuiDataGridJson;
import DataAn.common.pageModel.JsonMessage;
import DataAn.sys.dto.PermissionGroupDto;
import DataAn.sys.dto.RoleDto;
import DataAn.sys.service.IRoleService;

@Controller
@RequestMapping(value = "/admin/role")
public class RoleController  extends BaseController{

	@Resource
	private IRoleService roleService;
	
	// 返回角色管理主页
	@RequestMapping()
	public String toRoleIndex() {
		return "redirect:/admin/role/index";
	}
	@RequestMapping("index")
	public String roleIndex() {
		return "admin/role/index";
	}
	//获取角色列表
	@RequestMapping(value = "/getList", method = RequestMethod.POST)
	@ResponseBody
	public EasyuiDataGridJson getRoleList(int page, int rows) {
//		System.out.println("getRoleList..");
//		System.out.println("pageIndex: " + page);
//		System.out.println("pageSize: " + rows);
		EasyuiDataGridJson json = new EasyuiDataGridJson();
		Pager<RoleDto> pager = roleService.getRoleList(page, rows);
		json.setRows(pager.getDatas());
		json.setTotal(pager.getTotalCount());
		return json;
	}
	/**
	 * 创建系统角色
	 */
	@RequestMapping(value="/createRole")
	@ResponseBody
	public JsonMessage createRole(RoleDto role,HttpServletRequest request,HttpServletResponse response){
//		System.out.println("come in createRole");
//		System.out.println(role);
		JsonMessage jsonMsg = new JsonMessage();
//		boolean flag = roleService.existRole(role);
//		if(flag){
//			String msg = "角色名称已存在";
//			jsonMsg.setSuccess(false);
//			jsonMsg.setMsg(msg);
//			jsonMsg.setObj(null);
//			return jsonMsg;
//		}
		try {
			roleService.save(role);
		} catch (Exception e) {
			e.printStackTrace();
			jsonMsg.setSuccess(false);
			jsonMsg.setMsg("创建系统角色出错！");
			jsonMsg.setObj(e.getMessage());
			return jsonMsg;
		}
		jsonMsg.setSuccess(true);
		jsonMsg.setMsg("创建系统角色成功！");
	    return jsonMsg;
	}
	@RequestMapping(value="/editRole", method = RequestMethod.POST)
	@ResponseBody
	public JsonMessage editRole(@RequestParam(value = "id", required = true) long id,
			  					@RequestParam(value = "name", required = true) String name,
			  					@RequestParam(value = "description", required = false) String description){
		RoleDto role = new RoleDto();
		role.setId(id);
		role.setName(name);
		role.setDescription(description);
//		System.out.println("come in editRole");
//		System.out.println(role);
		JsonMessage jsonMsg = new JsonMessage();
//		boolean flag = roleService.existRole(role);
//		if(flag){
//			String msg = "角色名称已存在";
//			jsonMsg.setSuccess(false);
//			jsonMsg.setMsg(msg);
//			jsonMsg.setObj(null);
//			return jsonMsg;
//		}
		try {
			roleService.update(role);
		} catch (Exception e) {
			e.printStackTrace();
			jsonMsg.setSuccess(false);
			jsonMsg.setMsg("编辑系统角色出错！");
			jsonMsg.setObj(e.getMessage());
			return jsonMsg;
		}
		jsonMsg.setSuccess(true);
		jsonMsg.setMsg("编辑系统角色成功！");
	    return jsonMsg;
	}
	// 删除系统角色
	@RequestMapping("/deleteRole")
	@ResponseBody
	public JsonMessage deleteRole(String roleIds) {
		String[] roleIdArray = roleIds.split(",");
		JsonMessage jsonMsg = new JsonMessage();
		try {
			roleService.delete(roleIdArray);
		} catch (Exception e) {
			e.printStackTrace();
			jsonMsg.setSuccess(false);
			jsonMsg.setMsg("删除系统角色失败!");
			jsonMsg.setObj(e.getMessage());
			return jsonMsg;
		}
		jsonMsg.setSuccess(true);
		jsonMsg.setMsg("删除系统角色成功!");
		return jsonMsg;
	}
	@RequestMapping("/editRolePermission")
	@ResponseBody
	public JsonMessage editPermission(@RequestParam(value = "roleId", required = true)long roleId, 
									  @RequestParam(value = "permissionItemId", required = true)String permissionItemId){
//		System.out.println("come in editPermission..." + new Date());
//		System.out.println("permissionItemId: " + permissionItemId);
//		System.out.println("roleId: " + roleId);
		String[] rolePermissionItemArray = permissionItemId.split(",");
		JsonMessage jsonMsg = new JsonMessage();
		if(rolePermissionItemArray.length == 0){
			jsonMsg.setSuccess(false);
			jsonMsg.setMsg("设置角色权限失败!");
			jsonMsg.setObj("设置角色权限失败!");
			return jsonMsg;
		}
		try {
			roleService.saveRolePermissionItems(roleId, rolePermissionItemArray);
		} catch (Exception e) {
			e.printStackTrace();
			jsonMsg.setSuccess(false);
			jsonMsg.setMsg("设置角色权限失败!");
			jsonMsg.setObj(e.getMessage());
			return jsonMsg;
		}
		jsonMsg.setSuccess(true);
		jsonMsg.setMsg("设置角色权限成功！");
		return jsonMsg;
	}
	@RequestMapping("/getRoleComboData")
	@ResponseBody
	public List<Combo> getRoleComboData(long userId) {
//		System.out.println("getRoleComboData..");
//		System.out.println("userId: " + userId);
		List<Combo> list = roleService.getRoleComboData(userId);
//		for (Combo combo : list) {
//			System.out.println(combo);
//		}
		return list;
	}
}
