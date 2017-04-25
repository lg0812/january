package com.Jan.wx;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RequestUtils {
	final static Logger log = LogManager.getLogger(RequestUtils.class);

	public static String getRequest(String url, Map<String, String> hashMap) {
		BufferedReader bis;
		StringBuffer str = new StringBuffer("");
		try {
			// 如果hashmap是null 或者长度为0 ,不加？
			URLConnection http = new URL(
					url + ((hashMap == null || hashMap.isEmpty()) ? "" : "?") + getParamsFromMap(hashMap))
							.openConnection();
			log.info(http.getURL());
			http.setRequestProperty("accept", "*/*");
			http.setRequestProperty("connection", "Keep-Alive");
			// 这是从浏览器中copy出来的
			http.setRequestProperty("user-agent",
					"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/57.0.2987.133 Safari/537.36");
			http.setRequestProperty("Charset", "utf-8");
			http.connect();
			bis = new BufferedReader(new InputStreamReader(http.getInputStream(), "utf-8"));
			String line = "";
			while ((line = bis.readLine()) != null) {
				str.append(line);
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		log.info(str.toString());
		return str.toString();
	}

	private static String getParamsFromMap(Map<String, String> hashMap) throws UnsupportedEncodingException {
		// TODO Auto-generated method stub
		String str = "";
		if (hashMap != null) {
			for (String key : hashMap.keySet()) {
				String s = hashMap.get(key);
				System.out.println(s);
				str = str + key + "=" + URLEncoder.encode(s, "utf-8");
			}
		}
		log.info("getRequest params:" + str);
		return str;
	}

}
