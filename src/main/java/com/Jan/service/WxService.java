package com.Jan.service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.net.MalformedURLException;

import javax.persistence.criteria.CriteriaBuilder.In;

import com.Jan.wx.req.WechatRequest;
import com.Jan.wx.resp.WechatResponse;

public interface WxService {
	/* 微信消息 */
	String create_menu(String paramString) throws Exception;

	WechatRequest xmlToBean(Reader reader);

	WechatRequest xmlToBean(InputStream is);

	String beanToXml(WechatResponse resp, OutputStream out);

	WechatResponse dispatchMsg(WechatRequest req);

	/* 素材管理 */
	/* 新增临时素材 */
	String wx_upload(String token, String type, InputStream file);

	String media_temp(String toke, String mediaId);

	String wx_upload_long(String token, String type, InputStream file);
}
