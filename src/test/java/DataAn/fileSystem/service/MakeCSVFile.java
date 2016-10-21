package DataAn.fileSystem.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import com.csvreader.CsvWriter;

public class MakeCSVFile {

	@Test
	public void test() throws Exception{
		List<String> list = new ArrayList<String>();
		File file = new File("C:\\feb-9-05.csv");
		InputStream in = new FileInputStream(file);
		InputStreamReader inputStreamReader = new InputStreamReader(in, "gb2312");
		BufferedReader reader = new BufferedReader(inputStreamReader);// 换成你的文件名
		String line = reader.readLine();// 第一行信息，为标题信息，不用,如果需要，注释掉
		list.add(line);
		while ((line = reader.readLine()) != null) {
			list.add(line);
		}
		System.out.println("size: " + list.size());
		for (int year = 2010; year <= 2012; year++) {
			for (int month = 1; month <= 12; month++) {
				this.writeCSV(list, year, month);		
			}
		}
	}
	
	protected void writeCSV(List<String> list, int year,int month) throws Exception {
		String strMonth = "0" + month;
		//目录
		String dirPath = "E:\\data\\flywheel\\" + year;
		//文件路径
		String outputFile = dirPath +"\\" +"j9-02--" + year +"-0" + month +"-01" +".csv";
		if(month > 9){
			//dirPath = "E:\\data\\flywheel\\" + year + "\\" + month;
			outputFile = dirPath +"\\" +"j9-02--" + year +"-" + month + "-01" +".csv";
			strMonth = String.valueOf(month);
		}
		strMonth = strMonth + "月";
		String strYear = year + "年";
		File dir = new File(dirPath);
		if(!dir.exists()){
			dir.mkdirs();
		}
		CsvWriter csvOutput = new CsvWriter(outputFile, ',',Charset.forName("gb2312"));
		String line1 = list.get(0);
		String[] array1 = line1.split(",");
		for (String title : array1) {
			csvOutput.write(title);
		}
		csvOutput.endRecord();
		for (int i = 1; i < list.size(); i++) {
			String[] array = list.get(i).split(",");
			String datetime = array[0].replace("2016年", strYear).replace("02月", strMonth);
			csvOutput.write(datetime);
			for (int j = 1; j < array.length; j++) {
				csvOutput.write(array[j]);
			}
			csvOutput.endRecord();
		}
		csvOutput.close();
		System.out.println("file: " + outputFile);
	}
}
