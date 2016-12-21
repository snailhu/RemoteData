package DataAn.common.utils;

import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;

public class Log4jUtil {
	
	private static ConcurrentHashMap<String, Logger> logs = new ConcurrentHashMap<String, Logger>();
	private volatile static Log4jUtil obj = null;

	public static Log4jUtil getInstance() {
		if (obj == null) {
			synchronized (Log4jUtil.class) {
				if (obj == null) {
					obj = new Log4jUtil();
				}
			}
			obj = new Log4jUtil();
		}
		return obj;
	}

	private Log4jUtil() {
		
	}
    /**
     * getLogger:(根据Class类型获取其对应的logger). 
     */
	public Logger getLogger(Class<?> cls) {
    	String key = cls.getName();
    	Logger logger = logs.get(key);
        if (logger == null) {
        	logger =Logger.getLogger(key);
        	logs.put(key, logger);
        }
        return logger;
    }

}
