package com.Jan.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.Jan.constant.ApiConsts;
import com.Jan.constant.BaseResp;
import com.Jan.service.WxService;

@Controller
@RequestMapping(value = "/wxCon")
public class WxController {
	@Autowired
	public WxService wxService;

	@ResponseBody
	@RequestMapping(value = "/create_menu")
	public BaseResp creat_menu(String menu, HttpServletRequest req) throws Exception {
		System.out.println("------------>" + menu);
		System.out.println("---------->" + req.getParameter("menu"));
		BaseResp baseResp = new BaseResp();
		if (StringUtils.isEmpty(menu)) {
			baseResp.setCode(ApiConsts.FAIL);
			baseResp.setMessage("menu is null or empty!");
		} else {
			baseResp.setCode(ApiConsts.OK);
			baseResp.setResult(wxService.create_menu(menu));
		}
		return baseResp;
	}
}
