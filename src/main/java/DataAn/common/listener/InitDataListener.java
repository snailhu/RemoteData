package DataAn.common.listener;


import javax.annotation.Resource;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;
import DataAn.common.service.IInitDataService;
import DataAn.galaxyManager.service.IParameterService;
import DataAn.mongo.service.IMongoService;
import DataAn.status.service.IStatusTrackingService;

@Service
public class InitDataListener implements ApplicationListener<ContextRefreshedEvent>{

	@Resource
	private IParameterService paramService;
	@Resource
	private  IMongoService mongoService;
	@Resource
	private IStatusTrackingService statusTrackingService;
	@Resource
	private IInitDataService initDataService;
	private static volatile boolean flag = false;
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		System.out.println("InitDataListener..." + event);			
		if(event.getApplicationContext().getParent() == null){
			
			System.out.println("加载一次 InitDataListener..." + event);
			if(!flag){
				System.out.println("加载一次 InitDataListener... kafka、initServerConfig...");
				flag=true;
				
//				//开另外一个线程处理存入kafka的数据
//				new Thread(new SaveFileToKafka(paramService, mongoService,statusTrackingService)).start();
//				
//				//初始化数据 //TODO ?
//				new Thread(new Runnable() {
//					@Override
//					public void run() {
//						System.out.println("init j9 data...");
//						initDataService.initDataBase();
//					}
//				}).start();
//				
//				//配置服务器IP端口
//				new Thread(new Runnable() {
//					@Override
//					public void run() {
//						initDataService.initServerConfig();
//					}
//				}).start();
//				
//				//配置mongodb服务器
//				new Thread(new Runnable() {
//					@Override
//					public void run() {
//						initDataService.initMongodbConfig();
//					}
//				}).start();
			}
			
		}
	}

	

}
