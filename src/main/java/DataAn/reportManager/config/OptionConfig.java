package DataAn.reportManager.config;

import java.io.File;

import DataAn.common.utils.PropertiesUtil;
import DataAn.galaxyManager.option.J9Series_Star_ParameterType;
public class OptionConfig {
	
	/**
	 * 配置文件地址
	 */
	private static final String config="reportConfig.properties";
	
	private static final String charset="utf-8";
		
	
	public static String getParamStr() {
		String param_str = "";
		String paramStr = PropertiesUtil.getProperties(config, charset).getProperty("flywheelParamStr");
		if(paramStr != null && !paramStr.equals("")){
			param_str = paramStr.trim();
		}else{
			param_str = "转速,电流";
		}
		return param_str;
	}
	
	public static String getParamStr(String deviceType){
		if(J9Series_Star_ParameterType.FLYWHEEL.getValue().equals(deviceType)) {
			return getParamStr();
		}else if(J9Series_Star_ParameterType.TOP.getValue().equals(deviceType)) {
			String param_str = "";
			String paramStr = PropertiesUtil.getProperties(config, charset).getProperty("topParamStr");
			if(paramStr != null && !paramStr.equals("")){
				param_str = paramStr.trim();
			}
			return param_str;
		}
		return null;
	}
	public static String getWebPath(){
		String classPath = OptionConfig.class.getClassLoader().getResource("").getPath();	
		int index = classPath.indexOf("WEB-INF");
		if(index > -1){
			String webPath = classPath.substring(0, index);
			webPath = webPath.replace("/", File.separator);
			if(webPath.indexOf(File.separator) == 0){
				webPath = webPath.substring(1);
			}
			return webPath;			
		}
//		System.out.println("can not get web path...");
		String temp = "C:\\Development\\Workspaces\\Git\\Repository-1\\RemoteData\\src\\main\\webapp";
		return temp;
	}	
}
