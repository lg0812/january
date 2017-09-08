package com.Jan.service;

import com.Jan.may.GoodsInfo;

public interface GoodsService {
	public GoodsInfo details(Long goodsId);

	public boolean enshrine_add(String userId, Long goodsId);
}
