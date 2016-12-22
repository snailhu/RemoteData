package DataAn.sys.dao;

import java.util.List;
import java.util.Set;

import DataAn.common.dao.IBaseDao;
import DataAn.common.dao.Pager;
import DataAn.sys.domain.User;


public interface IUserDao extends IBaseDao<User> {
	
	public void deleteByUserIds(List<Long> userIds);
	
	public User getUserByName(String username);
				
	public Pager<User> selectByOption(int pageIndex, int pageSize, String userName,String createdateStart, 
			String createdateEnd,String updatedateStart, String updatedateEnd, Set<Long> userIdSet, String order);

}
