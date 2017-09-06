package com.Jan.service.Impl;


import javax.annotation.Resource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.Jan.may.GoodsInfo;
import com.Jan.service.GoodsService;
import com.alibaba.fastjson.JSON;

@Service
@Transactional
public class GoodsServiceImpl implements GoodsService {
	final Logger log = LogManager.getLogger(GoodsServiceImpl.class);
	@Resource
	public SessionFactory sessionFactory;

	@Override
	public GoodsInfo details(Long goodsId) {
		// TODO Auto-generated method stub
		GoodsInfo goodsInfo = (GoodsInfo) sessionFactory.getCurrentSession()
				.createQuery(String.format("from GoodsInfo where id = %d", goodsId)).uniqueResult();
		log.info(JSON.toJSONString(goodsInfo));
		return goodsInfo;
	}

}
