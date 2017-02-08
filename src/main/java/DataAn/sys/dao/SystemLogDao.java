package DataAn.sys.dao;

import java.util.Date;
import java.util.List;

import DataAn.common.dao.IBaseDao;
import DataAn.sys.domain.SystemLog;
import DataAn.sys.dto.SystemLogDto;

public interface SystemLogDao extends IBaseDao<SystemLog> {
	
	public List<SystemLog> getallSystemLogs();
	
	public List<SystemLog> getSystemLogsByTime(Date startDate, Date endDate); 
	
	public List<SystemLog> getSystemLogsBykeyWord(Date startDate, Date endDate,String keyWord);
	
	public void deleteSystemlogById (SystemLog log);
	
	public long getSystemLogCount();
	
}
