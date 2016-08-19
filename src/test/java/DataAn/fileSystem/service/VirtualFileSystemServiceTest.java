package DataAn.fileSystem.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import DataAn.common.pageModel.Pager;
import DataAn.fileSystem.domain.VirtualFileSystem;
import DataAn.fileSystem.dto.FileDto;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext-hibernate.xml","classpath:applicationContext.xml"})
public class VirtualFileSystemServiceTest {

	@Resource
	private IVirtualFileSystemService fileService;
	
	@Test
	public void isExistFile(){
		String str = "C:\\fakepath\\XX9(02)--20150109(公开).DAT";
		str = str.replace("\\", "/");
		String[] strs = str.split("/");
		System.out.println(strs[strs.length-1]);
		boolean flag = fileService.isExistFile(strs[strs.length-1]);
		System.out.println(flag);
	}
	
	@Test
	public void saveFilesThread() throws Exception{
		String sPath = "D:\\temp\\data\\2016\\1-6";
		if (!sPath.endsWith(File.separator)) {
			sPath = sPath + File.separator;
		}
		File dirFile = new File(sPath);
		if (dirFile.exists() && dirFile.isDirectory()) {
			File[] files = dirFile.listFiles();
			for (final File file : files) {
				new Thread(new Runnable(){
					@Override
					public void run() {
						long begin = System.currentTimeMillis();
						try {
							Map<String, FileDto> map = new HashMap<String,FileDto>();
							FileDto csvFileDto = new FileDto();
							File csvFile = new File(file.getAbsolutePath());
							InputStream csvInput = new FileInputStream(csvFile);
							csvFileDto.setFileName(csvFile.getName());
							csvFileDto.setFileSize(csvFile.length());
							csvFileDto.setIn(csvInput);
							map.put("csv", csvFileDto);			
							fileService.saveFile(map);
						} catch (Exception e) {
							e.printStackTrace();
						}				
						long end = System.currentTimeMillis();
						Thread current = Thread.currentThread();  
						System.out.println(current.getName() + "-time: " + (end - begin));
					}}).start();
			}
		}
	}
	@Test
	public void saveFiles() throws Exception{
		long begin = System.currentTimeMillis();
		String sPath = "D:\\temp\\data\\2016\\1-6";
		if (!sPath.endsWith(File.separator)) {
			sPath = sPath + File.separator;
		}
		File dirFile = new File(sPath);
		if (dirFile.exists() && dirFile.isDirectory()) {
			File[] files = dirFile.listFiles();
			for (File file : files) {
				System.out.println(file.getAbsolutePath());
				this.saveFile(file.getAbsolutePath(), null);
			}
		}
		long end = System.currentTimeMillis();
		System.out.println("time: " + (end - begin));
	}
	
	public void saveFile(String csv,String dat) throws Exception{
		Map<String, FileDto> map = new HashMap<String,FileDto>();
		if(csv != null && !csv.equals("")){
			FileDto csvFileDto = new FileDto();
			File csvFile = new File(csv);
			InputStream csvInput = new FileInputStream(csvFile);
			csvFileDto.setFileName(csvFile.getName());
			csvFileDto.setFileSize(csvFile.length());
			csvFileDto.setIn(csvInput);
			map.put("csv", csvFileDto);			
		}
		if(dat != null && !dat.equals("")){
			FileDto datFileDto = new FileDto();
			File datFile = new File(dat);
			InputStream datInput = new FileInputStream(datFile);
			datFileDto.setFileName(datFile.getName());
			datFileDto.setFileSize(datFile.length());
			datFileDto.setIn(datInput);
			map.put("dat", datFileDto);			
		}
		fileService.saveFile(map);
	}
	@Test
	public void saveFile() throws Exception{
		long begin = System.currentTimeMillis();
		String csv = "D:\\temp\\data\\2016\\7\\j9-02--2016-07-02.csv";
//		String dat = "C:\\XX9(02)--20150817(公开).DAT";
		this.saveFile(csv, null);
		long end = System.currentTimeMillis();
		System.out.println((end - begin));
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
		int pageSize = 10;
		String series = "j9";
		String star = "02";
		long dirId = 0;
		String beginTime = "";
		String endTime = "";
		String dataTypes = "";
		Pager pager = fileService.getMongoFSList(pageIndex, pageSize, series, star, dirId, beginTime, endTime, dataTypes);
		List<VirtualFileSystem> list =pager.getRows();
		for (VirtualFileSystem fs : list) {
			System.out.println(fs);
		}
	}
}
