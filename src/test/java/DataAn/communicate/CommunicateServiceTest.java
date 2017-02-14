package DataAn.communicate;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import DataAn.communicate.service.ICommunicateService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext-*.xml")
public class CommunicateServiceTest {

	@Resource
	private ICommunicateService communicateService;
	
	@Test
	public void getExceptionJobConfigList(){
		String json = communicateService.getExceptionJobConfigList("j8", "01", "top");
		System.out.println(json);
	}
}
