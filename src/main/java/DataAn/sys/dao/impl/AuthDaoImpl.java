package DataAn.sys.dao.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import DataAn.common.dao.BaseDaoImpl;
import DataAn.common.dao.Pager;
import DataAn.sys.dao.IAuthDao;
import DataAn.sys.domain.Auth;

@Repository
public class AuthDaoImpl extends BaseDaoImpl<Auth>
implements IAuthDao{

	@Override
	public void deleteByAuthIds(List<Long> ids) {
		String hql = "delete from Auth a where a.authId in (:ids)";
		this.getSession().createQuery(hql).setParameterList("ids", ids).executeUpdate();
	}

	@Override
	public void deleteByParentAuthId(Long parentAuthId) {
		String hql = "delete from Auth a where a.auth.id=?";
		this.getSession().createQuery(hql).setParameter(0, parentAuthId).executeUpdate();			
	}

	@SuppressWarnings("unchecked")
	@Override
	public Auth selectByCode(String code) {
		String hql = "from Auth a where a.code=?";
		List<Auth> list = this.getSession().createQuery(hql).setParameter(0, code).list();
		if(list != null && list.size() > 0){
			return list.get(0);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Pager<Auth> selectByParentAuthIdIsNullByOrder(String order, 
			int pageIndex, int pageSize) {
		String hql = "from Auth a where a.auth.id is null";
		String countHQL = "select count(*) from Auth a where a.auth.id is null";
		if(StringUtils.isNotBlank(order)){
			hql += " order by " + order;
		}else{
			hql += " order by createDate";
		}
		List<Auth> list = this.getSession().createQuery(hql)
										   .setMaxResults(pageSize)
										   .setFirstResult(pageSize * (pageIndex -1)).list();
		
		Long totalCount = 0l; 
		Object obj = this.getSession().createQuery(countHQL).uniqueResult();
		if(obj != null){
			totalCount = (Long) obj;
		}
		Pager<Auth> pager = new Pager<Auth>(pageIndex,pageSize,totalCount,list);
		return pager;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Auth> selectByParentAuthIdByOrder(long parentAuthId, String order) {
		String o = "";
		if(StringUtils.isNotBlank(order)){
			o += " order by " + order;
		}else{
			o += " order by createDate";
		}
		if(parentAuthId == 0){
			String hql = "from Auth a where a.auth.id is null" + o;
			return this.getSession().createQuery(hql).list();	
		}else{
			String hql = "from Auth a where a.auth.id=?" + o;
			return this.getSession().createQuery(hql).setParameter(0, parentAuthId).list();				
		}
	}

}
