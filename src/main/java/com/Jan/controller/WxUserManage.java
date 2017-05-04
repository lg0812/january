package com.Jan.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Request;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import com.alibaba.fastjson.JSONObject;

public class WxUserManage {
	public static String ACCESS_TOKEN = "qj_900LNPtpApNZTJJlgKFUFJMmJUYBQNYmfxWTHm_SFuQiDNbJVhZsp3eI3sw96tPO1qiTikLfe3zeKfFJ6I0f2amfR2Zwkrqc3_XkI5okPMVeAFAWTC";

	// 创建分组
	@Test
	public void createGroup() {
		Request post = Request.Post("https://api.weixin.qq.com/cgi-bin/groups/create?access_token=" + ACCESS_TOKEN);
		JSONObject json = new JSONObject();
		json.put("name", "test");
		JSONObject group = new JSONObject();
		System.out.println(json.toJSONString());
		group.put("group", json);
		System.out.println(group.toJSONString());
		try {
			post.body(new StringEntity(group.toJSONString()));
			System.out.println(EntityUtils.toString(post.execute().returnResponse().getEntity()));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 查询分组
	@Test
	public void getGroups() {
		Request get = Request.Get("https://api.weixin.qq.com/cgi-bin/groups/get?access_token=" + ACCESS_TOKEN);
		try {
			System.out.println(EntityUtils.toString(get.execute().returnResponse().getEntity()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
