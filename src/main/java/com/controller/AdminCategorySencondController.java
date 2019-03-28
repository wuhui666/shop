/**
 * 
 */
package com.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pojo.Category;
import com.pojo.CategorySencond;
import com.pojo.Msg;
import com.service.AdminCategorySencondService;
import com.service.CategorySevice;



/**
 * @description :TODO
 * @author :m1582
 * @date :2018-11-4
 */
@Controller
public class AdminCategorySencondController {
	@Autowired
	private AdminCategorySencondService categorySencondSevice;
	@Autowired
	private CategorySevice categorySevice;
	
	@RequestMapping("/admin-categorysencond-select")
	public String selectCategorySencondSencond(Integer pn,Model model,HttpServletRequest request) {
		HttpSession session=request.getSession();
        
        	//第一次查，默认第一页
        	if (session.getAttribute("nowPageNum")==null) {
        		session.setAttribute("nowPageNum",1);
        		pn=1;
        		System.out.println(1);
			}
        	else {
        		if (pn==-1) {
        			pn=(Integer) session.getAttribute("lastPageNum");
				}
        		else if (pn==-2) {
        			pn=(Integer) session.getAttribute("nowPageNum");
				}
        		else {
					System.out.println(909);
				}
			}
        	
		PageHelper.startPage(pn, 6);
		PageInfo<CategorySencond> pageInfo=new PageInfo<>(categorySencondSevice.selectAllCategorySencond(),3);
		model.addAttribute("pageInfo", pageInfo);
		
		return "forward:/admin/right-categorysencond.jsp";
		
	}

	@RequestMapping("/admin-categorySencond-addPage")
	public String CategorySencondAddPage(Model model) {
		model.addAttribute("categoryList", categorySevice.selectAllCategory());
		return "forward:/admin/categorySencondAddPage.jsp";
	}
	
	@RequestMapping("/admin-categorySencond-add")
	public String CategorySencondAdd(String csname,int cid) {
		CategorySencond categorySencond = new CategorySencond();
		categorySencond.setCsname(csname);
		categorySencondSevice.insert(categorySencond);
		
		return "forward:/admin-categorysencond-select?pn=-1";
	}
	
	@RequestMapping("/admin-categorysencond-editPage")
	public String CategorySencondEditPage(int csid, Model model) {

		model.addAttribute("categorysencond",categorySevice.selectCategorySencondByCsid(csid));
		model.addAttribute("categoryList", categorySevice.selectAllCategory());
	
		return "forward:/admin/categorySencondEditPage.jsp";
	}

	@RequestMapping("/admin-categorySencond-edit")
	public String CategorySencondEdit(CategorySencond categorySencond) {
		System.out.println(categorySencond.getCsname());
		System.out.println(categorySencond.getCsid());
		categorySevice.updateCategorySencondBycsid(categorySencond);
		return "forward:/admin-categorysencond-select?pn=-2";

	}
	
	@RequestMapping("/admin-categorysencond-delete")
	public String CategorySencondDelete(int cid) {
		categorySevice.deleteCategorySencondByCsid(cid); 
		return "forward:/admin-categorysencond-select?pn=-2";

	}
	@ResponseBody
	@RequestMapping("/getCategorySecondList")
	public Msg getCategoryList(@RequestParam("cid")Integer cid) {
		System.out.println(cid);
		List<CategorySencond> csList=categorySencondSevice.selectAllCategorySencondBycid(cid);
		
		return Msg.success().add("categorysecondList", csList);
	}
}
