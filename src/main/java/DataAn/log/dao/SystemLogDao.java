package DataAn.log.dao;

import java.util.Date;
import java.util.List;

import DataAn.common.dao.IBaseDao;
import DataAn.log.domain.SystemLog;

public interface SystemLogDao extends IBaseDao<SystemLog> {
	
	
	public List<SystemLog> getSystemLogsByTime(Date startDate, Date endDate); 
	
}
