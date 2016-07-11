package DataAn.fileSystem.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import DataAn.fileSystem.dto.FileDto;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext-hibernate.xml")
public class VirtualFileSystemServiceTest {

	@Resource
	private IVirtualFileSystemService fileService;
	
	@Test
	public void test(){
		fileService.isExistFile(null);
	}
	
	@Test
	public void saveFile() throws Exception{
		long begin = System.currentTimeMillis();
		Map<String, FileDto> map = new HashMap<String,FileDto>();
		
		FileDto csvFileDto = new FileDto();
		String csv = "C:\\j9-02--2015-08-11.csv";
		File csvFile = new File(csv);
		InputStream csvInput = new FileInputStream(csvFile);
		csvFileDto.setFileName(csvFile.getName());
		csvFileDto.setFileSize(csvFile.length());
		csvFileDto.setIn(csvInput);
		map.put("csv", csvFileDto);
		
		FileDto datFileDto = new FileDto();
		String dat = "C:\\XX9(02)--20150811(公开).DAT";
		File datFile = new File(dat);
		InputStream datInput = new FileInputStream(datFile);
		datFileDto.setFileName(datFile.getName());
		datFileDto.setFileSize(datFile.length());
		datFileDto.setIn(datInput);
		map.put("dat", datFileDto);
		
		fileService.saveFile(map);
		long end = System.currentTimeMillis();
		System.out.println((end - begin));
	}
	@Test
	public void downloadFiles() throws Exception{
		String str = "1/dir";
		FileDto fileDto = fileService.downloadFiles(str);
		System.out.println(fileDto);
	}
}
