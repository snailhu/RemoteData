package DataAn.common.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import DataAn.common.service.IInitDataService;
import DataAn.common.utils.SpringUtil;


public class InitDataListener implements ServletContextListener{

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("web 启动");
//		IInitDataServie initDataServie = (IInitDataServie) SpringUtil.getSpringService("initDataSerice");
//		try {
//			initDataServie.initDataBase();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		System.out.println("web 销毁");
		
	}

}
