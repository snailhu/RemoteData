package DataAn.common;

import org.junit.Test;
import DataAn.common.config.Config;

public class ConfigTest {

	@Test
	public void test(){
		String str = Config.getCachePath();
		System.out.println(str);
		System.out.println(Config.getUplodCachePath());
		System.out.println(Config.getDownloadCachePath());
	}
}
