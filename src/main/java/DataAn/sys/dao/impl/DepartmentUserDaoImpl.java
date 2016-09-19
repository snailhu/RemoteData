package DataAn.sys.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import DataAn.common.dao.BaseDaoImpl;
import DataAn.sys.dao.IDepartmentUserDao;
import DataAn.sys.domain.DepartmentUser;

@Repository
public class DepartmentUserDaoImpl extends BaseDaoImpl<DepartmentUser>
implements IDepartmentUserDao{

	@Override
	public void deleteByUserId(long userId) {
		String hql = "delete from DepartmentUser du where du.id.userId=?";
		this.getSession().createQuery(hql).setParameter(0, userId).executeUpdate();
	}

	@Override
	public void deleteByDepartmentId(long departmentId) {
		String hql = "delete from DepartmentUser du where du.id.departmentId=?";
		this.getSession().createQuery(hql).setParameter(0, departmentId).executeUpdate();
	}

	@SuppressWarnings("unchecked")
	@Override
	public DepartmentUser selectByUserId(long userId) {
		String hql = "from DepartmentUser du where du.id.userId=?";
		List<DepartmentUser> list = this.getSession().createQuery(hql).setParameter(0, userId).list();
		if(list != null && list.size() > 0){
			return list.get(0);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DepartmentUser> selectByDepartmentIds(List<Long> deptIds) {
		String hql = "from DepartmentUser du where du.id.departmentId in (:deptIds)";
		return this.getSession().createQuery(hql).setParameterList("deptIds", deptIds).list();
	}

}
