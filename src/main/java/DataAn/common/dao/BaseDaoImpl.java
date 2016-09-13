package DataAn.common.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

@Transactional
public class BaseDaoImpl<T> implements IBaseDao<T> {
	
	@Resource
	private SessionFactory sessionFactory;
		
	private Class<?> clz;
	
	public BaseDaoImpl(){		
		clz= ((Class<?>)
				(((ParameterizedType)(this.getClass().getGenericSuperclass())).getActualTypeArguments()[0]));
	}	
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	protected Session getSession() {  		
		Session session = null;
		session = sessionFactory.getCurrentSession();
		if(session == null){
			session = sessionFactory.openSession();
		}
		return session;
	} 
	
	@Override
	public T add(T t) {
		getSession().save(t);
		return t;
	}

	@Override
	public void add(List<T> list) {
		for (T t : list) {
			getSession().save(t);
		}
	}
	
	@Override
	public void update(T t) {
		getSession().update(t);
		
	}

	@Override
	public void delete(T t) {
		getSession().delete(t);
	}
	
	@Override
	public void delete(Serializable id) {
//		Session session = getSession();
//		session.delete(session.load(clz, id));
		getSession().delete(this.load(id));
	}
	
	@Override
	public T load(Serializable id) {
		return (T)getSession().load(clz, id);
	}
	
	@Override
	public T get(Serializable id){
		return (T)getSession().get(clz, id);
	}
	
	@Override
	public List<T> list(String hql, Object[] args) {
		return this.list(hql, args, null);
	}

	@Override
	public List<T> list(String hql, Object arg) {
		return this.list(hql, new Object[]{arg});
	}

	@Override
	public List<T> list(String hql) {
		return this.list(hql,null);
	}
	
	private String initSort(String hql) {
		
		String order = SystemContext.getOrder();
		String sort = SystemContext.getSort();
		if(sort!=null&&!"".equals(sort.trim())) {
			hql+=" order by "+sort;
			if(!"desc".equals(order)) hql+=" asc";
			else hql+=" desc";
		}
		return hql;
	}
	
	private void setAliasParameter(Query query,Map<String,Object> alias) {
		if(alias!=null) {
			Set<String> keys = alias.keySet();
			for(String key:keys) {
				Object val = alias.get(key);
				if(val instanceof Collection) {
					//查询条件是列表
					query.setParameterList(key, (Collection<?>)val);
				} else {
					query.setParameter(key, val);
				}
			}
		}
	}
	
	private void setParameter(Query query,Object[] args) {
		if(args!=null&&args.length>0) {
			int index = 0;
			for(Object arg:args) {
				query.setParameter(index++, arg);
			}
		}
	}
	
	@Override
	public List<T> list(String hql, Object[] args, Map<String, Object> alias) {
		hql = initSort(hql);
		Query query = getSession().createQuery(hql);
		setAliasParameter(query, alias);
		setParameter(query, args);
		return query.list();
	}
	
	@Override
	public Pager<T> find(String hql, Object[] args) {
		return this.find(hql, args, null);
	}

	@Override
	public Pager<T> find(String hql, Object arg) {
		return this.find(hql, new Object[]{arg});
	}

	@Override
	public Pager<T> find(String hql) {
		return this.find(hql,null);
	}
	
	@SuppressWarnings("rawtypes")
	private void setPagers(Query query,Pager pages) {
		Integer pageSize = SystemContext.getPageSize();
		Integer pageOffset = SystemContext.getPageOffset();
		if(pageOffset==null||pageOffset<0) pageOffset = 0;
		if(pageSize==null||pageSize<0) pageSize = 15;
		pages.setPageIndex(pageOffset);
		pages.setPageSize(pageSize);
		query.setFirstResult(pageOffset).setMaxResults(pageSize);
	}
	
	private String getCountHql(String hql,boolean isHql) {
		String e = hql.substring(hql.indexOf("from"));
		String c = "select count(*) "+e;
		if(isHql)
			c = c.replaceAll("fetch", "");
		return c;
	}

	@Override
	public Pager<T> find(String hql, Object[] args, Map<String, Object> alias) {
		hql = initSort(hql);
		String cq = getCountHql(hql,true);
		Query cquery = getSession().createQuery(cq);
		Query query = getSession().createQuery(hql);
		//设置别名参数
		setAliasParameter(query, alias);
		setAliasParameter(cquery, alias);
		//设置参数
		setParameter(query, args);
		setParameter(cquery, args);
		Pager<T> pages = new Pager<T>();
		setPagers(query,pages);
		List<T> datas = query.list();
		pages.setDatas(datas);
		long total = (Long)cquery.uniqueResult();
		pages.setTotalCount(total);
		return pages;
	}

	@Override
	public Pager<T> findByAlias(String hql, Map<String, Object> alias) {
		return this.find(hql,null, alias);
	}

	@Override
	public List<T> findAll() {
		String hql = "from "+ this.clz.getName();		
		return this.getSession().createQuery(hql).list();
	}

	@Override
	public List<T> findByParam(String propertyName, Object value) {
		String hql = "from " + this.clz.getName() + 
				" as model where model." + propertyName + "=?";
		return this.getSession().createQuery(hql).setParameter(0, value).list();
	}
	
	@Override
	public List<T> findByParam(String propertyName, Object value, String order) {
		String hql = "from " + this.clz.getName() + 
				" as model where model." + propertyName + "=? " + "order by " + order;
		return this.getSession().createQuery(hql).setParameter(0, value).list();
	}
	
	@Override
	public long getTotalCount(String propertyName, Object value) {
		String hql = ""; 
		if(propertyName == null || "".equals(propertyName)){
			hql = "select count (*) from " + this.clz.getName();
			return (long) this.getSession().createQuery(hql).uniqueResult();
		}else{
			hql = "select count (*) from " + this.clz.getName() + 
					" as model where model." + propertyName + "=?";
			return (long) this.getSession().createQuery(hql).setParameter(0, value).uniqueResult();
		}
	}

}
