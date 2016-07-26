package DataAn.common.config;

import DataAn.common.utils.PropertiesUtil;

public class Config {

	/**
	 * 配置文件地址
	 */
	private static final String config="config.properties";
	
	private static final String charset="utf-8";
	
	/** mongodb服务IP*/
	public static final String CACHE_PATH = PropertiesUtil.getProperties(config, charset).getProperty("path.cache");
}
