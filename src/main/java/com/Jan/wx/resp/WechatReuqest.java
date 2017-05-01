package com.Jan.wx.resp;

public class WechatReuqest {
	private String ToUserName;
	private String FromUserName;
	private String CreateTime;
	private String MsgType;
	private String MsgId;
	// 文本
	private String Content;
	// 图片
	private String PicUrl;
	private String MediaId;
	// 语音 + MediaId
	private String Format;
	// 视频 + MediaId
	private String ThumbMediaId;
	// 地理
	private String Location_X;
	private String Location_Y;
	private String Scale;
	private String Label;
	// 链接
	private String Title;
	private String Description;
	private String Url;
	// subscribe(订阅)、unsubscribe(取消订阅)
	private String Event;
	// 扫码关注/未关注 + event
	private String EventKey;
	private String Ticket;
	// 上报地理位置+event
	private String Latitude; // 上报地理位置事件Latitude
	private String Longitude; // 上报地理位置事件Longitude
	private String Precision; // 上报地理位置事件Precision
	// 自定义菜单事件 + event + eventKey
	// 点击菜单跳转链接时的事件推送+ event + eventKey
	// 事件推送状态
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
