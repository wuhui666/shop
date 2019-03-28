/**
 * 
 */
package com.controller;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pojo.Category;
import com.pojo.Product;
import com.service.CategorySevice;
import com.service.ProductService;

/**
 * @description :TODO
 * @author :m1582
 * @date :2018-10-2
 */
@Controller
public class CategoryController {
	
	@Autowired
	private CategorySevice categorySevice;
	@Autowired
	private ProductService productService;
	
	@RequestMapping("/index")
	public String index(HttpSession session) {
		List<Category> categorylist = categorySevice.selectAllCategoriesWithSecondCategory();
		List<Product> hotProductList = productService.findHotProduct();
		List<Product> newProductList = productService.findNewProduct();

		session.setAttribute("categorylist", categorylist);
		
		session.setAttribute("hotProductList", hotProductList);
		session.setAttribute("newProductList", newProductList);

		return "index.jsp";
	}
	
	/*@RequestMapping("/category")
	public String selectCategorySecondAndItsProduct(@RequestParam("cid") String cid,Model model) {
		model.addAttribute("secondCategoryList", categorySevice.selectAllSecondCategoriesByCid(Integer.parseInt(cid)));
		return "category.jsp";
	}*/
	@RequestMapping("/category")
	public String selectCategorySecondAndItsProduct(@RequestParam("cid") String cid,Model model,@RequestParam("pn") Integer pn) {
		
		PageInfo<Product> pageInfo=new PageInfo<>(productService.selectProductByCid(Integer.parseInt(cid),pn), 3);
		
		model.addAttribute("pageInfo", pageInfo);
		model.addAttribute("cid", cid);
		return "category.jsp";
	}

	@RequestMapping("/secondcategory")
	public String selectProductByCsid(@RequestParam("csid") String csid,Model model,@RequestParam("pn") Integer pn) {
		
		PageInfo<Product> pageInfo=new PageInfo<>(productService.selectProductByCsid(Integer.parseInt(csid),pn), 3);
		
		model.addAttribute("pageInfo", pageInfo);
		model.addAttribute("csid", csid);
		

		return "category.jsp";
	}
}
