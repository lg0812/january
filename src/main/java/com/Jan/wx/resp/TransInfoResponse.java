package com.Jan.wx.resp;

import javax.xml.bind.annotation.XmlElement;


public class TransInfoResponse {
	@XmlElement(name="KfAccount")
	private String KfAccount;//指定会话接入的客服账号
}
