package com.Jan.wx.req;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "xml")
public class WechatRequest {
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
	@XmlElement(name = "Content")
	private String Content;
	@XmlElement(name = "PicUrl")
	private String PicUrl;
	@XmlElement(name = "MediaId")
	private String MediaId;
	@XmlElement(name = "Format")
	private String Format;
	@XmlElement(name = "ThumbMediaId")
	private String ThumbMediaId;
	@XmlElement(name = "Location_X")
	private String Location_X;
	@XmlElement(name = "Location_Y")
	private String Location_Y;
	@XmlElement(name = "Scale")
	private String Scale;
	@XmlElement(name = "Label")
	private String Label;
	@XmlElement(name = "Title")
	private String Title;
	@XmlElement(name = "Description")
	private String Description;
	@XmlElement(name = "Url")
	private String Url;
	@XmlElement(name = "Event")
	private String Event;
	@XmlElement(name = "EventKey")
	private String EventKey;
	@XmlElement(name = "Ticket")
	private String Ticket;
	@XmlElement(name = "Latitude")
	private String Latitude;
	@XmlElement(name = "Longitude")
	private String Longitude;
	@XmlElement(name = "Precision")
	private String Precision;
	@XmlElement(name = "Status")
	private String Status;

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

	public String getMsgId() {
		return this.MsgId;
	}

	public void setMsgId(String msgId) {
		this.MsgId = msgId;
	}

	public String getContent() {
		return this.Content;
	}

	public void setContent(String content) {
		this.Content = content;
	}

	public String getPicUrl() {
		return this.PicUrl;
	}

	public void setPicUrl(String picUrl) {
		this.PicUrl = picUrl;
	}

	public String getMediaId() {
		return this.MediaId;
	}

	public void setMediaId(String mediaId) {
		this.MediaId = mediaId;
	}

	public String getFormat() {
		return this.Format;
	}

	public void setFormat(String format) {
		this.Format = format;
	}

	public String getThumbMediaId() {
		return this.ThumbMediaId;
	}

	public void setThumbMediaId(String thumbMediaId) {
		this.ThumbMediaId = thumbMediaId;
	}

	public String getLocation_X() {
		return this.Location_X;
	}

	public void setLocation_X(String location_X) {
		this.Location_X = location_X;
	}

	public String getLocation_Y() {
		return this.Location_Y;
	}

	public void setLocation_Y(String location_Y) {
		this.Location_Y = location_Y;
	}

	public String getScale() {
		return this.Scale;
	}

	public void setScale(String scale) {
		this.Scale = scale;
	}

	public String getLabel() {
		return this.Label;
	}

	public void setLabel(String label) {
		this.Label = label;
	}

	public String getTitle() {
		return this.Title;
	}

	public void setTitle(String title) {
		this.Title = title;
	}

	public String getDescription() {
		return this.Description;
	}

	public void setDescription(String description) {
		this.Description = description;
	}

	public String getUrl() {
		return this.Url;
	}

	public void setUrl(String url) {
		this.Url = url;
	}

	public String getEvent() {
		return this.Event;
	}

	public void setEvent(String event) {
		this.Event = event;
	}

	public String getEventKey() {
		return this.EventKey;
	}

	public void setEventKey(String eventKey) {
		this.EventKey = eventKey;
	}

	public String getTicket() {
		return this.Ticket;
	}

	public void setTicket(String ticket) {
		this.Ticket = ticket;
	}

	public String getLatitude() {
		return this.Latitude;
	}

	public void setLatitude(String latitude) {
		this.Latitude = latitude;
	}

	public String getLongitude() {
		return this.Longitude;
	}

	public void setLongitude(String longitude) {
		this.Longitude = longitude;
	}

	public String getPrecision() {
		return this.Precision;
	}

	public void setPrecision(String precision) {
		this.Precision = precision;
	}

	public String getStatus() {
		return this.Status;
	}

	public void setStatus(String status) {
		this.Status = status;
	}

	public String toString() {
		return "WechatReuqest [ToUserName=" + this.ToUserName + ", FromUserName=" + this.FromUserName + ", CreateTime="
				+ this.CreateTime + ", MsgType=" + this.MsgType + ", MsgId=" + this.MsgId + ", Content=" + this.Content
				+ ", PicUrl=" + this.PicUrl + ", MediaId=" + this.MediaId + ", Format=" + this.Format
				+ ", ThumbMediaId=" + this.ThumbMediaId + ", Location_X=" + this.Location_X + ", Location_Y="
				+ this.Location_Y + ", Scale=" + this.Scale + ", Label=" + this.Label + ", Title=" + this.Title
				+ ", Description=" + this.Description + ", Url=" + this.Url + ", Event=" + this.Event + ", EventKey="
				+ this.EventKey + ", Ticket=" + this.Ticket + ", Latitude=" + this.Latitude + ", Longitude="
				+ this.Longitude + ", Precision=" + this.Precision + ", Status=" + this.Status + "]";
	}
}
