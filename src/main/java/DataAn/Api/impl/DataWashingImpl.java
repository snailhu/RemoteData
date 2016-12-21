//package DataAn.Api.impl;
//
//import java.io.BufferedReader;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Map;
//import java.util.Set;
//
//import javax.annotation.Resource;
//
//import org.bson.Document;
//
//import DataAn.Api.DataWashing;
//import DataAn.common.utils.DateUtil;
//import DataAn.fileSystem.dto.CSVFileDataResultDto;
//import DataAn.fileSystem.service.IJ9Series_Star_Service;
//
//public class DataWashingImpl implements DataWashing {
//	
//	@Resource
//	private IJ9Series_Star_Service j9SeriesStarService;
//	
//
//	@Override
//	public void CSVFileDataWash(InputStream in) throws Exception {
//		List<Document> docList = new ArrayList<Document>();
//		//获取j9系列参数列表
//		Map<String,String> j9SeriesPatameterMap = j9SeriesStarService.getAllParameterList_allZh_and_en();
//		InputStreamReader inputStreamReader = new InputStreamReader(in, "gb2312");
//		BufferedReader reader = new BufferedReader(inputStreamReader);// 换成你的文件名
//		String title = reader.readLine();// 第一行信息，为标题信息，不用,如果需要，注释掉
//		//CSV格式文件为逗号分隔符文件，这里根据逗号切分
//		String[] array = title.split(",");
//		String line = null;
//		String date = "";
//		int count = 0;
//		String colData = "";
//		boolean flag = false; //判断是否存在 # 标示
//		//临时存储集合
//		List<Document> tempList = new ArrayList<Document>();
//		//存储无效点 索引值
//		Set<Integer> delDateSet = new HashSet<Integer>();
//		while ((line = reader.readLine()) != null) {
////			if(totalNumber !=0 && count == totalNumber){
////				break;
////			}
//		
//			//CSV格式文件为逗号分隔符文件，这里根据逗号切分
//			String[] items = line.split(",");
//
//			for (int i = 1; i < items.length; i++) {
//				colData = items[i].trim();
//				if(colData.indexOf("#") >= 0){
//					line.
//					flag = true;
//					break;
//				}else{
//					doc.append(j9SeriesPatameterMap.get(array[i]), colData);
//				}
//			}
//			//删除前后4行
//			if(flag){
//				//如果这一行记录存在无效点 则保存这一行记录的前后四行
//				for (int i = (count - delNumber); i <= (count + delNumber); i++) {
//					if(i >= 0){
//						delDateSet.add(i);												
//					}
//				}					
//			}
//			tempList.add(doc);
//			count ++;
//			flag = false;
//		}
//		count = 1; //初始化计数器
//		long time = 0;
//		Date datetime_1s = tempList.get(0).getDate("datetime");
//		List<Document> docList_1s = new ArrayList<Document>();
//		Date datetime_5s = tempList.get(0).getDate("datetime");
//		List<Document> docList_5s = new ArrayList<Document>();
//		Date datetime_15s = tempList.get(0).getDate("datetime");
//		List<Document> docList_15s = new ArrayList<Document>();
//		Date datetime_30s = tempList.get(0).getDate("datetime");
//		List<Document> docList_30s = new ArrayList<Document>();
//		Date datetime_1m = tempList.get(0).getDate("datetime");
//		List<Document> docList_1m = new ArrayList<Document>();
//		Date datetime_5m = tempList.get(0).getDate("datetime");
//		List<Document> docList_5m = new ArrayList<Document>();
//		Date datetime_15m = tempList.get(0).getDate("datetime");
//		List<Document> docList_15m = new ArrayList<Document>();
//		Date datetime_30m = tempList.get(0).getDate("datetime");
//		List<Document> docList_30m = new ArrayList<Document>();
//		Date datetime_1h = tempList.get(0).getDate("datetime");
//		List<Document> docList_1h = new ArrayList<Document>();
//		Date datetime_6h = tempList.get(0).getDate("datetime");
//		List<Document> docList_6h = new ArrayList<Document>();
//		Date datetime_12h = tempList.get(0).getDate("datetime");
//		List<Document> docList_12h = new ArrayList<Document>();
//		List<Document> docList_1d = new ArrayList<Document>();
//		//排除无效点保存
//		long time0 = tempList.get(0).getDate("datetime").getTime();
//		for (int i = 0; i < tempList.size(); i++) {
//			if(!delDateSet.contains(i)){
//				doc = tempList.get(i);
//				docList.add(doc);
//				//获取时间区间
//				time = (doc.getDate("datetime").getTime() - time0) / 1000;
//				if(time % 1 == 0){
//					if(datetime_1s.compareTo(doc.getDate("datetime")) != 0){
//						datetime_1s = doc.getDate("datetime");						
//						docList_1s.add(doc);
//					}
//				}
//				if(time % 5 == 0){
//					if(datetime_5s.compareTo(doc.getDate("datetime")) != 0){
//						datetime_5s = doc.getDate("datetime");						
//						docList_5s.add(doc);
//					}
//				}
//				if(time % 15 == 0){
//					if(datetime_15s.compareTo(doc.getDate("datetime")) != 0){
//						datetime_15s = doc.getDate("datetime");						
//						docList_15s.add(doc);
//					}
//				}
//				if(time % 30 == 0){
//					if(datetime_30s.compareTo(doc.getDate("datetime")) != 0){
//						datetime_30s = doc.getDate("datetime");						
//						docList_30s.add(doc);
//					}
//				}
//				if(time % 60 == 0){
//					if(datetime_1m.compareTo(doc.getDate("datetime")) != 0){
//						datetime_1m = doc.getDate("datetime");						
//						docList_1m.add(doc);
//					}
//				}
//				if(time % (5 * 60) == 0){
//					if(datetime_5m.compareTo(doc.getDate("datetime")) != 0){
//						datetime_5m = doc.getDate("datetime");						
//						docList_5m.add(doc);
//					}
//				}
//				if(time % (15 * 60) == 0){
//					if(datetime_15m.compareTo(doc.getDate("datetime")) != 0){
//						datetime_15m = doc.getDate("datetime");						
//						docList_15m.add(doc);
//					}
//				}
//				if(time % (30 * 60) == 0){
//					if(datetime_30m.compareTo(doc.getDate("datetime")) != 0){
//						datetime_30m = doc.getDate("datetime");						
//						docList_30m.add(doc);
//					}
//				}
//				if(time % (60 * 60) == 0){
//					if(datetime_1h.compareTo(doc.getDate("datetime")) != 0){
//						datetime_1h = doc.getDate("datetime");						
//						docList_1h.add(doc);
//					}
//				}
//				if(time % (6 * 60 * 60) == 0){
//					if(datetime_6h.compareTo(doc.getDate("datetime")) != 0){
//						datetime_6h = doc.getDate("datetime");						
//						docList_6h.add(doc);
//					}
//				}
//				if(time % (12 * 60 * 60) == 0){
//					if(datetime_12h.compareTo(doc.getDate("datetime")) != 0){
//						datetime_12h = doc.getDate("datetime");						
//						docList_12h.add(doc);
//					}
//				}
//			}
//		}
//		docList_1d.add(tempList.get(0));
//		
//		//返回读取文件结果集
//		CSVFileDataResultDto<Document> result = new CSVFileDataResultDto<Document>();
//		result.setDatas(docList);
//		result.setTitle(title);
//		
//		Map<String,List<Document>> map = new HashMap<String,List<Document>>();
//		map.put("1s", docList);
//		map.put("5s", docList_5s);
//		map.put("15s", docList_15s);
//		map.put("30s", docList_30s);
//		map.put("1m", docList_1m);
//		map.put("5m", docList_5m);
//		map.put("15m", docList_15m);
//		map.put("30m", docList_30m);
//		map.put("1h", docList_1h);
//		map.put("6h", docList_6h);
//		map.put("12h", docList_12h);
//		map.put("1d", docList_1d);
//		result.setMap(map);
//		return result;
//
//	}
//
//}
