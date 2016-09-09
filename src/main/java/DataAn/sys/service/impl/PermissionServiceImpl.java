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
import DataAn.common.pageModel.EasyuiTreeNode;
import DataAn.common.utils.DateUtil;
import DataAn.sys.dao.IAuthDao;
import DataAn.sys.dao.IRoleAuthDao;
import DataAn.sys.domain.Auth;
import DataAn.sys.domain.RoleAuth;
import DataAn.sys.dto.PermissionGroupDto;
import DataAn.sys.dto.PermissionItemDto;
import DataAn.sys.service.IPermissionService;

@Service
public class PermissionServiceImpl implements IPermissionService{

	@Resource
	private IAuthDao authDao;
	@Resource
	private IRoleAuthDao roleAuthDao;
	
	@Override
	@Transactional
	public void savePermissionGroup(PermissionGroupDto permissionGroup) {
		Auth auth = new Auth();
		auth.setAuthName(permissionGroup.getName());
		auth.setCreateDate(new Date());
		auth.setUpdateDate(auth.getCreateDate());
		auth.setDescription(permissionGroup.getDescription());
		auth.setVersion(1);
		auth.setStatus("1");
		authDao.add(auth);
	}

	@Override
	@Transactional
	public void savePermissionItem(PermissionItemDto permissionItem) {
		Auth auth = new Auth();
		Auth parentAuth = new Auth();
		parentAuth.setAuthId(permissionItem.getPermissionGroupId());
		auth.setAuth(parentAuth);
		auth.setAuthName(permissionItem.getDisplayName());
		auth.setCode(permissionItem.getCode());
		auth.setDescription(permissionItem.getDescription());
		auth.setCreateDate(new Date());
		auth.setUpdateDate(auth.getCreateDate());
		auth.setVersion(1);
		auth.setStatus("1");
		authDao.add(auth);
	}

	@Override
	@Transactional
	public void deleteById(long id) {
		authDao.deleteByParentAuthId(id);
		authDao.delete(id);
	}

	@Override
	@Transactional
	public void delete(String[] permissionGroupIdArray) {
		for (String permissionGroupId : permissionGroupIdArray) {
			long id = Long.parseLong(permissionGroupId);
			authDao.deleteByParentAuthId(id);
			authDao.delete(id);
		}
		
	}

	@Override
	@Transactional
	public void update(PermissionGroupDto permissionGroup) {
		Auth auth = authDao.get(permissionGroup.getId());
		if (StringUtils.isNotBlank(permissionGroup.getName())) {
			auth.setAuthName(permissionGroup.getName());			
		}
		if (StringUtils.isNotBlank(permissionGroup.getDescription())) {
			auth.setDescription(permissionGroup.getDescription());			
		}
		auth.setUpdateDate(new Date());
		auth.setVersion(auth.getVersion() + 1);
		authDao.update(auth);
	}

	@Override
	@Transactional
	public void update(PermissionItemDto permissionItem) {
		Auth auth = authDao.get(permissionItem.getId());
		if (StringUtils.isNotBlank(permissionItem.getDisplayName())) {
			auth.setAuthName(permissionItem.getDisplayName());			
		}
		if (StringUtils.isNotBlank(permissionItem.getCode())) {
			auth.setCode(permissionItem.getCode());
		}
		if (StringUtils.isNotBlank(permissionItem.getDescription())) {
			auth.setDescription(permissionItem.getDescription());
		}
		auth.setUpdateDate(new Date());
		auth.setVersion(auth.getVersion() + 1);
		authDao.update(auth);
	}

	@Override
	public Pager<PermissionGroupDto> getAllPermissionGroupList(int pageIndex,int pageSize) {
		if(pageIndex == 0){
			pageIndex = 1;
		}
		List<PermissionGroupDto> groups = new ArrayList<PermissionGroupDto>();
		Pager<Auth> authPager = authDao.selectByParentAuthIdIsNullByOrder(null,pageIndex,pageSize);
		List<Auth> list = authPager.getDatas();
		if (list != null && list.size() > 0){
			PermissionGroupDto group = null;
			for (Auth auth : list) {
				group = new PermissionGroupDto();
				group.setId(auth.getAuthId());
				group.setName(auth.getAuthName());
				group.setDescription(auth.getDescription());
				group.setCreateDate(DateUtil.format(auth.getCreateDate()));
				groups.add(group);
			}
		}
		Pager<PermissionGroupDto> pager = new Pager<PermissionGroupDto>(pageIndex, pageSize, authPager.getTotalCount(), groups);
		return pager;	
	}

	@Override
	public List<PermissionItemDto> getPermissionItemsByPermissionGroupId(
			long permissionGroupId) {
		List<PermissionItemDto> permissions = new ArrayList<PermissionItemDto>();
		List<Auth> list = authDao.selectByParentAuthIdByOrder(permissionGroupId,null);
		if(list != null && list.size() > 0){
			PermissionItemDto permission = null;
			for (Auth auth : list) {
				permission = new PermissionItemDto(); 
				permission.setId(auth.getAuthId());
				permission.setDisplayName(auth.getAuthName());
				permission.setCode(auth.getCode());
				permission.setDescription(auth.getDescription());
				permission.setCreateDate(DateUtil.format(auth.getCreateDate()));
				permission.setPermissionGroupId(permissionGroupId);
				permissions.add(permission);
			}
		}
		return permissions;		
	}

	@Override
	public List<EasyuiTreeNode> getTree(long roleId) {
		List<EasyuiTreeNode> nodes = new ArrayList<EasyuiTreeNode>();
		List<Auth> list = authDao.selectByParentAuthIdByOrder(0, null);
		if(list != null && list.size() > 0){
			//保存当前角色所关联的权限
			Set<Long> roleAuthIds = new HashSet<Long>();
			//获取当前角色所关联的权限
			List<RoleAuth> roleAuthList = roleAuthDao.selectByRoleId(roleId);
			if(roleAuthList != null && roleAuthList.size() > 0){
				for (RoleAuth ra : roleAuthList) {
					roleAuthIds.add(ra.getId().getAuthId());
				}
			}
			EasyuiTreeNode tree = null;
			//获取当前角色， 有关联的权限
			if(roleAuthIds != null && roleAuthIds.size() > 0){
				for (Auth group : list) {
					tree = this.toTree(group,roleAuthIds);
					if(tree != null){
						nodes.add(tree);						
					}
				}				
			}else{//获取当前角色， 无关联的权限
				for (Auth group : list) {
					tree = this.toTree(group);
					if(tree != null){
						nodes.add(tree);						
					}
				}	
			}
		}
		return nodes;
	}
	/**
	 * 递归辅助函数
	 * @return
	 */
	private EasyuiTreeNode toTree(Auth group, Set<Long> roleAuthIds) {
		List<Auth> list = authDao.selectByParentAuthIdByOrder(group.getAuthId(),null);
		if (list != null && list.size() > 0) {
			EasyuiTreeNode node = new EasyuiTreeNode();
			node.setId(group.getAuthId());
			node.setText(group.getAuthName());
			List<EasyuiTreeNode> children = new ArrayList<EasyuiTreeNode>();
			EasyuiTreeNode node_children = null;
			for (Auth auth_children : list) {
				node_children = new EasyuiTreeNode();
				node_children.setId(auth_children.getAuthId());
				node_children.setText(auth_children.getAuthName());
				if(roleAuthIds.contains(auth_children.getAuthId())){
					node_children.setChecked(true);
				}
				children.add(node_children);
			}
			node.setChildren(children);
			return node;
		}
		return null;
	}
	private EasyuiTreeNode toTree(Auth group) {
		List<Auth> list = authDao.selectByParentAuthIdByOrder(group.getAuthId(),null);
		if (list != null && list.size() > 0) {
			EasyuiTreeNode node = new EasyuiTreeNode();
			node.setId(group.getAuthId());
			node.setText(group.getAuthName());
			List<EasyuiTreeNode> children = new ArrayList<EasyuiTreeNode>();
			EasyuiTreeNode node_children = null;
			for (Auth auth_children : list) {
				node_children = new EasyuiTreeNode();
				node_children.setId(auth_children.getAuthId());
				node_children.setText(auth_children.getAuthName());
				children.add(node_children);
			}
			node.setChildren(children);
			return node;
		}
		return null;
	}
}
