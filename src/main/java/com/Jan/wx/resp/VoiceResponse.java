/**
 * 
 */
package com.Jan.wx.resp;

import java.util.List;

import javax.xml.bind.annotation.*;

public class VoiceResponse
{
  @XmlElement(name="MediaId")
  private String MediaId;
  
  public String getMediaId()
  {
    return this.MediaId;
  }
  
  public void setMediaId(String mediaId)
  {
    this.MediaId = mediaId;
  }
}
