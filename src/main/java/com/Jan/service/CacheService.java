package com.Jan.service;

import com.Jan.may.GoodsTag;

public interface CacheService {
	public String testStr();

	/**
	 * @see com.Jan.service.CacheService#getGoodsTags() 默认情况下二级缓存只会对load get
	 *      之类的方法缓存， 想list iterator 之类的方法也使用缓存需要使用.setCacheable(true) 如下所示:
	 * 
	 *      return sessionFactory.getCurrentSession().createQuery("from GoodsTag
	 *      where id = 1", GoodsTag.class) .setCacheable(true).uniqueResult();
	 */
	public GoodsTag getGoodsTags();
}
