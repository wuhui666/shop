/**
 * 
 */
package com.email;

import java.io.File;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

/**
 * @description :邮件发送工具类
 * @author :m1582
 * @date :2018-10-5
 */
@Component
public class MailSendUtil {

	//static的不能直接autowired,解决办法：在本类加上@Component让spring扫描到并通过自己创建的‘非静态’setter方法注入
	public static EmailSenderBuilder senderBuilder;
	
	@Autowired
	public void setSenderBuilder(EmailSenderBuilder senderBuilder) {
		MailSendUtil.senderBuilder = senderBuilder;
	}
	
	/**
	 * @throws MessagingException 
	 * @throws UnknownHostException 
	 * @Description:TODO
	 */

	public static void sendEmail(String from,String to,String code) throws MessagingException, UnknownHostException {
		
		JavaMailSenderImpl sender =senderBuilder.build();
		MimeMessage  mimeMessage =sender.createMimeMessage();
		MimeMessageHelper helper=new MimeMessageHelper(mimeMessage,true);
		helper.setFrom(from);
		helper.setTo(to);//收件人
		helper.setSubject("欢迎注册！");
		
		helper.setText("<html><body>"
                + "<h4>欢迎注册，点击以下链接激活账户：</h4>"
				+"</br>"
                +"http://"+InetAddress.getLocalHost().getHostAddress()+":8080/shop/active?code="
				+code
                + "</body></html>",true);
		
		sender.send(mimeMessage);
	}
	
}
