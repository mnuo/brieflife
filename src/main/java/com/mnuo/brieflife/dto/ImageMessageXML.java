/**
 * TextMessageXML.java created at 2016年10月26日 下午12:19:54
 */
package com.mnuo.brieflife.dto;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author saxon
 */
@XmlRootElement(name="xml")
public class ImageMessageXML extends BaseMessageXML {
	// 回复的消息内容
	private Image Image;

	public Image getImage() {
		return Image;
	}
	@XmlElement(name="Image")
	public void setImage(Image image) {
		Image = image;
	}
}

