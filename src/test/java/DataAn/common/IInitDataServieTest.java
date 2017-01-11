package DataAn.common;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import DataAn.common.config.CommonConfig;
import DataAn.common.service.IInitDataService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext-hibernate.xml","classpath:applicationContext-*.xml"})
public class IInitDataServieTest {

	@Resource
	private IInitDataService initDataServie;
	
	@Test
	public void test() throws Exception{
		//initDataServie.initDataBase();
	}
	
	@Test
	public void test2() throws Exception{
		String topDenoiseConfig=CommonConfig.getTopDenoiseConfig();
		System.out.println(topDenoiseConfig);
		String topjobConfig=CommonConfig.getTopjobConfig();
		System.out.println(topDenoiseConfig);
		
		initDataServie.initTopjobConfig();
		initDataServie.initTopDenoiseConfig();
		
	}
}
