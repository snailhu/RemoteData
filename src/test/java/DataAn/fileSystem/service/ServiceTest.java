package DataAn.fileSystem.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

import DataAn.common.utils.SpringUtil;
import DataAn.fileSystem.dto.FileDto;
import DataAn.fileSystem.option.J9Series_Star_ParameterType;

public class ServiceTest {

	private static IVirtualFileSystemService fileService = (IVirtualFileSystemService) SpringUtil.getSpringService("virtualFileSystemServiceImpl");

	public static void main(String[] args) throws Exception {
		
		saveFile();	
		
	}
	
	public static void saveFile() throws Exception{
		long begin = System.currentTimeMillis();
		String csv = "D:\\temp\\data\\2015\\1\\j9-02--2015-01-02.csv";
//		String dat = "C:\\XX9(02)--20150817(公开).DAT";
		saveFile(csv, null);
		long end = System.currentTimeMillis();
		System.out.println("保存文件： " + (end - begin) );
	}
	
	public static void saveFiles() throws Exception{
		long begin = System.currentTimeMillis();
		String sPath = "E:\\data\\top\\2012";
		if (!sPath.endsWith(File.separator)) {
			sPath = sPath + File.separator;
		}
		File dirFile = new File(sPath);
		if (dirFile.exists() && dirFile.isDirectory()) {
			File[] files = dirFile.listFiles();
			for (File file : files) {
				if(file.isFile()){
					if(file.getName().endsWith(".csv")){
						saveFile(file.getAbsolutePath(), null);											
					}else{
						saveFile(null, file.getAbsolutePath());
					}
				}else{
					saveDirectory(file.getAbsolutePath());
				}
			}
		}
		long end = System.currentTimeMillis();
		System.out.println("time: " + (end - begin));
	}
	
	public static void saveDirectory(String sPath) throws Exception{
		if (!sPath.endsWith(File.separator)) {
			sPath = sPath + File.separator;
		}
		File dirFile = new File(sPath);
		if (dirFile.exists() && dirFile.isDirectory()) {
			File[] files = dirFile.listFiles();
			for (File file : files) {
				if(file.isFile()){
					if(file.getName().endsWith(".csv")){
						saveFile(file.getAbsolutePath(), null);											
					}else{
						saveFile(null, file.getAbsolutePath());
					}					
				}else{
					saveDirectory(file.getAbsolutePath());
				}
			}
		}
	}
	
	public static void saveFile(String csvPath,String datPath) throws Exception{
		Map<String, FileDto> map = new HashMap<String,FileDto>();
		DecimalFormat df = new DecimalFormat("#.00");
		String parameterType = J9Series_Star_ParameterType.FLYWHEEL.getValue();
		if(csvPath != null && !csvPath.equals("")){
			System.out.println("csvPath: " + csvPath);
			FileDto csvFileDto = new FileDto();
			File csvFile = new File(csvPath);
			InputStream csvInput = new FileInputStream(csvFile);
			csvFileDto.setFileName(csvFile.getName());
			double size = csvFile.length() / 1024 /1024;
			String strSize = df.format(size);
			csvFileDto.setFileSize(Float.parseFloat(strSize));
			csvFileDto.setIn(csvInput);
			csvFileDto.setParameterType(parameterType);
			map.put("csv", csvFileDto);			
		}
		if(datPath != null && !datPath.equals("")){
			System.out.println("datPath: " + datPath);
			FileDto datFileDto = new FileDto();
			File datFile = new File(datPath);
			InputStream datInput = new FileInputStream(datFile);
			datFileDto.setFileName(datFile.getName());
			double size = datFile.length() / 1024 /1024;
			String strSize = df.format(size);
			datFileDto.setFileSize(Float.parseFloat(strSize));
			datFileDto.setIn(datInput);
			datFileDto.setParameterType(parameterType);
			map.put("dat", datFileDto);			
		}
		fileService.saveFile(map);
	}
	
}
