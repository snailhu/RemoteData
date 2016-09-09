package DataAn.sys.dao.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import DataAn.common.dao.BaseDaoImpl;
import DataAn.sys.dao.IDepartmentDao;
import DataAn.sys.domain.Department;

@Repository
public class DepartmentDaoImpl extends BaseDaoImpl<Department>
implements IDepartmentDao{

	@SuppressWarnings("unchecked")
	@Override
	public List<Department> selectByParentId(long parentId, String order) {
		String o = "";
		if(StringUtils.isNotBlank(order)){
			o = " order by dept." + order;
		}else{
			o = " order by dept.createDate";
		}
		if(parentId == 0){
			String hql = "from Department dept where dept.department.departmentId=?" + o;
			return this.getSession().createQuery(hql).setParameter(0, parentId).list();			
		}else{
			String hql = "from Department dept where dept.department.departmentId is null" + o;
			return this.getSession().createQuery(hql).list();	
		}
	}

}
