package DataAn.reportManager.service.impl;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
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

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import com.aspose.words.Document;
import com.aspose.words.SaveFormat;

import DataAn.wordManager.util.MapMailMergeDataSource;
import DataAn.common.config.CommonConfig;
import DataAn.common.pageModel.Pager;
import DataAn.common.utils.DateUtil;
import DataAn.fileSystem.dto.FileDto;
import DataAn.fileSystem.dto.MongoFSDto;
import DataAn.fileSystem.option.FileType;
import DataAn.mongo.db.MongodbUtil;
import DataAn.mongo.fs.IDfsDb;
import DataAn.mongo.fs.MongoDfsDb;
import DataAn.mongo.zip.ZipCompressorByAnt;
import DataAn.reportManager.dao.IReportFileSystemDao;
import DataAn.reportManager.domain.ReportFileSystem;
import DataAn.reportManager.dto.DataToDocDto;
import DataAn.reportManager.dto.ParamDto;
import DataAn.reportManager.dto.ParamImgDataDto;
import DataAn.reportManager.dto.ReportFileDto;
import DataAn.reportManager.option.ReportDataType;
import DataAn.reportManager.service.IReoportService;
import DataAn.wordManager.util.AsposeLicenseManage;
@Service
public class ReportServiceImpl implements IReoportService {
	
	
	@Resource
	private IReportFileSystemDao fileDao;
	@Override
	public void reportDoc(String filename, DataToDocDto data, String imgPath, String templateUrl, String templateName, String docPath)
			throws Exception {
		// 验证License		
        if (!AsposeLicenseManage.getAsposeLicense()) {
            return;
        }
    	//1 读取模板  
		Document doc = new Document(templateUrl);  
        //2 填充数据源  
        doc.getMailMerge().executeWithRegions(new MapMailMergeDataSource(getMapList(imgPath,data), templateName)); 
        doc.getMailMerge().executeWithRegions(new MapMailMergeDataSource(getMapList3(data), "paramImgTab")); 
        doc.getMailMerge().executeWithRegions(new MapMailMergeDataSource(getMapList2(data), "paramTab")); 
		//3生成报告
       doc.save(docPath, SaveFormat.DOC); 
	} 
	
	private List<Map<String, Object>> getMapList3(DataToDocDto data) throws Exception {  
		   List<Map<String, Object>> dataList = new ArrayList<Map<String,Object>>();  
	        List<ParamImgDataDto> paramImgDatas = data.getParamImgData();
	        for (ParamImgDataDto param : paramImgDatas) {
	        	   //读取一个二进制图片  
		        FileInputStream fis = new FileInputStream(param.getParImg());  
		        byte[] image = new byte[fis.available()];  
		        fis.read(image);
		        fis.close();  
	        	
	        	Map<String, Object> record = new HashMap<String, Object>();  
	        	record.put("parName", param.getParName());
	        	record.put("parImg",image);
	        	dataList.add(record);
			}
	        return dataList;  
	}
	
	private List<Map<String, Object>> getMapList2(DataToDocDto data) throws Exception {  
		   List<Map<String, Object>> dataList = new ArrayList<Map<String,Object>>();  
	        List<ParamDto> params = data.getParams();
	        for (ParamDto param : params) {
	        	Map<String, Object> record = new HashMap<String, Object>();  
	        	record.put("paramName", param.getParamName());
	        	record.put("paramNumMax",param.getParamNumMax());
	        	record.put("paramNumMin",param.getParamNumMin());     
	        	dataList.add(record);
			}
	        return dataList;  
	}
	
	private List<Map<String, Object>> getMapList(String imgPath,DataToDocDto data) throws Exception {  
        List<Map<String, Object>> dataList = new ArrayList<Map<String,Object>>();  
          
        //读取一个二进制图片  
        FileInputStream fis = new FileInputStream(imgPath);  
        byte[] image = new byte[fis.available()];  
        fis.read(image);
        fis.close();  
        
        Map<String, Object> record = new HashMap<String, Object>();  
        record.put("reporttitle", data.getReporttitle());
        record.put("parts",data.getParts());
        record.put("healthcondition",data.getHealthcondition());           
        record.put("PhotoBLOB", image);
        
        dataList.add(record);  
        return dataList;  
    }
	@Override
	public void saveReport(ReportFileDto reportFileDto, Map<String, String> dataMap) {
		
		String uuId = dataMap.get("versions");
		String series = dataMap.get("series");
		String star = dataMap.get("star");
		String date = dataMap.get("date");
		String year = dataMap.get("year");
		String month = dataMap.get("month");
		String startTime = dataMap.get("startTime");
		String endTime = dataMap.get("endTime");
		String parameterType = dataMap.get("parameterType");
		String databaseName = dataMap.get("databaseName");
		
		saveDocToMongoDFs(reportFileDto, uuId,databaseName);
		ReportFileSystem docDir = createDocDir(series, star, parameterType);
		ReportFileSystem yearDir = createYearDir(series, star, year, parameterType, docDir);
		ReportFileSystem monthDir = createMonthDir(series, star, year, month, parameterType, yearDir);
		saveFile(reportFileDto, uuId, series, star, date, startTime, endTime, parameterType, monthDir);
	}
	private void saveDocToMongoDFs(ReportFileDto reportFileDto, String uuId,String databaseName) {
		IDfsDb dfs = MongoDfsDb.getInstance();
		dfs.upload(databaseName,reportFileDto.getFileName(), uuId, reportFileDto.getIn());
	}
	private void saveFile(ReportFileDto reportFileDto, String uuId, String series, String star, String date,
			String startTime, String endTime, String parameterType, ReportFileSystem monthDir) {
		ReportFileSystem file = new ReportFileSystem();
		file.setSeries(series);
		file.setStar(star);
		file.setStartTime(startTime);
		file.setEndTime(endTime);
		file.setParameterType(parameterType);
		file.setDataType(ReportDataType.DOC);
		file.setFileName(reportFileDto.getFileName());
		file.setFileSize(reportFileDto.getFileSize());
		file.setFileType(FileType.FILE);
		file.setParentId(monthDir.getId());
		file.setYear_month_day(date);
		file.setMongoFSUUId(uuId);
		fileDao.add(file);
	}
	private ReportFileSystem createMonthDir(String series, String star, String year, String month, String parameterType,
			ReportFileSystem yearDir) {
		ReportFileSystem monthDir = fileDao.selectByParentIdAndFileName(yearDir.getId(), month);
		if(monthDir == null){
			monthDir = new ReportFileSystem();
			monthDir.setSeries(series);
			monthDir.setStar(star);
			monthDir.setParameterType(parameterType);
			monthDir.setDataType(ReportDataType.DOC);
			monthDir.setFileName(month);
			monthDir.setFileType(FileType.DIR);
			monthDir.setYear_month_day(year + "-" + month);
			monthDir.setParentId(yearDir.getId());
			monthDir = fileDao.add(monthDir);
		}
		return monthDir;
	}
	private ReportFileSystem createYearDir(String series, String star, String year, String parameterType,
			ReportFileSystem docDir) {
		ReportFileSystem yearDir = fileDao.selectByParentIdAndFileName(docDir.getId(), year);
		if(yearDir == null){
			yearDir = new ReportFileSystem();
			yearDir.setSeries(series);
			yearDir.setStar(star);
			yearDir.setParameterType(parameterType);
			yearDir.setDataType(ReportDataType.DOC);
			yearDir.setFileName(year);
			yearDir.setFileType(FileType.DIR);
			yearDir.setYear_month_day(year);
			yearDir.setParentId(docDir.getId());
			yearDir = fileDao.add(yearDir);
		}
		return yearDir;
	}
	private ReportFileSystem createDocDir(String series, String star, String parameterType) {
		ReportFileSystem docDir = fileDao.selectByParentIdisNullAndFileName("doc");
		if(docDir == null){
			docDir = new ReportFileSystem();
			docDir.setSeries(series);
			docDir.setParameterType(parameterType);
			docDir.setStar(star);
			docDir.setDataType(ReportDataType.DOC);
			docDir.setFileName("doc");
			docDir.setFileType(FileType.DIR);
			docDir = fileDao.add(docDir);
		}
		return docDir;
	}
	@Override
	public void downLoadReportForDb(long fileId,String databaseName,HttpServletResponse response) {
		OutputStream os = null;
		InputStream inputStream = null;
		try {
			ReportFileDto fileDto = downloadFile(fileId,databaseName);
			response.setCharacterEncoding("utf-8");
			response.setContentType("multipart/form-data");
			response.setHeader("Content-Disposition", "attachment;fileName="+ fileDto.getFileName());
			inputStream = fileDto.getIn();
			os = response.getOutputStream();
			byte[] b = new byte[2048];
			int length;
			while ((length = inputStream.read(b)) > 0) {
				os.write(b, 0, length);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			closeStream(os, inputStream);
		}
	}
	private void closeStream(OutputStream os, InputStream inputStream) {
		//关闭输入流
		if(inputStream != null){
			try {
				inputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}				
		}
		if(os != null){
			 // 这里主要关闭。
			try {
				os.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	@Transactional(readOnly = true)
	public ReportFileDto downloadFile(long fileId,String databaseName) throws Exception {
		ReportFileDto fileDto = new ReportFileDto();
		//从数据库中获取文件信息
		ReportFileSystem file = fileDao.get(fileId);
		fileDto.setFileName(file.getFileName());
		fileDto.setFileSize(file.getFileSize());
		//从mongofs中获取数据流
		IDfsDb dfs = MongoDfsDb.getInstance();
		fileDto.setIn(dfs.downLoadToStream(databaseName, file.getMongoFSUUId()));	
		return fileDto;
	}
	@Override
	public  void downLoadReportForDis(InputStream inputStream ,String fileName ,HttpServletResponse response) {
		OutputStream os = null;
		try {
			response.setCharacterEncoding("utf-8");
			response.setContentType("multipart/form-data");
			response.setHeader("Content-Disposition", "attachment;fileName="+fileName);
			os = response.getOutputStream();
			byte[] b = new byte[2048];
			int length;
			while ((length = inputStream.read(b)) > 0) {
				os.write(b, 0, length);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			closeStream(os, inputStream);
		}
	}
	
	@RequestMapping("/downloads")
	public String downLoadsReportForDb(String itemIds,String databaseName,HttpServletResponse response) {
		BufferedInputStream buff = null;
		OutputStream myout = null;
		FileInputStream fis = null;
		try {
			FileDto fileDto = downloadFiles(itemIds,databaseName);
			response.setContentType("text/html; charset=GBK");
			// 创建file对象
			File file = new File(fileDto.getFilePath());

			// 设置response的编码方式
			response.setContentType("application/octet-stream");

			// 写明要下载的文件的大小
			response.setContentLength((int) file.length());

			// 设置附加文件名
			String filename = fileDto.getFileName();
			byte[] bt;
			bt = filename.getBytes("UTF-8");
			filename = new String(bt, "8859_1");
			// 解决中文乱码
			response.setHeader("Content-Disposition", "attachment;filename="+ filename);
			// 读出文件到i/o流
			fis = new FileInputStream(file);
			buff = new BufferedInputStream(fis);
			byte[] b = new byte[1024];// 相当于我们的缓存
			long k = 0;// 该值用于计算当前实际下载了多少字节
			// 从response对象中得到输出流,准备下载
			myout = response.getOutputStream();
			// 开始循环下载
			while (k < file.length()) {
				int j = buff.read(b, 0, 1024);
				k += j;
				// 将b中的数据写到客户端的内存
				myout.write(b, 0, j);
			}
			// 将写入到客户端的内存的数据,刷新到磁盘
			myout.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (buff != null) {
					buff.close();
				}
				if (myout != null) {
					myout.close();
				}
				if (fis != null) {
					fis.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}
 
	@Override
	public void deleteFile(String ids) {
		String[] arrayIds = ids.split(",");
		ReportFileSystem file = null;
		Set<String> uuIds = new HashSet<String>();
		for (String id : arrayIds) {
			String[] items = id.split("/");
			//遍历目录写文件
			if("doc".equals(items[1])){
				ReportFileSystem dir = fileDao.get(Long.parseLong(items[0]));
				uuIds.addAll(deleteFile(dir));
			}else{
				file = fileDao.get(Long.parseLong(items[0]));
				uuIds.add(file.getMongoFSUUId());
				fileDao.delete(file);
			}
		}
		//删除mongodb的文件和标志记录状态
		IDfsDb dfs = MongoDfsDb.getInstance();
		MongodbUtil mg = MongodbUtil.getInstance();
		String collectionName = "";
		for (String uuId : uuIds) {
			if(uuId != null && !uuId.equals("")){
				dfs.delete(uuId);
			}
		}
	}
	private Set<String> deleteFile(ReportFileSystem dir) {
		Set<String> uuIds = new HashSet<String>();
		List<ReportFileSystem> fileList = fileDao.findByParam("parentId", dir.getId());
		if(fileList != null && fileList.size() > 0){
			for (ReportFileSystem childFile : fileList) {
				if(childFile.getFileType().getName().equals("dir")){
					uuIds.addAll(deleteFile(childFile));				
				}
				uuIds.add(childFile.getMongoFSUUId());
				//fileDao.delete(childFile);
			}
		}
		return uuIds;
	}
	
	@Override
	@Transactional(readOnly = true)
	public Pager<MongoFSDto> getMongoFSList(int pageIndex, int pageSize, String series,String star, String parameterType, 
			long dirId, String beginTime, String endTime,String dataTypes) {
		Pager<ReportFileSystem> pager = fileDao.selectByOption(series, star,parameterType, dirId, beginTime, endTime, dataTypes, "updateDate",pageIndex,pageSize);
		return returnPager(pageIndex, pageSize, pager.getRows(),pager.getTotalCount());
	}
	private Pager<MongoFSDto> returnPager(int pageIndex, int pageSize, List<ReportFileSystem> fileList,long totalCount){
		List<MongoFSDto> fsList = new ArrayList<MongoFSDto>();
		if(fileList != null && fileList.size() > 0){
			MongoFSDto fsDto = null;
			for (ReportFileSystem fs : fileList) {
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
	public Pager<MongoFSDto> getMongoFSList(int pageIndex, int pageSize, String series,
			String star, String parameterType, long dirId) {
		Pager<ReportFileSystem> pager = null;
		if(dirId == 0){
			pager = fileDao.selectBySeriesAndStarAndParameterTypeAndParentIdisNullAndOrder(series, star, parameterType, "updateDate", pageIndex, pageSize);
		}else{
			pager = fileDao.selectBySeriesAndStarAndParameterTypeAndParentIdAndOrder(series, star, parameterType, dirId, "updateDate", pageIndex, pageSize);
		}
		return this.returnPager(pageIndex, pageSize, pager.getRows(),pager.getTotalCount());
	}
	@Override
	public String getParentFSCatalog(long dirId) {
		List<ReportFileSystem> list = new ArrayList<ReportFileSystem>();
		ReportFileSystem fs = fileDao.get(dirId);
		list.add(fs);
		Long parentId = fs.getParentId();
		while(parentId != null){
			ReportFileSystem parentFs = fileDao.get(parentId);
			list.add(parentFs);
			parentId = parentFs.getParentId();
		}
		Collections.reverse(list);
		StringBuilder sb = new StringBuilder();
		sb.append("{");
		for (ReportFileSystem f : list) {
			sb.append("\"" + f.getId().toString() + "\"" + ":" + "\"" + f.getFileName() + "\"" +",");
		}
		if(sb.lastIndexOf(",") != -1){
			sb.deleteCharAt(sb.lastIndexOf(","));			
		}
		sb.append("}");
		return sb.toString();
	}
	
	@Override
	@Transactional(readOnly = true)
	public FileDto downloadFiles(String ids,String databaseName) throws Exception {
		FileDto fileDto = new FileDto();
		String[] arrayIds = ids.split(",");
		String mogodbFilePath = CommonConfig.getDownloadCachePath();
		ReportFileSystem file = null;
		IDfsDb dfs = MongoDfsDb.getInstance();
		for (String id : arrayIds) {
			String[] items = id.split("/");
			//遍历目录写文件
			if("doc".equals(items[1])){
				writeDirFile(Long.parseLong(items[0]), dfs, mogodbFilePath,databaseName);
			}else{
				file = fileDao.get(Long.parseLong(items[0]));
				//判断临时文件是否存在
					dfs.downLoadToLocal(databaseName,file.getMongoFSUUId(),mogodbFilePath);										
			}
		}
		String zipFileName = DateUtil.format(new Date(),"yyyy-MM-dd-HH-mm-ss") + ".zip";
		String zipPath = CommonConfig.getZipCachePath() + File.separator + zipFileName;
		ZipCompressorByAnt zca = new ZipCompressorByAnt(zipPath);  
	    zca.compressExe(mogodbFilePath);  
		fileDto.setFileName(zipFileName);
		fileDto.setFilePath(zipPath);
		return fileDto;
	}
	private void writeDirFile(long dirId,IDfsDb dfs,String path,String databaseName) throws Exception{
		ReportFileSystem dir = fileDao.get(dirId);
		path = path + File.separator + dir.getFileName();
		List<ReportFileSystem> fileList = fileDao.findByParam("parentId", dirId);
		if(fileList != null && fileList.size() > 0){
			for (ReportFileSystem childFile : fileList) {
				if(childFile.getFileType().getName().equals("doc")){
					writeDirFile(childFile.getId(), dfs, path,databaseName);					
				}else{
					dfs.downLoadToLocal(databaseName,childFile.getMongoFSUUId(),path);											
				}
			}
		}
	}
}
