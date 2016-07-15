package DataAn.fileSystem.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.annotation.Resource;

import org.bson.Document;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;

import DataAn.common.pageModel.Pager;
import DataAn.common.utils.DateUtil;
import DataAn.common.utils.UUIDGeneratorUtil;
import DataAn.fileSystem.dao.IVirtualFileSystemDao;
import DataAn.fileSystem.domain.VirtualFileSystem;
import DataAn.fileSystem.dto.FileDto;
import DataAn.fileSystem.dto.MongoFSDto;
import DataAn.fileSystem.option.DataType;
import DataAn.fileSystem.option.FileType;
import DataAn.fileSystem.option.J9SeriesType;
import DataAn.fileSystem.service.ICSVService;
import DataAn.fileSystem.service.IVirtualFileSystemService;
import DataAn.mongo.db.MongodbUtil;
import DataAn.mongo.fs.IDfsDb;
import DataAn.mongo.fs.MongoDfsDb;
import DataAn.mongo.zip.ZipCompressorByAnt;


@Service
public class VirtualFileSystemServiceImpl implements IVirtualFileSystemService{

	@Resource
	private IVirtualFileSystemDao fileDao;
	@Resource
	private ICSVService csvService;	

	@Override
	@Transactional
	public void saveFile(Map<String, FileDto> map) throws Exception {
		
		//获取map中的csv文件
		FileDto csvFileDto = map.get("csv");
		if(csvFileDto == null){
			System.out.println("saveFile-->csv文件不能为空。。。");
			return;
		}
		Map<String,String> dataMap = new HashMap<String,String>();
		String fileName = csvFileDto.getFileName();
		String[] strs = fileName.substring(0, fileName.lastIndexOf(".csv")).split("--");
		String[] ss = strs[0].split("-");
		String nowSeries = J9SeriesType.SERIES.getValue();
		dataMap.put("series", nowSeries);
		String nowStar = J9SeriesType.getJ9StarType(ss[1]).getValue();
		dataMap.put("star", nowStar);
		String date = strs[1];
		dataMap.put("date", date);
		String year = DateUtil.formatString(date, "yyyy-MM-dd", "yyyy");
		dataMap.put("year", year);
		String month = DateUtil.formatString(date, "yyyy-MM-dd", "MM");
		dataMap.put("month", month);
//		String day = DateUtil.formatString(date, "yyyy-MM-dd", "dd");
//		dateMap.put("day", day);
//		System.out.println(year + "-" + month + "-" + day);
		//解析 *.csv文件保存csv里面的数据
		InputStream csvInput = csvFileDto.getIn();
		this.saveDataOfCSVInMongoDB(csvInput, nowStar);
		// 保存 *.csv文件
		this.saveFileOfCSV(csvFileDto, dataMap);
		//获取map中的csv文件
		FileDto datFile = map.get("dat");
		if(datFile != null){			
			// 保存 *.DAT文件
			this.saveFileOfDAT(datFile, dataMap);
		}
		
	}
	
	@Override
	@Transactional(readOnly = true)
	public FileDto downloadFile(long fileId) {
		FileDto fileDto = new FileDto();
		//从数据库中获取文件信息
		VirtualFileSystem file = fileDao.get(fileId);
		fileDto.setFileName(file.getFileName());
		fileDto.setFileSize(file.getFileSize());
		//从mongofs中获取数据流
		IDfsDb dfs = MongoDfsDb.getInstance();
		fileDto.setIn(dfs.downLoad(file.getMongoFSUUId()));
		return fileDto;
	}
	
	@Override
	@Transactional(readOnly = true)
	public FileDto downloadFiles(String ids) throws Exception {
		FileDto fileDto = new FileDto();
		String[] arrayIds = ids.split(",");
		String mogodbFilePath = "D:\\temp\\mongo";
		VirtualFileSystem file = null;
		IDfsDb dfs = MongoDfsDb.getInstance();
		for (String id : arrayIds) {
			String[] items = id.split("/");
			//遍历目录写文件
			if("dir".equals(items[1])){
				this.writeDirFile(Long.parseLong(items[0]), dfs, mogodbFilePath);
			}else{
				file = fileDao.get(Long.parseLong(items[0]));
				dfs.downLoad(file.getMongoFSUUId(),mogodbFilePath);
			}
		}
		String zipPath = "D:\\szhzipant.zip";
		ZipCompressorByAnt zca = new ZipCompressorByAnt(zipPath);  
	    zca.compressExe(mogodbFilePath);  
		fileDto.setFileName("szhzipant.zip");
		fileDto.setFilePath(zipPath);
		return fileDto;
	}
	
	private void writeDirFile(long dirId,IDfsDb dfs,String path) throws Exception{
		VirtualFileSystem dir = fileDao.get(dirId);
		path = path + File.separator + dir.getFileName();
		List<VirtualFileSystem> fileList = fileDao.findByParam("parentId", dirId);
		if(fileList != null && fileList.size() > 0){
			for (VirtualFileSystem childFile : fileList) {
				if(childFile.getFileType().getName().equals("dir")){
					this.writeDirFile(childFile.getId(), dfs, path);					
				}
				dfs.downLoad(childFile.getMongoFSUUId(),path);
			}
		}
	}
	
	@Override
	@Transactional(readOnly = true)
	public FileDto downloadFiles(long dirId) throws Exception {
		FileDto fileDto = new FileDto();
		VirtualFileSystem dir = fileDao.get(dirId);
		List<VirtualFileSystem> fileList = fileDao.findByParam("parentId", dirId);
		if(fileList == null || fileList.size() == 0){
			return null;
		}
		ZipOutputStream zipOut = null;
		InputStream input = null;
		try {
			String path = "d:" + File.separator + "zipMany.zip";
			File zipFile = new File(path);
			zipOut = new ZipOutputStream(new FileOutputStream(zipFile));
			IDfsDb dfs = MongoDfsDb.getInstance();
			for (VirtualFileSystem file : fileList) {
				
				input = dfs.downLoad(file.getMongoFSUUId());
				
				zipOut.putNextEntry(new ZipEntry(dir.getFileName() + File.separator + file.getFileName()));
		        // 设置注释  
//		        zipOut.setComment("hello");  
		        int temp = 0;  
		        while((temp = input.read()) != -1){  
		            zipOut.write(temp);  
		        }  
		        input.close(); 
			}
			fileDto.setFileName("zipMany.zip");
			fileDto.setFilePath(path);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if (zipOut != null) {
				zipOut.flush();
				zipOut.closeEntry();
				zipOut.close();
			}
			if(input!=null){
				input.close();
			}
		}
		return fileDto;
	}

	@Override
	@Transactional(readOnly = true)
	public boolean isExistFile(String fileName) {
		List<VirtualFileSystem> list = fileDao.findAll();
		for (VirtualFileSystem fs : list) {
			System.out.println(fs);
		}
		return false;
	}
	
	@Override
	public Pager getMongoFSList(int pageIndex, int pageSize, long dirId) {
		List<VirtualFileSystem> fileList = null;
		if(dirId == 0){
			fileList = fileDao.selectByParentIdisNullAndOrder("fileName");
		}else{
			fileList = fileDao.findByParam("parentId", dirId, "fileName");
		}
		return this.returnPager(pageIndex, pageSize, fileList);
	}
	
	@Override
	public Pager getMongoFSList(int pageIndex, int pageSize, String series,
			String star, long dirId) {
		List<VirtualFileSystem> fileList = null;
		if(dirId == 0){
			fileList = fileDao.selectBySeriesAndStarAndParentIdisNullAndOrder(series, star, "fileName");
		}else{
			fileList = fileDao.selectBySeriesAndStarAndParentIdAndOrder(series, star, dirId, "fileName");
		}
		return this.returnPager(pageIndex, pageSize, fileList);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Pager getMongoFSList(int pageIndex, int pageSize, String series,String star, 
			long dirId, String beginTime, String endTime,String dataTypes) {
		List<VirtualFileSystem> fileList = fileDao.selectByOption(series, star, dirId, beginTime, endTime, dataTypes, "fileName");
		return this.returnPager(pageIndex, pageSize, fileList);
	}
	
	private Pager returnPager(int pageIndex, int pageSize, List<VirtualFileSystem> fileList){
		List<MongoFSDto> fsList = new ArrayList<MongoFSDto>();
		if(fileList != null && fileList.size() > 0){
			MongoFSDto fsDto = null;
			for (VirtualFileSystem fs : fileList) {
				fsDto = new MongoFSDto();
				fsDto.setId(fs.getId());
				fsDto.setCreateDate(DateUtil.format(fs.getUpdateDate()));
				if(fs.getFileType().getName().equals("dir")){
					fsDto.setFileSize("-");					
				}else{
					fsDto.setFileSize(String.valueOf(fs.getFileSize()));
				}
				fsDto.setName(fs.getFileName());
				fsDto.setType(fs.getFileType().getName());
				fsList.add(fsDto);
			}
		}
		long totalCount = 0;
		totalCount = fsList.size();
		Pager pager = new Pager(pageIndex, pageSize, totalCount, fsList);
		return pager;
	}
	
	@Override
	public String getParentFSCatalog(long dirId) {
		List<VirtualFileSystem> list = new ArrayList<VirtualFileSystem>();
		VirtualFileSystem fs = fileDao.get(dirId);
		list.add(fs);
		Long parentId = fs.getParentId();
		while(parentId != null){
			VirtualFileSystem parentFs = fileDao.get(parentId);
			list.add(parentFs);
			parentId = parentFs.getParentId();
		}
		Collections.reverse(list);
		StringBuilder sb = new StringBuilder();
		sb.append("{");
		for (VirtualFileSystem f : list) {
			sb.append("\"" + f.getId().toString() + "\"" + ":" + "\"" + f.getFileName() + "\"" +",");
		}
		if(sb.lastIndexOf(",") != -1){
			sb.deleteCharAt(sb.lastIndexOf(","));			
		}
		sb.append("}");
		return sb.toString();
	}
	
	private void saveDataOfCSVInMongoDB(InputStream csvInput, String nowStar) throws Exception{
		MongodbUtil mg = MongodbUtil.getInstance();
		List<Document> docList = csvService.readCSVFileToDoc(csvInput);
		String collectionName = J9SeriesType.getJ9StarType(nowStar).getName();
		mg.insert(collectionName , docList);
	}
	private void saveFileOfCSV(FileDto fileDto, Map<String,String> dataMap){
		
		String uuId = UUIDGeneratorUtil.getUUID();
		
		String series = dataMap.get("series");
		String star = dataMap.get("star");
		String date = dataMap.get("date");
		String year = dataMap.get("year");
		String month = dataMap.get("month");
//		String day = dateMap.get("day");
		
		//查找csv的文件夹是否存在
		VirtualFileSystem csvDir = fileDao.selectByParentIdisNullAndFileName("csv");
		if(csvDir == null){
			csvDir = new VirtualFileSystem();
			csvDir.setSeries(series);
			csvDir.setStar(star);
			csvDir.setDataType(DataType.CSV);
			csvDir.setFileName("csv");
			csvDir.setFileType(FileType.DIR);
			csvDir = fileDao.add(csvDir);
		}
		//查找年份的文件夹是否存在
		VirtualFileSystem yearDir = fileDao.selectByParentIdAndFileName(csvDir.getId(), year);
		if(yearDir == null){
			yearDir = new VirtualFileSystem();
			yearDir.setSeries(series);
			yearDir.setStar(star);
			yearDir.setDataType(DataType.CSV);
			yearDir.setFileName(year);
			yearDir.setFileType(FileType.DIR);
			yearDir.setYear_month_day(year);
			yearDir.setParentId(csvDir.getId());
			yearDir = fileDao.add(yearDir);
		}
		//查找月份的文件夹是否存在
		VirtualFileSystem monthDir = fileDao.selectByParentIdAndFileName(yearDir.getId(), month);
		if(monthDir == null){
			monthDir = new VirtualFileSystem();
			monthDir.setSeries(series);
			monthDir.setStar(star);
			monthDir.setDataType(DataType.CSV);
			monthDir.setFileName(month);
			monthDir.setFileType(FileType.DIR);
			monthDir.setYear_month_day(year + "-" + month);
			monthDir.setParentId(yearDir.getId());
			monthDir = fileDao.add(monthDir);
		}
		//保存文件记录
		VirtualFileSystem file = new VirtualFileSystem();
		file.setSeries(series);
		file.setStar(star);
		file.setDataType(DataType.CSV);
		file.setFileName(fileDto.getFileName());
		file.setFileSize(fileDto.getFileSize());
		file.setFileType(FileType.FILE);
		file.setParentId(monthDir.getId());
		file.setYear_month_day(date);
		file.setMongoFSUUId(uuId);
		fileDao.add(file);
		
		//保存csv文件
		IDfsDb dfs = MongoDfsDb.getInstance();
		dfs.upload(fileDto.getFileName(), uuId, fileDto.getIn());
	}
	
	private void saveFileOfDAT(FileDto fileDto, Map<String,String> dataMap){
		String uuId = UUIDGeneratorUtil.getUUID();
		
		String series = dataMap.get("series");
		String star = dataMap.get("star");
		String date = dataMap.get("date");
		String year = dataMap.get("year");
		String month = dataMap.get("month");
//		String day = dateMap.get("day");
		
		//查找dat的文件夹是否存在
		VirtualFileSystem datDir = fileDao.selectByParentIdisNullAndFileName("dat");
		if(datDir == null){
			datDir = new VirtualFileSystem();
			datDir.setSeries(series);
			datDir.setStar(star);
			datDir.setDataType(DataType.DAT);
			datDir.setFileName("dat");
			datDir.setFileType(FileType.DIR);
			datDir = fileDao.add(datDir);
		}
		//查找年份的文件夹是否存在
		VirtualFileSystem yearDir = fileDao.selectByParentIdAndFileName(datDir.getId(), year);
		if(yearDir == null){
			yearDir = new VirtualFileSystem();
			yearDir.setSeries(series);
			yearDir.setStar(star);
			yearDir.setDataType(DataType.DAT);
			yearDir.setFileName(year);
			yearDir.setFileType(FileType.DIR);
			yearDir.setYear_month_day(year);
			yearDir.setParentId(datDir.getId());
			yearDir = fileDao.add(yearDir);
		}
		//查找月份的文件夹是否存在
		VirtualFileSystem monthDir = fileDao.selectByParentIdAndFileName(yearDir.getId(), month);
		if(monthDir == null){
			monthDir = new VirtualFileSystem();
			monthDir.setSeries(series);
			monthDir.setStar(star);
			monthDir.setDataType(DataType.DAT);
			monthDir.setFileName(month);
			monthDir.setFileType(FileType.DIR);
			monthDir.setYear_month_day(year + "-" + month);
			monthDir.setParentId(yearDir.getId());
			monthDir = fileDao.add(monthDir);
		}
		//保存文件记录
		VirtualFileSystem file = new VirtualFileSystem();
		file.setSeries(series);
		file.setStar(star);
		file.setDataType(DataType.DAT);
		file.setFileName(fileDto.getFileName());
		file.setFileSize(fileDto.getFileSize());
		file.setFileType(FileType.FILE);
		file.setParentId(monthDir.getId());
		file.setYear_month_day(date);
		file.setMongoFSUUId(uuId);
		fileDao.add(file);
		
		//保存dat文件
		IDfsDb dfs = MongoDfsDb.getInstance();
		dfs.upload(fileDto.getFileName(), uuId, fileDto.getIn());
	}










}
