package DataAn.sys.dao;

import java.util.List;

import DataAn.common.dao.IBaseDao;
import DataAn.sys.domain.DepartmentUser;

public interface IDepartmentUserDao extends IBaseDao<DepartmentUser>{

	public void deleteByUserId(long userId);

	public void deleteByDepartmentId(long departmentId);

	public DepartmentUser selectByUserId(long userId);

	public List<DepartmentUser> selectByDepartmentIds(List<Long> deptIds);

}
