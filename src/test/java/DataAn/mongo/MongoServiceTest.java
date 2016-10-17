package DataAn.mongo;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.bson.Document;
import org.bson.types.ObjectId;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import DataAn.common.utils.UUIDGeneratorUtil;
import DataAn.fileSystem.dto.CSVFileDataResultDto;
import DataAn.fileSystem.option.J9SeriesType;
import DataAn.fileSystem.option.J9Series_Star_ParameterType;
import DataAn.fileSystem.option.SeriesType;
import DataAn.fileSystem.service.ICSVService;
import DataAn.mongo.service.IMongoService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext-hibernate.xml","classpath:applicationContext-cache.xml"})
public class MongoServiceTest {

	@Resource
	private IMongoService mongoService;
	
	@Resource
	private ICSVService csvService;
	private String filePath = "c:\\j9-02--2016-02-01.csv";
	
	@Test
	public void saveCSVData() throws Exception{
		long begin = System.currentTimeMillis();
		String uuId = UUIDGeneratorUtil.getUUID();
		String series = SeriesType.J9_SERIES.getName();
		String star = J9SeriesType.STRA2.getValue();
		String paramType = J9Series_Star_ParameterType.FLYWHEEL.getValue();
		String date= "2016-01-10";
		CSVFileDataResultDto<Document> result= csvService.readCSVFileToDoc(filePath,uuId);
		List<Document> list = result.getDatas();
		for (Document document : list) {
			System.out.println(document);
		}
	//	mongoService.saveCSVData(series, star, paramType, date, list, uuId);
		long end = System.currentTimeMillis();
		System.out.println("time: " + (end - begin));
	}
	
	@Test
	public void saveConfig() throws Exception{
		long begin = System.currentTimeMillis();
		List<Document> list = new ArrayList<Document>();
		Document doc = new Document();
		doc.append("ns", "config.tags");
		Document min = new Document();
		min.append("_id", new ObjectId());
		doc.append("min", min);
//		Document max = new Document();
//		min.append("_id", new ObjectId());
//		doc.append("max", max.);
		doc.append("tag", "AAA");
		list.add(doc);
		long end = System.currentTimeMillis();
		System.out.println("time: " + (end - begin));
	}
	
	@Test
	public void find(){
		mongoService.find();
	}
	
	
}
