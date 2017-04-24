package com.Jan.Timer;

import java.util.Timer;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TokenListener implements ServletContextListener {
	final Logger log = LogManager.getLogger(TokenListener.class);
	private Timer timer = null;

	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		log.info("accessToken监听器结束..........");
	}

	private void registeAccessTokenTimer() {
		// TODO Auto-generated method stub
		TokenTimer accessTokenTimer = new TokenTimer();
		timer.schedule(accessTokenTimer, TokenTimer.DELAY, TokenTimer.PERIOD);
		log.info("accessToken定时器注册成功，执行间隔为" + TokenTimer.PERIOD);
	}

	public void contextInitialized(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		log.info("accessToken监听器启动..........");
		timer = new Timer(true);
		registeAccessTokenTimer();
	}

}
