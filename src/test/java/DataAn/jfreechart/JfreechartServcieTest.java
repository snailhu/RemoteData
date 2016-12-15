package DataAn.jfreechart;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import DataAn.common.utils.DateUtil;
import DataAn.galaxyManager.option.J9SeriesType;
import DataAn.galaxyManager.option.SeriesType;
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
		c11.setParamName("飞轮转速Xa(00423)");
		c11.setParamCode("sequence_00423");
		list1.add(c11);
		ConstraintDto c12 = new ConstraintDto();
		c12.setParamName("飞轮转速Ya(00424)");
		c12.setParamCode("sequence_00424");
		list1.add(c12);
		ConstraintDto c13 = new ConstraintDto();
		c13.setParamName("飞轮转速Za(00425)");
		c13.setParamCode("sequence_00425");
		list1.add(c13);
		ConstraintDto c14 = new ConstraintDto();
		c14.setParamName("飞轮转速Xb(00426)");
		c14.setParamCode("sequence_00426");
		list1.add(c14);
		ConstraintDto c15 =new ConstraintDto();
		c15.setParamName("飞轮转速Yb(00427)");
		c15.setParamCode("sequence_00427");
		list1.add(c15);
		ConstraintDto c16 = new ConstraintDto();
		c16.setParamName("飞轮转速Zb(00428)");
		c16.setParamCode("sequence_00428");
		list1.add(c16);
		constraintsMap.put("chart1", list1);
		
		List<ConstraintDto> list2 = new ArrayList<ConstraintDto>();
		ConstraintDto c21 = new ConstraintDto();
		c21.setParamName("飞轮电流Xa(00814)");
		c21.setParamCode("sequence_00814");
		list2.add(c21);
		ConstraintDto c22 = new ConstraintDto();
		c22.setParamName("飞轮转速Xa(00423)");
		c22.setParamCode("sequence_00423");
		list2.add(c22);
		constraintsMap.put("chart2", list2);
		
		List<ConstraintDto> list3 = new ArrayList<ConstraintDto>();
		ConstraintDto c31 = new ConstraintDto();
		c31.setParamName("飞轮温度Xa(00815)");
		c31.setParamCode("sequence_00815");
		list3.add(c31);
		ConstraintDto c32 = new ConstraintDto();
		c32.setParamName("飞轮温度Ya(00817)");
		c32.setParamCode("sequence_00817");
		c32.setMax(80);
		c32.setMin(30);
		list3.add(c32);
		constraintsMap.put("chart3", list3);
		
		Date beginDate = DateUtil.format("2016-12-01 00:00:00");
		Date endDate = DateUtil.format("2016-12-07 00:00:00");
		
//		Date beginDate = DateUtil.format("2000-01-01 00:00:00");
//		Date endDate = DateUtil.format("2000-01-02 00:00:00");
		
		LineChartDto lineChartDto = jfreechartServcie.createLineChart(series, star, paramType, beginDate, endDate, constraintsMap);
		System.out.println(lineChartDto);
	}
	
	@Test
	public void test3() throws Exception{
		long begin = System.currentTimeMillis();
		String series = SeriesType.J9_SERIES.getValue();
		String star = J9SeriesType.STRA2.getValue();
		String paramType = "flywheel";
		Map<String,List<ConstraintDto>> map = reoportService.getConstraintDtoList(series,star,paramType);
		System.out.println(map);
		
		Map<String,List<ConstraintDto>> map2 = new HashMap<String,List<ConstraintDto>>();
		Set<String> keys = map.keySet();
		int count=0;
		for (String key : keys) {
			if(count==1)
				//break;
			count++;
			map2.put(key, map.get(key));			
		}
		System.out.println(map2);
		Date beginDate = DateUtil.format("2016-12-01 00:00:00");
		Date endDate = DateUtil.format("2016-12-07 00:00:00");
		
//		Date beginDate = DateUtil.format("2016-12-08 00:00:00");
//		Date endDate = DateUtil.format("2016-12-09 00:00:00");
		
		LineChartDto lineChartDto = jfreechartServcie.createLineChart(series, star, paramType, beginDate, endDate, map2);
		System.out.println(lineChartDto);
		long end = System.currentTimeMillis();
		System.out.println("画图总时间： " + (end-begin));
	}
	
	
}
