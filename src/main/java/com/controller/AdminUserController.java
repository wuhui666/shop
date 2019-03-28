/**
 * 
 */
package com.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pojo.AdminUser;
import com.service.AdminUserService;

/**
 * @description :TODO
 * @author :m1582
 * @date :2018-11-2
 */
@Controller
public class AdminUserController {
	@Autowired
	private AdminUserService adminService;
	
	@RequestMapping("/admin")
	public String admin() {
		
		return "forward:/admin/index.jsp";
	}

	@RequestMapping("/admin-main")
	public String toMain(String username, String password,HttpServletRequest request) {
		
		AdminUser adminUser = adminService.selectByNameAndPwd(username, password);
		if (adminUser==null) {
			request.getSession().setAttribute("loginFailed", "用户名或密码错误");
			return "forward:/admin";
		}
		//success
		if (request.getSession().getAttribute("loginFailed")!=null) {
			request.getSession().setAttribute("loginFailed", "");
		}
		request.getSession().setAttribute("adminUser", adminUser);
		return "forward:/admin/main.jsp";
	}
	
}
