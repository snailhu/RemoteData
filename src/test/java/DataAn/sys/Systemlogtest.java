package DataAn.sys;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import DataAn.sys.service.SystemLogService;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext-*.xml")
public class Systemlogtest {
	@Resource
	private SystemLogService systemlogService;
	
	@Test
	public void deletelogtest(){
		systemlogService.deleteSystemlogs();
	}
}
