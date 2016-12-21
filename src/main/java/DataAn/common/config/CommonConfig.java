package DataAn.common.config;

import java.io.File;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import DataAn.common.utils.DateUtil;
import DataAn.common.utils.PropertiesUtil;
import DataAn.common.utils.UUIDGeneratorUtil;

public class CommonConfig {

	/**
	 * 配置文件地址
	 */
	private static final String config="config.properties";
	
	private static final String charset="utf-8";
	
	/** mongodb服务IP*/
	private static String CACHE_PATH ;

	public static String getServerConfig() {
		String serverConfig = "http://localhost:8080";
		String ip = PropertiesUtil.getProperties(config, charset).getProperty("http.server.ip");
		String port = PropertiesUtil.getProperties(config, charset).getProperty("http.server.port");
		if(StringUtils.isNotBlank(ip) && StringUtils.isNotBlank(port)){
			serverConfig = "http://"+ip+":"+port;			
		}
		return serverConfig;
		
	}
	public static String getCachePath() {
		String cachePath = PropertiesUtil.getProperties(config, charset).getProperty("path.cache");
		if(cachePath != null && !cachePath.equals("")){
			CACHE_PATH = cachePath.trim();
		}else{
			CACHE_PATH = "C:/tongyuan/works/cache";
		}
		return CACHE_PATH.replace("/", File.separator);
	}
	
	public static String getUplodCachePath() {
		String uploadCachePath = getCachePath() + File.separator + 
								"upload"+ File.separator + 
								DateUtil.format(new Date(), "yyyy-MM-dd");
		return uploadCachePath;
	}
	public static String getDownloadCachePath() {
		String uploadCachePath = getCachePath() + File.separator + 
								"download"+ File.separator + 
								DateUtil.format(new Date(), "yyyy-MM-dd")+ File.separator + 
								UUIDGeneratorUtil.getUUID();
		return uploadCachePath;
	}
	public static String getZipCachePath() {
		String uploadCachePath = getCachePath() + File.separator + 
								"zip"+ File.separator + 
								DateUtil.format(new Date(), "yyyy-MM-dd")+ File.separator + 
								UUIDGeneratorUtil.getUUID();

		return uploadCachePath;
	}
	
	public static String getChartCachePath() {
		String uploadCachePath = getCachePath() + File.separator + 
								"chart"+ File.separator + 
								DateUtil.format(new Date(), "yyyy-MM-dd")+ File.separator + 
								UUIDGeneratorUtil.getUUID();
		return uploadCachePath;
	}
}
