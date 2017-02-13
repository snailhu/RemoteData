package DataAn.mongo;

import java.util.ArrayList;
import java.util.Date;
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

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;

import DataAn.common.utils.DateUtil;
import DataAn.common.utils.UUIDGeneratorUtil;
import DataAn.fileSystem.dto.CSVFileDataResultDto;
import DataAn.fileSystem.service.ICSVService;
import DataAn.galaxyManager.option.J9SeriesType;
import DataAn.galaxyManager.option.J9Series_Star_ParameterType;
import DataAn.galaxyManager.option.SeriesType;
import DataAn.mongo.service.IMongoService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext-hibernate.xml","classpath:applicationContext-cache.xml"})
public class MongoServiceTest {

	@Resource
	private IMongoService mongoService;
	
	@Resource
	private ICSVService csvService;
	private String filePath = "E:\\data\\flywheel\\2000\\01\\j9-02--2000-01-01.csv";
	
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
		long begin = System.currentTimeMillis();
		Date beginDate = DateUtil.format("2015-01-01 00:00:00");
		Date endDate = DateUtil.format("2015-01-02 00:00:00");
		MongoCursor<Document> cursor = mongoService.findByDate("j9", "05","flywheel", beginDate, endDate);
		int i=0;
		Document doc = null;
		while (cursor.hasNext()) {
			i++;
			doc = cursor.next();
//			System.out.println(i+++"..");
//			cursor.next().get("datatime");
//			long startt = System.currentTimeMillis();
			System.out.println(DateUtil.format(doc.getDate("datetime")) + " : " + doc.get("sequence_00814"));
//			long endddd = System.currentTimeMillis();
//			System.out.println("time: " + (endddd - startt));
		}
		long end = System.currentTimeMillis();
		System.out.println(i + " time: " + (end - begin));
		
	}
	
	@Test
	public void findJobNumByDeviceName(){
		String series = SeriesType.J9_SERIES.getName();
		String star = J9SeriesType.STRA5.getValue();
		String paramType = J9Series_Star_ParameterType.FLYWHEEL.getValue();
		String deviceName = "Za";
		
		Date beginDate = DateUtil.format("2016-01-01 00:00:00");
		Date endDate = DateUtil.format("2016-01-03 00:00:00");
		
		long count = mongoService.findJobNumByDeviceName(series, star, paramType, deviceName, beginDate, endDate);
		System.out.println(DateUtil.format(beginDate) + " to " + DateUtil.format(endDate) + " size: " + count);
		
		beginDate = DateUtil.format("2016-03-01 00:00:00");
		endDate = DateUtil.format("2016-03-03 00:00:00");
		
		count = mongoService.findJobNumByDeviceName(series, star, paramType, deviceName, beginDate, endDate);
		System.out.println(DateUtil.format(beginDate) + " to " + DateUtil.format(endDate) + " size: " + count);
		
	}
}
