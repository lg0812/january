package com.Jan.service.Impl;

import java.util.Date;
import java.util.Properties;

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
					.createQuery(
							"from User  where email = '" + email + "'")
					.uniqueResult();
			if (user == null) {
				baseResp.setCode(ApiConsts.NOT_EXIST);
				baseResp.setMessage("email not exist!");
				return null;
			} else {
				if (user.getPassword().equals(password)) {
					baseResp.setCode(ApiConsts.OK);
					baseResp.setMessage("login success");
					return new User(user.getId(),user.getUsername(),user.getEmail(),user.getRegister(),user.getAccess_token());
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
	public boolean register(String username, String password, String email, String verification) {
		// TODO Auto-generated method stub
		VerificationCode code = (VerificationCode) sessionFactory.getCurrentSession().createQuery(
				"from VerificationCode where email = '" + email + "' and verification_code = '" + verification + "'")
				.uniqueResult();
		// ��֤���δ����
		if (code != null && new Date().getTime() - code.getSend_time().getTime() < Constants.HALF_HOUR) {
			User user = new User();
			user.setUsername(username);
			user.setPassword(password);
			user.setEmail(email);
			user.setRegister(new Date());
			sessionFactory.getCurrentSession().save(user);
			return true;
		} else {
			return false;
		}
	}

	public boolean sendMail(String email, String code) {

		// �������������smtp����Ϣ���ӣ�http://mailhelp.mxhichina.com/smartmail/detail.vm?spm=0.0.0.0.6TWdiq&knoId=5871700
		// TODO Auto-generated method stub
		Properties props = new Properties();
		// �����ʼ�������������
		props.setProperty("mail.host", "smtp.mxhichina.com");
		// ���ͷ�������Ҫ�����֤
		props.setProperty("mail.smtp.auth", "true");
		// �����ʼ�Э������
		props.setProperty("mail.transport.protocol", "smtp");
		// �����ʼ����Ͷ˿ں�
		props.setProperty("mail.stmp.port", "465");
		// ���÷������˺�
		// props.getProperty("mail.smtp.from", Constants.EMAIL);
		// ���ó�ʱʱ��
		props.getProperty("mail.stmp.timeout", "30000");
		try {
			Session session = Session.getInstance(props);
			Message msg = new MimeMessage(session);
			msg.setSubject("May ��վע����");
			// �����ʼ�����
			msg.setText("��ӭע�᱾��վ�˺ţ�������֤���ǣ�" + code.toUpperCase());
			// ���÷�����
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
		String verification_code = "abcd";
		if (sendMail(email, verification_code)) {
			VerificationCode code = (VerificationCode) sessionFactory.getCurrentSession()
					.createQuery("from VerificationCode where email='" + email + "'");
			if (code != null) {
				code.setVerification_code(verification_code);
				code.setSend_time(new Date());
				sessionFactory.getCurrentSession().update(code);
			} else {
				code = new VerificationCode();
				code.setEmail(email);
				code.setVerification_code(verification_code);
				code.setSend_time(new Date());
				sessionFactory.getCurrentSession().save(code);
			}
			return true;
		}
		return false;
	}

	// @Test
	// public void send() {
	// System.out.println(sendMail("2356581512@qq.com"));
	// }

}
