package DataAn.sys.dao.impl;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import DataAn.common.dao.BaseDaoImpl;
import DataAn.sys.dao.SystemLogDao;
import DataAn.sys.domain.SystemLog;

@Repository
public class SystemLogDaoImpl extends BaseDaoImpl<SystemLog> implements
		SystemLogDao {

	@Override
	public List<SystemLog> getSystemLogsByTime(Date startDate, Date endDate) {
		String hql = "from SystemLog sl where sl.operateTime between ? and ? order by sl.operateTime desc";
		List<SystemLog> list = this.list(hql, new Object[]{startDate,endDate});
		return list;
		
	}

	@Override
	public List<SystemLog> getSystemLogsBykeyWord(Date startDate, Date endDate,String keyWord) {
		if (keyWord.equals("all"))
		{
			String hql = "from SystemLog sl where sl.operateTime between ? and ? order by sl.operateTime desc";
			List<SystemLog> list = this.list(hql, new Object[]{startDate,endDate});
			return list;
		}
		
		String hql = "from SystemLog sl where sl.operateJob like ? and sl.operateTime between ? and ? order by sl.operateTime desc";
		keyWord = "%" + keyWord + "%";
		List<SystemLog> list = this.list(hql, new Object[]{keyWord,startDate,endDate});
		return list;
	}

	@Override
	public List<SystemLog> getallSystemLogs() {
		String hql = "from SystemLog sl order by sl.operateTime desc";
		List<SystemLog> list = this.list(hql);
		return list;
	}

	@Override
	public void deleteSystemlog() {
		String hql ="select count(*) from SystemLog where 1=1";
		int count = ((Number)this.getSession().createQuery(hql).uniqueResult()).intValue();
		System.out.println(count);
		
	}

}
