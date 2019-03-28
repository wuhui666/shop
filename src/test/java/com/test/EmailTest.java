/**
 * 
 */
package com.test;

import java.io.File;

import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.internet.MimeMessage;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.email.EmailSenderBuilder;


/**
 * @description :TODO
 * @author :m1582
 * @date :2018-10-5
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring.xml"})
public class EmailTest {
	
	@Autowired
	public EmailSenderBuilder senderBuilder;
	
	@Test
	public void mailTest() {
		JavaMailSenderImpl sender =senderBuilder.build();
		SimpleMailMessage mimeMessage = new SimpleMailMessage();
		mimeMessage.setFrom("1174762989@qq.com");
		mimeMessage.setTo("1174762989@qq.com");//收件人
		mimeMessage.setSubject("测试邮箱，请忽略");
		mimeMessage.setText("测试邮箱，23333333");
		System.out.println(sender.getPort());
		System.out.println(sender.getHost());
		System.out.println(sender.getPassword());
		sender.send(mimeMessage);
		System.out.println("ojbk,完事了");
		
	}
	@Test
	public void mailTest2() throws MessagingException {
		JavaMailSenderImpl sender =senderBuilder.build();
		MimeMessage  mimeMessage =sender.createMimeMessage();
		MimeMessageHelper helper=new MimeMessageHelper(mimeMessage,true);
		helper.setFrom("1174762989@qq.com");
		helper.setTo("1174762989@qq.com");//收件人
		helper.setSubject("测试...");
		helper.setText("<html><body><img src='cid:image'>"
                + "<h4>Hello World!!!</h4>"
                + "</body></html>",true);
		//添加html内嵌图片
		helper.addInline("image",new File("C:\\Users\\m1582\\Pictures\\Saved Pictures\\me.ico"));
		//附件
		helper.addAttachment("233.ico", new File("C:\\Users\\m1582\\Pictures\\Saved Pictures\\me.ico"));
		
		sender.send(mimeMessage);
		System.out.println("ojbk,完事了");
		
	}
	
}
