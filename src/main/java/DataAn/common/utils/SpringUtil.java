package DataAn.common.utils;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringUtil {

	public static Object getSpringService(String serviceName){
		ApplicationContext ac  = new ClassPathXmlApplicationContext("classpath:applicationContext-*.xml");
		return ac.getBean(serviceName);
	}
}
