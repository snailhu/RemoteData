package DataAn.prewarning;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import DataAn.galaxy.option.SeriesType;
import DataAn.prewarning.dto.SelectOptionDTO;
import DataAn.prewarning.service.IPrewarningService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext-*.xml")
public class PrewarningServiceTest {

	@Resource
	private IPrewarningService prewarningService;
	
	@Test
	public void test() throws Exception{
//		String series = SeriesType.J9_SERIES.getName();
//		String parameterType = "flywheel";
//		SelectOptionDTO selectOptionDTO = prewarningService.getSelectOption(series, parameterType);
//		System.out.println(selectOptionDTO);
	}
	
}
