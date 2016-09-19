package DataAn.log.service;

import java.text.ParseException;

import java.util.List;

import DataAn.log.domain.SystemLog;

public interface SystemLogService {
	
	public void saveObject(SystemLog slog);
	
	public void saveObjectById(Integer logId);
	
	public List<SystemLog> getSystemLogs();
	
	public List<SystemLog> getSyetemLogsByTime(String startDate ,String endDate)throws ParseException;
}
