package DataAn.sys.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import DataAn.common.dao.Pager;
import DataAn.common.pageModel.Combo;
import DataAn.common.utils.DateUtil;
import DataAn.common.utils.UUIDGeneratorUtil;
import DataAn.sys.dao.IAuthDao;
import DataAn.sys.dao.IRoleAuthDao;
import DataAn.sys.dao.IRoleDao;
import DataAn.sys.dao.IUserRoleDao;
import DataAn.sys.domain.Auth;
import DataAn.sys.domain.Role;
import DataAn.sys.domain.RoleAuth;
import DataAn.sys.domain.RoleAuthId;
import DataAn.sys.domain.UserRole;
import DataAn.sys.dto.RoleDto;
import DataAn.sys.service.IRoleService;


@Service
public class RoleServiceImpl implements IRoleService {

	@Resource
	private IRoleDao roleDao;
	@Resource
	private IUserRoleDao userRoleDao;
	@Resource
	private IRoleAuthDao roleAuthDao;
	@Resource
	private IAuthDao authDao;
	
	@Override
	@Transactional
	public void save(RoleDto roleDto) {
		Role role = new Role();
		if (StringUtils.isNotBlank(roleDto.getName())) {
			role.setRoleName(roleDto.getName());
			role.setDescription(roleDto.getDescription());
			role.setCreateDate(new Date());
			role.setUpdateDate(role.getCreateDate());
			role.setVersion(1);
			roleDao.add(role);			
		}
	}
	
	@Override
	@Transactional
	public void delete(String[] roleIdArray) {
		for (String roleId : roleIdArray) {
			roleDao.delete(Long.parseLong(roleId));
		}
	}
	
	@Override
	@Transactional
	public void update(RoleDto roleDto) {
		Role role = roleDao.get(roleDto.getId());
		if (StringUtils.isNotBlank(roleDto.getName())){
			role.setRoleName(roleDto.getName());	
			
			if (StringUtils.isNotBlank(roleDto.getDescription())){
				role.setDescription(roleDto.getDescription());		
			}
			role.setUpdateDate(new Date());
			role.setVersion(role.getVersion() + 1);
			roleDao.update(role);
		}
	}
	
	@Override
	public RoleDto getRoleByRoleId(long roleId) {
		Role role = roleDao.get(roleId);
		RoleDto roleDto = new RoleDto();
		roleDto.setId(role.getRoleId());
		roleDto.setName(role.getRoleName());
		roleDto.setDescription(role.getDescription());
		roleDto.setCreateDate(DateUtil.format(role.getCreateDate()));
		return roleDto;
	}
	
	@Override
	public boolean existRole(RoleDto role) {
		List<Role> list = roleDao.findByParam("roleName", role.getName());
		if(list != null && list.size() > 0){
			return true;
		}
		return false;
	}
	@Override
	public Pager<RoleDto> getRoleList(int pageIndex, int pageSize) {
		if(pageIndex == 0){
			pageIndex = 1;
		}
		List<RoleDto> roleDtoList = new ArrayList<RoleDto>();
		Pager<Role> rolePager = roleDao.selectByPager(pageIndex, pageSize);
		if(rolePager != null){
			List<Role> roleList = rolePager.getDatas();
			if(roleList != null && roleList.size() > 0){
				for (Role role : roleList) {
					roleDtoList.add(this.pojoToDto(role));
				}
			}			
		}
		Pager<RoleDto> pager = new Pager<RoleDto>(pageIndex, pageSize, rolePager.getTotalCount(), roleDtoList);
		return pager;			
	}

	@Override
	public List<Combo> getRoleComboData(long userId) {
		List<Combo> comboList = new ArrayList<Combo>();
		List<UserRole> userRoleList = userRoleDao.findByParam("id.userId", userId);
		long roleId = 0;
		if(userRoleList != null && userRoleList.size() > 0){
			roleId = userRoleList.get(0).getRole().getRoleId();
		}
		List<Role> roles = roleDao.findAll();
		Combo combo = null;
		if(roles != null && roles.size() > 0){
			for (Role role : roles) {
				combo = new Combo();
				combo.setText(role.getRoleName());
				combo.setValue(role.getRoleId().toString());
				if(roleId !=0 && role.getRoleId() == roleId){
					combo.setSelected(true);
				}
				comboList.add(combo);
			}
		}
		return comboList;
	}

	@Override
	@Transactional
	public void saveRolePermissionItems(long roleId,String[] permissionItemArray) {
		//删除之前的角色权限
		roleAuthDao.deleteByRoleId(roleId);
		List<Auth> authList = authDao.selectByParentAuthIdByOrder(0, null);
		Set<Long> roleAuthIds = new HashSet<Long>();
		for (Auth a : authList) {
			roleAuthIds.add(a.getAuthId());
		}
		for (String  StrPermissionId : permissionItemArray) {
			Long permissionId = Long.parseLong(StrPermissionId);
			//只保存权限项，不用保存权限组
			if(!roleAuthIds.contains(permissionId)){
				RoleAuth roleAuth = new RoleAuth();
				RoleAuthId id = new RoleAuthId();
				id.setAuthId(permissionId);
				id.setRoleId(roleId);
				roleAuth.setId(id);
				roleAuth.setRoleAuthId(UUIDGeneratorUtil.getUUID());
				roleAuth.setCreateDate(new Date());
				roleAuth.setUpdateDate(roleAuth.getCreateDate());
				roleAuth.setVersion(1);
				roleAuthDao.add(roleAuth);
			}
		}
	}
	
	private RoleDto pojoToDto(Role role) {
		RoleDto dto = new RoleDto();
		dto.setId(role.getRoleId());
		dto.setName(role.getRoleName());
		dto.setDescription(role.getDescription());
		dto.setPermissionJsonData("");
		dto.setCreateDate(DateUtil.format(role.getCreateDate()));
		return dto;
	}
}
