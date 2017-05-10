package com.Jan.service.Impl;

import javax.annotation.Resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Jan.model.User;
import com.Jan.service.LoginService;

@Service
@Transactional
public class LoginServiceImpl implements LoginService {
	final Logger log = LogManager.getLogger(WxServiceImpl.class);
	@Resource
	public SessionFactory sessionFactory;

	@Override
	public User login(String username, String password) {
		// TODO Auto-generated method stub
		User user = (User) sessionFactory.getCurrentSession()
				.createQuery("select new User(id,username,register,access_token) from User  where username = '"
						+ username + "' and password = '" + password + "'")
				.uniqueResult();
		return user;
	}

}
