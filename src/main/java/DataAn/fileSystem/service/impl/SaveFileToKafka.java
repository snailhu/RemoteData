package DataAn.fileSystem.service.impl;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.storm.utils.Utils;
import DataAn.common.utils.DateUtil;
import DataAn.common.utils.SpringUtil;
import DataAn.common.utils.UUIDGeneratorUtil;
import DataAn.galaxyManager.service.IParameterService;
import DataAn.mongo.client.MongodbUtil;
import DataAn.mongo.init.InitMongo;
import DataAn.storm.kafka.Beginning;
import DataAn.storm.kafka.BoundProducer;
import DataAn.storm.kafka.DefaultFetchObj;
import DataAn.storm.kafka.Ending;
import DataAn.storm.kafka.InnerProducer;
import DataAn.storm.kafka.KafkaNameKeys;

public class SaveFileToKafka implements Runnable {

	private String series;
	
	private String star;
	
	private String name;
	
	private String filePath;
	
	public SaveFileToKafka(String series, String star, String paramType,
			String filePath) {
		super();
		this.series = series;
		this.star = star;
		this.name = paramType;
		this.filePath = filePath;
	}

	@Override
	public void run() {
		InputStream in = null;
		BufferedReader reader = null;
		try {
			IParameterService paramService = (IParameterService) SpringUtil.getSpringService("parameterService");
			in = new BufferedInputStream(new FileInputStream(new File(filePath)));
			reader = new BufferedReader(new InputStreamReader(in, "gb2312"));// 换成你的文件名
			String title = reader.readLine();// 第一行信息，为标题信息，不用,如果需要，注释掉
			//CSV格式文件为逗号分隔符文件，这里根据逗号切分
			String[] array = title.split(",");
			String[] properties = new String[array.length-1];
			for (int i = 1; i < array.length; i++) {
				//将中文字符串转换为英文
				properties[i-1] = paramService.getParameter_en_by_allZh(series, star, name, array[i]);
			}
			String line = null;
			String date = "";
			Date dateTime = null;
			List<DefaultFetchObj> defaultFetchObjList =new ArrayList<DefaultFetchObj>();
			DefaultFetchObj defaultFetchObj = null;
			String[] propertyVals = null;
			while ((line = reader.readLine()) != null) {
				//CSV格式文件为逗号分隔符文件，这里根据逗号切分
				String[] items = line.split(",");
				date = items[0].trim();
				dateTime = DateUtil.format(date, "yyyy年MM月dd日HH时mm分ss秒");
				propertyVals = new String[array.length-1];
				for (int i = 1; i < items.length; i++) {
					//获取值除时间外
					propertyVals[i-1] = items[i];
				}
				//
				defaultFetchObj = new DefaultFetchObj();
				defaultFetchObj.setId(UUIDGeneratorUtil.getUUID());
				defaultFetchObj.setName(this.name);
				defaultFetchObj.setSeries(this.series);
				defaultFetchObj.setStar(this.star);
				defaultFetchObj.setTime(date);
				defaultFetchObj.set_time(dateTime.getTime());
				defaultFetchObj.setProperties(properties);
				defaultFetchObj.setPropertyVals(propertyVals);
				defaultFetchObjList.add(defaultFetchObj);
			}
			System.out.println("defaultFetchObjList: " + defaultFetchObjList.size());
			for (int i = 0; i < 50; i++) {
				System.out.println(defaultFetchObjList.get(i).toString());
			}
//			MongodbUtil mg = MongodbUtil.getInstance();
//			String databaseName = InitMongo.getDataBaseNameBySeriesAndStar(series, star);
//			//集合名称： 参数名+等级
//			String collectionName = this.name;
//			//设置同一时间段的数据的状态为0
//			Date beginDate = DateUtil.format(defaultFetchObjList.get(0).getTime(), "yyyy年MM月dd日HH时mm分ss秒");;
//			Date endDate = dateTime;
//			mg.updateByDate(databaseName, collectionName, beginDate, endDate);
//			
//			Map conf=new HashMap<>();
//			KafkaNameKeys.setKafkaServer(conf, "192.168.0.97:9092");
//			InnerProducer innerProducer=new InnerProducer(conf);
//			BoundProducer boundProducer=new BoundProducer(innerProducer,"bound-replicated-1", 0);
//
//			boundProducer.send(new Beginning());
//			for (DefaultFetchObj fetchObj : defaultFetchObjList) {
//				boundProducer.send(fetchObj);
//			}
//			boundProducer.send(new Ending());
//			
//			Utils.sleep(10000000);
			
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
		
	}

}
