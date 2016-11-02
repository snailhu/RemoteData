package DataAn.fileSystem.service.impl;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import DataAn.common.utils.DateUtil;
import DataAn.common.utils.JJSON;
import DataAn.common.utils.UUIDGeneratorUtil;
import DataAn.galaxyManager.service.IParameterService;
import DataAn.mongo.service.IMongoService;
import DataAn.storm.Communication;
import DataAn.storm.FlowUtils;
import DataAn.storm.kafka.Beginning;
import DataAn.storm.kafka.BoundProducer;
import DataAn.storm.kafka.DefaultFetchObj;
import DataAn.storm.kafka.Ending;
import DataAn.storm.kafka.InnerProducer;
import DataAn.storm.kafka.KafkaNameKeys;
import DataAn.storm.zookeeper.DisAtomicLong;
import DataAn.storm.zookeeper.NodeSelector.WorkerPathVal;
import DataAn.storm.zookeeper.NodeWorker;
import DataAn.storm.zookeeper.NodeWorkers;
import DataAn.storm.zookeeper.ZooKeeperClient;
import DataAn.storm.zookeeper.ZooKeeperClient.ZookeeperExecutor;
import DataAn.storm.zookeeper.ZooKeeperNameKeys;

public class SaveFileToKafka implements Runnable {
	
	private DisAtomicLong disAtomicLong;
	
	private NodeWorker nodeWorker;
	
	private ZookeeperExecutor executor;
	
	private Map conf=new HashMap<>();
	
	private IParameterService paramService;
	
	private IMongoService mongoService;

	public SaveFileToKafka(IParameterService paramService, IMongoService mongoService) {
		
		KafkaNameKeys.setKafkaServer(conf, "192.168.0.97:9092");
		ZooKeeperNameKeys.setZooKeeperServer(conf, "nim1.storm.com:2182,nim2.storm.com");
		executor=new ZooKeeperClient()
		.connectString(ZooKeeperNameKeys.getZooKeeperServer(conf))
		.namespace(ZooKeeperNameKeys.getNamespace(conf))
		.build();
		disAtomicLong=new DisAtomicLong(executor);
		NodeWorkers.startup(executor,conf);
		nodeWorker=NodeWorkers.get(0);
		
		this.paramService = paramService;
		this.mongoService = mongoService;
	}


	@Override
	public void run() {
		while(true){
			try{
				System.out.println(nodeWorker.getId()+ " to get lock.");
				nodeWorker.acquire();
				System.out.println(nodeWorker.getId()+ " get lock , wait some time.");
				
				final WorkerPathVal workerPathVal=
						JJSON.get().parse(new String(executor.getPath(nodeWorker.path()), Charset.forName("utf-8"))
								,WorkerPathVal.class);
				
				long sequence=1000;//workerPathVal.getSequence();
				
				Communication communication = FlowUtils.getBegin(executor,sequence);
				
				String filePath=communication.getFilePath();
				String series, star, name,versions;
				series=communication.getSeries();
				star=communication.getStar();
				name=communication.getName();
				versions=communication.getVersions();
				
				InputStream in = null;
				BufferedReader reader = null;
				try {
//					IParameterService paramService = (IParameterService) SpringUtil.getSpringService("parameterService");
					in = new BufferedInputStream(new FileInputStream(new File(filePath)));
					reader = new BufferedReader(new InputStreamReader(in, "gb2312"));// 换成你的文件名
					String title = reader.readLine();// 第一行信息，为标题信息，不用,如果需要，注释掉
					//CSV格式文件为逗号分隔符文件，这里根据逗号切分
					String[] array = title.split(",");
					String[] properties = new String[array.length + 1];
					properties[0] = "versions";
					for (int i = 0; i < array.length; i++) {
						//将中文字符串转换为英文
						properties[i + 1] = paramService.getParameter_en_by_allZh(series, star, name, array[i]);
					}
					String line = null;
					String date = "";
					Date dateTime = null;
					Date dateTime1 = null;
					int count = 1;
					DefaultFetchObj defaultFetchObj = null;
					String[] propertyVals = null;
					
					//
					InnerProducer innerProducer=new InnerProducer(conf);
					BoundProducer boundProducer=new BoundProducer(innerProducer);
					String topic="data-prototype-"+disAtomicLong.getSequence();
					boundProducer.send(new Beginning(),topic);
					
					while ((line = reader.readLine()) != null) {
						//CSV格式文件为逗号分隔符文件，这里根据逗号切分
						String[] items = line.split(",");
						date = items[0].trim();
						dateTime = DateUtil.format(date, "yyyy年MM月dd日HH时mm分ss秒");
						if(count == 1){
							dateTime1 = dateTime;
						}
						count ++;
						
						propertyVals = new String[array.length + 1];
						propertyVals[0] = versions;
						propertyVals[1] = DateUtil.format(dateTime);
						for (int i = 1; i < items.length; i++) {
							//获取值除时间外
							propertyVals[i + 1] = items[i];
						}
						//
						defaultFetchObj = new DefaultFetchObj();
						defaultFetchObj.setId(UUIDGeneratorUtil.getUUID());
						defaultFetchObj.setName(name);
						defaultFetchObj.setSeries(series);
						defaultFetchObj.setStar(star);
						defaultFetchObj.setTime(propertyVals[1]);
						defaultFetchObj.set_time(dateTime.getTime());
						defaultFetchObj.setProperties(properties);
						defaultFetchObj.setPropertyVals(propertyVals);
						
						//发送到kafka
						boundProducer.send(defaultFetchObj,topic);
					}
					boundProducer.send(new Ending(),topic);
					
					// mongo...
					mongoService.updateCSVDataByDate(series, star, name, dateTime1, dateTime);
					
					
					communication.setTopicPartition(topic+":0");
					
					FlowUtils.setDenoise(executor, communication);
					
				} catch (Exception e) {
					e.printStackTrace();
				} finally{
					if(reader != null){
						try {
							reader.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
					if(in != null){
						try {
							in.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
			}catch (Exception e) {
				e.printStackTrace();
			}finally {
				try {
					nodeWorker.release();
					System.out.println(nodeWorker.getId()+ " release lock");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

}
