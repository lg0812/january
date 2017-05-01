/**
 * 
 */
package com.Jan.wx.resp;

import javax.xml.bind.annotation.XmlElement;

public class ArticleResponse {
	@XmlElement(name = "Title")
	private String Title; // 图文消息标题
	@XmlElement(name = "Description")
	private String Description; // 图文消息描述
	@XmlElement(name = "PicUrl")
	private String PicUrl; // 图片链接，支持JPG、PNG格式，较好的效果为大图360*200，小图200*200
	@XmlElement(name = "Url")
	private String Url; // 点击图文消息跳转链接

}
