package DataAn.log.dao.impl;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import DataAn.common.dao.BaseDaoImpl;
import DataAn.log.dao.SystemLogDao;
import DataAn.log.domain.SystemLog;

@Repository
public class SystemLogDaoImpl extends BaseDaoImpl<SystemLog> implements
		SystemLogDao {

	@Override
	public List<SystemLog> getSystemLogsByTime(Date startDate, Date endDate) {
		// TODO Auto-generated method stub
		String hql = "from SystemLog sl where sl.loginTime between ? and ? ";
		List<SystemLog> list = this.list(hql, new Object[]{startDate,endDate});
		return list;
		
	}

}
