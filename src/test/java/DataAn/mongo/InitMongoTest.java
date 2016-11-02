package DataAn.mongo;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

import DataAn.common.config.CommonConfig;
import DataAn.fileSystem.option.J9Series_Star_ParameterType;
import DataAn.mongo.init.InitMongo;
import DataAn.storm.hierarchy.HierarchyModel;
import DataAn.storm.hierarchy.HieraychyUtils;

public class InitMongoTest {

	@Test
	public void test() throws Exception{
//		System.out.println(InitMongo.SERVER_HOST);
//		System.out.println(InitMongo.SERVER_PORT);
//		System.out.println(InitMongo.DATABASE_TEST);
		List<HierarchyModel> list = HieraychyUtils.getHierarchyModels();
		for (HierarchyModel hierarchyModel : list) {
			System.out.println(hierarchyModel.getName()+" : " + hierarchyModel.getInterval());
		}
		
	}
	
	@Test
	public void getGradingCollectionName(){
		List<String> list = InitMongo.getGradingCollectionNames(J9Series_Star_ParameterType.FLYWHEEL.getValue());
		
		Set<String> isexistCols = new HashSet<String>();
		isexistCols.add("flywheel1s");
		for (String string : list) {
			System.out.println(isexistCols.contains(string));
		}
	}
}
