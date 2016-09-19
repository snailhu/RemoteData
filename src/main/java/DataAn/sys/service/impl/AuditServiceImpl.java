package DataAn.sys.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import DataAn.common.dao.Pager;
import DataAn.common.utils.DateUtil;
import DataAn.sys.dao.IAuditDao;
import DataAn.sys.dao.IUserDao;
import DataAn.sys.domain.Audit;
import DataAn.sys.dto.AuditIdDto;
import DataAn.sys.service.IAuditService;



@Service("auditService")
public class AuditServiceImpl implements IAuditService{

	@Autowired
	private IAuditDao auditDao;
	@Autowired
	private IUserDao userDao;
	
	@Override
	@Transactional
	public void saveLog(AuditIdDto auditDto) {
		Audit log = new Audit();
		log.setUserName(auditDto.getUserName());
		log.setContent(auditDto.getContent());
		log.setUserIp(auditDto.getUserIp());
		auditDao.add(log);
	}
	@Override
	public Pager<AuditIdDto> getLogList(int pageIndex, int pageSize, String userName,
			String content, String operationTimeStart, String operationTimeEnd) {
		List<AuditIdDto> auditDtoList = new ArrayList<AuditIdDto>();
		Pager<Audit> auditPager = auditDao.selectByOption(pageIndex, pageSize, userName, content, operationTimeStart, operationTimeEnd, "createDate");
		List<Audit> auditList = auditPager.getDatas();
		if(auditList != null && auditList.size() > 0){
			for (Audit audit : auditList) {
				auditDtoList.add(this.pojoToDto(audit));
			}
		}
		Pager<AuditIdDto> pager = new Pager<AuditIdDto>(pageIndex, pageSize, auditPager.getTotalCount(), auditDtoList);
		return pager;			
	}
	
	private AuditIdDto pojoToDto(Audit log){
		AuditIdDto auditDto = new AuditIdDto();
		auditDto.setUserName(log.getUserName());
		auditDto.setContent(log.getContent());
		auditDto.setCreateDate(DateUtil.format(log.getCreateDate()));
		auditDto.setUserIp(log.getUserIp());
		return auditDto;
	}
}
