package com.Jan.wx;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.junit.Test;

public class Wechat4j {
	@Test
	public void create_menu() throws Exception {
		String menu = "{\"button\" : [ {\"type\" : \"view\",\"name\" : \"我的订单\",\"url\" : \"http://ryy-api.xiaokejia.com/public/html/order_list.html\" }, {\"type\" : \"view\", \"name\" : \"我的积分\",\"url\" : \"http://wfx.xiaokejia.com/decorate/html/index.html\" }, { \"type\" : \"view\",\"name\" : \"我最喜欢\",\"url\" : \"http://wfx.xiaokejia.com/decorate/html/index.html\" } ]} ";
		String token = "9jjqVcqsyiUixX3ox6MexDWvk13O9WJf-DdryXhd8T0FxynURvAYaB0wJG95a4Wj8PXkNCUPMHZxKCh9NX-Fwjzi7wqrav8oZ7K4JTbjB3_VdnWl03l-Q_JmY45YO2EhFHCbACASUP";
		URL url = new URL("https://api.weixin.qq.com/cgi-bin/menu/create?access_token=" + token);
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("POST");
		con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		con.setDoOutput(true);
		con.setDoInput(true);
		con.connect();

		OutputStream os = con.getOutputStream();
		os.write(menu.getBytes());
		os.flush();
		os.close();

		InputStream is = con.getInputStream();
		byte[] bytes = new byte[1024];
		StringBuffer str = new StringBuffer("");
		while (is.read(bytes) != -1) {
			str = str.append(new String(bytes));
		}

		System.out.println(str);
	}
}
