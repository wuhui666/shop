/**
 * 
 */
package com.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;

/**
 * @description :TODO
 * @author :m1582
 * @date :2018-10-8
 */
@Controller
public class VerifyCodeController {
	    @Autowired
	    private Producer captchaProducer;
	    
	    @RequestMapping("/verifyCode")
		public void getCodeImage(HttpServletRequest request, HttpServletResponse response)
				throws IOException {
			/* Expires过时期限值，指浏览器或缓存服务器在该时间点后必须从真正的服务器中获取新的页面信息 */
			response.setDateHeader("Expires", 0);
			/* 浏览器和缓存服务器都不应该缓存页面信息 */
//			response.setHeader("Cache-Control", "no-cache");
			/* 请求和响应的信息都不应该被存储在对方的磁盘系统中 */
//			response.setHeader("Cache-Control", "no-store");
			/* 浏览器和缓存服务器都可以缓存页面信息 */
//			response.setHeader("Cache-Control", "public");
			/* 对于客户机的每次请求，代理服务器必须向服务器验证缓存是否过时 */
//			response.setHeader("Cache-Control", "must-revalidate");
			
			response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
			/* 不让浏览器或中间缓存服务器缓存页面，配合Expires 置为0限定更保险 */
			response.setHeader("Pragma", "no-cache");
			/*
			 * response.setContentType(MIME)的作用是使客户端浏览器区分不同类型的数据，
			 * 并根据不同的MIME调用浏览器内部不同的程序嵌入模块来处理相应的数据
			 */
			response.setContentType("image/jpeg");
			
			response.addHeader("Cache-Control", "post-check=0, pre-check=0"); 
			/* 生成验证码 */
			String capText = captchaProducer.createText(); 
			/* 保存验证码到Session中 */
			//System.out.println("get:"+request.getSession().getAttribute("SessionKey"));
			request.getSession().setAttribute("SessionKey", capText);
			//System.out.println(request.getSession().getId());
			
			//System.out.println("set:"+request.getSession().getAttribute("SessionKey"));
			/* 使用给定文字创建图片 */
			
			BufferedImage bi = captchaProducer.createImage(capText);
			/* 数据写入输出流 */
			ServletOutputStream out = response.getOutputStream();
			
			ImageIO.write(bi, "jpg", out);
			try {
				out.flush();
			} finally {
				out.close();
			}
		}

	    @ResponseBody
	    @RequestMapping("/checkVerifyCode")
	    public String checkVerifyCode(@RequestParam("inputCode") String codeString,HttpServletRequest request) {
	    	 HttpSession session = request.getSession();
	    
	    	if (codeString.equalsIgnoreCase((String)session.getAttribute("SessionKey"))) {
	    		return "true";
			}
	    	else {
	    		
				return "false";
			}
			
		}
	    
}
