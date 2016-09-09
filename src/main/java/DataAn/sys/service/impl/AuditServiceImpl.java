package DataAn.sys.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import DataAn.common.dao.Pager;
import DataAn.common.utils.DateUtil;
import DataAn.sys.dao.ILogDao;
import DataAn.sys.dao.IUserDao;
import DataAn.sys.domain.Log;
import DataAn.sys.dto.LogDto;
import DataAn.sys.service.IAuditService;



@Service("auditService")
public class AuditServiceImpl implements IAuditService{

	@Autowired
	private ILogDao logDao;
	@Autowired
	private IUserDao userDao;
	@Override
	public void saveLog(LogDto logModel) {
		
		Log log = new Log();
		log.setUserName(logModel.getUserName());
		log.setCreateDate(new Date());
		log.setContent(logModel.getContent());
		logDao.add(log);
	}
	@Override
	public Pager<LogDto> getLogList(int pageIndex, int pageSize, String userName,
			String content, String operationTimeStart, String operationTimeEnd) {
		List<LogDto> logModelList = new ArrayList<LogDto>();
		Pager<Log> logPager = logDao.selectByOption(pageIndex, pageSize, userName, content, operationTimeStart, operationTimeEnd, "createDate");
		List<Log> logList = logPager.getDatas();
		if(logList != null && logList.size() > 0){
			for (Log log : logList) {
				logModelList.add(this.pojoToDto(log));
			}
		}
		Pager<LogDto> pager = new Pager<LogDto>(pageIndex, pageSize, logPager.getTotalCount(), logModelList);
		return pager;			
	}
	
	private LogDto pojoToDto(Log log){
		LogDto logModel = new LogDto();
		logModel.setUserName(log.getUserName());
		logModel.setContent(log.getContent());
		logModel.setCreateDate(DateUtil.format(log.getCreateDate()));
		return logModel;
	}
}
