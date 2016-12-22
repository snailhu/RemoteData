package DataAn.sys.dao;

import java.util.List;

import DataAn.common.dao.IBaseDao;
import DataAn.common.dao.Pager;
import DataAn.sys.domain.Role;

public interface IRoleDao extends IBaseDao<Role>{

	public void deleteByRoleIds(List<Long> roleIds);
	
	public Role selectByRoleName(String roleName);
	
	public Pager<Role> selectByPager(int pageIndex, int pageSize);
	
}
