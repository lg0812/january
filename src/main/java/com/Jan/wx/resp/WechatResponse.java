package com.Jan.wx.resp;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement(name = "xml")
public class WechatResponse {
	@XmlElement(name = "ToUserName")
	private String ToUserName;
	@XmlElement(name = "FromUserName")
	private String FromUserName;
	@XmlElement(name = "CreateTime")
	private String CreateTime;
	@XmlElement(name = "MsgType")
	private String MsgType;
	@XmlElement(name = "Content")
	private String Content;
	@XmlElement(name = "ArticleCount")
	private String ArticleCount;
	@XmlElement(name = "Image")
	private ImageResponse Image;
	@XmlElement(name = "Voice")
	private VoiceResponse Voice;
	@XmlElement(name = "Video")
	private VideoResponse Video;
	@XmlElement(name = "Music")
	private MusicResponse Music;
	@XmlElementWrapper(name = "Articles")
	@XmlElement(name = "item")
	private List<ArticleResponse> article;

	public String getToUserName() {
		return this.ToUserName;
	}

	public void setToUserName(String toUserName) {
		this.ToUserName = toUserName;
	}

	public String getFromUserName() {
		return this.FromUserName;
	}

	public void setFromUserName(String fromUserName) {
		this.FromUserName = fromUserName;
	}

	public String getCreateTime() {
		return this.CreateTime;
	}

	public void setCreateTime(String createTime) {
		this.CreateTime = createTime;
	}

	public String getMsgType() {
		return this.MsgType;
	}

	public void setMsgType(String msgType) {
		this.MsgType = msgType;
	}

	public String getContent() {
		return this.Content;
	}

	public void setContent(String content) {
		this.Content = content;
	}

	public String getArticleCount() {
		return this.ArticleCount;
	}

	public void setArticleCount(String articleCount) {
		this.ArticleCount = articleCount;
	}

	public ImageResponse getImage() {
		return this.Image;
	}

	public void setImage(ImageResponse image) {
		this.Image = image;
	}

	public VoiceResponse getVoice() {
		return this.Voice;
	}

	public void setVoice(VoiceResponse voice) {
		this.Voice = voice;
	}

	public VideoResponse getVideo() {
		return this.Video;
	}

	public void setVideo(VideoResponse video) {
		this.Video = video;
	}

	public MusicResponse getMusic() {
		return this.Music;
	}

	public void setMusic(MusicResponse music) {
		this.Music = music;
	}

	@XmlTransient
	public List<ArticleResponse> getArticle() {
		return this.article;
	}

	public void setArticle(List<ArticleResponse> article) {
		this.article = article;
	}

	public String toString() {
		return "WechatResponse [ToUserName=" + this.ToUserName + ", FromUserName=" + this.FromUserName + ", CreateTime="
				+ this.CreateTime + ", MsgType=" + this.MsgType + ", Content=" + this.Content + ", ArticleCount="
				+ this.ArticleCount + ", Image=" + this.Image + ", Voice=" + this.Voice + ", Video=" + this.Video
				+ ", Music=" + this.Music + ", article=" + this.article + "]";
	}

	public static String[] CDATA_TAG = { "ToUserName", "FromUserName", "MsgType", "Event", "MsgId", "Content",
			"MediaId", "Title", "Description", "MusicUrl", "HQMusicUrl", "ThumbMediaId", "PicUrl", "Url" };
}
