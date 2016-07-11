package DataAn.mongo.init;

import DataAn.common.utils.PropertiesUtil;


public class InitMongo {
	
	/**
	 * 配置文件地址
	 */
	private static final String config="mongoDB.properties";
	
	private static final String charset="utf-8";
	/**
	 * 获取配置文件参数
	 * */
	/** mongodb服务IP*/
	public static final String SERVER_HOST = PropertiesUtil.getProperties(config, charset).getProperty("mongodb.ip");

	/** mongodb服务端口*/
	public static final int SERVER_PORT = Integer.parseInt(PropertiesUtil.getProperties(config, charset).getProperty("mongodb.port"));
	
	/** 测试数据库 database_test*/
	public static final String DATABASE_TEST = PropertiesUtil.getProperties(config, charset).getProperty("database.test");
	
	/** mongodb服务IP 集群*/
	public static final String SERVER_HOSTS = PropertiesUtil.getProperties(config, charset).getProperty("mongodb.ips");
	
	/** 飞轮数据库 database_flywheel*/
	public static final String DATABASE_FLYWHEEL = PropertiesUtil.getProperties(config, charset).getProperty("database.flyWheel");
	
	
}
