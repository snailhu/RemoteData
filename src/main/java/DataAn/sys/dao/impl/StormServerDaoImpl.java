package DataAn.sys.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import DataAn.common.dao.BaseDaoImpl;
import DataAn.common.dao.Pager;
import DataAn.sys.dao.IStormServerDao;
import DataAn.sys.domain.StormServer;

@Repository
public class StormServerDaoImpl extends BaseDaoImpl<StormServer>
implements IStormServerDao{

	@SuppressWarnings("unchecked")
	@Override
	public Pager<StormServer> selectByPager(int pageIndex, int pageSize) {
		String hql = "from StormServer order by createDate desc";
		String countHQL = "select count(*) from StormServer";
		List<StormServer> list = this.getSession().createQuery(hql)
										   .setMaxResults(pageSize)
										   .setFirstResult(pageSize * (pageIndex -1)).list();
		Long totalCount = 0l; 
		Object obj = this.getSession().createQuery(countHQL).uniqueResult();
		if(obj != null){
			totalCount = (Long) obj;
		}
		Pager<StormServer> pager = new Pager<StormServer>(pageIndex,pageSize,totalCount,list);
		return pager;
	}

}
