package com.Jan.service.Impl;

import com.Jan.Timer.TokenListener;
import com.Jan.model.MsgType;
import com.Jan.model.UserToken;
import com.Jan.model.WxMsg;
import com.Jan.service.WxService;
import com.Jan.wx.RequestUtils;
import com.Jan.wx.req.WechatRequest;
import com.Jan.wx.resp.ArticleResponse;
import com.Jan.wx.resp.WechatResponse;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sun.org.apache.xml.internal.serialize.OutputFormat;
import com.sun.org.apache.xml.internal.serialize.XMLSerializer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class WxServiceImpl implements WxService {
	final Logger log = LogManager.getLogger(WxServiceImpl.class);
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
			// onText();
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

	@SuppressWarnings("restriction")
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

	@Override
	public String wx_upload(String access_token, String type, File file) {

		// TODO Auto-generated method stub
		String result = null;
		try {
			CloseableHttpClient httpclient = HttpClients.createDefault();
			HttpEntity entity = MultipartEntityBuilder.create().addBinaryBody("media", file).build();
			HttpPost http = new HttpPost(
					new URIBuilder().setScheme("https").setHost("api.weixin.qq.com").setPath("/cgi-bin/media/upload")
							.setParameter("access_token", access_token).setParameter("type", type).build());
			System.out.println(http.getURI());
			http.setEntity(entity);
			System.out.println(new Date().getTime());
			result = IOUtils.toString(httpclient.execute(http).getEntity().getContent(), "utf-8");
			httpclient.close();
		} catch (URISyntaxException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// log.debug(result);
		System.out.println(result);
		return result;
	}

	@Override
	public String media_temp(String acces_token, String mediaId) {
		// TODO Auto-generated method stub
		String result = null;
		try {
			CloseableHttpClient httpclient = HttpClients.createDefault();
			HttpGet get = new HttpGet(
					"https://api.weixin.qq.com/cgi-bin/media/get?access_token=" + acces_token + "&media_id=" + mediaId);

			result = IOUtils.toString(httpclient.execute(get).getEntity().getContent(), "utf-8");
		} catch (UnsupportedOperationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public String wx_upload_long(String access_token, String type, File file) {
		// TODO Auto-generated method stub
		String result = null;
		try {
			CloseableHttpClient httpclient = HttpClients.createDefault();
			HttpEntity entity = MultipartEntityBuilder.create().addBinaryBody("media", file).addTextBody("type", type)
					.build();
			HttpPost http = new HttpPost(new URIBuilder().setScheme("https").setHost("api.weixin.qq.com")
					.setPath("/cgi-bin/material/add_material").setParameter("access_token", access_token).build());
			http.setEntity(entity);
			result = IOUtils.toString(httpclient.execute(http).getEntity().getContent(), "utf-8");
			httpclient.close();
			System.out.println("---");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	@Test
	public void t_upload() throws IOException {
		System.out.println(new Date().getTime());
		System.out.println(wx_upload(
				"qpQro2iK83hp8wS0QKO50ka6cDYy5Xvk5BWgjgzETgFnrzQi_oZRENvAgWKt5gEAgAGI1NSYeEq09wnW_-tbQa4oYtK71YgINkWHqzOnZ7cGOJJIJUwipYqaiMKSsnHWENMeAJALUT",
				"image", new File("D:/wx.png")));

	}

	// @Test
	// public void temp_() {
	// System.out.println(media_temp(
	// "RuIEdjjVgEijE0lvXFX_q8bNWRM870jv43pDi4ylYJjcX78wKJ0OgCd2taMa3_fJXETSyshun0VO1DR5WBJcBp9eyXVXW9CFt9bNJ-vB4Y2k9aYU_gm8Z3eBKCGMfA-_KUShAAAQAY",
	// "79Dc9zX7gYj0VamVztpEsssBxc2jc_8USiWTDSJrb8nNraKhBSKcikFxGJVG_S4w"));
	// }
	@Test
	public void test_wx_upload_long() throws FileNotFoundException {
		System.out.println(wx_upload_long(
				"RuIEdjjVgEijE0lvXFX_q8bNWRM870jv43pDi4ylYJjcX78wKJ0OgCd2taMa3_fJXETSyshun0VO1DR5WBJcBp9eyXVXW9CFt9bNJ-vB4Y2k9aYU_gm8Z3eBKCGMfA-_KUShAAAQAY",
				"image", new File("D:/dongman.jpg")));
	}

	@Override
	public String media_long(String token, String mediaId) {
		String result = null;
		try {
			CloseableHttpClient httpclient = HttpClients.createDefault();
			HttpPost post = new HttpPost(
					"https://api.weixin.qq.com/cgi-bin/material/batchget_material?access_token=" + token);
			JSONObject json = new JSONObject();
			json.put("media_id", mediaId);
			post.setEntity(new StringEntity(json.toJSONString()));
			result = EntityUtils.toString(httpclient.execute(post).getEntity());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public String get_media_total(String token) {
		// TODO Auto-generated method stub
		String result = null;
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpGet get = new HttpGet("https://api.weixin.qq.com/cgi-bin/material/get_materialcount?access_token=" + token);
		try {
			result = EntityUtils.toString(httpclient.execute(get).getEntity());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public String get_media_list(String token) {
		// TODO Auto-generated method stub
		String result = null;
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpPost get = new HttpPost(
				"https://api.weixin.qq.com/cgi-bin/material/batchget_material?access_token=" + token);
		try {
			result = EntityUtils.toString(httpclient.execute(get).getEntity());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
}
