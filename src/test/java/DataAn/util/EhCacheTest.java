package DataAn.util;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import DataAn.Util.EhCache;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext-hibernate.xml","classpath:applicationContext.xml"})
public class EhCacheTest {

	private EhCache ehCache = null;
	
	@Before
	public void init(){
		ehCache = new EhCache();
	}
	@Test
	public void add() throws Exception{
		ehCache.addToCache("age", 10);
	}
	
	@Test
	public void get() throws Exception{
		System.out.println(ehCache.getCacheElement("age"));
	}
}
