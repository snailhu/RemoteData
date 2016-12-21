package DataAn.sys.service;

import DataAn.common.dao.Pager;
import DataAn.sys.domain.User;
import DataAn.sys.dto.ActiveUserDto;
import DataAn.sys.dto.UserDto;



public interface IUserService {

	public abstract void save(UserDto userDto);

	public abstract void saveUserRole(long userId, long roleId);

	public abstract void delete(String[] userIdArray);

	public abstract void update(UserDto user);

	public abstract UserDto getUserByUserId(long userId);

	public abstract UserDto getUserByName(String userName);
	
	public abstract ActiveUserDto getActiveUserByName(String userName);
	
	public abstract Pager<UserDto> getUserList(int pageIndex, int pageSize, String userName,
			String createdateStart, String createdateEnd,String updatedateStart, 
			String updatedateEnd, String[] deptIds,String sort, String order);

	public abstract boolean existUserName(UserDto user);

	public abstract String getUserNameByRole(String roleName);



}
