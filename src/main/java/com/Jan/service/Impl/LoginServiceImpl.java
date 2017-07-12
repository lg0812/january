package com.Jan.service.Impl;

import java.util.Date;
import java.util.Properties;
import java.util.Random;
import java.util.UUID;

import javax.annotation.Resource;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

	public boolean sendMail(String email, String code) {
		System.out.println(code);
		// 阿里云邮箱关于smtp的信息链接：http://mailhelp.mxhichina.com/smartmail/detail.vm?spm=0.0.0.0.6TWdiq&knoId=5871700
		// TODO Auto-generated method stub
		Properties props = new Properties();
		props.setProperty("mail.debug", "true");
		// 设置邮件服务器主机名
		props.setProperty("mail.host", "smtp.mxhichina.com");
		// 发送服务器需要身份验证
		props.setProperty("mail.smtp.auth", "true");
		// 设置邮件协议名称
		props.setProperty("mail.transport.protocol", "smtp");
		// 设置邮件发送端口号
		props.setProperty("mail.stmp.port", "465");
		// 设置发送人账号
		// props.getProperty("mail.smtp.from", Constants.EMAIL);
		// 设置超时时间
		props.getProperty("mail.stmp.timeout", "30000");
		try {
			Session session = Session.getInstance(props);
			Message msg = new MimeMessage(session);
			msg.setSubject("May 网站注册码");
			// 设置邮件内容
			msg.setText("欢迎注册本网站账号，您的验证码是：" + code.toUpperCase());
			// 设置发件人
			msg.setFrom(new InternetAddress(Constants.EMAIL));

			Transport tp = session.getTransport();
			tp.connect(Constants.EMAIL, Constants.EMAIL_PW);
			tp.sendMessage(msg, new Address[] { new InternetAddress(email) });
			tp.close();
			return true;
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

	}

	@Override
	public boolean sendMailSave(String email) {
		// TODO Auto-generated method stub
		String verification_code = getRandomString(4);
		if (sendMail(email, verification_code)) {
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
		return false;
	}

	@Test
	public void send() {
		System.out.println(sendMail("2356581512@qq.com", getRandomString(4).toUpperCase()));
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
