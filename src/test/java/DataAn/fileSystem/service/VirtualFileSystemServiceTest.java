package DataAn.fileSystem.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import DataAn.common.pageModel.Pager;
import DataAn.fileSystem.domain.VirtualFileSystem;
import DataAn.fileSystem.dto.FileDto;
import DataAn.fileSystem.dto.MongoFSDto;
import DataAn.galaxyManager.option.J9Series_Star_ParameterType;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext-hibernate.xml","classpath:applicationContext-cache.xml"})
public class VirtualFileSystemServiceTest {

	@Resource
	private IVirtualFileSystemService fileService;
	
	@Test
	public void isExistFile() throws IOException{
		String sPath = "C:\\fakepath\\XX9(02)--20150109(公开).DAT";
//		sPath = sPath.replace("\\", "/");
//		String[] strs = sPath.split("/");
//		System.out.println(strs[strs.length-1]);
		//boolean flag = fileService.isExistFile(strs[strs.length-1]);
		//System.out.println(flag);
		System.out.println(fileService);
	}
	
	@Test
	public void saveFiles() throws Exception{
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
						this.saveFile(file.getAbsolutePath(), null);											
					}else{
						this.saveFile(null, file.getAbsolutePath());
					}
				}else{
					this.saveDirectory(file.getAbsolutePath());
				}
			}
		}
		long end = System.currentTimeMillis();
		System.out.println("time: " + (end - begin));
	}

	@Test
	public void saveFile() throws Exception{
		long begin = System.currentTimeMillis();
		String csv = "c:\\j9-02--2016-02-01.csv";
//		String dat = "C:\\XX9(02)--20150817(公开).DAT";
		this.saveFile(csv, null);
		long end = System.currentTimeMillis();
		System.out.println("保存文件： " + (end - begin) );
	}
	
	@Test
	public void deleteFile(){
		String str = "1/dir,8/file";
		fileService.deleteFile(str);
	}
	
	@Test
	public void downloadFiles() throws Exception{
		String str = "1/dir,5/dir";
		FileDto fileDto = fileService.downloadFiles(str);
		System.out.println(fileDto);
	}
	
	@Test
	public void getParentFSCatalog(){
//		List<VirtualFileSystem> list = fileService.getParentFSCatalog(5l);
//		Collections.reverse(list);
//		for (VirtualFileSystem fs : list) {
//			System.out.println(fs.getId() + "--" + fs.getFileName());
//		}
		String json = fileService.getParentFSCatalog(1l);
		System.out.println(json);
	}
	@Test
	public void getMongoFSList(){
		int pageIndex = 1;
		int pageSize = 100;
		String series = "j9";
		String star = "02";
		String strDirId = "1";
//		long dirId = 0;
		String beginTime = "";//"2009-11-17";
		String endTime = "";//"2016-11-17";
		String dataTypes = null;//"dat,csv";
		String paramType = J9Series_Star_ParameterType.FLYWHEEL.getValue();
		Pager<MongoFSDto> pager = null;//fileService.getMongoFSList(pageIndex, pageSize, series, star, paramType, null, null, null, dataTypes);
		if(StringUtils.isNotBlank(beginTime) || StringUtils.isNotBlank(endTime)){
			if (StringUtils.isNotBlank(strDirId)) {
				long dirId = Long.parseLong(strDirId);
				pager = fileService.getMongoFSList(pageIndex, pageSize, series, star, paramType, dirId, beginTime, endTime, dataTypes);								
			}else{
				pager = fileService.getMongoFSList(pageIndex, pageSize, series, star, paramType, null, beginTime, endTime, dataTypes);
			}
		}else{
			long dirId = 0;
			if (StringUtils.isNotBlank(strDirId)) {
				dirId = Long.parseLong(strDirId);
			}
			pager = fileService.getMongoFSList(pageIndex, pageSize, series, star, paramType, dirId);			
		}
		List<MongoFSDto> list =pager.getRows();
		for (MongoFSDto fs : list) {
			System.out.println(fs);
		}
	}
	
	private void saveDirectory(String sPath) throws Exception{
		if (!sPath.endsWith(File.separator)) {
			sPath = sPath + File.separator;
		}
		File dirFile = new File(sPath);
		if (dirFile.exists() && dirFile.isDirectory()) {
			File[] files = dirFile.listFiles();
			for (File file : files) {
				if(file.isFile()){
					if(file.getName().endsWith(".csv")){
						this.saveFile(file.getAbsolutePath(), null);											
					}else{
						this.saveFile(null, file.getAbsolutePath());
					}					
				}else{
					this.saveDirectory(file.getAbsolutePath());
				}
			}
		}
	}
	
	private void saveFile(String csvPath,String datPath) throws Exception{
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
