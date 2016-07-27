package DataAn.fileSystem.service;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import DataAn.Analysis.dto.ConstraintDto;
import DataAn.fileSystem.option.J9Series_Star_ParameterGroupType;
import DataAn.fileSystem.service.impl.FlyWheelServiceImpl;

public class FlyWheelServiceTest {

	private IFlyWheelService flyWheelService;
	
	@Before
	public void init(){
		flyWheelService = new FlyWheelServiceImpl();
	}
	
	@Test
	public void test(){
		String str = "采集数据25:飞轮a电机电流(16025)";
		System.out.println(str.indexOf("飞轮b"));
		List<J9Series_Star_ParameterGroupType> list = J9Series_Star_ParameterGroupType.get_FLYWHEE_Type();
		for (J9Series_Star_ParameterGroupType starDataType : list) {
			System.out.println(starDataType);
		}
		System.out.println("---");
		List<J9Series_Star_ParameterGroupType> list2 = J9Series_Star_ParameterGroupType.get_TOP_Type();
		for (J9Series_Star_ParameterGroupType starDataType : list2) {
			System.out.println(starDataType);
		}
	}
	@Test
	public void testGetFlyWheelParameterList() throws Exception{
		List<ConstraintDto> list = flyWheelService.getFlyWheelParameterList();
		for (ConstraintDto c : list) {
			System.out.println(c);
		}
	}
	
	
}
