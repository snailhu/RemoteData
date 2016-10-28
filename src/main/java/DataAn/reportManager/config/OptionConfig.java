package DataAn.reportManager.config;

import java.io.File;

import DataAn.common.utils.PropertiesUtil;
public class OptionConfig {
	
	/**
	 * 配置文件地址
	 */
	private static final String config="reportConfig.properties";
	
	private static final String charset="utf-8";
	
	
	
	private static String PARAM_STR ;
	
	public static String getParamStr() {
		String paramStr = PropertiesUtil.getProperties(config, charset).getProperty("paramStr");
		if(paramStr != null && !paramStr.equals("")){
			PARAM_STR = paramStr.trim();
		}else{
			PARAM_STR =   "转速,电流";
		}
		return PARAM_STR;
	}
	
	public static String getWebPath(){
		String classPath = OptionConfig.class.getClassLoader().getResource("").getPath();		
		String webPath = classPath.substring(0, classPath.indexOf("WEB-INF"));
		webPath = webPath.replace("/", File.separator);
		if(webPath.indexOf(File.separator) == 0){
			webPath = webPath.substring(1);
		}
		return webPath;
	}	
}
