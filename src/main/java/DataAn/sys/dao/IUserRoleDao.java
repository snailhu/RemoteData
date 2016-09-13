package DataAn.sys.dao;

import java.util.List;

import DataAn.common.dao.IBaseDao;
import DataAn.sys.domain.Role;
import DataAn.sys.domain.User;
import DataAn.sys.domain.UserRole;

public interface IUserRoleDao extends IBaseDao<UserRole>{

	public abstract void deleteByUserId(long userId);

	public abstract UserRole selectByUserId(long userId);
	
	public abstract Role selectRoleByUserId(long userId);

	public abstract User selectUserByRoleName(String roleName);
}
