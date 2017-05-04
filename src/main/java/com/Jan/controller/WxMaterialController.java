package com.Jan.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.fluent.Request;
import org.apache.http.entity.FileEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.Test;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.Jan.constant.BaseResp;
import com.Jan.utils.HttpRequest;
import com.Jan.wx.RequestUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

@Controller
@Transactional
@RequestMapping("/material")
public class WxMaterialController {
	public static String ACCESS_TOKEN = "qj_900LNPtpApNZTJJlgKFUFJMmJUYBQNYmfxWTHm_SFuQiDNbJVhZsp3eI3sw96tPO1qiTikLfe3zeKfFJ6I0f2amfR2Zwkrqc3_XkI5okPMVeAFAWTC";

	@ResponseBody
	@RequestMapping("/get_material_list")
	public BaseResp get_material_list() {
		get_list();
		return null;

	}

	@Test
	public void get_total() {
		Map map = new HashMap<String, String>();
		map.put("access_token", ACCESS_TOKEN);
		String result = RequestUtils.getRequest("https://api.weixin.qq.com/cgi-bin/material/get_materialcount", map);
	}

	@Test
	public void getByMediaId() {
		Map map = new HashMap<String, String>();
		map.put("access_token", ACCESS_TOKEN);
		map.put("media_id", "79Dc9zX7gYj0VamVztpEsssBxc2jc_8USiWTDSJrb8nNraKhBSKcikFxGJVG_S4w");
		String result = RequestUtils.getRequest("https://api.weixin.qq.com/cgi-bin/media/get", map);

	}

	@Test
	public void get_list() {
		Map map = new HashMap<String, String>();
		Map map1 = new HashMap<String, String>();

		map.put("access_token", ACCESS_TOKEN);
		map1.put("type", "image");
		map1.put("offset", "0");
		map1.put("count", "20");
		RequestUtils.postRequest("https://api.weixin.qq.com/cgi-bin/material/batchget_material", map,
				JSON.toJSONString(map1));
	}

	@Test
	public void upload() {
		Map map = new HashMap<String, String>();
		map.put("access_token", ACCESS_TOKEN);
		try {
			HttpEntity entity = MultipartEntityBuilder.create().addBinaryBody("media", new File("D:/dongman.jpg"))
					.build();
			Request post = Request.Post(
					"https://api.weixin.qq.com/cgi-bin/media/upload?access_token=" + ACCESS_TOKEN + "&type=image");
			post.body(entity);

			System.out.println(EntityUtils.toString(post.execute().returnResponse().getEntity()));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void upload_long() {
		Map map = new HashMap<String, String>();
		map.put("access_token", ACCESS_TOKEN);
		try {
			HttpEntity entity = MultipartEntityBuilder.create().addBinaryBody("media", new File("D:/dongman.jpg")).addTextBody("type", "image")
					.build();
			Request post = Request.Post(
					"https://api.weixin.qq.com/cgi-bin/material/add_material?access_token=" + ACCESS_TOKEN );
			post.body(entity);

			System.out.println(EntityUtils.toString(post.execute().returnResponse().getEntity()));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
