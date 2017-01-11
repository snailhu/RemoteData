package DataAn.common.config;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import DataAn.common.service.impl.InitDataServiceImpl;
import DataAn.common.utils.DateUtil;
import DataAn.common.utils.Log4jUtil;
import DataAn.common.utils.PropertiesUtil;
import DataAn.common.utils.UUIDGeneratorUtil;
import DataAn.storm.exceptioncheck.ExceptionUtils;

public class CommonConfig {

	/**
	 * 配置文件地址
	 */
	private static final String config="config.properties";
	
	private static final String charset="utf-8";
	
	/** mongodb服务IP*/
	private static String CACHE_PATH ;
	
	public static String getTopjobConfig(){
		String topjobConfig="";
		try {
			topjobConfig=new String(getBytes(
					CommonConfig.class.getResourceAsStream("topjidongcount.json")),"utf-8");
		} catch (UnsupportedEncodingException e) {
			Log4jUtil.getInstance().getLogger(CommonConfig.class).error("从JSON文件读取陀螺的机动规则失败");
			e.printStackTrace();
		}
		return topjobConfig;
	}
	
	public static String getTopDenoiseConfig(){
		String topDenoiseConfig="";
		try {
			topDenoiseConfig=new String(getBytes(
						CommonConfig.class.getResourceAsStream("topdenoiseparam.json")),"utf-8");
		} catch (UnsupportedEncodingException e) {
			Log4jUtil.getInstance().getLogger(CommonConfig.class).error("从JSON文件读取陀螺的去噪规则失败");
			e.printStackTrace();
		}
		return topDenoiseConfig;
	}

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
	
	public static String getDocCachePath() {
		String docCachePath = getCachePath() + File.separator + 
								"doc"+ File.separator + 
								DateUtil.format(new Date(), "yyyy-MM-dd")+ File.separator + 
								UUIDGeneratorUtil.getUUID();
		return docCachePath;
	}
	
	private static byte[] getBytes(InputStream input) {
	    ByteArrayOutputStream output = new ByteArrayOutputStream();
	    byte[] buffer = new byte[4096];
	    int n = 0;
	    try {
			while (-1 != (n = input.read(buffer))) {
			    output.write(buffer, 0, n);
			}
			output.flush();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	    return output.toByteArray();
	}
}
