/**
 * 
 */
package com.Jan.wx.resp;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;

public class MusicResponse {
	@XmlElement(name = "Title")
	private String Title; // 音乐标题
	@XmlElement(name = "Description")
	private String Description; // 音乐描述
	@XmlElement(name = "MusicURL")
	private String MusicURL; // 音乐链接
	@XmlElement(name = "HQMusicUrl")
	private String HQMusicUrl; // 高质量音乐链接，WIFI环境优先使用该链接播放音乐
	@XmlElement(name = "ThumbMediaId")
	private String ThumbMediaId; // 缩略图的媒体id，通过上传多媒体文件，得到的id

	public String getTitle() {
		return Title;
	}

	public void setTitle(String title) {
		Title = title;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public String getMusicURL() {
		return MusicURL;
	}

	public void setMusicURL(String musicURL) {
		MusicURL = musicURL;
	}
	@XmlTransient
	public String getHQMusicUrl() {
		return HQMusicUrl;
	}

	public void setHQMusicUrl(String hQMusicUrl) {
		HQMusicUrl = hQMusicUrl;
	}

	public String getThumbMediaId() {
		return ThumbMediaId;
	}

	public void setThumbMediaId(String thumbMediaId) {
		ThumbMediaId = thumbMediaId;
	}

}
