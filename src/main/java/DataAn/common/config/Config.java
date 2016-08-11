package DataAn.common.config;

import java.io.File;

import DataAn.common.utils.PropertiesUtil;

public class Config {

	/**
	 * 配置文件地址
	 */
	private static final String config="config.properties";
	
	private static final String charset="utf-8";
	
	/** mongodb服务IP*/
	private static String CACHE_PATH ;

	
	public static String getCACHE_PATH() {
		String cachePath = PropertiesUtil.getProperties(config, charset).getProperty("path.cache");
		if(cachePath != null && !cachePath.equals("")){
			CACHE_PATH = cachePath.trim();
		}else{
			CACHE_PATH = "C:/tongyuan/works/cache";
		}
		return CACHE_PATH.replace("/", File.separator);
	}
	
	
}
