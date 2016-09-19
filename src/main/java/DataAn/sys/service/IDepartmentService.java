package DataAn.sys.service;

import java.util.List;

import DataAn.common.pageModel.EasyuiTreeNode;
import DataAn.sys.dto.DepartmentDto;

public interface IDepartmentService {

	public abstract boolean save(DepartmentDto deptDto);

	public abstract boolean deleteById(long departmentId);

	public abstract boolean update(DepartmentDto deptDto);

	public abstract DepartmentDto getByDeptId(long departmentId);

	/**
	 * 根据部门Id获取子部门集合
	 * @param id
	 * @return
	 */
	public abstract List<DepartmentDto> getChildrenByDepartmentId(long id);

	/**
	 * 根据Id获取部门树节点集合
	 * 
	 * @param id
	 * @return
	 */
	public abstract List<EasyuiTreeNode> tree(long id);
	
	public abstract DepartmentDto getRootDepartment();

	public abstract long getDeptIdByUserId(long userId);
	
}
