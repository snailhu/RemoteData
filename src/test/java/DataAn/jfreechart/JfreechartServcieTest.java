package DataAn.jfreechart;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import DataAn.common.utils.DateUtil;
import DataAn.galaxyManager.option.J9SeriesType;
import DataAn.galaxyManager.option.SeriesType;
import DataAn.jfreechart.chart.Serie;
import DataAn.jfreechart.dto.ConstraintDto;
import DataAn.jfreechart.dto.LineChartDto;
import DataAn.jfreechart.service.IJfreechartServcie;
import DataAn.reportManager.service.IReoportService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext-*.xml")
public class JfreechartServcieTest {

	@Resource
	private IJfreechartServcie jfreechartServcie;
	@Resource
	private IReoportService reoportService;
	
	@Test
	public void createLineChart2() throws Exception{
		String series = SeriesType.J9_SERIES.getValue();
		String star = J9SeriesType.STRA2.getValue();
		String paramType = "flywheel";
		
		Map<String, String> params = new HashMap<String,String>();
//		params.put("sequence_16025", "飞轮a电机电流(16025)");
//		params.put("sequence_16026", "飞轮a电源+5V(16026)");
//		params.put("sequence_16028", "飞轮b电源+5V(16028)");
//		params.put("sequence_16107", "飞轮A转速(16107)");
		params.put("sequence_00815", "飞轮温度Xa(00815)");
		params.put("sequence_00817", "飞轮温度Ya(00817)");
		params.put("sequence_00819", "飞轮温度Za(00819)");
		params.put("sequence_00821", "飞轮温度Xb(00821)");
		
		Map<String,List<ConstraintDto>> constraintsMap = new HashMap<String,List<ConstraintDto>>();
		List<ConstraintDto> list1 = new ArrayList<ConstraintDto>();
		ConstraintDto c11 = new ConstraintDto();
		c11.setName("飞轮温度Xa(00815)");
		c11.setValue("sequence_00815");
		list1.add(c11);
		ConstraintDto c12 = new ConstraintDto();
		c12.setName("飞轮温度Ya(00817)");
		c12.setValue("sequence_00817");
		list1.add(c12);
		constraintsMap.put("chart1", list1);
		
		List<ConstraintDto> list2 = new ArrayList<ConstraintDto>();
		ConstraintDto c21 = new ConstraintDto();
		c21.setName("飞轮温度Za(00819)");
		c21.setValue("sequence_00819");
		list2.add(c21);
		ConstraintDto c22 = new ConstraintDto();
		c22.setName("飞轮温度Xb(00821)");
		c22.setValue("sequence_00821");
		list2.add(c22);
		constraintsMap.put("chart2", list2);
		
		Date beginDate = DateUtil.format("2016-02-01 00:00:00");
		Date endDate = DateUtil.format("2016-02-07 00:00:00");
		
		LineChartDto lineChartDto = jfreechartServcie.createLineChart(series, star, paramType, beginDate, endDate, constraintsMap);
		System.out.println(lineChartDto);
	}
	
	@Test
	public void test3() throws Exception{
		String series = SeriesType.J9_SERIES.getValue();
		String star = J9SeriesType.STRA2.getValue();
		String paramType = "flywheel";
		Map<String,List<ConstraintDto>> map = reoportService.getConstraintDtoList(series,star,paramType);
		System.out.println(map);
		Date beginDate = DateUtil.format("2010-02-01 00:00:00");
		Date endDate = DateUtil.format("2010-02-07 00:00:00");
		
		LineChartDto lineChartDto = jfreechartServcie.createLineChart(series, star, paramType, beginDate, endDate, map);
		System.out.println(lineChartDto);
	}
	@Test
	public void createLineChart() throws Exception{
		Vector<String> categoriesV = new Vector<String>();
		// 标注类别
		String[] categories = { "Jan", "Feb", "Mar", "Apr", "May", "Jun",
				"Jul", "Aug", "Sep", "Oct", "Nov", "Dec" };
		for (String categorie : categories) {
			categoriesV.add(categorie);			
		}
		
		Vector<Serie> series = new Vector<Serie>();
		// 柱子名称：柱子所有的值集合
		
		Serie serieTokyo = new Serie("Tokyo", new Double[] { 49.9, 71.5, 106.4, 129.2,144.0, 
				176.0, 135.6, 148.5, 216.4, 194.1, 95.6, 54.4 });
		serieTokyo.setY2Axis(false);
		series.add(serieTokyo);
		
		Serie serieNewYork = new Serie("New York", new Double[] { 83.6, 78.8, 98.5, 93.4,
		106.0, 84.5, 105.0, 104.3, 91.2, 83.5, 106.6, 92.3 });
		serieNewYork.setY2Axis(false);
		series.add(serieNewYork);
		
		Serie serieLondon = new Serie("London", new Double[] { 48.9, 38.8, 39.3, 41.4,
		47.0, 48.3, 59.0, 59.6, 52.4, 65.2, 59.3, 51.2 });
		serieLondon.setY2Axis(true);
		series.add(serieLondon);
		
		Serie serieBerlin = new Serie("Berlin", new Double[] { 42.4, 33.2, 34.5, 39.7,
		52.6, 75.5, 57.4, 60.4, 47.6, 39.1, 46.8, 51.1 });
		serieBerlin.setY2Axis(true);
		series.add(serieBerlin);

		String title = "Monthly Average Rainfall";
		String categoryAxisLabel = "";
		String valueAxisLabel = "Rainfall (mm)";
		String chartPaht = jfreechartServcie.createLineChart(title, categoryAxisLabel, valueAxisLabel, series, categoriesV);
		System.out.println(chartPaht);
	}
	
}
