package DataAn.log.service.impl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import DataAn.log.dao.SystemLogDao;
import DataAn.log.domain.SystemLog;
import DataAn.log.service.SystemLogService;

@Service
public class SystemLogServiceImpl implements SystemLogService{

	@Resource
	private SystemLogDao systemLogDao;
	
	@Override
	public void saveObject(SystemLog slog) {
		systemLogDao.add(slog);
	}

	@Override
	public void saveObjectById(Integer logId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<SystemLog> getSystemLogs() {
		// TODO Auto-generated method stub
		return systemLogDao.findAll();
	}


	@Override
	public List<SystemLog> getSyetemLogsByTime(String startDate, String endDate) throws ParseException {
		
		DateFormat fmt =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		Date startdate = fmt.parse(startDate);
		Date enddate = fmt.parse(endDate);
		return systemLogDao.getSystemLogsByTime(startdate, enddate);
	}
	
}
