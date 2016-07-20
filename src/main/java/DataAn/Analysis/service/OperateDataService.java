package DataAn.Analysis.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

public interface OperateDataService {
	
	public void uploadExcelSdoFile(InputStream is) throws IOException;
	
	public List<Map<String, String>> readCSVFile(InputStream in) throws Exception ;
	
	public String[] getDateArray()throws Exception;
	
	public List<Map<String, String>> readCSVFile(String filePath) throws Exception;
}
