package com.Jan.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.fluent.Request;
import org.apache.http.entity.ContentType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.dom4j.DocumentException;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.Jan.constant.ApiConsts;
import com.Jan.constant.BaseResp;
import com.Jan.constant.Constants;
import com.Jan.model.UserToken;
import com.Jan.service.WxService;
import com.Jan.utils.AccessTokenHelper;
import com.Jan.utils.HttpRequest;
import com.Jan.wx.resp.WechatResponse;
import com.alibaba.fastjson.JSON;

import net.sf.json.JSONObject;

@Controller
@Transactional
@RequestMapping("/wx")
public class WeixinController {
	static final Logger log = LogManager.getLogger(WeixinController.class);
	@Resource
	public SessionFactory sessionFactory;
	@Autowired
	public WxService wxService;

	@RequestMapping("/getuserinfo")
	@ResponseBody
	public String getUserInfo(String code) {
		BaseResp baseResp = new BaseResp();
		try {
			String result = HttpRequest.sendGet("https://api.weixin.qq.com/sns/oauth2/access_token",
					"appid=" + Constants.APP_ID + "&code=" + code + "&grant_type=authorization_code");

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
			baseResp.setCode(ApiConsts.FAIL);
			baseResp.setMessage("!");
		} else {
			String jsapi_ticket = AccessTokenHelper.getJsTicket();
			String url = reqUrl;
			Map<String, String> ret = AccessTokenHelper.sign(jsapi_ticket, url);
			ret.put("appId", "wxcdf05353b7193ab3");
			baseResp.setCode("1001");
			baseResp.setResult(ret);
			baseResp.setMessage("jsticket");
		}
		return JSON.toJSONString(baseResp);
	}

	@RequestMapping(value = "/verification", method = RequestMethod.GET)
	public void ret(String signature, String timestamp, String nonce, String echostr, HttpServletResponse resp)
			throws IOException {
		UserToken userToken = (UserToken) this.sessionFactory.getCurrentSession().createQuery("from UserToken")
				.uniqueResult();
		String token = userToken.getAccessToken();
		log.debug(token, signature, timestamp, nonce, echostr);
		PrintWriter out = resp.getWriter();
		if ((StringUtils.isEmpty(token)) || (StringUtils.isEmpty(timestamp)) || (StringUtils.isEmpty(nonce))) {
			out.write("error");
		} else {
			String[] sha = { token, timestamp, nonce };
			Arrays.sort(sha);
			String sortStr = sha[0] + sha[1] + sha[2];
			if (DigestUtils.sha1Hex(sortStr).equals(signature)) {
				out.write(echostr);
			} else {
				out.write("error");
			}
		}
	}

	@RequestMapping(value = "/verification", method = RequestMethod.POST)
	public void ret1(HttpServletRequest req, HttpServletResponse res) throws DocumentException, IOException {
		WechatResponse wr = this.wxService.dispatchMsg(this.wxService.xmlToBean(req.getInputStream()));
		OutputStream out = res.getOutputStream();
		this.wxService.beanToXml(wr, out);
	}

//	@Test
//	public void Testtt() {
//		String menu = " <xml><ToUserName><![CDATA[toUser]]></ToUserName><FromUserName><![CDATA[fromUser]]></FromUserName><CreateTime>1348831860</CreateTime><MsgType><![CDATA[text]]></MsgType><Content><![CDATA[this is a test]]></Content><MsgId>1234567890123456</MsgId></xml>";
//		WxService wx = new WxServiceImpl();
//		wx.beanToXml(wx.dispatchMsg(wx.xmlToBean(new StringReader(menu))), System.out);
//		// WechatResponse wr = wx.dispatchMsg(wx.xmlToBean(new
//		// StringReader(menu)));
//		// wx.beanToXml(wr, System.out);
//	}

	@RequestMapping("/create_menu")
	@ResponseBody
	public String createMenu(HttpServletRequest req, HttpServletResponse res) throws IOException {
		UserToken userToken = (UserToken) this.sessionFactory.getCurrentSession().createQuery("from UserToken")
				.uniqueResult();
		String token = userToken.getAccessToken();
		System.out.println(token);
		return HttpRequest.postBody("https://api.weixin.qq.com/cgi-bin/menu/create?access_token=" + token,
				req.getInputStream());
	}

	@RequestMapping("/get_menu")
	@ResponseBody
	public String getMenu() {
		UserToken userToken = (UserToken) this.sessionFactory.getCurrentSession().createQuery("from UserToken")
				.uniqueResult();
		String token = userToken.getAccessToken();
		System.out.println(token);
		String str = HttpRequest.sendGet("https://api.weixin.qq.com/cgi-bin/menu/get", "access_token=" + token);
		return str;
	}

	public static String postBody(String url, String data) throws IOException {
		HttpEntity entity = Request.Post(url)
				.bodyString(data, ContentType.create("application/x-www-form-urlencoded", "utf-8")).execute()
				.returnResponse().getEntity();
		BufferedReader buf = new BufferedReader(new InputStreamReader(entity.getContent()));

		String s = "";
		StringBuffer sb = new StringBuffer("");
		while ((s = buf.readLine()) != null) {
			sb.append(s);
		}
		System.out.println(sb);
		return sb.toString();
	}

	@RequestMapping("/test_arr")
	@ResponseBody
	public String testArr(String tags) {
		System.out.print(tags);
		return "success";
	}

	@ResponseBody
	@RequestMapping("/test_success")
	public String testSuccess(String str) {
		System.out.print("--------------" + str);
		return str;
	}

	@ResponseBody
	@RequestMapping("/test_array")
	public String testArray(@RequestParam("str[]") String[] str) {
		for (int t = 0; t < str.length; t++) {
			System.out.println(str[t]);
		}
		return "success";
	}
}