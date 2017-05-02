package com.Jan.service;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.net.MalformedURLException;

import com.Jan.wx.req.WechatRequest;
import com.Jan.wx.resp.WechatResponse;

public interface WxService {
	String create_menu(String paramString) throws Exception;

	WechatRequest xmlToBean(Reader reader);

	WechatRequest xmlToBean(InputStream is);

	String beanToXml(WechatResponse resp, OutputStream out);

	WechatResponse dispatchMsg(WechatRequest req);
}
