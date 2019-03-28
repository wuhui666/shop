/**
 * 
 */
package com.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pojo.Msg;
import com.pojo.Orders;
import com.service.OrderService;

/**
 * @description :TODO
 * @author :m1582
 * @date :2018-11-12
 */
@Controller
public class AdminOrderController {

	@Autowired
	private OrderService orderService;
	
	@RequestMapping("/admin-order-select")
	public String selectOrders(Integer pn,Model model) {
		PageHelper.startPage(pn, 3);
		PageInfo<Orders> pageInfo=new PageInfo<>(orderService.selectAllOrder(), 3);
		model.addAttribute("pageInfo", pageInfo);
		return "forward:/admin/right-order.jsp";
	}
	@RequestMapping("/admin-order-detail")
	public String getorderMsg(Integer oid,Model model) {
		model.addAttribute("order", orderService.selectOrderById(oid));
		return "forward:/admin/admin-order-detail.jsp";
	}
	@RequestMapping("/admin-order-delivery")
	public String delivery(Integer oid,HttpSession session) {
		Orders order = orderService.selectOrderById(oid);
		order.setState(3);
		orderService.update(order);
		return "forward:/admin-order-select?pn="+session.getAttribute("nowPageNum-order");
	}
}
