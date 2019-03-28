/**
 * 
 */
package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.PageHelper;
import com.pojo.Product;
import com.service.ProductService;

/**
 * @description :TODO
 * @author :m1582
 * @date :2018-10-14
 */
@Controller
public class ProductController {
	@Autowired
	private ProductService productService;
	@RequestMapping("/productDetails")
	public String selectProductById(@RequestParam("pid") String pid,Model model) {
		
		model.addAttribute("currentProduct", productService.selectProductById(Integer.parseInt(pid)));
		return "details.jsp";
	}
	
}
