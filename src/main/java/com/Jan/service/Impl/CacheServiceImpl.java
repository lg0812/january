package com.Jan.service.Impl;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Jan.may.GoodsTag;
import com.Jan.service.CacheService;

@Service
@Transactional
public class CacheServiceImpl implements CacheService {
	@Resource
	public SessionFactory sessionFactory;

	@Override
	public String testStr() {
		// TODO Auto-generated method stub
		return "success";
	}

	@Override
	public GoodsTag getGoodsTags() {
		// TODO Auto-generated method stub
		return sessionFactory.getCurrentSession().createQuery("from GoodsTag where id = 1", GoodsTag.class)
				.setCacheable(true).uniqueResult();
	}

}
