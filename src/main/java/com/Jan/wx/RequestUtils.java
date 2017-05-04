package com.Jan.wx;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RequestUtils {
	final static Logger log = LogManager.getLogger(RequestUtils.class);

	// url只包含？号前面的部分,hashMap 是参数
	public static String getRequest(String url, Map<String, String> hashMap) {
		BufferedReader bis;
		StringBuffer str = new StringBuffer("");
		try {
			// 如果hashmap是null 或者长度为0 ,不加？
			HttpURLConnection http = (HttpURLConnection) new URL(
					url + ((hashMap == null || hashMap.isEmpty()) ? "" : "?") + getParamsFromMap(hashMap))
							.openConnection();
			log.info(http.getURL());
			http.setRequestProperty("accept", "*/*");
			http.setRequestProperty("connection", "Keep-Alive");
			// 这是从浏览器中copy出来的
			http.setRequestProperty("user-agent",
					"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/57.0.2987.133 Safari/537.36");
			http.setRequestMethod("GET");
			// 这里的时间单位是ms
			http.setRequestProperty("Charset", "utf-8");
			http.setConnectTimeout(30000);
			http.setReadTimeout(30000);
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

	// url只包含？号前面的部分,hashMap 是参数,body是需要写到body中的参数的值
	public static String postRequest(String url, Map<String, String> hashMap, String body) {
		BufferedReader bis;
		StringBuffer str = new StringBuffer("");
		try {
			// 如果hashmap是null 或者长度为0 ,不加？
			HttpURLConnection http = (HttpURLConnection) new URL(
					url + ((hashMap == null || hashMap.isEmpty()) ? "" : "?") + getParamsFromMap(hashMap))
							.openConnection();
			log.info(http.getURL());
			http.setRequestProperty("accept", "*/*");
			http.setRequestProperty("connection", "Keep-Alive");
			// 这是从浏览器中copy出来的
			http.setRequestProperty("user-agent",
					"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/57.0.2987.133 Safari/537.36");
			http.setRequestProperty("Charset", "utf-8");
			http.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; encoding=utf-8");
			http.setRequestMethod("POST");
			http.setDoOutput(true);
			http.setDoInput(true);
			// 这里的时间单位是ms
			http.setConnectTimeout(30000);
			http.setReadTimeout(30000);
			http.connect();
			OutputStream os = http.getOutputStream();
			if (body != null)
				os.write(body.getBytes());
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
		int t=0;
		if (hashMap != null) {
			for (String key : hashMap.keySet()) {
				String s = hashMap.get(key);
				System.out.println(s);
				if (t!=0){
					str = str + "&" + key + "=" + URLEncoder.encode(s, "utf-8");
				}else{
					str = str + key + "=" + URLEncoder.encode(s, "utf-8");
				}
				t++;
			}
		}
		log.info("getRequest params:" + str);
		return str;
	}
}
