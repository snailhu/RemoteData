package DataAn.sys.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import DataAn.common.dao.Pager;
import DataAn.common.utils.DateUtil;
import DataAn.common.utils.UUIDGeneratorUtil;
import DataAn.sys.dao.IDepartmentUserDao;
import DataAn.sys.dao.IRoleDao;
import DataAn.sys.dao.IUserDao;
import DataAn.sys.dao.IUserRoleDao;
import DataAn.sys.domain.DepartmentUser;
import DataAn.sys.domain.Role;
import DataAn.sys.domain.User;
import DataAn.sys.domain.UserRole;
import DataAn.sys.domain.UserRoleId;
import DataAn.sys.dto.UserDto;
import DataAn.sys.service.IUserService;


@Service
public class UserServiceimpl implements IUserService{

	@Resource
	private IUserDao userDao;
	@Resource
	private IUserRoleDao userRoleDao;
	@Resource
	private IRoleDao roleDao;
	@Resource
	private IDepartmentUserDao departmentUserDao;
	
	@Override
	@Transactional
	public void save(UserDto userDto) {
		User user = new User();
		user.setUserName(userDto.getUserName());
		user.setEmail(userDto.getEmail());
		user.setMobile(userDto.getMobile());
		user.setPassWord(userDto.getPassWord());
		user.setRealName(userDto.getRealName());
		user.setGender(userDto.getGender());
		user.setCreateUser(userDto.getCreateUser());
		user.setUpdateUser(user.getCreateUser());
		user.setCreateDate(new Date());
		user.setUpdateDate(user.getCreateDate());
		user.setVersion(1);
		userDao.add(user);
	}

	@Override
	@Transactional
	public void saveUserRole(long userId, long roleId) {
		//删除之前的用户角色
		userRoleDao.deleteByUserId(userId);
		UserRole userRole = new UserRole();
		UserRoleId id  =new UserRoleId();
		id.setRoleId(roleId);
		id.setUserId(userId);
		userRole.setId(id);
		userRole.setUserRoleId(UUIDGeneratorUtil.getUUID());
		userRole.setCreateDate(new Date());
		userRole.setUpdateDate(userRole.getCreateDate());
		userRole.setVersion(1);
		userRoleDao.add(userRole );
	}

	@Override
	@Transactional
	public void delete(String[] userIdArray) {
		for (String userId : userIdArray) {
			userDao.delete(Long.parseLong(userId));
		}
	}

	@Override
	@Transactional
	public void update(UserDto userDto) {
		User user = userDao.get(userDto.getId());
		if (StringUtils.isNotBlank(userDto.getUserName())) {
			user.setUserName(userDto.getUserName());
		}
		if (StringUtils.isNotBlank(userDto.getPassWord())) {
			user.setPassWord(userDto.getPassWord());
		}
		if (StringUtils.isNotBlank(userDto.getEmail())) {
			user.setEmail(userDto.getEmail());
		}
		if (StringUtils.isNotBlank(userDto.getMobile())) {
			user.setMobile(userDto.getMobile());
		}
		if (StringUtils.isNotBlank(userDto.getRealName())) {
			user.setRealName(userDto.getRealName());
		}
		user.setGender(userDto.getGender());
		user.setUpdateUser(userDto.getUpdateUser());
		user.setUpdateDate(new Date());
		user.setVersion(user.getVersion() + 1);
		userDao.update(user);
	}
	
	@Override
	public UserDto getUserByUserId(long userId) {
		User user = userDao.get(userId);
		if(user != null){
			return this.pojoToDto(user, false);
		}
		return null;
	}
	
	@Override
	public UserDto getUserByName(String userName) {
		User user = userDao.getUserByName(userName);
		if(user != null){
			return this.pojoToDto(user, false);
		}
		return null;
	}

	@Override
	public Pager<UserDto> getUserList(int pageIndex, int pageSize, String userName,
			String createdateStart, String createdateEnd,
			String updatedateStart, String updatedateEnd, String[] deptIds) {
		if(pageIndex == 0){
			pageIndex = 1;
		}
		Set<Long> userIdSet = new HashSet<Long>();
		if (deptIds != null && deptIds.length > 0) {
			List<Long> deptIdList = new ArrayList<Long>();
			for (String deptId : deptIds) {
				deptIdList.add(Long.parseLong(deptId));
			}
			List<DepartmentUser> list = departmentUserDao.selectByDepartmentIds(deptIdList);
			if(list != null && list.size() > 0){
				for (DepartmentUser departmentUser : list) {
					userIdSet.add(departmentUser.getId().getUserId());
				}
			}
		}
		List<UserDto> userModelList = new ArrayList<UserDto>();
		Pager<User> userPager = userDao.selectByOption(pageIndex, pageSize, userName, 
				createdateStart, createdateEnd, updatedateStart, updatedateEnd,userIdSet, null);
		List<User> userList = userPager.getDatas();
		if(userList != null && userList.size() > 0){
			for (User user : userList) {
				userModelList.add(this.pojoToDto(user,true));
			}
		}
		Pager<UserDto> pager = new Pager<UserDto>(pageIndex, pageSize, userPager.getTotalCount(), userModelList);
		return pager;			
	}
	
	@Override
	public boolean existUserName(String userName) {
		User user = userDao.getUserByName(userName);
		if(user != null){
			return true;
		}
		return false;
	}
	
	private UserDto pojoToDto(User user,boolean isGetRole) {
		UserDto userDto = new UserDto();
		userDto.setId(user.getUserId());
		userDto.setUserName(user.getUserName());
		userDto.setPassWord(user.getPassWord());
		userDto.setMobile(user.getMobile());
		userDto.setEmail(user.getEmail());
		userDto.setCreateUser(user.getCreateUser());
		userDto.setCreateDate(DateUtil.format(user.getCreateDate()));
		if(isGetRole){
			Role role = userRoleDao.selectRoleByUserId(user.getUserId());
			if(role != null){
				userDto.setUserRole(role.getRoleName());				
			}
		}
		return userDto;
	}

	@Override
	@Transactional(readOnly = true)
	public String getUserNameByRole(String roleName) {
		User user = userRoleDao.selectUserByRoleName(roleName);
		if(user != null){
			return user.getUserName();
		}
		return null;
	}


	
	

}
