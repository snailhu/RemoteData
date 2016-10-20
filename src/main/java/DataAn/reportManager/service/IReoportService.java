package DataAn.reportManager.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import DataAn.common.pageModel.Pager;
import DataAn.fileSystem.dto.FileDto;
import DataAn.fileSystem.dto.MongoFSDto;
import DataAn.reportManager.domain.ReportFileSystem;
import DataAn.reportManager.dto.DataToDocDto;
import DataAn.reportManager.dto.ReportFileDto;

public interface IReoportService {
	
	public void reportDoc(String filename,DataToDocDto data,String imgUrl,String templateUrl,String templateName,String docPath) throws Exception;
	
	public ReportFileSystem saveReport(ReportFileDto reportFileDto, Map<String,String> dataMap);
	
	public void downLoadReportForDb(long fileId, String databaseName, HttpServletResponse response);
	
	public  void downLoadReportForDis(InputStream inputStream ,String fileName ,HttpServletResponse response);
	
	public Pager<MongoFSDto> getMongoFSList(int pageIndex, int pageSize, String series,String star, String parameterType, 
			long dirId, String beginTime, String endTime,String dataTypes);
	
	public Pager<MongoFSDto> getMongoFSList(int pageIndex, int pageSize, String series,
			String star, String parameterType, long dirId) ;
	
	public String getParentFSCatalog(long dirId);
	
	public String downLoadsReportForDb(String itemIds,String databaseName,HttpServletResponse response);
	
	public FileDto downloadFiles(String ids,String databaseName) throws Exception;
	
	public void deleteFile(String ids);
	
/*	public void createReport(String nowDate, String filename, String imgUrl, String templateUrl, String templateName,
			String docPath, String seriesId, String starId, String partsType) throws Exception;*/
	
	public ReportFileSystem insertReportToDB(String filename, String docPath,String seriesId,String starId, String partsType)
			throws FileNotFoundException, IOException;
	
	public void removeDoc(String docPath);
	
	public void downloadReport(HttpServletResponse response, String docPath,String filename ) throws FileNotFoundException ;
	
	public void createReport(Date beginDate,Date endDate,  String filename, String imgUrl, String templateUrl, String templateName,
			String docPath, String seriesId, String starId, String partsType) throws Exception;
}
