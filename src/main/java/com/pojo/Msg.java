/**
 * 
 */
package com.pojo;

import java.util.HashMap;
import java.util.Map;

/**
 * @description :TODO
 * @author :m1582
 * @date :2018-11-9
 */
public class Msg {

	public int status;
	public String message;
	Map<String, Object> contentMap;
	
	public Msg add(String key,Object object) {
		this.contentMap.put(key, object);
		return this;
	}
	public  Msg() {
		contentMap=new HashMap<String, Object>();
	}
	public static Msg success() {
		
		Msg msg=new Msg();
		msg.setStatus(1);
		msg.setMessage("成功了");
		return msg;
	}
 public static Msg failure() {
		
		Msg msg=new Msg();
		msg.setStatus(0);
		msg.setMessage("失败了");
		return msg;
	}
	
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Map<String, Object> getContentMap() {
		return contentMap;
	}
	public void setContentMap(Map<String, Object> contentMap) {
		this.contentMap = contentMap;
	}
	
}
