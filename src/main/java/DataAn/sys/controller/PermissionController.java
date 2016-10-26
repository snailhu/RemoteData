package DataAn.sys.controller;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import DataAn.common.dao.Pager;
import DataAn.common.pageModel.EasyuiDataGridJson;
import DataAn.common.pageModel.EasyuiTreeNode;
import DataAn.common.pageModel.JsonMessage;
import DataAn.sys.dto.PermissionGroupDto;
import DataAn.sys.dto.PermissionItemDto;
import DataAn.sys.service.IPermissionService;


@Controller
@RequestMapping("/admin/permission")
public class PermissionController {

	@Resource
	private IPermissionService permissionService;
	
	// 返回权限管理界面
	@RequestMapping()
	public String toPermissionIndex() {
		return "redirect:/admin/permission/index";
	}
	@RequestMapping("/index")
	public String permissionIndex() {
		return "/admin/permission/index";
	}
	//
	@RequestMapping(value = "/getPermissionGroups", method = RequestMethod.POST)
	@ResponseBody
	public EasyuiDataGridJson getPermissionGroups(int page, int rows) {
//		System.out.println("getPermissionGroups..");
//		System.out.println("pageIndex: " + page);
//		System.out.println("pageSize: " + rows);
		EasyuiDataGridJson json = new EasyuiDataGridJson();
		Pager<PermissionGroupDto> pager = permissionService.getAllPermissionGroupList(page, rows);
		json.setRows(pager.getDatas());
		json.setTotal(pager.getTotalCount());
		return json;
	}	
	@RequestMapping("/getPermissionItems")
	@ResponseBody
	public List<PermissionItemDto> getPermissionItems(long permissionGroupId)
	{
		List<PermissionItemDto> permissionItems = permissionService.getPermissionItemsByPermissionGroupId(permissionGroupId);
		return permissionItems;
	}
	/**
	 * 创建权限组
	 */
	@RequestMapping(value="/createPermissionGroup")
	@ResponseBody
	public JsonMessage createPermissionGroup(@RequestParam(value = "name", required = true) String name,
											 @RequestParam(value = "description", required = false) String description){
		PermissionGroupDto permissionGroup = new PermissionGroupDto();
		permissionGroup.setName(name);
		permissionGroup.setDescription(description);
//		System.out.println("come in createPermissionGroup....");
//		System.out.println(permissionGroup);
		JsonMessage jsonMsg = new JsonMessage();
		//判断此权限组是否存在
		boolean flag = permissionService.isExistPermissionGroup(permissionGroup);
		if(flag){
			jsonMsg.setSuccess(false);
			jsonMsg.setMsg("权限组已存在！");
			return jsonMsg;
		}
		try {
			permissionService.savePermissionGroup(permissionGroup);
		} catch (Exception e) {
			e.printStackTrace();
			jsonMsg.setSuccess(false);
			jsonMsg.setMsg("创建权限组出错！");
			jsonMsg.setObj(e.getMessage());
			return jsonMsg;
		}
		jsonMsg.setSuccess(true);
		jsonMsg.setMsg("创建权限组成功！");
	    return jsonMsg;
	}
	@RequestMapping(value="/editPermissionGroup")
	@ResponseBody
	public JsonMessage editPermissionGroup(@RequestParam(value = "id", required = true) long id,
			  							   @RequestParam(value = "name", required = true) String name,
			  							   @RequestParam(value = "description", required = false) String description){
		PermissionGroupDto permissionGroup = new PermissionGroupDto();
		permissionGroup.setId(id);
		permissionGroup.setName(name);
		permissionGroup.setDescription(description);
//		System.out.println("come in editPermissionGroup....");
//		System.out.println(permissionGroup);
		JsonMessage jsonMsg = new JsonMessage();
		try {
			permissionService.update(permissionGroup);
		} catch (Exception e) {
			e.printStackTrace();
			jsonMsg.setSuccess(false);
			jsonMsg.setMsg("编辑权限组出错！");
			jsonMsg.setObj(e.getMessage());
			return jsonMsg;
		}
		jsonMsg.setSuccess(true);
		jsonMsg.setMsg("编辑权限组成功！");
	    return jsonMsg;
	}
	/**
	 * 删除权限组
	 * @param permissionGroupIds
	 */
	@RequestMapping("/deletePermissionGroup")
	@ResponseBody
	public JsonMessage deletePermissionGroup(String permissionGroupIds)
	{
		String[] permissionGroupIdArray = permissionGroupIds.split(",");
		JsonMessage jsonMsg = new JsonMessage();
		try {
			permissionService.delete(permissionGroupIdArray);	
		} catch (Exception e) {
			e.printStackTrace();
			jsonMsg.setSuccess(false);
			jsonMsg.setMsg("删除权限组失败!");
			jsonMsg.setObj(e.getMessage());
			return jsonMsg;
		}
		jsonMsg.setSuccess(true);
		jsonMsg.setMsg("删除权限组成功!");
		return jsonMsg;
	}
	
	@RequestMapping(value="/createPermissionItem", method = RequestMethod.POST)
	@ResponseBody
	public JsonMessage createPermissionItem(@RequestParam(value = "permissionGroupId", required = true) long permissionGroupId,
								  @RequestParam(value = "displayName", required = true) String displayName,
								  @RequestParam(value = "code", required = true) String code,
								  @RequestParam(value = "description", required = false) String description){
		PermissionItemDto permissionItem = new PermissionItemDto();
		permissionItem.setPermissionGroupId(permissionGroupId);
		permissionItem.setDisplayName(displayName);
		permissionItem.setCode(code);
		permissionItem.setDescription(description);
//		System.out.println("come in createPermissionItem..");
//		System.out.println(permissionItem);
		JsonMessage jsonMsg = new JsonMessage();
		//判断权限项是否存在
		boolean flag = permissionService.isExistPermissionItem(permissionItem);
		if(flag){
			jsonMsg.setSuccess(false);
			jsonMsg.setMsg("权限项已存在！");
			return jsonMsg;
		}
		try {
			permissionService.savePermissionItem(permissionItem);
		} catch (Exception e) {
			e.printStackTrace();
			jsonMsg.setSuccess(false);
			jsonMsg.setMsg("创建权限项出错！");
			jsonMsg.setObj(e.getMessage());
			return jsonMsg;
		}
		jsonMsg.setSuccess(true);
		jsonMsg.setMsg("创建权限项成功！");
	    return jsonMsg;
	}
	
	@RequestMapping(value="/editPermissionItem", method = RequestMethod.POST)
	@ResponseBody
	public JsonMessage editPermissionItem(@RequestParam(value = "id", required = true) long id,
								  @RequestParam(value = "displayName", required = true) String displayName,
								  @RequestParam(value = "code", required = true) String code,
								  @RequestParam(value = "description", required = false) String description){
		PermissionItemDto permissionItem = new PermissionItemDto();
		permissionItem.setId(id);
		permissionItem.setDisplayName(displayName);
		permissionItem.setCode(code);
		permissionItem.setDescription(description);
//		System.out.println("come in editPermissionItem..");
//		System.out.println(permissionItem);
		JsonMessage jsonMsg = new JsonMessage();
		try {
			permissionService.update(permissionItem);
		} catch (Exception e) {
			e.printStackTrace();
			jsonMsg.setSuccess(false);
			jsonMsg.setMsg("编辑权限项出错！");
			jsonMsg.setObj(e.getMessage());
			return jsonMsg;
		}
		jsonMsg.setSuccess(true);
		jsonMsg.setMsg("编辑权限项成功！");
	    return jsonMsg;
	}
	@RequestMapping("/deletePermissionItem")
	@ResponseBody
	public JsonMessage deletePermissionItem(long permissionItemId)
	{
		JsonMessage jsonMsg = new JsonMessage();
		try {
			permissionService.deleteById(permissionItemId);	
		} catch (Exception e) {
			e.printStackTrace();
			jsonMsg.setSuccess(false);
			jsonMsg.setMsg("删除权限项失败!");
			jsonMsg.setObj(e.getMessage());
			return jsonMsg;
		}
		jsonMsg.setSuccess(true);
		jsonMsg.setMsg("删除权限项成功!");
		return jsonMsg;
	}
	@RequestMapping("/getTree")
	@ResponseBody
	public List<EasyuiTreeNode> getTree(long roleId) {
		System.out.println("come in getTree");
		System.out.println("roleId: " + roleId);
		List<EasyuiTreeNode> treeNodes = permissionService.getTree(roleId);
//		for (EasyuiTreeNode node : treeNodes) {
//			System.out.println(node.toString());
//			if(node.getChildren() != null){
//				for (EasyuiTreeNode children : node.getChildren()) {
//					System.out.println(children.toString());
//					
//				}
//			}
//		}
		return treeNodes;
	}
}
