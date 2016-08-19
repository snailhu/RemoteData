package DataAn.mongo;

import java.util.List;

import javax.annotation.Resource;

import org.bson.Document;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import DataAn.common.utils.UUIDGeneratorUtil;
import DataAn.fileSystem.service.ICSVService;
import DataAn.mongo.service.IMongoService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext-hibernate.xml","classpath:applicationContext.xml"})
public class MongoServiceTest {

	@Resource
	private IMongoService mongoService;
	@Resource
	private ICSVService csvService;
	private String filePath = "D:\\temp\\data\\2016\\1-6\\j9-02--2016-01-10.csv";
	
	@Test
	public void saveCSVData() throws Exception{
		long begin = System.currentTimeMillis();
		String uuId = UUIDGeneratorUtil.getUUID();
		List<Document> list = csvService.readCSVFileToDoc(filePath,uuId);
		System.out.println("size: " + list.size());
		for (Document document : list) {
			System.out.println(document);
		}
		//mongoService.saveCSVData(null);
		long end = System.currentTimeMillis();
		System.out.println("time: " + (end - begin));
	}
}
