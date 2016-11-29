package DataAn.mongo.init;

import java.util.ArrayList;
import java.util.List;

import DataAn.common.utils.PropertiesUtil;
import DataAn.galaxyManager.option.J9SeriesType;
import DataAn.galaxyManager.option.J9Series_Star_ParameterType;
import DataAn.galaxyManager.option.SeriesType;
import DataAn.storm.hierarchy.HierarchyModel;
import DataAn.storm.hierarchy.HieraychyUtils;


public class InitMongo {
	
	/**
	 * 配置文件地址
	 */
	private static final String config="mongoDB.properties";
	
	private static final String charset="utf-8";
	
	/**
	 * 获取配置文件参数
	 * */
	/** mongodb单机测试服务IP*/
	public static final String TEST_SERVER_HOST = PropertiesUtil.getProperties(config, charset).getProperty("test.mongodb.ip").trim();
	/** mongodb单机测试服务端口*/
	public static final int TEST_SERVER_PORT = Integer.parseInt(PropertiesUtil.getProperties(config, charset).getProperty("test.mongodb.port").trim());
	
	/** mongodb数据服务IP*/
	public static final String DB_SERVER_HOST = PropertiesUtil.getProperties(config, charset).getProperty("db.mongodb.ip").trim();
	/** mongodb数据服务端口*/
	public static final int DB_SERVER_PORT = Integer.parseInt(PropertiesUtil.getProperties(config, charset).getProperty("db.mongodb.port").trim());
	
	/** mongodb文件服务IP*/
	public static final String FS_SERVER_HOST = PropertiesUtil.getProperties(config, charset).getProperty("fs.mongodb.ip").trim();
	/** mongodb文件服务端口*/
	public static final int FS_SERVER_PORT = Integer.parseInt(PropertiesUtil.getProperties(config, charset).getProperty("fs.mongodb.port").trim());
	
	/** j9系列 01星数据库*/
	public static final String DB_J9STAR1 = getDataBaseNameBySeriesAndStar(SeriesType.J9_SERIES.getName(), J9SeriesType.STRA1.getValue());
	/** j9系列 01星文件库*/
	public static final String FS_J9STAR1 = getFSBDNameBySeriesAndStar(SeriesType.J9_SERIES.getName(), J9SeriesType.STRA1.getValue());
	
	/** j9系列 02星数据库*/
	public static final String DB_J9STAR2 = getDataBaseNameBySeriesAndStar(SeriesType.J9_SERIES.getName(), J9SeriesType.STRA2.getValue());
	/** j9系列 02星文件库*/
	public static final String FS_J9STAR2 = getFSBDNameBySeriesAndStar(SeriesType.J9_SERIES.getName(), J9SeriesType.STRA2.getValue());

	/** j9系列 03星数据库*/
	public static final String DB_J9STAR3 = getDataBaseNameBySeriesAndStar(SeriesType.J9_SERIES.getName(), J9SeriesType.STRA3.getValue());
	/** j9系列 03星文件库*/
	public static final String FS_J9STAR3 = getFSBDNameBySeriesAndStar(SeriesType.J9_SERIES.getName(), J9SeriesType.STRA3.getValue());

	/** j9系列 04星数据库*/
	public static final String DB_J9STAR4 = getDataBaseNameBySeriesAndStar(SeriesType.J9_SERIES.getName(), J9SeriesType.STRA4.getValue());
	/** j9系列 04星文件库*/
	public static final String FS_J9STAR4 = getFSBDNameBySeriesAndStar(SeriesType.J9_SERIES.getName(), J9SeriesType.STRA4.getValue());

	/** j9系列 05星数据库*/
	public static final String DB_J9STAR5 = getDataBaseNameBySeriesAndStar(SeriesType.J9_SERIES.getName(), J9SeriesType.STRA5.getValue());
	/** j9系列 05星文件库*/
	public static final String FS_J9STAR5 = getFSBDNameBySeriesAndStar(SeriesType.J9_SERIES.getName(), J9SeriesType.STRA5.getValue());
	
	/** mongodb服务IP 集群*/
	public static final String SERVER_HOSTS = PropertiesUtil.getProperties(config, charset).getProperty("mongodb.ips");
	
	/** 测试数据库 database_test*/
	public static final String DATABASE_TEST = PropertiesUtil.getProperties(config, charset).getProperty("database.test");
	
	/**
	* Description: 通过系列和星名称获取当前数据库名称
	* @param series SeriesType.J9_SERIES.getValue() .. j9
	* @param star J9SeriesType.STRA1.getValue() .. 01、02
	* @return
	* @author Shenwp
	* @date 2016年8月23日
	* @version 1.0
	*/
	public static final String getDataBaseNameBySeriesAndStar(String series,String star){
//		String seriesName = SeriesType.getSeriesType(series).getName();
//		String starName = J9SeriesType.getJ9SeriesType(star).getName();
		String dbName = "db_" + series + "_" + star;
		return dbName;
	}
	
	/**
	* Description: 通过系列和星名称获取当前数据库名称
	* @param series J9SeriesType.SERIES.getName()
	* @param star J9SeriesType.STRA1.getName()
	* @return
	* @author Shenwp
	* @date 2016年8月23日
	* @version 1.0
	*/
	public static final String getFSBDNameBySeriesAndStar(String series,String star){
//		String seriesName = SeriesType.getSeriesType(series).getName();
//		String starName = J9SeriesType.getJ9SeriesType(star).getName();
		String dbName =  "fs_" + series + "_" + star;
		return dbName;
	}
	
	//集合名称： 参数名+等级
	public static List<String> getGradingCollectionNames(String paramType){
		List<HierarchyModel> hierarchyModelList = null;
		try {
			paramType = J9Series_Star_ParameterType.getJ9SeriesStarParameterType(paramType).getValue();
			hierarchyModelList = HieraychyUtils.getHierarchyModels();
			List<String> list = new ArrayList<String>();
			for (HierarchyModel hierarchyModel : hierarchyModelList) {
				list.add(paramType + hierarchyModel.getName());
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
