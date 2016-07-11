package DataAn.common.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 公共的DAO处理对象，这个对象中包含了Hibernate的所有基本操作和对SQL的操作
 * @author Snail
 *
 * @param <T>
 */
public interface IBaseDao<T> {
	/**
	 * 添加对象
	 * @param t
	 * @return
	 */
	public T add(T t);
	/**
	 * 更新对象
	 * @param t
	 */
	public void update(T t);
	/**
	 * 删除对象
	 * @param t
	 */
	public void delete(T t);
	/**
	 * 根据id删除对象
	 * @param id
	 */
	public void delete(Serializable id);
	/**
	 * 根据id加载对象
	 * @param id
	 * @return
	 */
	public T load(Serializable id);	
	/**
	 * 根据id加载对象
	 * @param id
	 * @return
	 */
	public T get(Serializable id);
	
	/**
	 * 根据hql语句，查询返回查询列表
	 * @param hql 
	 * @param args 查询参数
	 * @return
	 */
	public List<T> list(String hql, Object[] args);
	
	public List<T> list(String hql, Object arg);
	
	public List<T> list(String hql);
	
	/**
	 * 根据hql语句，查询返回查询列表;
	 * @param hql
	 * @param args
	 * @param alias:参数别名
	 * @return
	 */
	public List<T> list(String hql, Object[] args, Map<String, Object> alias);
	
	/**
	 * 按照分页返回查询列表
	 * @param hql
	 * @param args
	 * @return
	 */
	public Pager<T> find(String hql, Object[] args);
	
	public Pager<T> find(String hql, Object arg);
	
	public Pager<T> find(String hql);
	
	public Pager<T> find(String hql, Object[] args, Map<String, Object> alias);
	
	public Pager<T> findByAlias(String hql, Map<String, Object> alias);
	/**
	 * 查询表所有数据;
	 * @return
	 */
	public abstract List<T> findAll();
	/**
	 * 根据列名，查询返回查询列表;
	 * @param propertyName 列名
	 * @param value 列值
	 * @return
	 */
	public abstract List<T> findByParam(String propertyName, Object value);
	
	public abstract List<T> findByParam(String propertyName, Object value, String order);
	
	public abstract long getTotalCount(String propertyName, Object value);
	
}

