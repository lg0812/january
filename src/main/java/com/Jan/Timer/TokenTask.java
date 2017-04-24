package com.Jan.Timer;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.Jan.constant.Constants;
import com.Jan.model.UserToken;
import com.Jan.utils.HttpRequest;
import com.alibaba.fastjson.JSON;

import net.sf.json.JSONObject;

@Component
@Transactional
public class TokenTask {
	final Logger log = LogManager.getLogger(TokenTask.class);
	public static final long PERIOD = 7000 * 1000;
	public static final long DELAY = 0; //
	@Resource
	public SessionFactory sessionFactory;

	@Scheduled(fixedRate = PERIOD)
	public void getTokenSchedule() {
		log.info("accessToken 定时任务启动，获取新的accessToken");
		System.out.println("accessToken 定时任务启动，获取新的accessToken");
		String result = HttpRequest.sendGet("https://api.weixin.qq.com/cgi-bin/token",
				"grant_type=client_credential&" + "appid=" + Constants.APP_ID + "&secret=" + Constants.APP_SECRET);
		System.out.println(JSON.parse(result));
		Map<String, Object> map = (Map<String, Object>) JSON.parse(result);

		List<UserToken> list = this.sessionFactory.getCurrentSession().createQuery("from UserToken").list();
		if (list.size() > 0) {
			list.get(0).accessToken = JSONObject.fromObject(result).getString("access_token");
			this.sessionFactory.getCurrentSession().update(list.get(0));
		} else {
			UserToken userToken = new UserToken();
			userToken.accessToken = JSONObject.fromObject(result).getString("access_token");
			this.sessionFactory.getCurrentSession().save(userToken);
		}
	}
}
