package DataAn.prewarning;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import DataAn.common.dao.Pager;
import DataAn.prewarning.dto.QueryLogDTO;
import DataAn.prewarning.service.IPrewarningService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext-*.xml")
public class PrewarningServiceTest {

	@Resource
	private IPrewarningService prewarningService;

	@Test
	public void test() throws Exception {
		int pageIndex = 1;
		int pageSize = 50;
		String series = "1";
		String star = "5";
		String parameterType = "flywheel";
		String parameter = "Xa";
		String createdatetimeStart = "2017-02-28 00:00:00";
		String createdatetimeEnd = "2017-03-06 00:00:00";
		String warningType = "0";
		String hadRead = "1";
		String clickCount = "1";
		Pager<QueryLogDTO> pager = prewarningService.pageQueryWarningLog(pageIndex, pageSize, series, star,
				parameterType, parameter, createdatetimeStart, createdatetimeEnd, warningType, hadRead);
		if(pager != null){
			System.out.println("count: " + pager.getTotalCount());
			List<QueryLogDTO> list = pager.getDatas();
			for (QueryLogDTO queryLogDTO : list) {
				System.out.println(queryLogDTO);
			}
		}
	}

}
