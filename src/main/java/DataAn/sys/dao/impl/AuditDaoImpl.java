package DataAn.sys.dao.impl;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import DataAn.common.dao.BaseDaoImpl;
import DataAn.common.dao.Pager;
import DataAn.common.utils.DateUtil;
import DataAn.sys.dao.IAuditDao;
import DataAn.sys.domain.Audit;

@Repository
public class AuditDaoImpl extends BaseDaoImpl<Audit>
implements IAuditDao{

	@SuppressWarnings("unchecked")
	@Override
	public Pager<Audit> selectByOption(int pageIndex, int pageSize,
			String userName, String content, String operationTimeStart,String operationTimeEnd, String order) {
		String hql = "from Log log where 1=1";
		String countHql = "select count(*) from Log log where 1=1";
		if(StringUtils.isNotBlank(userName)){
			hql += " and log.userName like :userName";
			countHql += " and log.userName like :userName";
		}
		if(StringUtils.isNotBlank(content)){
			hql += " and log.content like :content";
			countHql += " and log.content like :content";
		}
		if(StringUtils.isNotBlank(operationTimeStart)){
			hql += " and u.createDate<=:operationTimeStart";
			countHql += " and u.createDate<=:operationTimeStart";
		}
		if(StringUtils.isNotBlank(operationTimeEnd)){
			hql += " and u.updateDate>=:operationTimeEnd";
			countHql += " and u.updateDate>=:operationTimeEnd";
		}
		
		if(StringUtils.isNotBlank(order)){
			hql += " order by " + order;
		}
		Query query = this.getSession().createQuery(hql);
		Query countQuery = this.getSession().createQuery(countHql);
		if(StringUtils.isNotBlank(userName)){
			userName = "%" + userName + "%";
			query.setParameter("userName", userName);
			countQuery.setParameter("userName", userName);
		}
		if(StringUtils.isNotBlank(content)){
			content = "%" + content + "%";
			query.setParameter("content", content);
			countQuery.setParameter("content", content);
		}
		if(StringUtils.isNotBlank(operationTimeStart)){
			Date createDate = DateUtil.format(operationTimeStart);
			query.setParameter("operationTimeStart", createDate);
			countQuery.setParameter("operationTimeStart", createDate);
		}
		if(StringUtils.isNotBlank(operationTimeEnd)){
			Date updateDate = DateUtil.format(operationTimeEnd);
			query.setParameter("operationTimeEnd", updateDate);
			countQuery.setParameter("operationTimeEnd", updateDate);
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
		Pager<Audit> pager = new Pager<Audit>(pageIndex, pageSize, totalCount, query.list());
		return pager;
	}
}
