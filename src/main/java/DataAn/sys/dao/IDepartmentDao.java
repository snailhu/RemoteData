package DataAn.sys.dao;

import java.util.List;

import DataAn.common.dao.IBaseDao;
import DataAn.sys.domain.Department;

public interface IDepartmentDao extends IBaseDao<Department>{

	public List<Department> selectByParentId(long parentId, String order);
}
