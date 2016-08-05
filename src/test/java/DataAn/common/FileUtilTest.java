package DataAn.common;

import java.io.File;

import org.junit.Test;

import DataAn.common.config.Config;
import DataAn.common.utils.FileUtil;

public class FileUtilTest {

	@Test
	public void test(){
		String sPath = Config.getCACHE_PATH();
		System.out.println(sPath);
		FileUtil.deleteDirectory(sPath,false);
	}
}
