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
		String id = "b36d97d617c241878abedb812ad1697e";
		fs.downLoadToLocal(id, "d:/temp/");
	}
}
