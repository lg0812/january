package com.Jan.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.Jan.constant.ApiConsts;
import com.Jan.constant.BaseResp;
import com.Jan.constant.Constants;
import com.Jan.utils.AccessTokenHelper;
import com.Jan.utils.HttpRequest;
import com.alibaba.fastjson.JSON;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/WeixinController")
public class WeixinController {

	@RequestMapping("/getuserinfo")
	@ResponseBody
	public String getUserInfo(String code) {
		BaseResp baseResp = new BaseResp();
		try {
			String result = HttpRequest.sendGet("https://api.weixin.qq.com/sns/oauth2/access_token",
					"appid=" + Constants.APP_ID + "&secret=" + Constants.APP_SECRET + "&code=" + code
							+ "&grant_type=authorization_code");
			JSONObject jsonObject = JSONObject.fromObject(result);
			String openId = jsonObject.getString("openid");
			String token = AccessTokenHelper.getWXAccessToken();
			String userInfoUrl = "https://api.weixin.qq.com/cgi-bin/user/info";
			String userInfo = HttpRequest.sendGet(userInfoUrl,
					"access_token=" + token + "&openid=" + openId + "&lang=zh_CN");

			JSONObject obj = JSONObject.fromObject(userInfo);

			baseResp.setCode(ApiConsts.OK);
			baseResp.setMessage("success");
			baseResp.setResult(obj);
			System.out.println("getUserInfo------------->" + JSON.toJSON(result));
		} catch (Exception e) {
			baseResp.setCode(ApiConsts.ERROR);
			baseResp.setMessage("error");
		}
		return JSON.toJSONString(baseResp);
	}

	@RequestMapping("/getJsApiTicket")
	@ResponseBody
	public String getJsApiTicket(String reqUrl) {
		BaseResp baseResp = new BaseResp();
		if (StringUtils.isEmpty(reqUrl)) {
			baseResp.setCode(ApiConsts.PARAMS_ERROR);
			baseResp.setMessage("参数错误!");
		} else {
			String jsapi_ticket = AccessTokenHelper.getJsTicket();
			String url = reqUrl;
			Map<String, String> ret = AccessTokenHelper.sign(jsapi_ticket, url);
			ret.put("appId", Constants.APP_ID);
			baseResp.setCode(ApiConsts.OK);
			baseResp.setResult(ret);
			baseResp.setMessage("jsticket");
		}
		return JSON.toJSONString(baseResp);
	}

	@RequestMapping("/verification")
	@ResponseBody
	public String ret(String echostr) {
		return echostr;
	}

}
