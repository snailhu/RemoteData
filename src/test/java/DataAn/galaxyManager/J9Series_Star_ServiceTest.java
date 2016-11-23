package DataAn.galaxyManager;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import DataAn.Analysis.dto.ConstraintDto;
import DataAn.Util.EhCache;
import DataAn.galaxyManager.option.J9SeriesType;
import DataAn.galaxyManager.option.J9Series_Star_ParameterType;
import DataAn.galaxyManager.option.SeriesType;
import DataAn.galaxyManager.service.IJ9Series_Star_Service;
import DataAn.galaxyManager.service.impl.J9Series_Star_ServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext-hibernate.xml","classpath:applicationContext-cache.xml"})
public class J9Series_Star_ServiceTest {

	@Resource
	private IJ9Series_Star_Service service;
//	private EhCache ehCache = null;
	
	@Before
	public void init(){
//		service = new J9Series_Star_ServiceImpl();
//		new EhCache("j9seriesConfig");
	}
	
	@Test
	public void getFlyWheelName() throws Exception{
		String param_en = "sequence_00814";
		System.out.println(service.getFlyWheelName(param_en));
		System.out.println(service.getFlyWheelParameterType(param_en));
	}
	@Test
	public void getAllParameterList() throws Exception{
		String series = SeriesType.J9_SERIES.getName();
		String star = J9SeriesType.STRA2.getValue();
		String paramType = J9Series_Star_ParameterType.FLYWHEEL.getValue();
		List<ConstraintDto> list = service.getAllParameterList(null, null, series, star, paramType);
		for (ConstraintDto constraintDto : list) {
			System.out.println(constraintDto);
		}
	}
	@Test
	public void getAllParameterListFromBeginDateToEndDate() throws Exception{
		//  "10/08/2015", "10/01/2016"
		String type = J9Series_Star_ParameterType.TOP.getValue();
		List<ConstraintDto> list = service.getAllParameterList(null,null,type);
		for (ConstraintDto constraintDto : list) {
			System.out.println(constraintDto);
		}
		System.out.println("size: " + list.size());
	}
	@SuppressWarnings("unchecked")
	@Test
	public void getFlyWheelParameterList() throws Exception{
//		service.getFlyWheelParameterList();
//		EhCache ehCache = new EhCache("j9seriesConfig");
//		List<ConstraintDto> list = (List<ConstraintDto>) ehCache.getCacheElement("flyWheelParameterList");
		List<ConstraintDto> list = service.getFlyWheelParameterList();
		for (ConstraintDto constraintDto : list) {
			System.out.println(constraintDto);
		}
		System.out.println(list.size());
	}
	
	@Test
	public void getTopParameterList(){
		List<ConstraintDto> list = service.getTopParameterList(null, null);
		for (ConstraintDto constraintDto : list) {
			System.out.println(constraintDto);
		}
		System.out.println(list.size());
	}
	@Test
	public void test(){
		String str = "采集数据26:飞轮a电源+5V(16026)";
		System.out.println(str.split(":")[1]);
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void getAllParameterList_en_and_simplyZh() throws Exception{
		service.getAllParameterList_en_and_simplyZh();
		EhCache ehCache = new EhCache("j9seriesConfig");
		Map<String,String> map =  (Map<String, String>) ehCache.getCacheElement("allParameterList_en_and_simplyZh");
		Set<String> keys = map.keySet();
		for (String key : keys) {
			System.out.println(key + " : " + map.get(key));
		}
		System.out.println("1: " + keys.size());
		service.getAllParameterList_en_and_allZh();
//		EhCache ehCache = new EhCache("j9seriesConfig");
		Map<String,String> map2 =  (Map<String, String>) ehCache.getCacheElement("allParameterList_en_and_allZh");
		Set<String> keys2 = map2.keySet();
		for (String key : keys2) {
			System.out.println(key + " : " + map2.get(key));
		}
		System.out.println("2: " + keys.size());
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void getAllParameterList_en_and_allZh() throws Exception{
		service.getAllParameterList_en_and_allZh();
		EhCache ehCache = new EhCache("j9seriesConfig");
		Map<String,String> map =  (Map<String, String>) ehCache.getCacheElement("allParameterList_en_and_allZh");
		Set<String> keys = map.keySet();
		for (String key : keys) {
			System.out.println(key + " : " + map.get(key));
		}
		System.out.println(keys.size());
	}
	
	@Test
	public void getAllParameterList_simplyZh_and_en() throws Exception{
		Map<String,String> map =  service.getAllParameterList_simplyZh_and_en();
		Set<String> keys = map.keySet();
		for (String key : keys) {
			System.out.println(key + " : " + map.get(key));
		}
		System.out.println(map.get("飞轮温度Xa(00815)"));
	}
	
	@Test
	public void getAllParameterList_allZh_and_en() throws Exception{
		Map<String,String> map =  service.getAllParameterList_allZh_and_en();
		Set<String> keys = map.keySet();
		for (String key : keys) {
			System.out.println(key + " : " + map.get(key));
		}
		System.out.println(keys.size());
		System.out.println(map.get("接收地方时"));
	}
	

	@Test
	public void getAllParameterList_allZh_and_enByOption() throws Exception{
		String type = J9Series_Star_ParameterType.FLYWHEEL.getName();
		//"电流","转速","温度","指令","供电状态","角动量"
		List<String> params = J9Series_Star_ParameterType.getFlywheelTypeOnDataType();
		Map<String,String> map =  service.getAllParameterList_allZh_and_enByOption(type,null);
		Set<String> keys = map.keySet();
		for (String key : keys) {
			System.out.println(key + " : " + map.get(key));
		}
		System.out.println(keys.size());
	}
	
}
