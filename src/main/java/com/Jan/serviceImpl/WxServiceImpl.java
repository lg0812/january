package com.Jan.serviceImpl;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Jan.model.UserToken;
import com.Jan.service.WxService;

@Service
@Transactional
public class WxServiceImpl implements WxService {
	@Resource
	public SessionFactory sessionFactory;

	@Override
	public String create_menu(String menu) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("----------------->"+menu);
		UserToken userToken = (UserToken) sessionFactory.getCurrentSession().createQuery("from UserToken")
				.uniqueResult();
		String token = userToken.getAccessToken();
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
		return null;
	}

}
