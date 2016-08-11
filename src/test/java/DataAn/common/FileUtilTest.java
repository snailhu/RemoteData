package DataAn.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import org.junit.Test;

import DataAn.common.config.Config;
import DataAn.common.utils.FileUtil;

public class FileUtilTest {

	@Test
	public void test(){
		String sPath = Config.getCachePath();
		System.out.println(sPath);
		FileUtil.deleteDirectory(sPath,false);
	}
	
	@Test
	public void saveFile() throws Exception{
		File file = new File("C:\\excel_result\\数管分系统.csv");
		InputStream in = new FileInputStream(file);
		FileUtil.saveFile(Config.getUplodCachePath(), "数管分系统.csv", in);
	}
}
