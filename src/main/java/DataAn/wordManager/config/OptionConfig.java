package DataAn.wordManager.config;

import java.io.File;
public class OptionConfig {
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
