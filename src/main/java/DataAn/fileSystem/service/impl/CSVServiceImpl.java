package DataAn.fileSystem.service.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
		while ((line = reader.readLine()) != null) {
			if(count  == 10){
				break;
			}
			count ++;
			doc = new Document();
			//CSV格式文件为逗号分隔符文件，这里根据逗号切分
			String[] items = line.split(",");
			date = items[0];
			doc.append("year", DateUtil.formatString(date, "yyyy"));
			doc.append("year_month", DateUtil.formatString(date, "yyyy-MM"));
			doc.append("year_month_day", DateUtil.formatString(date, "yyyy-MM-dd"));
			//items.length;
			for (int i = 0; i < 3; i++) {
				doc.append(FlyWheelDataType.getFlyWheelDataType(array[i]).getName(), items[i].trim());
			}
			docList.add(doc);
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
					resultJson =  resultJson + "\""+ FlyWheelDataType.getFlyWheelDataType(array[i]).getName() +"\":" + "\"" +items[i].trim() + "\""; 							
				}else{
					resultJson =  resultJson + "\""+ FlyWheelDataType.getFlyWheelDataType(array[i]).getName() +"\":" + "\"" +items[i].trim() + "\","; 							
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
			map.put("year", DateUtil.formatString(date, "yyyy"));
			map.put("year_month", DateUtil.formatString(date, "yyyy-MM"));
			map.put("year_month_day", DateUtil.formatString(date, "yyyy-MM-dd"));
			//items.length;
			for (int i = 0; i < 3; i++) {
				map.put(FlyWheelDataType.getFlyWheelDataType(array[i]).getName(), items[i].trim());
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
					resultJson =  resultJson + "\""+ FlyWheelDataType.getFlyWheelDataType(array[i]).getName() +"\":" + "\"" +items[i].trim() + "\""; 							
				}else{
					resultJson =  resultJson + "\""+ FlyWheelDataType.getFlyWheelDataType(array[i]).getName() +"\":" + "\"" +items[i].trim() + "\","; 							
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
