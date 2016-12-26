package DataAn.reportManager;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import DataAn.reportManager.dao.IStarParamDao;
import DataAn.reportManager.domain.StarParam;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext-*.xml")
public class StarParamDaoTest {

	@Resource
	private IStarParamDao starParamDao;
	
	@Test
	public void test(){
		List<StarParam> starList = starParamDao.getStarParamByParts();
		for (StarParam starParam : starList) {
			String seriesId = starParam.getSeries();
			String starId = starParam.getStar();
			String partsType = starParam.getPartsType() ;
			System.out.println(seriesId + " " + starId + " "+ partsType + " ");
		}
	}
}
