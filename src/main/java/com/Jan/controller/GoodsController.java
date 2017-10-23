package com.Jan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.Jan.constant.ApiConsts;
import com.Jan.constant.BaseResp;
import com.Jan.may.GoodsTag;
import com.Jan.service.CacheService;
import com.Jan.service.GoodsService;

@Controller
@RequestMapping(value = "/goods")
public class GoodsController {
	@Autowired
	public GoodsService goodsService;

	@RequestMapping(value = "/details", method = RequestMethod.POST)
	@ResponseBody
	public BaseResp details(Long goodsId) {
		BaseResp baseResp = new BaseResp();
		if (goodsId != null) {
			baseResp.setCode(ApiConsts.OK);
			baseResp.setResult(goodsService.details(goodsId));
			baseResp.setMessage("获取商品详情成功");
		} else {
			baseResp.setCode(ApiConsts.PARAMS_ERROR);
			baseResp.setResult(null);
			baseResp.setMessage("goodsId is null");
		}
		return baseResp;
	}

	@RequestMapping(value = "/enshrine_add", method = RequestMethod.POST)
	@ResponseBody
	public BaseResp enshrine_add(String userId, Long goodsId) {
		BaseResp baseResp = new BaseResp();
		if (goodsId != null && userId != null) {
			baseResp.setCode(ApiConsts.OK);
			baseResp.setResult(goodsService.enshrine_add(userId, goodsId));
			baseResp.setMessage("获取商品详情成功");
		} else {
			baseResp.setCode(ApiConsts.PARAMS_ERROR);
			baseResp.setResult(null);
			baseResp.setMessage("userId or goodsId is null");
		}
		return baseResp;
	}

}
