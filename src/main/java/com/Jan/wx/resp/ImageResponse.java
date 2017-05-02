/**
 * 
 */
package com.Jan.wx.resp;

import javax.xml.bind.annotation.XmlElement;

public class ImageResponse {
	@XmlElement(name = "MediaId")
	private String MediaId;

	public String getMediaId() {
		return this.MediaId;
	}

	public void setMediaId(String mediaId) {
		this.MediaId = mediaId;
	}
}
