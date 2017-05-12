package com.Jan.controller;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.Jan.constant.ApiConsts;
import com.Jan.constant.BaseResp;
import com.Jan.model.User;
import com.Jan.model.UserToken;
import com.Jan.service.LoginService;
import com.Jan.service.WxService;

@Controller
@RequestMapping("/login")
public class LoginController {
	@Autowired
	public LoginService loginService;

	@RequestMapping(value = "/login_in", method = RequestMethod.POST)
	@ResponseBody
	public BaseResp login_in(String email, String password) {
		BaseResp baseResp = new BaseResp();
		if (StringUtils.isEmpty(email) || StringUtils.isEmpty(password)) {
			baseResp.setCode(ApiConsts.PARAMS_ERROR);
			baseResp.setResult(null);
			baseResp.setMessage("email or password is null");
		} else {
			baseResp.setResult(loginService.login(email, password, baseResp));
		}
		return baseResp;
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	@ResponseBody
	public BaseResp register(String username, String password, String email, String verification) {
		BaseResp baseResp = new BaseResp();
		if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password) || StringUtils.isEmpty(email)
				|| StringUtils.isEmpty(verification)) {
			baseResp.setCode(ApiConsts.PARAMS_ERROR);
			baseResp.setResult(null);
			baseResp.setMessage("one or more args is null");
		} else {
			if (!loginService.register(username, password, email, verification, baseResp))
				baseResp.setResult(false);
		}
		return baseResp;
	}

	@RequestMapping(value = "/reset_pw", method = RequestMethod.POST)
	@ResponseBody
	public BaseResp reset_pw(String password, String email, String verification) {
		BaseResp baseResp = new BaseResp();
		if (StringUtils.isEmpty(password) || StringUtils.isEmpty(email) || StringUtils.isEmpty(verification)) {
			baseResp.setCode(ApiConsts.PARAMS_ERROR);
			baseResp.setResult(null);
			baseResp.setMessage("one or more args is null");
		} else {
			if (!loginService.resetPW(password, email, verification, baseResp))
				baseResp.setResult(false);
		}
		return baseResp;
	}

	@RequestMapping(value = "/send_email", method = RequestMethod.POST)
	@ResponseBody
	public BaseResp send_email(String email) {
		BaseResp baseResp = new BaseResp();
		if (StringUtils.isEmpty(email)) {
			baseResp.setCode(ApiConsts.PARAMS_ERROR);
			baseResp.setResult(null);
			baseResp.setMessage("email is null");
		} else {
			baseResp.setCode(ApiConsts.OK);
			baseResp.setResult(loginService.sendMailSave(email));
			baseResp.setCode(ApiConsts.OK);
		}
		return baseResp;
	}
}
