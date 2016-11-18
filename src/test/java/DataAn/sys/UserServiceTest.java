package DataAn.sys;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import DataAn.common.dao.Pager;
import DataAn.sys.dto.PermissionGroupDto;
import DataAn.sys.service.IPermissionService;
import DataAn.sys.service.IUserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext-hibernate.xml","classpath:applicationContext-cache.xml","classpath:applicationContext-quartz.xml"})
public class UserServiceTest {

	@Resource
	private IPermissionService permissionService;
	@Resource
	private IUserService userService;
	
	@Test
	public void getPermissionGroups(){
		Pager<PermissionGroupDto> pager = permissionService.getAllPermissionGroupList(1, 10);
		List<PermissionGroupDto> list = pager.getDatas();
		if(list != null && list.size() > 0){
			for (PermissionGroupDto permissionGroupDto : list) {
				System.out.println(permissionGroupDto);
			}
		}
	}
	
	@Test
	public void existUserName(){
	}
}
