package DataAn.fileSystem.service.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bson.Document;
import org.springframework.stereotype.Service;

import DataAn.common.utils.DateUtil;
import DataAn.fileSystem.option.FlyWheelDataType;
import DataAn.fileSystem.service.ICSVService;


@Service
public class CSVServiceImpl implements ICSVService{

	
	@Override
	public List<Document> readCSVFileToDoc(String filePath) throws Exception {
		File file = new File(filePath);
		InputStream in = new FileInputStream(file);
		return this.readCSVFileToDoc(in);
	}

	@Override
	public List<Document> readCSVFileToDoc(InputStream in) throws Exception {
		List<Document> docList = new ArrayList<Document>();
		InputStreamReader inputStreamReader = new InputStreamReader(in, "gb2312");
		BufferedReader reader = new BufferedReader(inputStreamReader);// 换成你的文件名
		String title = reader.readLine();// 第一行信息，为标题信息，不用,如果需要，注释掉
		//CSV格式文件为逗号分隔符文件，这里根据逗号切分
		String[] array = title.split(",");
		String line = null;
		Document doc = null;
		String date = "";
		int count = 0;
		String colData = "";
		boolean flag = false; //判断是否存在 # 标示
//		Map<String,List<Document>> map = new HashMap<String,List<Document>>();
		Set<String> datetime = new HashSet<String>();
//		List<Document> tempList = null;// 2
		List<Document> tempList = new ArrayList<Document>();
		Set<Integer> delDateSet = new HashSet<Integer>();
		while ((line = reader.readLine()) != null) {
//			System.out.println(line);
//			if(count  == 50){
//				break;
//			}
			doc = new Document();
			//CSV格式文件为逗号分隔符文件，这里根据逗号切分
			String[] items = line.split(",");
			date = items[0].trim();
			doc.append("year", DateUtil.formatString(date, "yyyy"));
			doc.append("year_month", DateUtil.formatString(date, "yyyy-MM"));
			doc.append("year_month_day", DateUtil.formatString(date, "yyyy-MM-dd"));
//			doc.append(FlyWheelDataType.getFlyWheelDataTypeByZh(array[0]).getName(), DateUtil.formatString(date, "yyyy-MM-dd HH-mm-ss"));
			//items.length;
			for (int i = 0; i < items.length; i++) {
				colData = items[i].trim();
//				System.out.println(colData);
				if(colData.indexOf("#") >= 0){
					flag = true;
					break;
				}else{
					doc.append(FlyWheelDataType.getFlyWheelDataTypeByZh(array[i]).getName(), colData);					
				}
			}
			//判断一条记录没有无效点就保存 1
//			if(!flag){
//				docList.add(doc);	
//			}
//			flag = false;
			
			//判断一条记录没有无效点就保存 就不保存这一秒 使用map这个是无序 2
//			if(flag){
//				//如果这一秒存在无效点 则保存这一秒的一个标示
//				datetime.add(date);					
//			}
//			tempList = map.get(date);
//			if(tempList == null){
//				tempList = new ArrayList<Document>();
//			}
//			tempList.add(doc);
//			map.put(date, tempList);	
//			flag = false;
			
			//删除某一秒 有序
//			if(flag){
//				//如果这一秒存在无效点 则保存这一秒的一个标示
//				datetime.add(date);
//			}else{
//				tempList.add(doc);
//			}
//			flag = false;
			
			//删除前后4行
			if(flag){
				//如果这一行记录存在无效点 则保存这一行记录的前后四行
				for (int i = (count - 4); i <= (count + 4); i++) {
					if(i >= 0){
						delDateSet.add(i);												
					}
				}					
			}
			tempList.add(doc);
			count ++;
			flag = false;
		}
		//删除某一秒无序
//		Iterator<String> it = map.keySet().iterator();
//		while (it.hasNext()) {
//			String d = it.next();
//			if(!datetime.contains(d)){
//				docList.addAll(map.get(d));
//			}
//			
//		}
		//删除某一秒  有序
//		for (Document document : tempList) {
//			if(!datetime.contains(document.get("datetime"))){
//				docList.add(document);
//			}
//		}
		
//		System.out.println("delSet size: " + delDateSet.size());
		//删除前后四行
		for (int i = 0; i < tempList.size(); i++) {
			if(!delDateSet.contains(i)){
				docList.add(tempList.get(i));
			}
		}
		return docList;
	}

	@Override
	public List<Document> readCSVFileOfJsonToDoc(String filePath) throws Exception {
		File file = new File(filePath);
		InputStream in = new FileInputStream(file);
		return this.readCSVFileOfJsonToDoc(in);
	}

	@Override
	public List<Document> readCSVFileOfJsonToDoc(InputStream in)throws Exception {
		List<Document> docList = new ArrayList<Document>();
		InputStreamReader inputStreamReader = new InputStreamReader(in, "gb2312");
		BufferedReader reader = new BufferedReader(inputStreamReader);// 换成你的文件名
		String title = reader.readLine();// 第一行信息，为标题信息，不用,如果需要，注释掉
		//CSV格式文件为逗号分隔符文件，这里根据逗号切分
		String[] array = title.split(",");
		String line = null;
		String date = "";
		String resultJson = "";
		int count = 0;
		while ((line = reader.readLine()) != null) {
			if(count  == 3){
				break;
			}
			count ++;
			//CSV格式文件为逗号分隔符文件，这里根据逗号切分
			String[] items = line.split(",");
			date = items[0];
			resultJson = resultJson + "{";
			resultJson =  resultJson + "\"year\":" + "\"" + DateUtil.formatString(date, "yyyy") + "\","; 
			resultJson =  resultJson + "\"year_month\":" + "\"" + DateUtil.formatString(date, "yyyy-MM") + "\","; 
			resultJson =  resultJson + "\"year_month_day\":" + "\"" + DateUtil.formatString(date, "yyyy-MM-dd") + "\","; 
			//items.length
			int length = 3;
			for (int i = 0; i < length; i++) {
				if((i + 1) == length){
					resultJson =  resultJson + "\""+ FlyWheelDataType.getFlyWheelDataTypeByZh(array[i]).getName() +"\":" + "\"" +items[i].trim() + "\""; 							
				}else{
					resultJson =  resultJson + "\""+ FlyWheelDataType.getFlyWheelDataTypeByZh(array[i]).getName() +"\":" + "\"" +items[i].trim() + "\","; 							
				}
			}
			resultJson =  resultJson + "},";
			docList.add(Document.parse(resultJson));
			resultJson = "";
		}
		return docList;
	}
	
	@Override
	public List<Map<String, String>> readCSVFile(String filePath) throws Exception {
		File file = new File(filePath);
		InputStream in = new FileInputStream(file);
		return this.readCSVFile(in);
	}

	@Override
	public List<Map<String, String>> readCSVFile(InputStream in) throws Exception {
		List<Map<String,String>> list = new ArrayList<Map<String,String>>();
		InputStreamReader inputStreamReader = new InputStreamReader(in, "gb2312");
		BufferedReader reader = new BufferedReader(inputStreamReader);// 换成你的文件名
		String title = reader.readLine();// 第一行信息，为标题信息，不用,如果需要，注释掉
		//CSV格式文件为逗号分隔符文件，这里根据逗号切分
		String[] array = title.split(",");
		String line = null;
		Map<String,String> map = null;
		String date = "";
		int count = 0;
		while ((line = reader.readLine()) != null) {
			if(count  == 3){
				break;
			}
			count ++;
			map = new HashMap<String,String>();
			//CSV格式文件为逗号分隔符文件，这里根据逗号切分
			String[] items = line.split(",");
			date = items[0];
//			map.put("year", DateUtil.formatString(date, "yyyy"));
//			map.put("year_month", DateUtil.formatString(date, "yyyy-MM"));
			map.put("yyyy-MM-dd-HH-mm-ss", DateUtil.formatString(date, "yyyy-MM-dd-HH-mm-ss"));
			//items.length;
			for (int i = 0; i < 3; i++) {
				map.put(FlyWheelDataType.getFlyWheelDataTypeByZh(array[i]).getName(), items[i].trim());
			}
			list.add(map);
		}
		return list;
	}

	@Override
	public String readCSVFileOfJson(String filePath) throws Exception {
		File file = new File(filePath);
		InputStream in = new FileInputStream(file);
		return this.readCSVFileOfJson(in);
	}
	
	@Override
	public String readCSVFileOfJson(InputStream in)  throws Exception {
		InputStreamReader inputStreamReader = new InputStreamReader(in, "gb2312");
		BufferedReader reader = new BufferedReader(inputStreamReader);// 换成你的文件名
		String title = reader.readLine();// 第一行信息，为标题信息，不用,如果需要，注释掉
		//CSV格式文件为逗号分隔符文件，这里根据逗号切分
		String[] array = title.split(",");
		String line = null;
		String date = "";
		String resultJson = "";
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		int count = 0;
		while ((line = reader.readLine()) != null) {
			if(count  == 3){
				break;
			}
			count ++;
			//CSV格式文件为逗号分隔符文件，这里根据逗号切分
			String[] items = line.split(",");
			date = items[0];
			resultJson = resultJson + "{";
			resultJson =  resultJson + "\"year\":" + "\"" + DateUtil.formatString(date, "yyyy") + "\","; 
			resultJson =  resultJson + "\"year_month\":" + "\"" + DateUtil.formatString(date, "yyyy-MM") + "\","; 
			resultJson =  resultJson + "\"year_month_day\":" + "\"" + DateUtil.formatString(date, "yyyy-MM-dd") + "\","; 
			//items.length
			int length = 3;
			for (int i = 0; i < length; i++) {
				if((i + 1) == length){
					resultJson =  resultJson + "\""+ FlyWheelDataType.getFlyWheelDataTypeByZh(array[i]).getName() +"\":" + "\"" +items[i].trim() + "\""; 							
				}else{
					resultJson =  resultJson + "\""+ FlyWheelDataType.getFlyWheelDataTypeByZh(array[i]).getName() +"\":" + "\"" +items[i].trim() + "\","; 							
				}
			}
			resultJson =  resultJson + "},";
			sb.append(resultJson);
			resultJson = "";
		}
		sb.deleteCharAt(sb.lastIndexOf(","));
		sb.append("]");
		return sb.toString();
	}

	

}
