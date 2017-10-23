package com.Jan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.Jan.may.GoodsTag;
import com.Jan.service.CacheService;

@Controller
@RequestMapping(value = "/cache")
public class CacheController {
	@Autowired
	public CacheService cacheService;

	@RequestMapping(value = "/getGoodsTag", method = RequestMethod.GET)
	@ResponseBody
	public GoodsTag getGoodsTag() {
		return cacheService.getGoodsTags();
	}
}
