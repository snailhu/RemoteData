package DataAn.sys.dao;

import java.util.List;

import DataAn.common.dao.IBaseDao;
import DataAn.common.dao.Pager;
import DataAn.sys.domain.Auth;

public interface IAuthDao extends IBaseDao<Auth>{

	public void deleteByAuthIds(List<Long> ids);
	
	public void deleteByParentAuthId(Long parentAuthId);
	
	public List<Auth> selectByAuthIds(List<Long> ids);
	
	public Auth selectByCode(String code);
	
	public Pager<Auth> selectByParentAuthIdIsNullByOrder(String order, int pageIndex, int pageSize);
	
	public List<Auth> selectByParentAuthIdByOrder(long parentAuthId, String order);
}
