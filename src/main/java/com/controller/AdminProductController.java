/**
 * 
 */
package com.controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pojo.Product;
import com.service.AdminProductService;
import com.service.CategorySevice;
import com.service.ProductService;

/**
 * @description :TODO
 * @author :m1582
 * @date :2018-11-7
 */
@Controller
public class AdminProductController {

	@Autowired
	private ProductService productService;
	@Autowired
	private AdminProductService adminProductService;
	@Autowired
	private CategorySevice categorySevice;
	
	@RequestMapping("/admin-product-addPage")
	public String toAddPage(Model model) {
		model.addAttribute("categoryList", categorySevice.selectAllCategory());
		return "forward:/admin/productAddPage.jsp";
	}
	@RequestMapping("/admin-product-add")
	public String add(HttpSession session,String pname,Double marketPrice,Double shopPrice,
			          Integer isHot,Integer cid,String pdesc,Integer csid,MultipartFile image)
	throws IllegalStateException, IOException
	{
		if (image==null) {
			System.out.println("it is null");
		}
		if (image.getSize()<=0) {
			System.out.println("it is empty");
			
		}
		String filenameString=new Date().getTime()+image.getOriginalFilename();
		File file=new File(session.getServletContext().getRealPath("/products"),filenameString);
		if (!file.exists()) {
			file.mkdirs();
		}
		image.transferTo(file);
		Product product=new Product();
		product.setCsid(csid);
		product.setIsHot(isHot);
		product.setPname(pname);
		product.setShopPrice(shopPrice);
		product.setPdate(new Date());
		product.setPdesc(pdesc);
		product.setMarketPrice(marketPrice);
		product.setImage("/products/"+filenameString);
        
		adminProductService.insert(product);
        if ((long)session.getAttribute("recordcount")%6==0) {//加之前如果除得尽，页数加一
			session.setAttribute("lastPageNum-product", (Integer)session.getAttribute("lastPageNum-product")+1);
		}
		//总记录数加一
		session.setAttribute("recordcount", (long)session.getAttribute("recordcount")+1);
		
		return "forward:/admin-product-select?pn=-1";
	}
	
	
	@RequestMapping("/admin-product-editPage")
	public String toEditPage(@RequestParam("pid") Integer pid,Model model) {
		model.addAttribute("target", productService.selectByPrimaryKey(pid));
		model.addAttribute("categoryList", categorySevice.selectAllCategoriesWithSecondCategory());
		
		return "forward:/admin/productEditPage.jsp";
	}
	@RequestMapping("/admin-product-edit")
	public String edit(HttpSession session,String pname,Double marketPrice,Double shopPrice,
	          Integer isHot,Integer cid,String pdesc,Integer csid,MultipartFile image,Integer pid)
	          throws IllegalStateException, IOException {
		
		Product product=productService.selectByPrimaryKey(pid);
		
		if (image==null) {
			System.out.println("it is null");
		}
       if (image.getSize()>0) {//>0;说明图片被修改
    	   System.out.println(3456789);
    	   String filenameString=new Date().getTime()+image.getOriginalFilename();
	   		File file=new File(session.getServletContext().getRealPath("/products"),filenameString);
	   		if (!file.exists()) {
	   			file.mkdirs();
	   		}
	   		image.transferTo(file);
	   	    //删除原来图片
			File oldimageFile=new File(session.getServletContext().getRealPath("/")+product.getImage());
			oldimageFile.delete();
	   		product.setImage("/products/"+filenameString);
		}
		
		
		product.setCsid(csid);
		product.setIsHot(isHot);
		product.setPname(pname);
		product.setShopPrice(shopPrice);
		product.setPdate(new Date());
		product.setPdesc(pdesc);
		product.setMarketPrice(marketPrice);
		
		
		adminProductService.update(product);
		return "forward:/admin-product-select?pn=-2";
		
	}
	@RequestMapping("/admin-product-select")
	public String selectAllProduct(Integer pn,Model model,HttpSession session) {
		//第一次查，默认第一页
    	if (session.getAttribute("nowPageNum-product")==null) {
    		session.setAttribute("nowPageNum-product",1);
    		
    		pn=1;
    		
		}
    	else {
    		if (pn==-1) {
    			
    			pn=(Integer) session.getAttribute("lastPageNum-product");
			}
    		if (pn==-2) {
    			pn=(Integer) session.getAttribute("nowPageNum-product");
			}
    		
		}
    	
		PageHelper.startPage(pn, 6);
		PageInfo<Product> pageInfo=new PageInfo<>(productService.selectAllProduct(), 3);
		model.addAttribute("pageInfo",pageInfo );
		
		return "forward:/admin/right-product.jsp";
	
	}
	@RequestMapping("/admin-product-delete")
	public String delete(Integer pid,HttpSession session) {
		
		Product product=productService.selectByPrimaryKey(pid);
		
		File oldimageFile=new File(session.getServletContext().getRealPath("/")+product.getImage());
		
		oldimageFile.delete();
		
		adminProductService.delete(pid);
		return "forward:/admin-product-select?pn="+session.getAttribute("nowPageNum-product");
	}
}
