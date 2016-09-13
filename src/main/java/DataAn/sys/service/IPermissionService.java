package DataAn.sys.service;

import java.util.List;

import DataAn.common.dao.Pager;
import DataAn.common.pageModel.EasyuiTreeNode;
import DataAn.sys.dto.PermissionGroupDto;
import DataAn.sys.dto.PermissionItemDto;

public interface IPermissionService {

	public void initPermission();
	
	void savePermissionGroup(PermissionGroupDto permissionGroup);

	void savePermissionItem(PermissionItemDto permissionItem);

	void deleteById(long id);

	void delete(String[] permissionGroupIdArray);

	void update(PermissionGroupDto permissionGroup);

	void update(PermissionItemDto permissionItem);

	Pager<PermissionGroupDto> getAllPermissionGroupList(int pageIndex, int pageSize);

	List<PermissionItemDto> getPermissionItemsByPermissionGroupId(long permissionGroupId);

	List<EasyuiTreeNode> getTree(long roleId);

}
