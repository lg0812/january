package com.Jan.service.Impl;

import javax.annotation.Resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Jan.may.Enshrine;
import com.Jan.may.GoodsInfo;
import com.Jan.may.Recommend;
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
		GoodsInfo goodsInfo = sessionFactory.getCurrentSession()
				.createQuery(String.format("from GoodsInfo where id = %d", goodsId), GoodsInfo.class).uniqueResult();
		System.out.println(JSON.toJSONString(goodsInfo));
		for (Recommend r : goodsInfo.getRecommend()) {
			GoodsInfo g = sessionFactory.getCurrentSession()
					.createQuery(String.format(
							"select new GoodsInfo(g.id,g.goodsName, g.goodsLogoPath) from GoodsInfo g where id = %d",
							r.getRecommendGoodsId()), GoodsInfo.class)
					.uniqueResult();
			if (g != null)
				goodsInfo.getRecommendList().add(g);
		}
		// log.info(JSON.toJSONString(goodsInfo));
		return goodsInfo;
	}

	@Override
	public boolean enshrine_add(String userId, Long goodsId) {
		// TODO Auto-generated method stub

		Enshrine enshrine = new Enshrine();
		enshrine.setCreator(userId);
		enshrine.setGoods_id(goodsId);
		return false;
	}

}
