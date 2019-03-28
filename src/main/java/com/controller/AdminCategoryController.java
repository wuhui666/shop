/**
 * 
 */
package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pojo.Category;
import com.pojo.Msg;
import com.service.CategorySevice;

/**
 * @description :TODO
 * @author :m1582
 * @date :2018-11-2
 */
@Controller
public class AdminCategoryController {

	@Autowired
	CategorySevice categorySevice;
	
	@RequestMapping("/admin-category-select")
	public String selectAllCategory(Model model) {
		//新增的是木有二级分类的，所以调用这个方法查不出新增的一级分类，坑！mmp
		 //List<Category> cList=categorySevice.selectAllCategoriesWithSecondCategory();
		 List<Category> cList=categorySevice.selectAllCategory();
		//System.out.println(777);
		 model.addAttribute("categoryList", cList);
		 return "forward:/admin/right.jsp";
	}
	@RequestMapping("/admin-category-addPage")
	public String CategoryAddPage() {
		return "forward:/admin/categoryAddPage.jsp";
	}
	
	@RequestMapping("/admin-category-add")
	public String CategoryAdd(String cname) {
		Category category = new Category();
		category.setCname(cname);
		categorySevice.insert(category);
		return "forward:/admin-category-select";
	}
	
	@RequestMapping("/admin-category-editPage")
	public String CategoryEditPage(int cid,Model model) {
		
		model.addAttribute("category", categorySevice.selectCategoryByCid(cid));
		return "forward:/admin/categoryEditPage.jsp";
	}
	
	@RequestMapping("/admin-category-edit")
	public String CategoryEdit(Category category) {
		categorySevice.updateCategoryByCid(category);
		return "forward:/admin-category-select";

	}
	@RequestMapping("/admin-category-delete")
	public String CategoryDelete(int cid) {
		categorySevice.deleteCategoryByCid(cid);
		return "forward:/admin-category-select";

	}
	
}
