package com.Jan.utils;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface BaseDao<T> {
	public Serializable save(T o);

	public void delete(T o);

	public void update(T o);

	public void saveOrUpdate(T o);

	public List<T> find(String hql);

	public T get(Class<T> c, Serializable id);

	public Integer executeHql(String hql);

	List<T> find(String hql, Map<String, Object> params);

	List<T> find(String hql, Map<String, Object> params, Integer page, Integer rows);

	T get(String hql, Map<String, Object> param);

	List<T> findBySQL(String sql);
}
