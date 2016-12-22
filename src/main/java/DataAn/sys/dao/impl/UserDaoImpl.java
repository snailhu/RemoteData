package DataAn.sys.dao.impl;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import DataAn.common.dao.BaseDaoImpl;
import DataAn.common.dao.Pager;
import DataAn.common.utils.DateUtil;
import DataAn.sys.dao.IUserDao;
import DataAn.sys.domain.User;


@Repository
public class UserDaoImpl extends BaseDaoImpl<User> implements IUserDao {

	
	@Override
	public void deleteByUserIds(List<Long> userIds) {
		String hql = "delete from User u where u.userId in (:userIds)";
		this.getSession().createQuery(hql).setParameterList("userIds", userIds).executeUpdate();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public User getUserByName(String username) {
		String hql = "from User us where us.userName = ?";
		List<User> list = this.getSession().createQuery(hql).setParameter(0, username).list();
		if(list != null && list.size() > 0){
			return list.get(0);			
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Pager<User> selectByOption(int pageIndex, int pageSize,
			String userName, String createdateStart, String createdateEnd,
			String updatedateStart, String updatedateEnd, Set<Long> userIds,
			String order) {
		String hql = "from User u where 1=1";
		String countHql = "select count(*) from User u where 1=1";
		if(StringUtils.isNotBlank(userName)){
			hql += " and u.userName like :userName";
			countHql += " and u.userName like :userName";
		}
		if(StringUtils.isNotBlank(createdateStart)){
			hql += " and u.createDate>=:createdateStart";
			countHql += " and u.createDate>=:createdateStart";
		}
		if(StringUtils.isNotBlank(createdateEnd)){
			hql += " and u.createDate<=:createdateEnd";
			countHql += " and u.createDate<=:createdateEnd";
		}
		if(StringUtils.isNotBlank(updatedateStart)){
			hql += " and u.updateDate>=:updatedateStart";
			countHql += " and u.updateDate>=:updatedateStart";
		}
		if(StringUtils.isNotBlank(updatedateEnd)){
			hql += " and u.updateDate>=:updatedateEnd";
			countHql += " and u.updateDate>=:updatedateEnd";
		}
		if(userIds != null && userIds.size() > 0){
			hql += " and u.userId in (:userIds)";
			countHql += " and u.userId in (:userIds)";
		}
		if(StringUtils.isNotBlank(order)){
			hql += " order by u." + order;
		}else{
			hql += " order by u.createDate desc";
		}
		
		Query query = this.getSession().createQuery(hql);
		Query countQuery = this.getSession().createQuery(countHql);
		if(StringUtils.isNotBlank(userName)){
			userName = "%" + userName + "%";
			query.setParameter("userName", userName);
			countQuery.setParameter("userName", userName);
		}
		if(StringUtils.isNotBlank(createdateStart)){
			Date createDate = DateUtil.format(createdateStart);
			query.setParameter("createdateStart", createDate);
			countQuery.setParameter("createdateStart", createDate);
		}
		if(StringUtils.isNotBlank(createdateEnd)){
			Date createDate = DateUtil.format(createdateEnd);
			query.setParameter("createdateEnd", createDate);
			countQuery.setParameter("createdateEnd", createDate);
		}
		if(StringUtils.isNotBlank(updatedateStart)){
			Date updateDate = DateUtil.format(updatedateStart);
			query.setParameter("updatedateStart", updateDate);
			countQuery.setParameter("updatedateStart", updateDate);
		}
		if(StringUtils.isNotBlank(updatedateEnd)){
			Date updateDate = DateUtil.format(updatedateEnd);
			query.setParameter("updatedateEnd", updateDate);
			countQuery.setParameter("updatedateEnd", updateDate);
		}
		if(userIds != null && userIds.size() > 0){
			query.setParameterList("userIds", userIds);
			countQuery.setParameterList("userIds", userIds);
		}
		Long totalCount = 0l;
		Object obj = countQuery.uniqueResult();
		if(obj != null){
			totalCount = (Long) obj;
		}
		//设置每页显示多少个，设置最大结果
		query.setMaxResults(pageSize);
		//设置起点
		query.setFirstResult(pageSize * (pageIndex - 1));
		Pager<User> pager = new Pager<User>(pageIndex, pageSize, totalCount, query.list());
		return pager;
	}

}
