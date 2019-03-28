/**
 * 
 */
package com.test;

import org.springframework.beans.factory.annotation.Value;

/**
 * @description :TODO
 * @author :m1582
 * @date :2018-10-5
 */
public class MMp {

	@Value("#{email-config.host}")
	private static String host;
	
	@Value("#{email-config.port}")
	private static int port;

	@Value("#{email-config.username}")
	private static String username;

	@Value("#{email-config.password}")
	private static String password;

	@Value("#{email-config.defaultEncoding}")
	private static String defaultEncoding;
	
	public static void main(String[] args) {
		System.out.println(port);
	}

}
