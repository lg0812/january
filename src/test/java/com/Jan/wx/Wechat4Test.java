package com.Jan.wx;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class Wechat4Test {
	@Test
	public void testGetReq() {
		Map map = new HashMap<String, String>();
		map.put("str", "this is a test case!");
		System.out.println(RequestUtils.getRequest("http://127.0.0.1:8081/january/wx/test_success", map));
	}
}
