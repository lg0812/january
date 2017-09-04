package com.Jan.reflex;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class ReflexTest {
	private Map<String, Object> pool;

	public ReflexTest() {
		// TODO Auto-generated constructor stub
	}

	public Object initFiles(String className, JSONArray fields)
			throws ClassNotFoundException, NoSuchMethodException, SecurityException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException, InstantiationException {
		Class<?> clazz = Class.forName(className);
		Object target = clazz.newInstance();
		for (int i = 0; i < fields.size(); i++) {
			JSONObject j = fields.getJSONObject(i);
			String fieldName = j.getString("name");
			String fieldValue = j.getString("value");
			String setterName = "set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
			Method setterMethod = clazz.getMethod(setterName, fieldValue.getClass());
			setterMethod.invoke(target, fieldValue);
		}
		return target;
	}

	@Test
	public void init()
			throws NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {

		String str = "{'objects':[{'id':'id1','class':'com.Jan.model.User','fields':[{'name':'username','value':'feiqingyang'}]}]}";
		JSONArray obj = JSONObject.parseObject(str).getJSONArray("objects");
		pool = new HashMap<String, Object>();
		for (int t = 0; t < obj.size(); t++) {
			JSONObject o = obj.getJSONObject(t);
			String id = o.getString("id");
			String className = o.getString("class");
			try {
				pool.put(id, initFiles(className, o.getJSONArray("fields")));
			} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println(JSON.toJSONString(pool.get("id1")));
	}
}
