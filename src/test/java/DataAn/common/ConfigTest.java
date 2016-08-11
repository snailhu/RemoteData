package DataAn.common;

import java.io.File;

import org.junit.Test;

import DataAn.common.config.Config;

public class ConfigTest {

	@Test
	public void test(){
		String str = Config.getCACHE_PATH();
		System.out.println(str);
	}
}
