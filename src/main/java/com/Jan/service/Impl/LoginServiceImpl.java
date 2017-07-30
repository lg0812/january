package com.Jan.service.Impl;

import java.io.IOException;
import java.util.Date;
import java.util.Properties;
import java.util.Random;
import java.util.UUID;

import javax.annotation.Resource;
import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.NonUniqueResultException;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Jan.constant.ApiConsts;
import com.Jan.constant.BaseResp;
import com.Jan.constant.Constants;
import com.Jan.model.User;
import com.Jan.model.VerificationCode;
import com.Jan.service.LoginService;
import com.Jan.utils.JavaMailUtils;

@Service
@Transactional
public class LoginServiceImpl implements LoginService {
	final Logger log = LogManager.getLogger(WxServiceImpl.class);
	@Resource
	public SessionFactory sessionFactory;

	@Override
	public User login(String email, String password, BaseResp baseResp) {
		// TODO Auto-generated method stub
		try {
			User user = (User) sessionFactory.getCurrentSession()
					.createQuery("from User  where email = '" + email + "'").uniqueResult();
			if (user == null) {
				baseResp.setCode(ApiConsts.NOT_EXIST);
				baseResp.setMessage("email not exist!");
				return null;
			} else {
				if (user.getPassword().equals(password)) {
					user.setAccess_token(UUID.randomUUID().toString());
					baseResp.setCode(ApiConsts.OK);
					baseResp.setMessage("login success");
					return new User(user.getId(), user.getUsername(), user.getEmail(), user.getRegister(),
							user.getAccess_token());
				} else {
					baseResp.setCode(ApiConsts.PW_ERR);
					baseResp.setMessage("password error!");
					return null;
				}
			}
		} catch (NonUniqueResultException e) {
			// TODO: handle exception
			baseResp.setCode(ApiConsts.ERROR);
			baseResp.setMessage("server error!");
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean register(String username, String password, String email, String verification, BaseResp baseResp) {
		// TODO Auto-generated method stub
		VerificationCode code = (VerificationCode) sessionFactory.getCurrentSession()
				.createQuery("from VerificationCode where email = '" + email + "' and verification_code = '"
						+ verification.toUpperCase() + "'")
				.uniqueResult();
		// 验证码的未过期
		if (code != null && new Date().getTime() - code.getSend_time().getTime() < Constants.HALF_HOUR) {

			User u = (User) sessionFactory.getCurrentSession().createQuery("from User where email = '" + email + "'")
					.uniqueResult();
			if (u != null) {
				baseResp.setCode(ApiConsts.ALREADY_EXIST);
				baseResp.setMessage("This email has been registered");
				return false;
			}
			code.setVerification_code(null);
			User user = new User();
			user.setUsername(username);
			user.setPassword(password);
			user.setEmail(email);
			user.setRegister(new Date());
			sessionFactory.getCurrentSession().save(user);
			baseResp.setCode(ApiConsts.OK);
			baseResp.setMessage("register success");
			baseResp.setResult(new User(user.getId(), user.getUsername(), user.getEmail(), user.getRegister(),
					user.getAccess_token()));
			return true;
		} else {
			baseResp.setCode(ApiConsts.VERIFICATION);
			baseResp.setMessage("invalid email or verification code");
			return false;
		}
	}

	@Override
	public boolean sendMailSave(String email) {
		// TODO Auto-generated method stub
		String verification_code = getRandomString(4);
		try {
			if (JavaMailUtils.sendMail(email, verification_code)) {
				VerificationCode code = (VerificationCode) sessionFactory.getCurrentSession()
						.createQuery("from VerificationCode where email='" + email + "'").uniqueResult();
				if (code != null) {
					code.setVerification_code(verification_code);
					code.setSend_time(new Date());
					// sessionFactory.getCurrentSession().update(code);
				} else {
					code = new VerificationCode();
					code.setEmail(email);
					code.setVerification_code(verification_code.toUpperCase());
					code.setSend_time(new Date());
					sessionFactory.getCurrentSession().save(code);
				}
				return true;
			}
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Test
	public void send() {
		try {
			System.out.println(JavaMailUtils.sendMail("18140341063@163.com", getRandomString(4).toUpperCase()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getRandomString(int length) { // length表示生成字符串的长度
		String base = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789_";
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			int number = random.nextInt(base.length());
			sb.append(base.charAt(number));
		}
		return sb.toString();
	}

	@Override
	public boolean resetPW(String password, String email, String verification, BaseResp baseResp) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		VerificationCode code = (VerificationCode) sessionFactory.getCurrentSession()
				.createQuery("from VerificationCode where email = '" + email + "' and verification_code = '"
						+ verification.toUpperCase() + "'")
				.uniqueResult();
		// 验证码的未过期
		if (code != null && new Date().getTime() - code.getSend_time().getTime() < Constants.HALF_HOUR) {

			User u = (User) sessionFactory.getCurrentSession().createQuery("from User where email = '" + email + "'")
					.uniqueResult();
			if (u != null) {
				baseResp.setCode(ApiConsts.OK);
				baseResp.setMessage("reset success");
				u.setPassword(password);
				baseResp.setResult(
						new User(u.getId(), u.getUsername(), u.getEmail(), u.getRegister(), u.getAccess_token()));
				code.setVerification_code(null);
				return true;
			} else {
				baseResp.setCode(ApiConsts.NOT_EXIST);
				baseResp.setMessage("该用户不存在");
				return false;
			}

		} else {
			baseResp.setCode(ApiConsts.VERIFICATION);
			baseResp.setMessage("invalid verification code");
			return false;
		}
	}

	// @Test
	// public void rrr() {
	// System.out.println(UUID.randomUUID().toString());
	// }

}
