package DataAn.sys.service.impl;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import DataAn.common.utils.GetIpUtil;
import DataAn.sys.dao.SystemLogDao;
import DataAn.sys.domain.SystemLog;
import DataAn.sys.domain.User;
import DataAn.sys.dto.SystemLogDto;
import DataAn.sys.service.SystemLogService;

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
	@Override
	public List<SystemLog> getSyetemLogsByTimeAndkeyWord(String startDate,
			String endDate, String keyWord) throws ParseException {
		DateFormat fmt =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		Date startdate = fmt.parse(startDate);
		Date enddate = fmt.parse(endDate);
		return systemLogDao.getSystemLogsBykeyWord(startdate,enddate,keyWord);
	}

	@Override
	public void addOneSystemlogs(HttpServletRequest request,
			String operateJob) {
		HttpSession session = request.getSession();
		String ip="";
		try {
			ip = GetIpUtil.getIpAddress(request);
		} catch (IOException e) {
			e.printStackTrace();
		}
		User user = (User)session.getAttribute("user");
		String username = user.getUserName();
		Date currenttime =new Date();
		
		SystemLog slog = new SystemLog();
		slog.setUserName(username);
		slog.setLoginIp(ip);
		slog.setOperateTime(currenttime);
		slog.setOperateJob(operateJob);
		systemLogDao.add(slog);
	}

	@Override
	public void deleteSystemlogs() {
		// TODO Auto-generated method stub
		
	}



	
}
