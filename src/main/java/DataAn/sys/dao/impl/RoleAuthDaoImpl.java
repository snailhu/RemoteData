package DataAn.sys.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import DataAn.common.dao.BaseDaoImpl;
import DataAn.sys.dao.IRoleAuthDao;
import DataAn.sys.domain.RoleAuth;

@Repository
public class RoleAuthDaoImpl extends BaseDaoImpl<RoleAuth>
implements IRoleAuthDao{

	@Override
	public void deleteByAuthId(long authId) {
		String hql = "delete from RoleAuth ra where ra.id.authId=:authId";
		this.getSession().createQuery(hql).setParameter("authId", authId).executeUpdate();
		
	}

	@Override
	public void deleteByAuthIds(List<Long> authIds) {
		String hql = "delete from RoleAuth ra where ra.id.authId in (:authIds)";
		this.getSession().createQuery(hql).setParameterList("authIds", authIds).executeUpdate();
	}
	
	@Override
	public void deleteByRoleId(long roleId) {
		String hql = "delete from RoleAuth ra where ra.id.roleId=:roleId";
		this.getSession().createQuery(hql).setParameter("roleId", roleId).executeUpdate();
	}

	@Override
	public void deleteByRoleIds(List<Long> roleIds) {
		String hql = "delete from RoleAuth ra where ra.id.roleId in (:roleIds)";
		this.getSession().createQuery(hql).setParameterList("roleIds", roleIds).executeUpdate();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<RoleAuth> selectByAuthId(long authId) {
		String hql = "from RoleAuth ra where ra.id.authId=:authId";
		return this.getSession().createQuery(hql).setParameter("authId", authId).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<RoleAuth> selectByRoleId(long roleId) {
		String hql = "from RoleAuth ra where ra.id.roleId=:roleId";
		return this.getSession().createQuery(hql).setParameter("roleId", roleId).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> selectAuthCodeByRoleId(long roleId) {
		String hql = "select a.code from RoleAuth ra,Auth a where ra.id.authId=a.authId and ra.id.roleId=?";
		List<String> list =  this.getSession().createQuery(hql).setParameter(0, roleId).list();
//		for (Object o : list) {
//			Object[] objs = (Object[]) o;
//			for (Object object : objs) {
//				System.out.println(object);
//			}
//		}
		return list;
	}

}
