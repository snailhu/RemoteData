package DataAn.galaxyManager.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import DataAn.common.dao.BaseDaoImpl;
import DataAn.common.dao.Pager;
import DataAn.galaxyManager.dao.IParameterDao;
import DataAn.galaxyManager.domain.Parameter;
import DataAn.sys.domain.Role;

@Repository
public class ParameterDaoImpl extends BaseDaoImpl<Parameter>
implements IParameterDao{

	@SuppressWarnings("unchecked")
	@Override
	public Pager<Parameter> selectByPager(int pageIndex, int pageSize) {
		
		String hql = "from Parameter";
		String countHQL = "select count(*) from Parameter";
		List<Parameter> list = this.getSession().createQuery(hql)
										   .setMaxResults(pageSize)
										   .setFirstResult(pageSize * (pageIndex -1)).list();
		Long totalCount = 0l; 
		Object obj = this.getSession().createQuery(countHQL).uniqueResult();
		if(obj != null){
			totalCount = (Long) obj;
		}
		Pager<Parameter> pager = new Pager<Parameter>(pageIndex,pageSize,totalCount,list);
		return pager;
	}

}
