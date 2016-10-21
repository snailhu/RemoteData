package DataAn.prewarning;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import DataAn.prewarning.dao.IWarningLogMongoDao;
import DataAn.prewarning.dto.QueryLogDTO;
import DataAn.status.option.StatusTrackingType;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext-hibernate.xml",
		"classpath:applicationContext-cache.xml", "classpath:applicationContext-quartz.xml" })

public class PrewarningMongoDaoTest {

	@Resource
	private IWarningLogMongoDao warningLogMongoDao;

	@Test
	public void getPermissionGroups() {
		List<QueryLogDTO> pager = warningLogMongoDao.getQueryLogDTOs();
		for (QueryLogDTO dto : pager) {
			System.out.println("----------------------" + dto.getLogId());
			System.out.println("----------------------" + dto.getParameter());
			System.out.println("----------------------" + dto.getTimeValue());
			System.out.println("----------------------" + dto.getParameterType());
			System.out.println("----------------------" + dto.getSeries());
			System.out.println("----------------------" + dto.getStar());
			System.out.println("----------------------" + dto.getWarningType());
			System.out.println("");
			System.out.println("");
		}
	}

}
