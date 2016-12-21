package DataAn.fileSystem.service.impl;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.bson.Document;
import org.springframework.stereotype.Service;

import DataAn.common.utils.DateUtil;
import DataAn.fileSystem.dto.CSVFileDataResultDto;
import DataAn.fileSystem.service.ICSVService;
import DataAn.galaxyManager.service.IJ9Series_Star_Service;


@Service
public class CSVServiceImpl implements ICSVService{

	@Resource
	private IJ9Series_Star_Service j9SeriesStarService;
	
	@Override
	public CSVFileDataResultDto<Document> readCSVFileToDocAndgetTitle(String filePath) throws Exception {		
		InputStream in = null;
		BufferedReader reader = null;
		try {
			in = new BufferedInputStream(new FileInputStream(new File(filePath)));
			reader = new BufferedReader(new InputStreamReader(in, "gb2312"));// 换成你的文件名
			String title = reader.readLine();// 第一行信息，为标题信息，不用,如果需要，注释掉
			CSVFileDataResultDto<Document> result = new CSVFileDataResultDto<Document>();
			result.setTitle(title);
			return result;
		} finally{
			if(reader != null){
				reader.close();
			}
			if(in != null){
				in.close();
			}
		}
	}	
	
	@Override
	public CSVFileDataResultDto<Document> readCSVFileToDoc(String filePath, String versions) throws Exception {
		InputStream in = null;
		try {
//			in = new FileInputStream(new File(filePath));
			in = new BufferedInputStream(new FileInputStream(new File(filePath)));
			return this.readCSVFileToDoc(in,versions);
		} finally{
			if(in != null){
				in.close();
			}
		}
	}

	@Override
	public CSVFileDataResultDto<Document> readCSVFileToDoc(InputStream in, String versions) throws Exception {

//		return this.readCSVFileToDoc_delFrontAndBack_arithmetic1(in, versions, 4, 0);
		
//		return this.readCSVFileToDoc_delFrontAndBack_arithmetic1_grading(in, versions, 4, 0);
		
//		return this.readCSVFileToDoc_delFrontAndBack_arithmetic2(in, versions, 4, 50);
		
		
//		return this.readCSVFileToDoc_delOneItem(in, versions, 0);
		
		return this.readCSVFileToDoc_delOneSecondItems(in, versions,0);
	}
	
	/**
	* Description: 通过算法1 删除前后记录
	* @param in 输入流
	* @param versions 标志某一次上传的一个版本号 方便数据库事务会滚 可以是UUID
	* @param delNumber 删除前后记录数
	* @param totalNumber 获取总记录数 0为全部
	* @return
	* @throws Exception
	* @author Shenwp
	* @date 2016年7月29日
	* @version 1.0
	*/
	protected CSVFileDataResultDto<Document> readCSVFileToDoc_delFrontAndBack_arithmetic1(
			InputStream in, String versions, int delNumber, int totalNumber) throws Exception {		
		List<Document> docList = new ArrayList<Document>();
		//获取j9系列参数列表
		Map<String,String> j9SeriesPatameterMap = j9SeriesStarService.getAllParameterList_allZh_and_en();
		BufferedReader reader = new BufferedReader(new InputStreamReader(in, "gb2312"));// 换成你的文件名
		String title = reader.readLine();// 第一行信息，为标题信息，不用,如果需要，注释掉
		//CSV格式文件为逗号分隔符文件，这里根据逗号切分
		String[] array = title.split(",");
		String line = null;
		Document doc = null;
		String date = "";
		int count = 0;
		String colData = "";
		boolean flag = false; //判断是否存在 # 标示
		//临时存储集合
		List<Document> tempList = new ArrayList<Document>();
		//存储无效点 索引值
		Set<Integer> delDateSet = new HashSet<Integer>();
		//时间处理
		Calendar cal = Calendar.getInstance();
		while ((line = reader.readLine()) != null) {
			if(totalNumber !=0 && count == totalNumber){
				break;
			}
			doc = new Document();
			//CSV格式文件为逗号分隔符文件，这里根据逗号切分
			String[] items = line.split(",");
			date = items[0].trim();
			Date dateTime = DateUtil.format(date, "yyyy年MM月dd日HH时mm分ss秒");
			
			doc.append("versions", versions);
			doc.append("status", 1);
			doc.append("year", DateUtil.format(dateTime, "yyyy"));
			doc.append("year_month", DateUtil.format(dateTime, "yyyy-MM"));
			doc.append("year_month_day", DateUtil.format(dateTime, "yyyy-MM-dd"));
			cal.setTime(dateTime);
			doc.append("week_of_year", cal.get(Calendar.WEEK_OF_YEAR));
			
			//doc.append(j9SeriesPatameterMap.get(array[0]), DateUtil.formatString(date, "yyyy-MM-dd HH:mm:ss"));
			doc.append(j9SeriesPatameterMap.get(array[0]), dateTime);
			for (int i = 1; i < items.length; i++) {
				colData = items[i].trim();
				if(colData.indexOf("#") >= 0){ //TODO ?
					flag = true;
					break;
				}else{
					doc.append(j9SeriesPatameterMap.get(array[i]), colData);
				}
			}
			//删除前后4行
			if(flag){
				//如果这一行记录存在无效点 则保存这一行记录的前后四行
				for (int i = (count - delNumber); i <= (count + delNumber); i++) {
					if(i >= 0){
						delDateSet.add(i);												
					}
				}					
			}
			tempList.add(doc);
			count ++;
			flag = false;
		}
		reader.close();
		
		for (int i = 0; i < tempList.size(); i++) {
			//排除无效点保存
			if(!delDateSet.contains(i)){
				doc = tempList.get(i);
				docList.add(doc);
			}
		}
		//返回读取文件结果集
		CSVFileDataResultDto<Document> result = new CSVFileDataResultDto<Document>();
		result.setDatas(docList);
		result.setTitle(title);

		return result;
	}
	
	/**
	* Description: 通过算法1 删除前后记录-分级
	* @param in 输入流
	* @param versions 标志某一次上传的一个版本号 方便数据库事务会滚 可以是UUID
	* @param delNumber 删除前后记录数
	* @param totalNumber 获取总记录数 0为全部
	* @return
	* @throws Exception
	* @author Shenwp
	* @date 2016年7月29日
	* @version 1.0
	*/
	protected CSVFileDataResultDto<Document> readCSVFileToDoc_delFrontAndBack_arithmetic1_grading(
			InputStream in, String versions, int delNumber, int totalNumber) throws Exception {		
		List<Document> docList = new ArrayList<Document>();
		//获取j9系列参数列表
		Map<String,String> j9SeriesPatameterMap = j9SeriesStarService.getAllParameterList_allZh_and_en();
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
		//临时存储集合
		List<Document> tempList = new ArrayList<Document>();
		//存储无效点 索引值
		Set<Integer> delDateSet = new HashSet<Integer>();
		while ((line = reader.readLine()) != null) {
			if(totalNumber !=0 && count == totalNumber){
				break;
			}
			doc = new Document();
			//CSV格式文件为逗号分隔符文件，这里根据逗号切分
			String[] items = line.split(",");
			date = items[0].trim();
			Date dateTime = DateUtil.format(date, "yyyy年MM月dd日HH时mm分ss秒");
			
			doc.append("versions", versions);
			doc.append("status", 1);
			doc.append("year", DateUtil.format(dateTime, "yyyy"));
			doc.append("year_month", DateUtil.format(dateTime, "yyyy-MM"));
			doc.append("year_month_day", DateUtil.format(dateTime, "yyyy-MM-dd"));
			
			//doc.append(j9SeriesPatameterMap.get(array[0]), DateUtil.formatString(date, "yyyy-MM-dd HH:mm:ss"));
//			doc.append(j9SeriesPatameterMap.get(array[0]), dateTime);
			doc.append("datetime", dateTime);
			for (int i = 1; i < items.length; i++) {
				colData = items[i].trim();
				if(colData.indexOf("#") == 0){ //TODO ?
					flag = true;
					break;
				}else{
					String key = j9SeriesPatameterMap.get(array[i]);
					if(StringUtils.isNotBlank(key))
						doc.append(key, colData);
					else
						throw new RuntimeException(array[i] + " 找不到key..");
				}
						
			}
			//删除前后4行
			if(flag){
				//如果这一行记录存在无效点 则保存这一行记录的前后四行
				for (int i = (count - delNumber); i <= (count + delNumber); i++) {
					if(i >= 0){
						delDateSet.add(i);												
					}
				}					
			}
			tempList.add(doc);
			count ++;
			flag = false;
		}
		Date datetime_1s = tempList.get(0).getDate("datetime");
		List<Document> docList_1s = new ArrayList<Document>();
		Date datetime_5s = tempList.get(0).getDate("datetime");
		List<Document> docList_5s = new ArrayList<Document>();
		Date datetime_15s = tempList.get(0).getDate("datetime");
		List<Document> docList_15s = new ArrayList<Document>();
		Date datetime_30s = tempList.get(0).getDate("datetime");
		List<Document> docList_30s = new ArrayList<Document>();
		Date datetime_1m = tempList.get(0).getDate("datetime");
		List<Document> docList_1m = new ArrayList<Document>();
		Date datetime_5m = tempList.get(0).getDate("datetime");
		List<Document> docList_5m = new ArrayList<Document>();
		Date datetime_15m = tempList.get(0).getDate("datetime");
		List<Document> docList_15m = new ArrayList<Document>();
		Date datetime_30m = tempList.get(0).getDate("datetime");
		List<Document> docList_30m = new ArrayList<Document>();
		Date datetime_1h = tempList.get(0).getDate("datetime");
		List<Document> docList_1h = new ArrayList<Document>();
		Date datetime_6h = tempList.get(0).getDate("datetime");
		List<Document> docList_6h = new ArrayList<Document>();
		Date datetime_12h = tempList.get(0).getDate("datetime");
		List<Document> docList_12h = new ArrayList<Document>();
		Date datetime_1d = tempList.get(0).getDate("datetime");
		List<Document> docList_1d = new ArrayList<Document>();
		long time = 0;
		long time0 = tempList.get(0).getDate("datetime").getTime();
		for (int i = 0; i < tempList.size(); i++) {
			//排除无效点保存
			if(!delDateSet.contains(i)){
				doc = tempList.get(i);
				//原始数据集
				docList.add(doc);
				
				//获取时间区间
				time = (doc.getDate("datetime").getTime() - time0) / 1000;
				
				if(time % 1 == 0){ //1s使用原始数据集
					if(datetime_1s.compareTo(doc.getDate("datetime")) != 0){
						datetime_1s = doc.getDate("datetime");						
						docList_1s.add(doc);
					}
				}
				
				if(time % 5 == 0){
					if(datetime_5s.compareTo(doc.getDate("datetime")) != 0){
						datetime_5s = doc.getDate("datetime");						
						docList_5s.add(doc);
					}
				}
				if(time % 15 == 0){
					if(datetime_15s.compareTo(doc.getDate("datetime")) != 0){
						datetime_15s = doc.getDate("datetime");						
						docList_15s.add(doc);
					}
				}
				if(time % 30 == 0){
					if(datetime_30s.compareTo(doc.getDate("datetime")) != 0){
						datetime_30s = doc.getDate("datetime");						
						docList_30s.add(doc);
					}
				}
				if(time % 60 == 0){
					if(datetime_1m.compareTo(doc.getDate("datetime")) != 0){
						datetime_1m = doc.getDate("datetime");						
						docList_1m.add(doc);
					}
				}
				if(time % (5 * 60) == 0){
					if(datetime_5m.compareTo(doc.getDate("datetime")) != 0){
						datetime_5m = doc.getDate("datetime");						
						docList_5m.add(doc);
					}
				}
				if(time % (15 * 60) == 0){
					if(datetime_15m.compareTo(doc.getDate("datetime")) != 0){
						datetime_15m = doc.getDate("datetime");						
						docList_15m.add(doc);
					}
				}
				if(time % (30 * 60) == 0){
					if(datetime_30m.compareTo(doc.getDate("datetime")) != 0){
						datetime_30m = doc.getDate("datetime");						
						docList_30m.add(doc);
					}
				}
				if(time % (60 * 60) == 0){
					if(datetime_1h.compareTo(doc.getDate("datetime")) != 0){
						datetime_1h = doc.getDate("datetime");						
						docList_1h.add(doc);
					}
				}
				if(time % (6 * 60 * 60) == 0){
					if(datetime_6h.compareTo(doc.getDate("datetime")) != 0){
						datetime_6h = doc.getDate("datetime");						
						docList_6h.add(doc);
					}
				}
				if(time % (12 * 60 * 60) == 0){
					if(datetime_12h.compareTo(doc.getDate("datetime")) != 0){
						datetime_12h = doc.getDate("datetime");						
						docList_12h.add(doc);
					}
				}
				if(time % (24 * 60 * 60) == 0){
					if(datetime_1d.compareTo(doc.getDate("datetime")) != 0){
						datetime_1d = doc.getDate("datetime");						
						docList_1d.add(doc);
					}
				}
			}
		}
		
		//返回读取文件结果集
		CSVFileDataResultDto<Document> result = new CSVFileDataResultDto<Document>();
		//result.setDatas(docList);
		result.setTitle(title);
		
		Map<String,List<Document>> map = new HashMap<String,List<Document>>();
		map.put("0s", docList);
		map.put("1s", docList_1s);
		map.put("5s", docList_5s);
		map.put("15s", docList_15s);
		map.put("30s", docList_30s);
		map.put("1m", docList_1m);
		map.put("5m", docList_5m);
		map.put("15m", docList_15m);
		map.put("30m", docList_30m);
		map.put("1h", docList_1h);
		map.put("6h", docList_6h);
		map.put("12h", docList_12h);
		map.put("1d", docList_1d);
		result.setMap(map);
		return result;
	}

	protected double getMax(double data1,double data2){
		if(data1 >= data2)
			return data1;
		return data2;
	}
	protected double getMin(double data1,double data2){
		if(data1 <= data2)
			return data1;
		return data2;
	}
	/**
	* Description: 通过算法2 删除前后记录
	* @param in 输入流
	* @param versions 标志某一次上传的一个版本号 方便数据库事务会滚 可以是UUID
	* @param delNumber 删除前后记录数
	* @param totalNumber 获取总记录数 0为全部
	* @return
	* @throws Exception
	* @author Shenwp
	* @date 2016年7月29日
	* @version 1.0
	*/
	protected CSVFileDataResultDto<Document> readCSVFileToDoc_delFrontAndBack_arithmetic2(
			InputStream in, String versions,int delNumber, int totalNumber) throws Exception {
		List<Document> docList = new ArrayList<Document>();
		//获取j9系列参数列表
		Map<String,String> j9SeriesPatameterMap = j9SeriesStarService.getAllParameterList_allZh_and_en();
		InputStreamReader inputStreamReader = new InputStreamReader(in, "gb2312");
		BufferedReader reader = new BufferedReader(inputStreamReader);// 换成你的文件名
		String title = reader.readLine();// 第一行信息，为标题信息，不用,如果需要，注释掉
		//CSV格式文件为逗号分隔符文件，这里根据逗号切分
		String[] array = title.split(",");
		String line = null;
		Document doc = null;
		String date = "";
		int count = 0;
		int flagCount = 0;
		String colData = "";
		boolean flag = false; //判断是否存在 # 标示
		//临时存储集合
		List<Document> tempList = new ArrayList<Document>();
		while ((line = reader.readLine()) != null) {
			if(totalNumber !=0 && count == totalNumber){
				break;
			}
			doc = new Document();
			//CSV格式文件为逗号分隔符文件，这里根据逗号切分
			String[] items = line.split(",");
			date = items[0].trim();
			
			Date dateTime = DateUtil.format(date, "yyyy年MM月dd日HH时mm分ss秒");
			
			doc.append("versions", versions);
			doc.append("status", 1);
			doc.append("year", DateUtil.format(dateTime, "yyyy"));
			doc.append("year_month", DateUtil.format(dateTime, "yyyy-MM"));
			doc.append("year_month_day", DateUtil.format(dateTime, "yyyy-MM-dd"));
			
			//doc.append(j9SeriesPatameterMap.get(array[0]), DateUtil.formatString(date, "yyyy-MM-dd HH:mm:ss"));
			doc.append(j9SeriesPatameterMap.get(array[0]), dateTime);
			for (int i = 1; i < items.length; i++) {
				colData = items[i].trim();
				if(colData.indexOf("#") >= 0){
					flag = true;
					break;
				}else{
					doc.append(j9SeriesPatameterMap.get(array[i]), colData);
				}
			}
			//存在无效点
			if(flag){
				flagCount = count + delNumber;				
			}
			tempList.add(doc);
			int i = count - delNumber;
			if(i > flagCount){
				docList.add(tempList.get(i));
			}
			count ++;
			flag = false;
		}
		//后面还存在数据
		if(flagCount < count){
			for (int i = (count - delNumber); i < count; i++) {
				docList.add(tempList.get(i));
			}
		}
		//返回读取文件结果集
		CSVFileDataResultDto<Document> result = new CSVFileDataResultDto<Document>();
		result.setDatas(docList);
		result.setTitle(title);
		return result;
	}

	/**
	* Description: 删除某一条无效值
	* @param in 输入流
	* @param versions 标志某一次上传的一个版本号 方便数据库事务会滚 可以是UUID
	* @param totalNumber 获取总记录数 0为全部
	* @return
	* @throws Exception
	* @author Shenwp
	* @date 2016年7月29日
	* @version 1.0
	*/
	protected CSVFileDataResultDto<Document> readCSVFileToDoc_delOneItem(InputStream in,String versions, int totalNumber) throws Exception {
		List<Document> docList = new ArrayList<Document>();
		//获取j9系列参数列表
		Map<String,String> j9SeriesPatameterMap = j9SeriesStarService.getAllParameterList_allZh_and_en();
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
		while ((line = reader.readLine()) != null) {
			if(totalNumber !=0 && count == totalNumber){
				break;
			}
			count ++;
			doc = new Document();
			//CSV格式文件为逗号分隔符文件，这里根据逗号切分
			String[] items = line.split(",");
			date = items[0].trim();
			Date dateTime = DateUtil.format(date, "yyyy年MM月dd日HH时mm分ss秒");
			
			doc.append("versions", versions);
			doc.append("status", 1);
			doc.append("year", DateUtil.format(dateTime, "yyyy"));
			doc.append("year_month", DateUtil.format(dateTime, "yyyy-MM"));
			doc.append("year_month_day", DateUtil.format(dateTime, "yyyy-MM-dd"));
			
			//doc.append(j9SeriesPatameterMap.get(array[0]), DateUtil.formatString(date, "yyyy-MM-dd HH:mm:ss"));
			doc.append(j9SeriesPatameterMap.get(array[0]), dateTime);
			for (int i = 1; i < items.length; i++) {
				colData = items[i].trim();
				if(colData.indexOf("#") >= 0){
					flag = true;
					break;
				}else{
					doc.append(j9SeriesPatameterMap.get(array[i]), colData);
				}
			}
			//判断一条记录没有无效点就保存 1
			if(!flag){
				docList.add(doc);	
			}
			flag = false;
		}
		//返回读取文件结果集
		CSVFileDataResultDto<Document> result = new CSVFileDataResultDto<Document>();
		result.setDatas(docList);
		result.setTitle(title);
		return result;
	}

	/**
	* Description: 删除某一秒无效值
	* @param in 输入流
	* @param versions 标志某一次上传的一个版本号 方便数据库事务会滚 可以是UUID
	* @param totalNumber 获取总记录数 0为全部
	* @return
	* @throws Exception
	* @author Shenwp
	* @date 2016年7月29日
	* @version 1.0
	*/
	public CSVFileDataResultDto<Document> readCSVFileToDoc_delOneSecondItems(InputStream in,String versions, int totalNumber) throws Exception {
		List<Document> docList = new ArrayList<Document>();
		//获取j9系列参数列表
		Map<String,String> j9SeriesPatameterMap = j9SeriesStarService.getAllParameterList_allZh_and_en();
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
		Set<String> datetime = new HashSet<String>();
		List<Document> tempList = new ArrayList<Document>();
		while ((line = reader.readLine()) != null) {
			if(totalNumber !=0 && count == totalNumber){
				break;
			}
			count ++;
			doc = new Document();
			//CSV格式文件为逗号分隔符文件，这里根据逗号切分
			String[] items = line.split(",");
			date = items[0].trim();
			Date dateTime = DateUtil.format(date, "yyyy年MM月dd日HH时mm分ss秒");
			
			doc.append("versions", versions);
			doc.append("status", 1);
			doc.append("year", DateUtil.format(dateTime, "yyyy"));
			doc.append("year_month", DateUtil.format(dateTime, "yyyy-MM"));
			doc.append("year_month_day", DateUtil.format(dateTime, "yyyy-MM-dd"));
			
			//doc.append(j9SeriesPatameterMap.get(array[0]), DateUtil.formatString(date, "yyyy-MM-dd HH:mm:ss"));
			doc.append(j9SeriesPatameterMap.get(array[0]), dateTime);
			for (int i = 1; i < items.length; i++) {
				colData = items[i].trim();
				if(colData.indexOf("#") >= 0){
					flag = true;
					break;
				}else{
					doc.append(j9SeriesPatameterMap.get(array[i]), colData);
				}
			}
			//删除某一秒 有序
			if(flag){
				//如果这一秒存在无效点 则保存这一秒的一个标示
				datetime.add(date);
			}else{
				tempList.add(doc);
			}
			flag = false;
		}
		//删除某一秒  有序
		for (Document document : tempList) {
			if(!datetime.contains(document.get("datetime"))){
				docList.add(document);
			}
		}
		//返回读取文件结果集
		CSVFileDataResultDto<Document> result = new CSVFileDataResultDto<Document>();
		result.setDatas(docList);
		result.setTitle(title);
		return result;
	}

}
