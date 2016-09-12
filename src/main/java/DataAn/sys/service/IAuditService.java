package DataAn.sys.service;

import DataAn.common.dao.Pager;
import DataAn.sys.dto.LogDto;


/**
* Title: IAuditService
* @Description: 审计日志管理
* @author  Shewp
* @date 2016年9月7日
*/
public interface IAuditService {

	public abstract void saveLog(LogDto logModel);
	
	Pager<LogDto> getLogList(int pageIndex, int pageSize, String userName, 
			String content,String operationTimeStart, String operationTimeEnd);
}
