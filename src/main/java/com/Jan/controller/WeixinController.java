package com.Jan.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.fluent.Request;
import org.apache.http.entity.ContentType;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.hibernate.SessionFactory;
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
import com.Jan.utils.AccessTokenHelper;
import com.Jan.utils.HttpRequest;
import com.alibaba.fastjson.JSON;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/wx")
public class WeixinController {

	@Resource
	public SessionFactory sessionFactory;

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

	@RequestMapping(value = "/verification", method = RequestMethod.GET)
	@ResponseBody
	public String ret(String signature, String timestamp, String nonce, String echostr) {
		UserToken userToken = (UserToken) sessionFactory.getCurrentSession().createQuery("from UserToken")
				.uniqueResult();
		String token = userToken.getAccessToken();

		if (StringUtils.isEmpty(token) || StringUtils.isEmpty(timestamp) || StringUtils.isEmpty(nonce)) {
			return "error";
		} else {
			String[] sha = { token, timestamp, nonce };
			Arrays.sort(sha);
			String sortStr = sha[0] + sha[1] + sha[2];
			if (DigestUtils.sha1Hex(sortStr).equals(signature)) {
				return echostr;
			} else {
				return "error";
			}
		}
	}

	@RequestMapping(value = "/verification", method = RequestMethod.POST)
	@ResponseBody
	public String ret1(HttpServletRequest req, HttpServletResponse res) throws DocumentException, IOException {
		// ServletInputStream buf = req.getInputStream();
		// BufferedReader buf = new BufferedReader(new InputStreamReader(
		// req.getInputStream()));
		// StringBuffer str = new StringBuffer("");
		// String s = "";
		// while ((s = buf.readLine()) != null) {
		// str = str.append(s);
		// }

		// String str1=IOUtils.toString(req.getInputStream(),"utf-8");
		Map map = new HashMap();
		SAXReader saxReader = new SAXReader();
		Document document = saxReader.read(req.getInputStream());
		Element element = document.getRootElement();
		List<Element> list = element.elements();
		for (Element e : list) {
			map.put(e.getName(), e.getText());
		}
		System.out.println(JSON.toJSONString(map));

		// PrintWriter out = res.getWriter();
		String text = "<xml><ToUserName><![CDATA[" + map.get("FromUserName") + "]]></ToUserName>"
				+ "<FromUserName><![CDATA[" + map.get("ToUserName") + "]]></FromUserName>" + "<CreateTime>"
				+ new Date().getTime() + "</CreateTime>" + "<MsgType><![CDATA[text]]></MsgType>" + "<Content><![CDATA["
				+ JSON.toJSONString(map) + "]]></Content>" + "</xml>";
		return text;
	}

	@RequestMapping("/create_menu")
	@ResponseBody
	public String createMenu(HttpServletRequest req, HttpServletResponse res) throws IOException {
		UserToken userToken = (UserToken) sessionFactory.getCurrentSession().createQuery("from UserToken")
				.uniqueResult();
		String token = userToken.getAccessToken();
		System.out.println(token);
		return HttpRequest.postBody("https://api.weixin.qq.com/cgi-bin/menu/create?access_token=" + token,
				req.getInputStream());
	}

	@RequestMapping("/get_menu")
	@ResponseBody
	public String getMenu() {
		UserToken userToken = (UserToken) sessionFactory.getCurrentSession().createQuery("from UserToken")
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

//	@ResponseBody
//	@RequestMapping(value = "/upload", method = RequestMethod.POST)
//	public String uploadfiles(String imgString, String path) {
//		SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd/HHmmss");
//		String timeString = "";
//		System.out.println(timeString = format.format(new Date()));
//		File file = new File("/upload/" + format.format(new Date()) + "/");
//		if (!file.exists()) {
//			file.mkdirs();
//		}
//
//		try {
//			// ctrl + alt + T 自动生成try catch 模块
//			byte[] bytes = new BASE64Decoder().decodeBuffer(imgString);
//			OutputStream out = new FileOutputStream(new File(file, path));
//			for (int t = 0; t < bytes.length; t++) {
//				if (bytes[t] < 0) {
//					bytes[t] += 256;
//				}
//			}
//			out.write(bytes);
//		} catch (IOException e) {
//			e.printStackTrace();
//			return "faild";
//		}
//		return "success";
//	}

	@RequestMapping("/test_arr")
	@ResponseBody
	public String testArr(String tags) {
		System.out.print(tags);
		return "success";
	}

	@ResponseBody
	@RequestMapping(value = "/test_success")
	public String testSuccess(String str) {
		System.out.print("--------------");
		System.out.println(JSON.toJSONString(str));
		return JSON.toJSONString(str);
	}

	@ResponseBody
	@RequestMapping(value = "/test_array")
	public String testArray(@RequestParam(value = "str[]") String[] str) {
		for (int t = 0; t < str.length; t++)
			System.out.println(str[t]);
		return "success";
	}
}
