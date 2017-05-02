package com.Jan.service.Impl;

import com.Jan.model.MsgType;
import com.Jan.model.UserToken;
import com.Jan.model.WxMsg;
import com.Jan.service.WxService;
import com.Jan.wx.req.WechatRequest;
import com.Jan.wx.resp.ArticleResponse;
import com.Jan.wx.resp.WechatResponse;
import com.alibaba.fastjson.JSON;
import com.sun.org.apache.xml.internal.serialize.OutputFormat;
import com.sun.org.apache.xml.internal.serialize.XMLSerializer;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.annotation.Resource;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class WxServiceImpl implements WxService {
	@Resource
	public SessionFactory sessionFactory;
	public WechatResponse wechatResponse;
	public WechatRequest wechatRequest;

	public String create_menu(String menu) throws Exception {
		System.out.println("----------------->" + menu);

		UserToken userToken = (UserToken) this.sessionFactory.getCurrentSession().createQuery("from UserToken")
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
		byte[] bytes = new byte['?'];
		StringBuffer str = new StringBuffer("");
		while (is.read(bytes) != -1) {
			str = str.append(new String(bytes, 0, bytes.length));
		}
		System.out.println(str.toString());
		return str.toString();
	}

	public WechatRequest xmlToBean(Reader reader) {
		try {
			JAXBContext jc = JAXBContext.newInstance(WechatRequest.class);
			System.out.println(reader.toString());
			return (WechatRequest) jc.createUnmarshaller().unmarshal(reader);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return null;
	}

	public WechatRequest xmlToBean(InputStream is) {
		try {
			JAXBContext jc = JAXBContext.newInstance(WechatRequest.class);
			return (WechatRequest) jc.createUnmarshaller().unmarshal(is);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return null;
	}

	public WechatResponse dispatchMsg(WechatRequest xmlToBean) {
		MsgType mt = MsgType.valueOf(xmlToBean.getMsgType());
		this.wechatRequest = xmlToBean;
		this.wechatResponse = new WechatResponse();
		MsgType msgType = MsgType.valueOf(wechatRequest.getMsgType());
		switch (msgType) {
		case event:
			dispatchEvent();
			break;
		case text:
//			onText();
			responseNews();
			break;
		case image:
			// onImage();
			break;
		case voice:
			// onVoice();
			break;
		case video:
			// onVideo();
			break;
		case shortvideo:
			// onShortVideo();
			break;
		case location:
			// onLocation();
			break;
		case link:
			// onLink();
			break;
		default:
			// onUnknown();
			break;
		}
		return wechatResponse;
	}

	private void dispatchEvent() {
	}

	private void onText() {
		responseBase();
		this.wechatResponse.setMsgType(MsgType.text.name());
		this.wechatResponse.setContent(
				((WxMsg) sessionFactory.getCurrentSession().createQuery("from WxMsg").list().get(0)).getMsg());
	}

	private void responseBase() {
		this.wechatResponse.setToUserName(this.wechatRequest.getFromUserName());
		this.wechatResponse.setFromUserName(this.wechatRequest.getToUserName());
		this.wechatResponse.setCreateTime(this.wechatRequest.getCreateTime());
	}

	public String beanToXml(WechatResponse wxbean, OutputStream out) {
		try {
			JAXBContext context = JAXBContext.newInstance(new Class[] { wxbean.getClass() });
			Marshaller m = context.createMarshaller();
			m.setProperty("jaxb.encoding", "UTF-8");
			m.setProperty("jaxb.formatted.output", Boolean.valueOf(true));
			m.setProperty("jaxb.fragment", Boolean.valueOf(true));
			try {
				m.marshal(wxbean, getXMLSerializer(out, wxbean).asContentHandler());
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return null;
	}

	private XMLSerializer getXMLSerializer(OutputStream os, WechatResponse wxbean) {
		OutputFormat of = new OutputFormat();
		formatCDataTag();
		of.setCDataElements(WechatResponse.CDATA_TAG);
		of.setPreserveSpace(true);
		of.setIndenting(true);
		of.setOmitXMLDeclaration(true);
		XMLSerializer serializer = new XMLSerializer(of);
		serializer.setOutputByteStream(os);
		return serializer;
	}

	private void formatCDataTag() {
		for (int i = 0; i < WechatResponse.CDATA_TAG.length; i++) {
			WechatResponse.CDATA_TAG[i] = ("^" + WechatResponse.CDATA_TAG[i]);
		}
	}

	public void responseNews() {
		List<ArticleResponse> items = new ArrayList();
		ArticleResponse item = new ArticleResponse();
		item.setTitle("abc");
		item.setUrl("http://zkh.successinfo.com.cn/january/index_.html");
		item.setDescription("Description");
		item.setPicUrl("http://zkh.successinfo.com.cn/january/chrome.png");
		items.add(item);
		ArticleResponse item1 = new ArticleResponse();
		item1.setTitle("bac");
		item1.setUrl("http://zkh.successinfo.com.cn/january/index_.html");
		item1.setDescription("Description1");
		item1.setPicUrl("http://zkh.successinfo.com.cn/january/chrome.png");
		items.add(item1);
		responseBase();
		this.wechatResponse.setMsgType(MsgType.news.name());
		this.wechatResponse.setArticleCount(String.valueOf(items.size()));
		this.wechatResponse.setArticle(items);
	}

}
