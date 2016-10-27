package DataAn.galaxyManager;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import DataAn.galaxyManager.dto.ParameterDto;
import DataAn.galaxyManager.option.J9SeriesType;
import DataAn.galaxyManager.option.J9Series_Star_ParameterType;
import DataAn.galaxyManager.option.SeriesType;
import DataAn.galaxyManager.service.IParameterService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext-hibernate.xml","classpath:applicationContext-cache.xml"})
public class ParameterServiceTest {

	@Resource
	private IParameterService parameterService;
	
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
}
