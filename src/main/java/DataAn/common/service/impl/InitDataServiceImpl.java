package DataAn.common.service.impl;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import DataAn.common.config.CommonConfig;
import DataAn.common.service.IInitDataService;
import DataAn.common.utils.JJSON;
import DataAn.galaxyManager.service.IJ9Series_Star_Service;
import DataAn.galaxyManager.service.ISeriesService;
import DataAn.mongo.init.InitMongo;
import DataAn.storm.BaseConfig;
import DataAn.storm.StormUtils;
import DataAn.storm.exceptioncheck.ExceptionUtils;
import DataAn.storm.zookeeper.ZooKeeperClient;
import DataAn.storm.zookeeper.ZooKeeperNameKeys;
import DataAn.storm.zookeeper.ZooKeeperClient.ZookeeperExecutor;
import DataAn.sys.service.IPermissionService;

@Service
public class InitDataServiceImpl implements IInitDataService{

	@Resource
	private ISeriesService seriesService;
	@Resource
	private IPermissionService permissionService;
	@Resource
	private IJ9Series_Star_Service j9Series_Star_Service;
	
	@Override
	public void initDataBase(){
		System.out.println("come in initDataBase...");
		//初始化j9星系库数据
		seriesService.initJ9Series();
		//初始化j9系列参数
		j9Series_Star_Service.initJ9SeriesParameterData();
		//初始化权限库数据
		permissionService.initPermission();
	}

	@Override
	public void initServerConfig() {
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
			String serverConfig = CommonConfig.getServerConfig();
			String path = "/cfg/serverConfig";
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

	@Override
	public void initMongodbConfig() {
		try {
			System.out.println("init mongodbConfig...");
			Map conf=new HashMap<>();
			BaseConfig baseConfig=null;
			baseConfig= StormUtils.getBaseConfig(BaseConfig.class);
			ZooKeeperNameKeys.setZooKeeperServer(conf, baseConfig.getZooKeeper());
			ZooKeeperNameKeys.setNamespace(conf, baseConfig.getNamespace());
			ZookeeperExecutor executor=new ZooKeeperClient()
					.connectString(ZooKeeperNameKeys.getZooKeeperServer(conf))
					.namespace(ZooKeeperNameKeys.getNamespace(conf))
					.build();
			Map<String,String> mongodbConfig = new HashMap<String,String>();
			mongodbConfig.put("db.mongodb.ip", InitMongo.DB_SERVER_HOST);
			mongodbConfig.put("db.mongodb.port", String.valueOf(InitMongo.DB_SERVER_PORT));
			mongodbConfig.put("fs.mongodb.ip", InitMongo.FS_SERVER_HOST);
			mongodbConfig.put("fs.mongodb.port", String.valueOf(InitMongo.FS_SERVER_PORT));
			String path = "/cfg/monodbConfig";
			boolean flag = executor.exists(path);
			if(flag){
//				byte[] bytes = executor.getPath(path);
//				Map<String,String> config = JJSON.get().parse(new String(bytes, Charset.forName("utf-8")), Map.class);
				
				executor.setPath(path, JJSON.get().formatObject(mongodbConfig));
			}else{
				executor.createPath(path,JJSON.get().formatObject(mongodbConfig).getBytes(Charset.forName("utf-8")));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override 
	public void initTopjobConfig(){
		try{
			System.out.println("int TopjobrulesConfig...");
			Map conf=new HashMap<>();
			BaseConfig baseConfig=null;
			baseConfig=StormUtils.getBaseConfig(BaseConfig.class);
			ZooKeeperNameKeys.setZooKeeperServer(conf, baseConfig.getZooKeeper());
			ZooKeeperNameKeys.setNamespace(conf, baseConfig.getNamespace());
			ZookeeperExecutor executor=new ZooKeeperClient()
			.connectString(ZooKeeperNameKeys.getZooKeeperServer(conf))
			.namespace(ZooKeeperNameKeys.getNamespace(conf))
			.build();
			String topjobConfig=CommonConfig.getTopjobConfig();
			String path = "/cfg/topjobConfig";
			boolean flag=executor.exists(path);
			if(flag){
				byte[] bytes=executor.getPath(path);
				String config = new String(bytes,Charset.forName("utf-8"));
				if(!topjobConfig.equals(config))
					executor.setPath(path, topjobConfig);
			}else{
				executor.createPath(path,topjobConfig.getBytes(Charset.forName("utf-8")));
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
		
		@Override 
		public void initTopDenoiseConfig(){
			try{
				System.out.println("int TopDenoiseConfig...");
				Map conf=new HashMap<>();
				BaseConfig baseConfig=null;
				baseConfig=StormUtils.getBaseConfig(BaseConfig.class);
				ZooKeeperNameKeys.setZooKeeperServer(conf, baseConfig.getZooKeeper());
				ZooKeeperNameKeys.setNamespace(conf, baseConfig.getNamespace());
				ZookeeperExecutor executor=new ZooKeeperClient()
				.connectString(ZooKeeperNameKeys.getZooKeeperServer(conf))
				.namespace(ZooKeeperNameKeys.getNamespace(conf))
				.build();
				String topDenoiseConfig=CommonConfig.getTopDenoiseConfig();
				String path = "/cfg/topDenioseConfig";
				boolean flag=executor.exists(path);
				if(flag){
					byte[] bytes=executor.getPath(path);
					String config = new String(bytes,Charset.forName("utf-8"));
					if(!topDenoiseConfig.equals(config))
						executor.setPath(path, topDenoiseConfig);
				}else{
					executor.createPath(path,topDenoiseConfig.getBytes(Charset.forName("utf-8")));
				}
			}catch(Exception e){
				e.printStackTrace();
			}
	}
	
	

}
