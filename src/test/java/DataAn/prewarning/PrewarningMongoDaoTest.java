package DataAn.prewarning;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import DataAn.common.dao.Pager;
import DataAn.prewarning.dao.IWarningLogMongoDao;
import DataAn.prewarning.dto.QueryLogDTO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext-hibernate.xml",
		"classpath:applicationContext-cache.xml", "classpath:applicationContext-quartz.xml" })

public class PrewarningMongoDaoTest {

	@Resource
	private IWarningLogMongoDao warningLogMongoDao;

	@Test
	public void getPermissionGroups() {
		Pager<QueryLogDTO> pager = warningLogMongoDao.selectByOption(1, 3, "j9", "02", "flywheel", "", "", "2015-01-03 16:00:00.000", "0", "");
		for (QueryLogDTO dto : pager.getDatas()) {
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
		System.out.println(pager.getTotalCount());
	}

}
