package DataAn.sys.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import DataAn.common.dao.BaseDaoImpl;
import DataAn.sys.dao.IUserRoleDao;
import DataAn.sys.domain.Role;
import DataAn.sys.domain.User;
import DataAn.sys.domain.UserRole;


@Repository
public class UserRoleDaoImpl extends BaseDaoImpl<UserRole>
implements IUserRoleDao{

	@Override
	public void deleteByUserId(long userId) {
		String hql = "delete from UserRole ur where ur.id.userId=:userId";
		this.getSession().createQuery(hql).setParameter("userId", userId).executeUpdate();
	}

	@Override
	public Role selectRoleByUserId(long userId) {
		List<UserRole> userRoleList = this.findByParam("id.userId", userId);
		if(userRoleList != null && userRoleList.size() > 0){
			return userRoleList.get(0).getRole();
		}
		return null;
	}

	@Override
	public User selectUserByRoleName(String roleName) {
		String hql = "select ur.user from UserRole ur,Role r where ur.id.roleId = r.roleId and r.roleName = ?";
		Object obj =  this.getSession().createQuery(hql).setParameter(0, roleName).uniqueResult();
		if(obj != null ){
			User user= (User) obj;
			return user;
		}
		return null;
	}

	
}
