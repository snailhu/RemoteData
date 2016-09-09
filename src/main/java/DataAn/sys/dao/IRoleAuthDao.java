package DataAn.sys.dao;

import java.util.List;

import DataAn.common.dao.IBaseDao;
import DataAn.sys.domain.RoleAuth;

public interface IRoleAuthDao extends IBaseDao<RoleAuth>{

	public void deleteByAuthId(long authId);
	
	public void deleteByAuthIds(List<Long> authIds);

	public void deleteByRoleId(long roleId);
	
	public void deleteByRoleIds(List<Long> roleIds);

	public List<RoleAuth> selectByAuthId(long authId);
	
	public List<RoleAuth> selectByRoleId(long roleId);
	
	public List<String> selectAuthCodeByRoleId(long roleId);

}
