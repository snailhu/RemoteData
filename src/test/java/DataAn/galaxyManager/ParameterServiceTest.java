package DataAn.galaxyManager;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.bson.Document;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import DataAn.common.utils.DateUtil;
import DataAn.common.utils.UUIDGeneratorUtil;
import DataAn.fileSystem.dto.CSVFileDataResultDto;
import DataAn.fileSystem.service.ICSVService;
import DataAn.galaxyManager.dto.ParameterDto;
import DataAn.galaxyManager.option.J9SeriesType;
import DataAn.galaxyManager.option.J9Series_Star_ParameterType;
import DataAn.galaxyManager.option.SeriesType;
import DataAn.galaxyManager.service.IJ9Series_Star_Service;
import DataAn.galaxyManager.service.IParameterService;
import DataAn.storm.kafka.Beginning;
import DataAn.storm.kafka.BoundProducer;
import DataAn.storm.kafka.DefaultFetchObj;
import DataAn.storm.kafka.InnerProducer;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext-hibernate.xml","classpath:applicationContext-cache.xml"})
public class ParameterServiceTest {

	@Resource
	private IJ9Series_Star_Service j9Series_Star_Service;
	@Resource
	private IParameterService parameterService;
	@Resource
	private ICSVService csvService;
	
	private String filePath = "C:\\j9-02--2016-02-01.csv";

	@Test
	public void initJ9SeriesParameterData() throws Exception {
		//初始化飞轮参数数据
		String type = J9Series_Star_ParameterType.TOP.getName();
		String paramType = J9Series_Star_ParameterType.TOP.getValue();
		//"电流","转速","温度","指令","供电状态","角动量"
		List<String> params = J9Series_Star_ParameterType.getFlywheelTypeOnDataType();
		Map<String,String> map =  j9Series_Star_Service.getAllParameterList_allZh_and_enByOption(type,null);
		Set<String> keys = map.keySet();
		String series = SeriesType.J9_SERIES.getName();
		String star = J9SeriesType.STRA2.getValue();
		for (String key : keys) {
			String param_en = parameterService.getParameter_en_by_allZh(series, star,paramType, key);
			System.out.println(param_en);
		}
	}
	
	@Test
	public void getParameterList(){
		String series = SeriesType.J9_SERIES.getName();
		String star = J9SeriesType.STRA2.getValue();
		String paramType = J9Series_Star_ParameterType.FLYWHEEL.getValue();
		List<ParameterDto> list = parameterService.getParameterList(series, star, paramType);
		for (ParameterDto parameterDto : list) {
			System.out.println(parameterDto);
		}
	}
	
	@Test
	public void test() throws Exception{
		CSVFileDataResultDto<Document> result = csvService.readCSVFileToDocAndgetTitle(filePath);
		System.out.println(result.getTitle());
		
		String title = result.getTitle();
		//CSV格式文件为逗号分隔符文件，这里根据逗号切分
		String[] array = title .split(",");
		String[] properties = new String[array.length + 1];
		properties[0] = "versions";
		for (int i = 0; i < array.length; i++) {
			//将中文字符串转换为英文
			properties[i + 1] = parameterService.getParameter_en_by_allZh("j9", "02", null, array[i]);
		}
		for (String str : properties) {
			System.out.println(str);
		}
	}
	
	@Test
	public void isExistParameter(){
		long id =  95;
		String series = "j9";
		String star = "02";
		String name = "F4W120:飞轮温度Xa(00274)";
		boolean flag = parameterService.isExistParameter(id, series, star, name);
		System.out.println("flag: " + flag);
	}
}
