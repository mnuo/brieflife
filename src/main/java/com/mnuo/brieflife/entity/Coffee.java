/**
 * Coffee.java created at 2016年10月21日 下午3:43:02
 */
package com.mnuo.brieflife.entity;

/**
 * @author saxon
 */
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "coffee")
public class Coffee extends BaseMessage{
	String name;
	int quanlity;
	public String getName() {
		return name;
	}
	@XmlElement
	public void setName(String name) {
		this.name = name;
	}
	public int getQuanlity() {
		return quanlity;
	}
	@XmlElement
	public void setQuanlity(int quanlity) {
		this.quanlity = quanlity;
	}
	public Coffee(String name, int quanlity) {
		this.name = name;
		this.quanlity = quanlity;
	}
	public Coffee() {
	}
}
