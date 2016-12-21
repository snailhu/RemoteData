package DataAn.sys.service;

import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import DataAn.sys.domain.SystemLog;
import DataAn.sys.dto.SystemLogDto;

public interface SystemLogService {
	
	public void saveObject(SystemLog slog);
	
	public void saveObjectById(Integer logId);
	
	//添加一条日志记录，需要的参数为 request对象和操作的具体内容
	public void addOneSystemlogs(HttpServletRequest request,String operateJob);
	public void deleteSystemlogs();
	
	public List<SystemLog> getSystemLogs();
	
	public List<SystemLog> getSyetemLogsByTime(String startDate ,String endDate)throws ParseException;
	public List<SystemLog> getSyetemLogsByTimeAndkeyWord(String startDate ,String endDate,String keyWord)throws ParseException;
}
