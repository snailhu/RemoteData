package DataAn.sys.dao;


import DataAn.common.dao.IBaseDao;
import DataAn.common.dao.Pager;
import DataAn.sys.domain.Audit;

public interface IAuditDao extends IBaseDao<Audit>{

	public Pager<Audit> selectByOption(int pageIndex, int pageSize, String userName,
			String content, String operationTimeStart, String operationTimeEnd,String order);
}
