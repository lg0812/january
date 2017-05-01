package com.Jan.wx.req;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "xml")
public class WechatReuqest {
	@XmlElement(name = "ToUserName")
	private String ToUserName;
	@XmlElement(name = "FromUserName")
	private String FromUserName;
	@XmlElement(name = "CreateTime")
	private String CreateTime;
	@XmlElement(name = "MsgType")
	private String MsgType;
	@XmlElement(name = "MsgId")
	private String MsgId;

	// 文本
	@XmlElement(name = "Content")
	private String Content;
	// 图片
	@XmlElement(name = "PicUrl")
	private String PicUrl;
	@XmlElement(name = "MediaId")
	private String MediaId;
	// 语音 + MediaId
	@XmlElement(name = "Format")
	private String Format;
	// 视频 + MediaId
	@XmlElement(name = "ThumbMediaId")
	private String ThumbMediaId;
	// 地理
	@XmlElement(name = "Location_X")
	private String Location_X;
	@XmlElement(name = "Location_Y")
	private String Location_Y;
	@XmlElement(name = "Scale")
	private String Scale;
	@XmlElement(name = "Label")
	private String Label;
	// 链接
	@XmlElement(name = "Title")
	private String Title;
	@XmlElement(name = "Description")
	private String Description;
	@XmlElement(name = "Url")
	private String Url;
	// subscribe(订阅)、unsubscribe(取消订阅)
	@XmlElement(name = "Event")
	private String Event;
	// 扫码关注/未关注 + event
	@XmlElement(name = "EventKey")
	private String EventKey;
	@XmlElement(name = "Ticket")
	private String Ticket;
	// 上报地理位置+event
	@XmlElement(name = "Latitude")
	private String Latitude;
	@XmlElement(name = "Longitude") // 上报地理位置事件Latitude
	private String Longitude;
	@XmlElement(name = "Precision") // 上报地理位置事件Longitude
	private String Precision;
	// 上报地理位置事件Precision
	// 自定义菜单事件 + event + eventKey
	// 点击菜单跳转链接时的事件推送+ event + eventKey
	// 事件推送状态
	@XmlElement(name = "Status")
	private String Status;

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

	public String getMsgId() {
		return MsgId;
	}

	public void setMsgId(String msgId) {
		MsgId = msgId;
	}

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	}

	public String getPicUrl() {
		return PicUrl;
	}

	public void setPicUrl(String picUrl) {
		PicUrl = picUrl;
	}

	public String getMediaId() {
		return MediaId;
	}

	public void setMediaId(String mediaId) {
		MediaId = mediaId;
	}

	public String getFormat() {
		return Format;
	}

	public void setFormat(String format) {
		Format = format;
	}

	public String getThumbMediaId() {
		return ThumbMediaId;
	}

	public void setThumbMediaId(String thumbMediaId) {
		ThumbMediaId = thumbMediaId;
	}

	public String getLocation_X() {
		return Location_X;
	}

	public void setLocation_X(String location_X) {
		Location_X = location_X;
	}

	public String getLocation_Y() {
		return Location_Y;
	}

	public void setLocation_Y(String location_Y) {
		Location_Y = location_Y;
	}

	public String getScale() {
		return Scale;
	}

	public void setScale(String scale) {
		Scale = scale;
	}

	public String getLabel() {
		return Label;
	}

	public void setLabel(String label) {
		Label = label;
	}

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

	public String getUrl() {
		return Url;
	}

	public void setUrl(String url) {
		Url = url;
	}

	public String getEvent() {
		return Event;
	}

	public void setEvent(String event) {
		Event = event;
	}

	public String getEventKey() {
		return EventKey;
	}

	public void setEventKey(String eventKey) {
		EventKey = eventKey;
	}

	public String getTicket() {
		return Ticket;
	}

	public void setTicket(String ticket) {
		Ticket = ticket;
	}

	public String getLatitude() {
		return Latitude;
	}

	public void setLatitude(String latitude) {
		Latitude = latitude;
	}

	public String getLongitude() {
		return Longitude;
	}

	public void setLongitude(String longitude) {
		Longitude = longitude;
	}

	public String getPrecision() {
		return Precision;
	}

	public void setPrecision(String precision) {
		Precision = precision;
	}

	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
	}

	@Override
	public String toString() {
		return "WechatReuqest [ToUserName=" + ToUserName + ", FromUserName=" + FromUserName + ", CreateTime="
				+ CreateTime + ", MsgType=" + MsgType + ", MsgId=" + MsgId + ", Content=" + Content + ", PicUrl="
				+ PicUrl + ", MediaId=" + MediaId + ", Format=" + Format + ", ThumbMediaId=" + ThumbMediaId
				+ ", Location_X=" + Location_X + ", Location_Y=" + Location_Y + ", Scale=" + Scale + ", Label=" + Label
				+ ", Title=" + Title + ", Description=" + Description + ", Url=" + Url + ", Event=" + Event
				+ ", EventKey=" + EventKey + ", Ticket=" + Ticket + ", Latitude=" + Latitude + ", Longitude="
				+ Longitude + ", Precision=" + Precision + ", Status=" + Status + "]";
	}

}
