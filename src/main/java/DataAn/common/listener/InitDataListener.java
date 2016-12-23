package DataAn.common.listener;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;
import DataAn.common.config.CommonConfig;
import DataAn.common.service.IInitDataService;
import DataAn.fileSystem.service.impl.SaveFileToKafka;
import DataAn.galaxyManager.service.IParameterService;
import DataAn.mongo.service.IMongoService;
import DataAn.status.service.IStatusTrackingService;
import DataAn.storm.BaseConfig;
import DataAn.storm.StormUtils;
import DataAn.storm.zookeeper.ZooKeeperClient;
import DataAn.storm.zookeeper.ZooKeeperNameKeys;
import DataAn.storm.zookeeper.ZooKeeperClient.ZookeeperExecutor;

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
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		System.out.println("InitDataListener..." + event);			
		if(event.getApplicationContext().getParent() == null){
			System.out.println("加载一次 InitDataListener..." + event);
			//开另外一个线程处理存入kafka的数据
			new Thread(new SaveFileToKafka(paramService, mongoService,statusTrackingService)).start();
			//初始化数据 //TODO ?
//			new Thread(new Runnable() {
//				
//				@Override
//				public void run() {
//					initDataService.initDataBase();
//					
//				}
//			}).start();
			//配置服务器
//			new Thread(new Runnable() {
//				
//				@Override
//				public void run() {
//					
//				}
//			}).start();;
			
			try {
				System.out.println("init serverConfig...");
				Map conf=new HashMap<>();
				BaseConfig baseConfig=null;
				baseConfig= StormUtils.getBaseConfig(BaseConfig.class);
				ZooKeeperNameKeys.setZooKeeperServer(conf, baseConfig.getZooKeeper());
				ZooKeeperNameKeys.setNamespace(conf, baseConfig.getNamespace());
				ZookeeperExecutor executor=new ZooKeeperClient()
						.connectString(ZooKeeperNameKeys.getZooKeeperServer(conf))
						.namespace(ZooKeeperNameKeys.getNamespace(conf))
						.build();
				String path = "/cfg/serverConfig";
				String serverConfig = CommonConfig.getServerConfig();
				boolean flag = executor.exists(path);
				if(flag){
					byte[] bytes = executor.getPath(path);
					String config = new String(bytes, Charset.forName("utf-8"));
					if(!serverConfig.equals(config))
						executor.setPath(path, serverConfig);
				}else{
					executor.createPath(path,serverConfig.getBytes(Charset.forName("utf-8")));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	

}
