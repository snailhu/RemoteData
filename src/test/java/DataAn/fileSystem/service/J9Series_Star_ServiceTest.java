package DataAn.fileSystem.service;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import DataAn.Analysis.dto.ConstraintDto;
import DataAn.fileSystem.service.impl.J9Series_Star_ServiceImpl;

public class J9Series_Star_ServiceTest {

	private IJ9Series_Star_Service service;
	
	@Before
	public void init(){
		service = new J9Series_Star_ServiceImpl();
	}
	
	@Test
	public void test(){
		String str = "采集数据26:飞轮a电源+5V(16026)";
		System.out.println(str.split(":")[1]);
	}
	@Test
	public void testGetFlyWheelParameterList() throws Exception{
		List<ConstraintDto> list = service.getFlyWheelParameterList();
		for (ConstraintDto constraintDto : list) {
			System.out.println(constraintDto);
		}
	}
	
}
