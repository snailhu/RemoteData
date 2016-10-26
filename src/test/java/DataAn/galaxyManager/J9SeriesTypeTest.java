package DataAn.galaxyManager;

import org.junit.Test;

import DataAn.galaxyManager.domain.Star;
import DataAn.galaxyManager.option.J9SeriesType;


public class J9SeriesTypeTest {

	@Test
	public void test(){
		J9SeriesType[] types = J9SeriesType.values();
		for (J9SeriesType j9SeriesType : types) {
			Star star = new Star();
			star.setName(j9SeriesType.getValue());
			star.setCode(j9SeriesType.getValue());
			star.setDescription(j9SeriesType.getValue()+"星");
			star.setStartRunDate(j9SeriesType.getStartRunDate());
			System.out.println(star);
		}
	}
}
