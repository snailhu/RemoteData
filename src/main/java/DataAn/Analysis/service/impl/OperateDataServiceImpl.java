package DataAn.Analysis.service.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.stereotype.Service;

import DataAn.Analysis.service.OperateDataService;
import DataAn.common.utils.DateUtil;
import DataAn.fileSystem.option.FlyWheelDataType;

@Service
public class OperateDataServiceImpl implements OperateDataService {

	@Override
	public void uploadExcelSdoFile(InputStream iso) throws IOException {
		// TODO Auto-generated method stub
		String  str[ ] = new String[]{}; 	
		InputStream is = new FileInputStream("pldrxkxxmb.xls");
		
	}

	@Override
	public List<Map<String, String>> readCSVFile(InputStream in)
			throws Exception {
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
			map.put("yyyy-MM-dd-HH-mm-ss", DateUtil.formatString(date, "yyyy-MM-dd-HH-mm-ss"));
			//items.length;
			for (int i = 0; i < 3; i++) {
				map.put(FlyWheelDataType.getFlyWheelDataType(array[i]).getName(), items[i].trim());
			}
			list.add(map);
		}
		return list;
	}

	@Override
	public String[] getDateArray() throws Exception{
		String [] dateArray= new String[]{};
		File file = new File("D:/QQCustom/2625279655/FileRecv/j9-02--2015-08-10.csv");
		InputStream in = new FileInputStream(file);
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
			dateArray[count]=(DateUtil.formatString(date, "yyyy-MM-dd-HH-mm-ss"));		
	
		}
		return dateArray;

	}

	@Override
	public List<Map<String, String>> readCSVFile(String filePath)
			throws Exception {
		File file = new File("D:/QQCustom/2625279655/FileRecv/j9-02--2015-08-10.csv");
		InputStream in = new FileInputStream(file);
		return this.readCSVFile(in);
	}

}
