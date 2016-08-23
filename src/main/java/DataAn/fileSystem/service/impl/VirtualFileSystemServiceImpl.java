package DataAn.fileSystem.service.impl;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import javax.annotation.Resource;
import org.bson.Document;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import DataAn.common.config.Config;
import DataAn.common.pageModel.Pager;
import DataAn.common.utils.DateUtil;
import DataAn.common.utils.FileUtil;
import DataAn.common.utils.UUIDGeneratorUtil;
import DataAn.fileSystem.dao.IDateParametersDao;
import DataAn.fileSystem.dao.IVirtualFileSystemDao;
import DataAn.fileSystem.domain.DateParameters;
import DataAn.fileSystem.domain.VirtualFileSystem;
import DataAn.fileSystem.dto.CSVFileDataResultDto;
import DataAn.fileSystem.dto.FileDto;
import DataAn.fileSystem.dto.MongoFSDto;
import DataAn.fileSystem.option.FileDataType;
import DataAn.fileSystem.option.FileType;
import DataAn.fileSystem.option.J9SeriesType;
import DataAn.fileSystem.service.ICSVService;
import DataAn.fileSystem.service.IVirtualFileSystemService;
import DataAn.mongo.db.MongodbUtil;
import DataAn.mongo.fs.IDfsDb;
import DataAn.mongo.fs.MongoDfsDb;
import DataAn.mongo.service.IMongoService;
import DataAn.mongo.zip.ZipCompressorByAnt;


@Service
public class VirtualFileSystemServiceImpl implements IVirtualFileSystemService{

	@Resource
	private IVirtualFileSystemDao fileDao;
	@Resource
	private IDateParametersDao parametersDao;
	@Resource
	private ICSVService csvService;	
	@Resource
	private IMongoService mongoService;

	@Override
	@Transactional
	public void saveFile(Map<String, FileDto> map) throws Exception {
		
		//获取map中的csv文件
		FileDto csvFileDto = map.get("csv");
		Map<String,String> dataMap = new HashMap<String,String>();
		//解析csv中的文件名称 以获取信息
		String fileName = csvFileDto.getFileName();
		String[] strs = fileName.substring(0, fileName.lastIndexOf(".csv")).split("--");
		String[] ss = strs[0].split("-");
		String nowSeries = J9SeriesType.SERIES.getValue();
		dataMap.put("series", nowSeries);
		String nowStar = J9SeriesType.getJ9SeriesType(ss[1]).getValue();
		dataMap.put("star", nowStar);
		String date = strs[1];
		dataMap.put("date", date);
		String year = DateUtil.formatString(date, "yyyy-MM-dd", "yyyy");
		dataMap.put("year", year);
		String month = DateUtil.formatString(date, "yyyy-MM-dd", "MM");
		dataMap.put("month", month);
//		String day = DateUtil.formatString(date, "yyyy-MM-dd", "dd");
//		dataMap.put("day", day);
//		System.out.println(year + "-" + month + "-" + day);
		
		String versions = UUIDGeneratorUtil.getUUID();
		dataMap.put("versions", versions);
		
		//保存csv临时文件
		String csvTempFilePath = Config.getUplodCachePath() + File.separator + versions;
		FileUtil.saveFile(csvTempFilePath, fileName, csvFileDto.getIn());
		csvTempFilePath = csvTempFilePath + File.separator + fileName;
		csvFileDto.setFilePath(csvTempFilePath);
		
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
	public void deleteFile(String ids) {
		String[] arrayIds = ids.split(",");
		VirtualFileSystem file = null;
		Set<String> uuIds = new HashSet<String>();
		for (String id : arrayIds) {
			String[] items = id.split("/");
			//遍历目录写文件
			if("dir".equals(items[1])){
				VirtualFileSystem dir = fileDao.get(Long.parseLong(items[0]));
				uuIds.addAll(this.deleteFile(dir));
			}else{
				file = fileDao.get(Long.parseLong(items[0]));
				uuIds.add(file.getMongoFSUUId());
//				fileDao.delete(file);
			}
		}
		//删除mongodb的文件和标志记录状态
//		IDfsDb dfs = MongoDfsDb.getInstance();
//		MongodbUtil mg = MongodbUtil.getInstance();
//		String collectionName = "";
		for (String uuId : uuIds) {
			if(uuId != null && !uuId.equals("")){
				System.out.println(uuId);				
				//dfs.delete(uuId);
				//通过更新的方式设置状态为0
//				mg.update(collectionName, "versions", uuId);
			}
		}
	}
	private Set<String> deleteFile(VirtualFileSystem dir) {
		Set<String> uuIds = new HashSet<String>();
		List<VirtualFileSystem> fileList = fileDao.findByParam("parentId", dir.getId());
		if(fileList != null && fileList.size() > 0){
			for (VirtualFileSystem childFile : fileList) {
				if(childFile.getFileType().getName().equals("dir")){
					uuIds.addAll(this.deleteFile(childFile));				
				}
				uuIds.add(childFile.getMongoFSUUId());
				//fileDao.delete(childFile);
			}
		}
		return uuIds;
	}
	
	@Override
	@Transactional(readOnly = true)
	public FileDto downloadFile(long fileId) throws Exception {
		FileDto fileDto = new FileDto();
		//从数据库中获取文件信息
		VirtualFileSystem file = fileDao.get(fileId);
		fileDto.setFileName(file.getFileName());
		fileDto.setFileSize(file.getFileSize());
		//判断临时文件是否存在
		if(file.getCachePath() != null && !file.getCachePath().equals("")){
			File cacheFile = new File(file.getCachePath());
			InputStream in = new FileInputStream(cacheFile);
			fileDto.setIn(in);
		}else{
			//从mongofs中获取数据流
			IDfsDb dfs = MongoDfsDb.getInstance();
			fileDto.setIn(dfs.downLoad(file.getMongoFSUUId()));			
		}
		return fileDto;
	}
	
	@Override
	@Transactional(readOnly = true)
	public FileDto downloadFiles(String ids) throws Exception {
		FileDto fileDto = new FileDto();
		String[] arrayIds = ids.split(",");
		String mogodbFilePath = Config.getDownloadCachePath();
		VirtualFileSystem file = null;
		IDfsDb dfs = MongoDfsDb.getInstance();
		for (String id : arrayIds) {
			String[] items = id.split("/");
			//遍历目录写文件
			if("dir".equals(items[1])){
				this.writeDirFile(Long.parseLong(items[0]), dfs, mogodbFilePath);
			}else{
				file = fileDao.get(Long.parseLong(items[0]));
				//判断临时文件是否存在
				if(file.getCachePath() != null && !file.getCachePath().equals("")){
					FileUtil.copyFile(file.getCachePath(), mogodbFilePath, true);
				}else{
					dfs.downLoad(file.getMongoFSUUId(),mogodbFilePath);										
				}
			}
		}
		String zipFileName = DateUtil.format(new Date(),"yyyy-MM-dd-HH-mm-ss") + ".zip";
		String zipPath = Config.getZipCachePath() + File.separator + zipFileName;
		ZipCompressorByAnt zca = new ZipCompressorByAnt(zipPath);  
	    zca.compressExe(mogodbFilePath);  
		fileDto.setFileName(zipFileName);
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
				}else{
					//判断临时文件是否存在
					if(childFile.getCachePath() != null && !childFile.getCachePath().equals("")){
						FileUtil.copyFile(childFile.getCachePath(), path, true);
					}else{
						dfs.downLoad(childFile.getMongoFSUUId(),path);											
					}
				}
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
			String zipFileName = DateUtil.format(new Date(),"yyyy-MM-dd-HH-mm-ss") + ".zip";
			String zipPath = Config.getZipCachePath() + File.separator + zipFileName;
			zipOut = new ZipOutputStream(new FileOutputStream(new File(zipPath)));
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
			fileDto.setFileName(zipFileName);
			fileDto.setFilePath(zipPath);
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
		VirtualFileSystem file = fileDao.selectByFileName(fileName);
		if(file == null){
			return false;
		}
		return true;
	}
	
	@Override
	public Pager<MongoFSDto> getMongoFSList(int pageIndex, int pageSize, long dirId) {
		List<VirtualFileSystem> fileList = null;
		if(dirId == 0){
			fileList = fileDao.selectByParentIdisNullAndOrder("updateDate");
		}else{
			fileList = fileDao.findByParam("parentId", dirId, "updateDate");
		}
		return this.returnPager(pageIndex, pageSize, fileList,0);
	}
	
	@Override
	public Pager<MongoFSDto> getMongoFSList(int pageIndex, int pageSize, String series,
			String star, long dirId) {
		Pager<VirtualFileSystem> pager = null;
		if(dirId == 0){
			pager = fileDao.selectBySeriesAndStarAndParentIdisNullAndOrder(series, star, "updateDate", pageIndex, pageSize);
		}else{
			pager = fileDao.selectBySeriesAndStarAndParentIdAndOrder(series, star, dirId, "updateDate", pageIndex, pageSize);
		}
		return this.returnPager(pageIndex, pageSize, pager.getRows(),pager.getTotalCount());
	}
	
	@Override
	@Transactional(readOnly = true)
	public Pager<MongoFSDto> getMongoFSList(int pageIndex, int pageSize, String series,String star, 
			long dirId, String beginTime, String endTime,String dataTypes) {
		Pager<VirtualFileSystem> pager = fileDao.selectByOption(series, star, dirId, beginTime, endTime, dataTypes, "updateDate",pageIndex,pageSize);
		return this.returnPager(pageIndex, pageSize, pager.getRows(),pager.getTotalCount());
	}
	
	private Pager<MongoFSDto> returnPager(int pageIndex, int pageSize, List<VirtualFileSystem> fileList,long totalCount){
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
					fsDto.setFileSize(String.valueOf(fs.getFileSize()) + " M");
				}
				fsDto.setName(fs.getFileName());
				fsDto.setType(fs.getFileType().getName());
				fsList.add(fsDto);
			}
		}
		Pager<MongoFSDto> pager = new Pager<MongoFSDto>(pageIndex, pageSize, totalCount, fsList);
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
	
	private void saveFileOfCSV(FileDto fileDto, Map<String,String> dataMap) throws Exception{
		
		
		String uuId = dataMap.get("versions");
		String series = dataMap.get("series");
		String star = dataMap.get("star");
		String date = dataMap.get("date");
		String year = dataMap.get("year");
		String month = dataMap.get("month");
//		String day = dataMap.get("day");
		
		//保存csv 原文件				
//		IDfsDb dfs = MongoDfsDb.getInstance();
//		BufferedInputStream bis = null;
//		try {
//			bis = new BufferedInputStream(new FileInputStream(fileDto.getFilePath()));  
//			dfs.upload(fileDto.getFileName(), uuId, bis);
//		} catch(Exception e){
////			e.printStackTrace();
//			//删除上传csv原文件
//			dfs.delete(uuId);
//			throw new Exception("csv 文件上传失败！！！");
//		}finally {
//			if(bis != null){
//				bis.close();
//			}
//		}
		
		CSVFileDataResultDto<Document> result = csvService.readCSVFileToDoc(fileDto.getFilePath(),uuId);
		List<Document> docList = result.getDatas();
//		MongodbUtil mg = MongodbUtil.getInstance();
//		String collectionName = J9SeriesType.getJ9SeriesType(star).getName();
//		try {
//			mg.insert(collectionName , docList);
////			int a =9/0;
////			System.out.println(a);
//		} catch (Exception e) {
////			e.printStackTrace();
////			dfs.delete(uuId);
//			//删除mogodb记录 效率比较慢
////			mg.deleteMany(collectionName, "versions", uuId);
//			//通过更新的方式设置状态为0
//			mg.update(collectionName, "versions", uuId);
//			throw new Exception("csv 文件解析失败！！！");
//		}
		
		//保存csv文件数据
		mongoService.saveCSVData(series, star, date, docList, uuId);
		
		//存储某一天的参数信息
		if(docList != null && docList.size() > 0){
			Document doc1 = docList.get(0);
			String year_month_day = doc1.getString("year_month_day");
			String title = result.getTitle();
			DateParameters dateParameters = new DateParameters();
			dateParameters.setParameters(title);
			dateParameters.setYear_month_day(year_month_day);
			parametersDao.add(dateParameters);
		}
		//查找csv的文件夹是否存在
		VirtualFileSystem csvDir = fileDao.selectByParentIdisNullAndFileName("csv");
		if(csvDir == null){
			csvDir = new VirtualFileSystem();
			csvDir.setSeries(series);
			csvDir.setStar(star);
			csvDir.setDataType(FileDataType.CSV);
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
			yearDir.setDataType(FileDataType.CSV);
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
			monthDir.setDataType(FileDataType.CSV);
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
		file.setDataType(FileDataType.CSV);
		file.setFileName(fileDto.getFileName());
		file.setFileSize(fileDto.getFileSize());
		file.setFileType(FileType.FILE);
		file.setParentId(monthDir.getId());
		file.setYear_month_day(date);
		file.setMongoFSUUId(uuId);
		//保存缓存路径，以后通过定时任务上传
		file.setCachePath(fileDto.getFilePath());
		fileDao.add(file);
		

	}
	
//	private void saveDataOfCSVInMongoDB(InputStream csvInput, String nowStar, String versions) throws Exception{
//		MongodbUtil mg = MongodbUtil.getInstance();
//		List<Document> docList = csvService.readCSVFileToDoc(csvInput,versions);
//		String collectionName = J9SeriesType.getJ9StarType(nowStar).getName();
//		mg.insert(collectionName , docList);
//	}
//	private void saveDataOfCSVInMongoDB(String csvPath, String nowStar, String versions) throws Exception{
//		MongodbUtil mg = MongodbUtil.getInstance();
//		List<Document> docList = csvService.readCSVFileToDoc(csvPath,versions);
//		String collectionName = J9SeriesType.getJ9StarType(nowStar).getName();
//		mg.insert(collectionName , docList);
//	}
	
	private void saveFileOfDAT(FileDto fileDto, Map<String,String> dataMap){
		
		
		String uuId = dataMap.get("versions");
		String series = dataMap.get("series");
		String star = dataMap.get("star");
		String date = dataMap.get("date");
		String year = dataMap.get("year");
		String month = dataMap.get("month");
//		String day = dateMap.get("day");
		
		//保存dat文件
		IDfsDb dfs = MongoDfsDb.getInstance();
		dfs.upload(fileDto.getFileName(), uuId, fileDto.getIn());

		//查找dat的文件夹是否存在
		VirtualFileSystem datDir = fileDao.selectByParentIdisNullAndFileName("dat");
		if(datDir == null){
			datDir = new VirtualFileSystem();
			datDir.setSeries(series);
			datDir.setStar(star);
			datDir.setDataType(FileDataType.DAT);
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
			yearDir.setDataType(FileDataType.DAT);
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
			monthDir.setDataType(FileDataType.DAT);
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
		file.setDataType(FileDataType.DAT);
		file.setFileName(fileDto.getFileName());
		file.setFileSize(fileDto.getFileSize());
		file.setFileType(FileType.FILE);
		file.setParentId(monthDir.getId());
		file.setYear_month_day(date);
		file.setMongoFSUUId(uuId);
		fileDao.add(file);
		

	}


}
