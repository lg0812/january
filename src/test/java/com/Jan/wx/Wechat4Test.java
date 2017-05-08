package com.Jan.wx;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class Wechat4Test {
	public static String accessToken = "VMg4cPNtVfq5QeRMy-PIZ_a1C8GX2PMBukOctUTZKJvz-hveMjvzVhAJE7apToIvMjAjRNETXCiCsJgUdkcaUDBe8jvyFS31YHfBl2MPhNuVmsXKvbqUSIk9tMi7piQuHADfAHAOHK";

	@Test
	public void testGetReq() {
		Map map = new HashMap<String, String>();
		map.put("str", "this is a test case!");
		RequestUtils.getRequest("http://127.0.0.1:8081/january/wx/test_success", map);
	}

	// 创建菜单
	@Test
	public void testPostReq() {
		String menu = "{\"button\" : [ {\"type\" : \"view\",\"name\" : \"我的订单\",\"url\" : \"http://ryy-api.xiaokejia.com/public/html/order_list.html\" }, {\"type\" : \"view\", \"name\" : \"我的积分\",\"url\" : \"http://wfx.xiaokejia.com/decorate/html/index.html\" }, { \"type\" : \"view\",\"name\" : \"我最喜欢\",\"url\" : \"http://wfx.xiaokejia.com/decorate/html/index.html\" } ]} ";
		Map map = new HashMap<String, String>();
		map.put("access_token", accessToken);
		RequestUtils.postRequest("https://api.weixin.qq.com/cgi-bin/menu/create", map, menu);
	}

	// 获取菜单
	@Test
	public void testGetMenu() {
		Map map = new HashMap<String, String>();
		map.put("access_token", accessToken);
		RequestUtils.getRequest("https://api.weixin.qq.com/cgi-bin/menu/delete", map);
	}
}
