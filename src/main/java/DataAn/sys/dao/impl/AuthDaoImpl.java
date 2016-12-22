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
	public void deleteByParentId(Long parentId) {
		String hql = "delete from Auth a where a.auth.authId=?";
		this.getSession().createQuery(hql).setParameter(0, parentId).executeUpdate();			
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Auth> selectByAuthIds(List<Long> ids) {
		String hql = "from Auth a where a.authId in (:ids)";
		return this.getSession().createQuery(hql).setParameterList("ids", ids).list();
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
		String hql = "from Auth a where a.auth.authId is null";
		String countHQL = "select count(*) from Auth a where a.auth.authId is null";
		if(StringUtils.isNotBlank(order)){
			hql += " order by " + order + " desc";
		}else{
			hql += " order by createDate desc";
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
	public List<Auth> selectByParentIdByOrder(long parentId, String order) {
		String o = "";
		if(StringUtils.isNotBlank(order)){
			o += " order by " + order + " desc";
		}else{
			o += " order by createDate desc";
		}
		if(parentId == 0){
			String hql = "from Auth a where a.auth.authId is null" + o;
			return this.getSession().createQuery(hql).list();	
		}else{
			String hql = "from Auth a where a.auth.authId=?" + o;
			return this.getSession().createQuery(hql).setParameter(0, parentId).list();				
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Auth> selectByParentIdAndAuthName(Long parentId, String authName) {
		if(parentId == null || parentId == 0){
			String hql = "from Auth a where a.auth.authId is null and a.authName=?";
			return this.getSession().createQuery(hql).setParameter(0, authName).list();	
		}
		String hql = "from Auth a where a.auth.authId=? and a.authName=?";
		return this.getSession().createQuery(hql).setParameter(0, parentId).setParameter(1, authName).list();	
	}



}
