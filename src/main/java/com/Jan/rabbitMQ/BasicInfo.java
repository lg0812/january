package com.Jan.rabbitMQ;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class BasicInfo {
	public Map<String, String> Info() throws FileNotFoundException, IOException {
		Properties props = new Properties();
		props.load(new FileInputStream(
				new File("D:/code/january/src/main/java/com/Jan/rabbitMQ/rabbit-config.properties")));
		System.out.println(props.getProperty("host"));
		Map<String, String> map = new HashMap<String, String>();
		map.put("host", props.getProperty("host"));
		map.put("host", props.getProperty("username"));
		map.put("host", props.getProperty("password"));
		return map;
	}
}
