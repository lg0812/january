package com.Jan.utils;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;

import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.ResourceUtils;

import com.Jan.constant.Constants;

public class JavaMailUtils {
	/**
	 * demo: https://github.com/javaee/javamail 邮件发送具体实现
	 * 
	 * @param email
	 *            目标邮箱
	 * @param code
	 *            将要发送的验证码
	 * @throws IOException
	 */
	public static boolean sendMail(String email, String code) throws IOException {
		System.out.println(code);
		// 阿里云邮箱关于smtp的信息链接：http://mailhelp.mxhichina.com/smartmail/detail.vm?spm=0.0.0.0.6TWdiq&knoId=5871700
		// TODO Auto-generated method stub
		Properties props = new Properties();
		props.setProperty("mail.debug", "true");
		// 设置邮件服务器主机名
		// props.setProperty("mail.host", "smtp.163.com");
		props.setProperty("mail.host", "smtp.mxhichina.com");
		// 设置邮件发送端口号,阿里云的25端口默认被禁，即使添加安全组也无效
		// props.setProperty("mail.smtp.port", "25");
		props.setProperty("mail.smtp.port", "465");
		props.setProperty("mail.smtp.socketFactory.port", "465");
		props.setProperty("mail.smtp.socketFactory.fallback", "false");
		props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		// 发送服务器需要身份验证
		props.setProperty("mail.smtp.auth", "true");
		// 设置邮件协议名称
		props.setProperty("mail.transport.protocol", "smtp");
		// 设置发送人账号
		// props.getProperty("mail.smtp.from", Constants.EMAIL);
		// 设置超时时间
		props.getProperty("mail.stmp.timeout", "30000");
		try {
			Session session = Session.getInstance(props, new Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(Constants.EMAIL, Constants.EMAIL_PW);
				}
			});
			Message msg = new MimeMessage(session);
			msg.setSubject("May 网站注册码");
			// 设置邮件内容
			// TextMsg(msg, code);
			// HtmlMsg(msg, code);

			HtmlMsgPlaceholder(msg, code, email, "email.html");
			// 设置发件人
			msg.setFrom(new InternetAddress(Constants.EMAIL));
			msg.setSentDate(new Date());
			msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
			Transport.send(msg);
			return true;
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

	}

	@Test
	public void testResourceUtilsGetFile() {
		File file;
		try {
			// file = ResourceUtils.getFile("classpath:email.html");
			// System.out.println(file.getName());
			// StringBuffer sb = new StringBuffer();
			// // BufferedReader bf = new BufferedReader(new
			// InputStreamReader(new
			// // FileInputStream(file), "UTF-8"));
			// BufferedReader bf = new BufferedReader(new FileReader(file));
			// String temp = "";
			// while ((temp = bf.readLine()) != null) {
			// sb.append(temp);
			// }

			String sb = FileCopyUtils
					.copyToString(new EncodedResource(new ClassPathResource("email_.html"), "utf-8").getReader());
			System.out.println(sb);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * 发送基本文本邮件 2017/7/30
	 * 
	 * @param msg
	 *            javax.mail.Message实例
	 * @param code
	 *            String
	 */
	public static void TextMsg(Message msg, String code) throws MessagingException {
		msg.setText("欢迎注册，您的验证码是：" + code.toUpperCase());
	}

	/**
	 * 发送html邮件 2017/7/30
	 * 
	 * @param msg
	 *            javax.mail.Message实例
	 * @param code
	 *            String
	 * @throws IOException
	 */
	public static void HtmlMsg(Message msg, String code) throws MessagingException, IOException {
		String str = new String();
		str = "<html><body><p style='color:red;font-size:32px;'>欢迎注册，您的验证码是：" + code + "</p></body></html>";
		msg.setDataHandler(new DataHandler(new ByteArrayDataSource(str, "text/html")));
	}

	/**
	 * @param msg
	 * @param file
	 * 
	 * @param code
	 * @throws MessagingException
	 * @throws IOException
	 */
	/**
	 * @param msg
	 * @param code
	 * @param email
	 * @param filePath
	 *            邮件模板，包含特殊符号如：$email $code等,注意该文件编码格式的是GBK
	 * @throws MessagingException
	 * @throws IOException
	 */
	public static void HtmlMsgPlaceholder(Message msg, String code, String email, String filePath)
			throws MessagingException, IOException {
		msg.setDataHandler(new DataHandler(new ByteArrayDataSource(
				FileCopyUtils.copyToString(new EncodedResource(new ClassPathResource(filePath), "GBK").getReader())
						.replace("$email", email).replace("$code", code),
				"text/html")));
	}

	/**
	 * @param msg
	 * @param code
	 * @param email
	 * @param filePath
	 * @throws MessagingException
	 * @throws IOException
	 */
	public static void HtmlMsgMult(Message msg, String code, String email, String filePath)
			throws MessagingException, IOException {
		MimeBodyPart text = new MimeBodyPart();
		text.setDataHandler(new DataHandler(new ByteArrayDataSource(
				FileCopyUtils.copyToString(new EncodedResource(new ClassPathResource(filePath), "GBK").getReader())
						.replace("$email", email).replace("$code", code),
				"text/html")));

		MimeBodyPart img = new MimeBodyPart();
		img.setFileName("mail.png");
		img.setDataHandler(new DataHandler(new FileDataSource(ResourceUtils.getFile("classpath:mail.png"))));

		Multipart mp = new MimeMultipart();
		mp.addBodyPart(text);
		mp.addBodyPart(img);

		msg.setContent(mp);
	}
}
