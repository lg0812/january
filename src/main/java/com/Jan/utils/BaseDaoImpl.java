package com.Jan.utils;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class BaseDaoImpl<T> extends HibernateDaoSupport implements BaseDao<T> {

    @Resource
    public SessionFactory sessionFactory;

    public Serializable save(T o) {
        // TODO Auto-generated method stub
        return this.sessionFactory.getCurrentSession().save(o);
    }

    public void delete(T o) {
        // TODO Auto-generated method stub
        this.sessionFactory.getCurrentSession().delete(o);
    }

    public void update(T o) {
        // TODO Auto-generated method stub
        this.sessionFactory.getCurrentSession().update(o);
    }

    public void saveOrUpdate(T o) {
        // TODO Auto-generated method stub
        this.sessionFactory.getCurrentSession().saveOrUpdate(o);
    }

    public List<T> find(String hql) {
        // TODO Auto-generated method stub
        return this.sessionFactory.getCurrentSession().createQuery(hql).list();
    }

    public T get(Class<T> c, Serializable id) {
        // TODO Auto-generated method stub
        return (T) this.sessionFactory.getCurrentSession().get(c, id);
    }

    public Integer executeHql(String hql) {
        // TODO Auto-generated method stub
        return this.sessionFactory.getCurrentSession().createQuery(hql).executeUpdate();
    }

    public List<T> find(String hql, Map<String, Object> params) {
        // TODO Auto-generated method stub
        Query query = this.sessionFactory.getCurrentSession().createQuery(hql);
        if (params != null && !params.isEmpty()) {
            for (String key : params.keySet()) {
                query.setParameter(key, params.get(key));
            }
        }
        return query.list();
    }

    // ��ҳ��ѯ
    public List<T> find(String hql, Map<String, Object> params, Integer page, Integer rows) {
        // TODO Auto-generated method stub
        if (page == null || page < 1) {
            page = 1;
        }
        if (rows == null || rows < 1) {
            rows = 10;
        }
        Query query = this.sessionFactory.getCurrentSession().createQuery(hql);
        if (params != null && !params.isEmpty()) {
            for (String key : params.keySet()) {
                query.setParameter(key, params.get(key));
            }
        }
        return query.setFirstResult((page - 1) * rows).setMaxResults(rows).list();
    }

    public T get(String hql, Map<String, Object> param) {
        // TODO Auto-generated method stub
        List<T> list = this.find(hql, param);
        if (list != null && list.size() > 0) {
            return list.get(0);
        } else {
            return null;
        }
    }

    public List<T> findBySQL(String sql) {
        // TODO Auto-generated method stub
        return this.sessionFactory.getCurrentSession().createSQLQuery(sql).list();
    }

}
