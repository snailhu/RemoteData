package DataAn.sys.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import DataAn.common.pageModel.EasyuiTreeNode;
import DataAn.common.utils.DateUtil;
import DataAn.sys.dao.IDepartmentDao;
import DataAn.sys.dao.IDepartmentUserDao;
import DataAn.sys.domain.Department;
import DataAn.sys.domain.DepartmentUser;
import DataAn.sys.dto.DepartmentDto;
import DataAn.sys.service.IDepartmentService;

@Service
public class DepartmentServiceImpl implements IDepartmentService {

	@Resource
	private IDepartmentDao departmentDao;
	@Resource
	private IDepartmentUserDao departmentUserDao;
	
	@Override
	@Transactional
	public boolean save(DepartmentDto deptDto) {
		Department dept=new Department();
		if(deptDto.getParentId() != 0){
			Department parentDept=new Department();
			parentDept.setDepartmentId(deptDto.getParentId());
			dept.setDepartment(parentDept);			
		}
    	dept.setSort(deptDto.getSort());
    	dept.setDepartmentName(deptDto.getName());
    	dept.setDescription(deptDto.getDesc());
    	dept.setCreatePerson(deptDto.getCreatePerson());
    	dept.setCreatePerson(dept.getCreatePerson());
    	dept.setCreateDate(new Date());
    	dept.setUpdateDate(dept.getCreateDate());
    	dept.setVersion(1);
    	departmentDao.add(dept);
		return true;
	}

	@Override
	@Transactional
	public boolean deleteById(long departmentId) {
		List<Department> deptList = departmentDao.selectByParentId(departmentId, null);
		if(deptList != null && deptList.size() > 0){
			for (Department dept : deptList) {
				this.deleteById(dept.getDepartmentId());
			}
		}
		departmentUserDao.deleteByDepartmentId(departmentId);
		departmentDao.delete(departmentId);
		return true;
	}

	@Override
	@Transactional
	public boolean update(DepartmentDto deptDto) {
		Department dept = departmentDao.get(deptDto.getId());
		if(StringUtils.isNotBlank(deptDto.getName())){
			dept.setDepartmentName(deptDto.getName());			
		}
//		dept.setSort(model.getSort());
		if(StringUtils.isNotBlank(deptDto.getDesc())){
			dept.setDescription(deptDto.getDesc());			
		}
		dept.setUpdateDate(new Date());
		//dept.setParentId(deptDto.getParentId());
		departmentDao.update(dept);
		return true;
	}

	@Override
	public DepartmentDto getByDeptId(long departmentId) {
		if(departmentId != 0){
			Department dept = departmentDao.get(departmentId);
			if(dept != null){
				return this.pojotoDto(dept);
			}
		}
		return null;
	}


	@Override
	public List<DepartmentDto> getChildrenByDepartmentId(long id) {
		List<DepartmentDto> departments = new ArrayList<DepartmentDto>();
		List<Department> list = departmentDao.selectByParentId(id, "createDate");
		for (Department department : list) {
			DepartmentDto dept = treeGrid(department, true);
			departments.add(dept);
		}
		return departments;
	}

	@Override
	public List<EasyuiTreeNode> tree(long id) {
		List<EasyuiTreeNode> treeNodes = new ArrayList<EasyuiTreeNode>();
		List<Department> list = departmentDao.selectByParentId(0, "createDate");
		for (Department department : list) {
			EasyuiTreeNode node = tree(department, true);
			treeNodes.add(node);
		}
		return treeNodes;
	}

	@Override
	public DepartmentDto getRootDepartment() {
		List<Department> list = departmentDao.selectByParentId(0, null);
		if(list != null && list.size() > 0){
			return this.pojotoDto(list.get(0));
		}
		return null;
	}

	@Override
	public long getDeptIdByUserId(long userId) {
		
		return 0;
	}
	
	private DepartmentDto pojotoDto(Department department)
    {
		DepartmentDto deptModel = new DepartmentDto();
    	deptModel.setId(department.getDepartmentId());
    	deptModel.setParentId(department.getDepartment().getDepartmentId());
    	deptModel.setSort(department.getSort());
    	deptModel.setName(department.getDepartmentName());
    	deptModel.setDesc(department.getDescription());
    	deptModel.setCreatePerson(department.getCreatePerson());
    	deptModel.setCreateTime(DateUtil.format(department.getCreateDate()));
    	deptModel.setUpdateTime(DateUtil.format(department.getUpdateDate()));
    	deptModel.setUpdatePerson(department.getUpdatePerson());
    	return deptModel;
    }
	/**
	 * 递归辅助函数
	 * @param department
	 * @param recursive
	 * @return
	 */
	private DepartmentDto treeGrid(Department department, boolean recursive) {
		DepartmentDto departmentModel = this.pojotoDto(department);
		List<Department> list = departmentDao.selectByParentId(department.getDepartmentId(), "createDate");
		if (list != null && list.size() > 0) {
			if (recursive) {// 设置是否循环
				List<DepartmentDto> children = new ArrayList<DepartmentDto>();
				for (Department d : list) {
					DepartmentDto dept = treeGrid(d, true);
					children.add(dept);
				}
				departmentModel.setChildren(children);
			}
		}
		return departmentModel;
	}

	/**
	 * 递归辅助函数
	 * @param department
	 * @param recursive
	 * @return
	 */
	private EasyuiTreeNode tree(Department department, boolean recursive) {
		EasyuiTreeNode node = new EasyuiTreeNode();
		node.setId(department.getDepartmentId());
		node.setText(department.getDepartmentName());
		List<Department> list = departmentDao.selectByParentId(department.getDepartmentId(), "createDate");
		if (list != null && list.size() > 0) {
			if (recursive) {// 设置是否循环
				List<EasyuiTreeNode> children = new ArrayList<EasyuiTreeNode>();
				for (Department d : list) {
					EasyuiTreeNode dept = tree(d, true);
					children.add(dept);
				}
				node.setChildren(children);
			}
		}
		return node;
	}



}
