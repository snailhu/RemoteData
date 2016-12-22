package DataAn.sys.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import DataAn.common.dao.BaseDaoImpl;
import DataAn.common.dao.Pager;
import DataAn.sys.dao.IRoleDao;
import DataAn.sys.domain.Role;


@Repository
public class RoleDaoImpl extends BaseDaoImpl<Role>
implements IRoleDao{

	@Override
	public void deleteByRoleIds(List<Long> roleIds) {
		String hql = "delete from Role r where r.roleId in (:roleIds)";
		this.getSession().createQuery(hql).setParameterList("roleIds", roleIds).executeUpdate();
	}
	
	@Override
	public Role selectByRoleName(String roleName) {
		String hql = "form Role r where r.roleName = ?";
		return (Role) this.getSession().createQuery(hql).setParameter(0, roleName).uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Pager<Role> selectByPager(int pageIndex, int pageSize) {
		String hql = "from Role order by createDate desc";
		String countHQL = "select count(*) from Role";
		List<Role> list = this.getSession().createQuery(hql)
										   .setMaxResults(pageSize)
										   .setFirstResult(pageSize * (pageIndex -1)).list();
		Long totalCount = 0l; 
		Object obj = this.getSession().createQuery(countHQL).uniqueResult();
		if(obj != null){
			totalCount = (Long) obj;
		}
		Pager<Role> pager = new Pager<Role>(pageIndex,pageSize,totalCount,list);
		return pager;
	}
}
