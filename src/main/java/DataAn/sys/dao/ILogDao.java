package DataAn.sys.dao;


import DataAn.common.dao.IBaseDao;
import DataAn.common.dao.Pager;
import DataAn.sys.domain.Log;

public interface ILogDao extends IBaseDao<Log>{

	public Pager<Log> selectByOption(int pageIndex, int pageSize, String userName,
			String content, String operationTimeStart, String operationTimeEnd,String order);
}
