/**
 * 
 */
package com.email;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;


/**
 * @description :TODO
 * @author :m1582
 * @date :2018-10-5
 */
@Component
public class EmailSenderBuilder {
	/*private String protocol;默认smtp,不用了*/

	@Value("${host}")
	private String host;
	
	@Value("${port}")
	private  int port;

	@Value("${username}")
	private  String username;

	@Value("${password}")
	private  String password;

	@Value("${defaultEncoding}")
	private String defaultEncoding;
	
	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDefaultEncoding() {
		return defaultEncoding;
	}

	public void setDefaultEncoding(String defaultEncoding) {
		this.defaultEncoding = defaultEncoding;
	}
	
	public JavaMailSenderImpl build() {
		JavaMailSenderImpl sender=new JavaMailSenderImpl();
		sender.setDefaultEncoding(defaultEncoding);
		sender.setHost(host);
		sender.setPort(port);
		sender.setUsername(username);
		sender.setPassword(password);
		/*sender.setDefaultEncoding("UTF-8");
		sender.setHost("smtp.qq.com");
		sender.setPort(587);
		sender.setUsername("1174762989@qq.com");
		sender.setPassword("avltjczgsiyfigcf");*/
		return sender;
	}

}
