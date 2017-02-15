package DataAn.communicate;

import java.io.IOException;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sun.corba.se.impl.presentation.rmi.IDLTypeException;

import DataAn.common.utils.HttpUtil;
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
		String entity;
		try {
			entity = HttpUtil.get("http://192.168.0.158:8080/DataRemote/Communicate/getExceptionJobConfigList?series=j8&star=01&parameterType=top");
			entity = new String(entity.getBytes("iso8859-1"),"utf-8");
			System.out.println(entity);
		} catch (IOException | IDLTypeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		
	}
}
