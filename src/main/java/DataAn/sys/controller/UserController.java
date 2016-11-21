package DataAn.sys.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

import DataAn.common.controller.BaseController;
import DataAn.common.dao.Pager;
import DataAn.common.pageModel.EasyuiDataGridJson;
import DataAn.common.pageModel.JsonMessage;
import DataAn.sys.dto.UserDto;
import DataAn.sys.service.IAuditService;
import DataAn.sys.service.IDepartmentService;
import DataAn.sys.service.IUserService;


@Controller
@RequestMapping(value = "/admin/user")
public class UserController extends BaseController{

	@Resource
	private IUserService userService;
	@Resource
	private IDepartmentService departmentService;
	@Resource
	private IAuditService auditService;
	
	// 返回用户管理主页
	@RequestMapping()
	public String toUserIndex() {
		return "redirect:/admin/user/index";
	}
	@RequestMapping("index")
	public String userIndex() {
		return "admin/user/index";
	}
	
	//获取用户列表
	@RequestMapping(value = "/getUserList", method = RequestMethod.POST)
	@ResponseBody
	public EasyuiDataGridJson getUserList(int page, int rows,String sort, String order, WebRequest request) {
		EasyuiDataGridJson json = new EasyuiDataGridJson();
		String userName = request.getParameter("name");
		String createdateStart = request.getParameter("createdatetimeStart");
		String createdateEnd = request.getParameter("createdatetimeEnd");
		String updatedateStart = request.getParameter("updatedatetimeStart");
		String updatedateEnd = request.getParameter("updatedatetimeEnd");
		String orgIds = request.getParameter("orgIds");
		String[] deptIds = null;
		if (StringUtils.isNotBlank(orgIds)) {
			deptIds = orgIds.split(",");
		}
		System.out.println("come in getUserList...");
		System.out.println("pageIndex: " + page);
		System.out.println("pageSize: " + rows);
		System.out.println("sort: " + sort);
		System.out.println("order: " + order);
		System.out.println("userName: " + userName);
		System.out.println("deptIds: " + orgIds);
		System.out.println("createdateStart: " + createdateStart);
		System.out.println("createdateEnd: " + createdateEnd);
		System.out.println("updatedateStart: " + updatedateStart);
		System.out.println("updatedateEnd: " + updatedateEnd);
		
		Pager<UserDto> pager = userService.getUserList(page, rows, userName,createdateStart,
				createdateEnd, updatedateStart, updatedateEnd,deptIds,sort,order);
		if(pager != null){
			json.setTotal(pager.getTotalCount());
			json.setRows(pager.getDatas());			
		}else{
			System.out.println("pager is null");
		}
		return json;			
	}

	// 创建用户
	@RequestMapping(value = "/createUser")
	@ResponseBody
	public JsonMessage createUser(UserDto user,HttpServletRequest request,HttpServletResponse response) {
		System.out.println("come in createUser ");
		System.out.println(user);
		JsonMessage jsonMsg = new JsonMessage();
		if (userService.existUserName(user)) {
			jsonMsg.setSuccess(false);
			jsonMsg.setMsg("用户名已存在！");
			jsonMsg.setObj("用户名已存在！");
			return jsonMsg;
		} 
		try {
			String currentUserName = getCurrentUserName(request);
			user.setCreateUser(currentUserName);
			userService.save(user);
//			UserDto userModel = userService.getUserByName(currentUserName);
//			// 保存日志记录
//			LogDto logModel = new LogDto();
//			logModel.setUserName(currentUserName);
//			logModel.setContent(currentUserName + "添加了用户：" + user.getUserName());
//			auditService.saveLog(logModel);
		} catch (Exception e) {
			e.printStackTrace();
			jsonMsg.setSuccess(false);
			jsonMsg.setMsg("添加用户出错！");
			jsonMsg.setObj(e.getMessage());
			return jsonMsg;
		}
		jsonMsg.setSuccess(true);
		jsonMsg.setMsg("添加用户成功！");
		
		return jsonMsg;
	}

	// 返回编辑用户数据
	@RequestMapping(value = "/editUserForm")
	@ResponseBody
	public UserDto editUserForm(long userId) {
		UserDto user = userService.getUserByUserId(userId);
		user.setPassWord("");
//		long departmentId = departmentService.getDeptIdByUserId(userId);
//		user.setDepartmentId(departmentId);
		return user;
	}

	// 编辑用户
	@RequestMapping(value = "/editUser")
	@ResponseBody
	public JsonMessage editUser(UserDto user, HttpServletRequest request, HttpServletResponse response) {
		System.out.println("update user： "+ user.toString());
		JsonMessage jsonMsg = new JsonMessage();
		if (userService.existUserName(user)) {
			jsonMsg.setSuccess(false);
			jsonMsg.setMsg("用户名已存在！");
			jsonMsg.setObj("用户名已存在！");
			return jsonMsg;
		} 
		try {
			String currentUserName = getCurrentUserName(request);
			user.setUpdateUser(currentUserName);
			userService.update(user);
//			UserDto userModel = userService.getUserByName(currentUserName);
//			// 保存日志记录
//			LogDto logModel = new LogDto();
//			logModel.setUserName(currentUserName);
//			logModel.setContent(currentUserName + "修改了用户：" + user.getUserName());
//			auditService.saveLog(logModel);
		} catch (Exception e) {
			e.printStackTrace();
			jsonMsg.setSuccess(false);
			jsonMsg.setMsg("编辑用户失败！");
			jsonMsg.setObj(e.getMessage());
			return jsonMsg;
		}
		jsonMsg.setSuccess(true);
		jsonMsg.setMsg("编辑用户成功！");
		return jsonMsg;
	}
	// 批量删除用户
	@RequestMapping(value = "/deleteUser")
	@ResponseBody
	public JsonMessage deleteUser(HttpServletRequest request, String userIds) {
		System.out.println("come in deleteUser");
		System.out.println(userIds);
		String[] userIdArray = userIds.split(",");
		JsonMessage jsonMsg = new JsonMessage();
		try {
			userService.delete(userIdArray);
//			String currentUserName = getCurrentUserName(request);
			// 保存日志记录
//			LogDto logModel = new LogDto();
//			logModel.setUserName(currentUserName);
//			logModel.setContent(currentUserName + "删除了用户：" + result);
//			auditService.saveLog(logModel);
		} catch (Exception e) {
			e.printStackTrace();
			jsonMsg.setSuccess(false);
			jsonMsg.setMsg("删除用户失败!");
			jsonMsg.setObj(e.getMessage());
			return jsonMsg;
		}
		jsonMsg.setSuccess(true);
		jsonMsg.setMsg("删除用户成功!");
		return jsonMsg;
	}

	// 编辑用户角色
	@RequestMapping(value = "/editRole")
	@ResponseBody
	public JsonMessage editRole(long userId, long roleId) {
		System.out.println("editRole...");
		System.out.println("userId: " + userId);
		System.out.println("roleId: " + roleId);
		JsonMessage jsonMsg = new JsonMessage();
		try {
			userService.saveUserRole(userId, roleId);
		} catch (Exception e) {
			e.printStackTrace();
			jsonMsg.setSuccess(false);
			jsonMsg.setMsg("编辑个人角色失败!");
			jsonMsg.setObj(e.getMessage());
			return jsonMsg;
		}
		jsonMsg.setSuccess(true);
		jsonMsg.setMsg("编辑个人角色成功!");
		return jsonMsg;
	}
}
