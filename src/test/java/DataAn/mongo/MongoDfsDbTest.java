package DataAn.mongo;

import java.io.File;
import java.io.FileInputStream;

import org.junit.Before;
import org.junit.Test;

import DataAn.mongo.fs.IDfsDb;
import DataAn.mongo.fs.MongoDfsDb;


public class MongoDfsDbTest {

	private IDfsDb fs;
	
	@Before
	public void init(){
		fs = MongoDfsDb.getInstance();
	}
	@Test
	public void showList(){
		fs.showList();
	}
	
	@Test
	public void upload() throws Exception{
		String filePath = "D:/webservice.zip";
		File file = new File(filePath);
		FileInputStream in = new FileInputStream(file);
		fs.upload("webservice.zip","",in);
	}
	
	@Test
	public void downLoad() throws Exception{
		String id = "2ad4350b06394d618c8bb736dec2732a";
		fs.downLoad(id, "d:/temp/");
	}
}
