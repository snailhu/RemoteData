package DataAn.fileSystem.service.impl;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.Resource;
import org.bson.Document;
import org.springframework.stereotype.Service;
import DataAn.common.config.Config;
import DataAn.common.utils.DateUtil;
import DataAn.fileSystem.dto.CSVFileDataResultDto;
import DataAn.fileSystem.service.ICSVService;
import DataAn.fileSystem.service.IJ9Series_Star_Service;


@Service
public class CSVServiceImpl implements ICSVService{

	@Resource
	private IJ9Series_Star_Service j9SeriesStarService;
	
	@Override
	public CSVFileDataResultDto<Document> readCSVFileToDocAndSaveCacheFile(
			String fileName, InputStream in, String versions) throws Exception {
		int totalNumber = 0;//标示全部
		int delNumber = 4;//标示删除前后4行
		//保存csv临时文件
		String csvTempFilePath = Config.getUplodCachePath() + File.separator + versions;
		File parentDir = new File(csvTempFilePath);
		if (!parentDir.exists()) {
			parentDir.mkdirs();
		}
		csvTempFilePath = csvTempFilePath + File.separator + fileName;
		BufferedWriter  writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(csvTempFilePath)), "gb2312"));
		
		//文件内容List
		List<Document> docList = new ArrayList<Document>();
		//获取j9系列参数列表
		Map<String,String> j9SeriesPatameterMap = j9SeriesStarService.getAllParameterList_allZh_and_en();
		BufferedReader reader = new BufferedReader(new InputStreamReader(in, "gb2312"));// 换成你的文件名
		String title = "";
		try {
			title = reader.readLine();// 第一行信息，为标题信息，不用,如果需要，注释掉
			//写入第一行
			writer.write(title);
			writer.newLine();
			
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
				//写入第n行
				writer.write(line);
				writer.newLine();
				
				doc = new Document();
				//CSV格式文件为逗号分隔符文件，这里根据逗号切分
				String[] items = line.split(",");
				date = items[0].trim();
				doc.append("versions", versions);
				doc.append("status", 1);
				doc.append("year", DateUtil.formatString(date, "yyyy"));
				doc.append("year_month", DateUtil.formatString(date, "yyyy-MM"));
				doc.append("year_month_day", DateUtil.formatString(date, "yyyy-MM-dd"));
//			doc.append(FlyWheelDataType.getFlyWheelDataTypeByZh(array[0]).getName(), DateUtil.formatString(date, "yyyy-MM-dd HH-mm-ss"));
				doc.append(j9SeriesPatameterMap.get(array[0]), DateUtil.formatString(date, "yyyy-MM-dd HH:mm:ss"));
				for (int i = 1; i < items.length; i++) {
					colData = items[i].trim();
					if(colData.indexOf("#") >= 0){
						flag = true;
						break;
					}else{
//					doc.append(FlyWheelDataType.getFlyWheelDataTypeByZh(array[i]).getName().toLowerCase(), colData);
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
		} finally {
			writer.flush();
			writer.close();
			reader.close();
		}
		CSVFileDataResultDto<Document> result = new CSVFileDataResultDto<Document>();
		result.setDatas(docList);
		result.setCacheFilePath(csvTempFilePath);
		result.setTitle(title);
		return result;
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

		return this.readCSVFileToDoc_delFrontAndBack_arithmetic1(in, versions, 4, 0);
		
//		return this.readCSVFileToDoc_delFrontAndBack_arithmetic2(in, versions, 4, 50);
		
		
//		return this.readCSVFileToDoc_delOneItem(in, versions, 0);
		
//		return this.readCSVFileToDoc_delOneSecondItems(in, versions,0);
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
		//格式化时间处理类
		Calendar cal = Calendar.getInstance();
		int year = 0;
		int month = 0;
		int day = 0;
		while ((line = reader.readLine()) != null) {
			if(totalNumber !=0 && count == totalNumber){
				break;
			}
			doc = new Document();
			//CSV格式文件为逗号分隔符文件，这里根据逗号切分
			String[] items = line.split(",");
			date = items[0].trim();
			Date dateTime = DateUtil.format(date, "yyyy年MM月dd日HH时mm分ss秒");
			
//			cal.setTime(dateTime);
//			year = cal.get(Calendar.YEAR);
//			month = cal.get(Calendar.MONTH) + 1;
//			day = cal.get(Calendar.DATE);
//			doc.append("versions", versions);
//			doc.append("status", 1);
//			doc.append("year", year);
//			doc.append("year_month", year + "-" + month);
//			doc.append("year_month_day", year + "-" + month + "-" + day);
			
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
		//删除前后四行
//		System.out.println("delete size: " + delDateSet.size());
		for (int i = 0; i < tempList.size(); i++) {
			if(!delDateSet.contains(i)){
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
		//格式化时间处理类
		Calendar cal = Calendar.getInstance();
		int year = 0;
		int month = 0;
		int day = 0;
		while ((line = reader.readLine()) != null) {
			if(totalNumber !=0 && count == totalNumber){
				break;
			}
			doc = new Document();
			//CSV格式文件为逗号分隔符文件，这里根据逗号切分
			String[] items = line.split(",");
			date = items[0].trim();
			
			Date dateTime = DateUtil.format(date, "yyyy年MM月dd日HH时mm分ss秒");
//			cal.setTime(dateTime);
//			year = cal.get(Calendar.YEAR);
//			month = cal.get(Calendar.MONTH) + 1;
//			day = cal.get(Calendar.DATE);
//			doc.append("versions", versions);
//			doc.append("status", 1);
//			doc.append("year", year);
//			doc.append("year_month", year + "-" + month);
//			doc.append("year_month_day", year + "-" + month + "-" + day);
			
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
		//格式化时间处理类
		Calendar cal = Calendar.getInstance();
		int year = 0;
		int month = 0;
		int day = 0;
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
			cal.setTime(dateTime);
			year = cal.get(Calendar.YEAR);
			month = cal.get(Calendar.MONTH) + 1;
			day = cal.get(Calendar.DATE);
			doc.append("versions", versions);
			doc.append("status", 1);
			doc.append("year", year);
			doc.append("year_month", year + "-" + month);
			doc.append("year_month_day", year + "-" + month + "-" + day);
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
		//格式化时间处理类
		Calendar cal = Calendar.getInstance();
		int year = 0;
		int month = 0;
		int day = 0;
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
			cal.setTime(dateTime);
			year = cal.get(Calendar.YEAR);
			month = cal.get(Calendar.MONTH) + 1;
			day = cal.get(Calendar.DATE);
			doc.append("versions", versions);
			doc.append("status", 1);
			doc.append("year", year);
			doc.append("year_month", year + "-" + month);
			doc.append("year_month_day", year + "-" + month + "-" + day);
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
