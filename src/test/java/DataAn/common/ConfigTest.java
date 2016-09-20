package DataAn.common;

import java.util.Date;

import org.junit.Test;
import DataAn.common.config.CommonConfig;

public class ConfigTest {

	@Test
	public void test(){
		String str = CommonConfig.getCachePath();
		System.out.println(str);
		System.out.println(CommonConfig.getUplodCachePath());
		System.out.println(CommonConfig.getDownloadCachePath());
	}
	
	@Test
	public void test2(){
		Date tempDate = new Date();
		long time = tempDate.getTime();
		Date date = new Date(time + 500);
		int grade = (int) ((date.getTime() - tempDate.getTime()) / 1000);
		System.out.println((date.getTime() - tempDate.getTime()) / 1000);
		System.out.println(tempDate.compareTo(tempDate));
	}
	
	@Test
	public void test3(){
	
	}
	
}
