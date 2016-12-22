package DataAn.sys.dao;

import java.util.List;

import DataAn.common.dao.IBaseDao;
import DataAn.sys.domain.Role;
import DataAn.sys.domain.User;
import DataAn.sys.domain.UserRole;

public interface IUserRoleDao extends IBaseDao<UserRole>{

	public void deleteByUserId(long userId);
	
	public void deleteByUserIds(List<Long> userIds);

	public void deleteByRoleId(long roleId);
	
	public void deleteByRoleIds(List<Long> roleIds);
	
	public UserRole selectByUserId(long userId);
	
	public Role selectRoleByUserId(long userId);

	public User selectUserByRoleName(String roleName);
}
