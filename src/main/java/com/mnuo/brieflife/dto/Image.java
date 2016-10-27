/**
 * Image.java created at 2016年10月27日 下午12:02:08
 */
package com.mnuo.brieflife.dto;

import javax.xml.bind.annotation.XmlElement;

/**
 * @author saxon
 */
public class Image{
	public String MediaId;

	public Image() {
	}
	public Image(String mediaId){
		this.MediaId = mediaId;
	}
	public String getMediaId() {
		return MediaId;
	}
	@XmlElement
	public void setMediaId(String mediaId) {
		MediaId = mediaId;
	}
}