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
	// 文本消息
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
		return ToUserName;
	}

	public void setToUserName(String toUserName) {
		ToUserName = toUserName;
	}

	public String getFromUserName() {
		return FromUserName;
	}

	public void setFromUserName(String fromUserName) {
		FromUserName = fromUserName;
	}

	public String getCreateTime() {
		return CreateTime;
	}

	public void setCreateTime(String createTime) {
		CreateTime = createTime;
	}

	public String getMsgType() {
		return MsgType;
	}

	public void setMsgType(String msgType) {
		MsgType = msgType;
	}

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	}

	public String getArticleCount() {
		return ArticleCount;
	}

	public void setArticleCount(String articleCount) {
		ArticleCount = articleCount;
	}

	public ImageResponse getImage() {
		return Image;
	}

	public void setImage(ImageResponse image) {
		Image = image;
	}

	public VoiceResponse getVoice() {
		return Voice;
	}

	public void setVoice(VoiceResponse voice) {
		Voice = voice;
	}

	public VideoResponse getVideo() {
		return Video;
	}

	public void setVideo(VideoResponse video) {
		Video = video;
	}

	public MusicResponse getMusic() {
		return Music;
	}

	public void setMusic(MusicResponse music) {
		Music = music;
	}

	@XmlTransient
	public List<ArticleResponse> getArticle() {
		return article;
	}

	public void setArticle(List<ArticleResponse> article) {
		this.article = article;
	}

	@Override
	public String toString() {
		return "WechatResponse [ToUserName=" + ToUserName + ", FromUserName=" + FromUserName + ", CreateTime="
				+ CreateTime + ", MsgType=" + MsgType + ", Content=" + Content + ", ArticleCount=" + ArticleCount
				+ ", Image=" + Image + ", Voice=" + Voice + ", Video=" + Video + ", Music=" + Music + ", article="
				+ article + "]";
	}
	
	public static String[] CDATA_TAG = {"ToUserName",
			"FromUserName","MsgType","Event","MsgId","Content","MediaId","Title","Description","MusicUrl","HQMusicUrl","ThumbMediaId",
			"PicUrl","Url"
			};
		

}
