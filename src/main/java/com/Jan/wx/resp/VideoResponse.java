/**
 * 
 */
package com.Jan.wx.resp;

import javax.xml.bind.annotation.XmlElement;

public class VideoResponse {
	@XmlElement(name = "MediaId")
	private String MediaId; // 通过上传多媒体文件，得到的id
	@XmlElement(name = "Title")
	private String Title; // 视频消息的标题
	@XmlElement(name = "Description")
	private String Description; // 视频消息的描述

}
