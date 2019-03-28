/**
 * 
 */
package com.controller;

import java.net.UnknownHostException;
import java.util.UUID;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.email.MailSendUtil;
import com.pojo.Msg;
import com.pojo.User;
import com.service.UserService;


/**
 * @description :用户处理器
 * @author :m1582 
 * @date :2018-10-3
 */
@Controller
public class UserController {
	@Autowired
	private UserService userService;
	
	@RequestMapping("/selectAllUser")
	public void selectAllUser() {
		
	}
	
	@RequestMapping("/register-page")
	public String registerpage() {
		return "register.jsp";
	}

	@RequestMapping("/register")
	public String register(User user) throws MessagingException, UnknownHostException {
		String code=UUID.randomUUID().toString().substring(0,8);
		//uuid随机码格式：xxxxxxxx-xxxx-xxxx-xxxx-xxxxxxxxxxxx
		user.setCode(code);
		user.setState(0);//未激活
		userService.insertUser(user); 
		MailSendUtil.sendEmail("1174762989@qq.com", "1174762989@qq.com", code);
		
		return "registerSuccess.jsp";
	}
	@RequestMapping("/active")
	public String activeUser(@Param(value="code") String code) {
		User user=userService.selectUserByCode(code);
		/*if (user==null) {
			return "error.jsp";
		}*/
		if (user==null) {
			return "login.jsp";
		}
		user.setState(1);
		user.setCode(null);
		//System.out.println("actived!");
		userService.updateUser(user);
		return "activeSuccess.jsp";
	}
	
	@RequestMapping("/login-page")
	public String loginPage(){
		
		return "login.jsp";
	}
	@RequestMapping("/login")
	public ModelAndView login(@RequestParam("username") String name,
			            @RequestParam("password") String password,
			            HttpSession session) {
		User user = userService.selectOneUser(name);
		
		ModelAndView modelAndView=new ModelAndView();
		if (user==null) {
			
			modelAndView.addObject("nameNotExist", "用户名不存在");
			modelAndView.setViewName("login.jsp");
			
		}
		else if (user.getPassword().equals(password)==false) {
			
			modelAndView.addObject("passwordError", "密码不正确");
			modelAndView.setViewName("login.jsp");

			
		}
		else if (user.getCode()!=null) {
			
			modelAndView.addObject("userNotActived", "用户未激活");
			modelAndView.setViewName("login.jsp");

		}
		else {
			//System.out.println(666);
			session.setAttribute("user",user);
			modelAndView.setViewName("index.jsp");
		}
		return modelAndView;
		
	}
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("user");
		return "login.jsp";
	}

	@RequestMapping("/checkName")
	@ResponseBody
	public Msg checkName(String name) {
		if (userService.selectOneUser(name)!=null) {
			return Msg.success();
		}
		return Msg.failure();//用户名不存在
	}

}
