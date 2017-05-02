/**
 * 
 */
package com.Jan.wx.resp;

import javax.xml.bind.annotation.XmlElement;

public class VideoResponse {
	@XmlElement(name = "MediaId")
	private String MediaId;
	@XmlElement(name = "Title")
	private String Title;
	@XmlElement(name = "Description")
	private String Description;

	public String getMediaId() {
		return this.MediaId;
	}

	public void setMediaId(String mediaId) {
		this.MediaId = mediaId;
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
}
