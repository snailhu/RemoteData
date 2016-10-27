package DataAn.common;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import DataAn.common.service.IInitDataServie;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext-hibernate.xml","classpath:applicationContext-*.xml"})
public class IInitDataServieTest {

	@Resource
	private IInitDataServie initDataServie;
	
	@Test
	public void test() throws Exception{
		initDataServie.initDataBase();
	}
}
