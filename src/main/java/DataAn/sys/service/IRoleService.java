package DataAn.sys.service;

import java.util.List;

import DataAn.common.dao.Pager;
import DataAn.common.pageModel.Combo;
import DataAn.sys.dto.RoleDto;


public interface IRoleService {

	public abstract void save(RoleDto roleDto);
	
	public abstract void delete(String[] roleIdArray);
	
	public abstract void update(RoleDto roleDto);
	
	public abstract RoleDto getRoleByRoleId(long roleId);
	
	public abstract boolean existRole(RoleDto role);
	
	public abstract Pager<RoleDto> getRoleList(int pageIndex, int pageSize);

	public abstract List<Combo> getRoleComboData(long userId);

	public abstract void saveRolePermissionItems(long roleId,String[] permissionItemArray);

}
