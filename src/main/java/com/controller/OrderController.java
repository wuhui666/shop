/**
 * 
 */
package com.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.support.RequestContext;

import com.pojo.OrderItem;
import com.pojo.Orders;
import com.pojo.User;
import com.service.OrderItemService;
import com.service.OrderService;

/**
 * @description :TODO
 * @author :m1582
 * @date :2018-10-26
 */
@Controller
public class OrderController {

	@Autowired
	private OrderService orderService;
	@Autowired
	private OrderItemService orderItemService;
	
	@RequestMapping("/toOrderPage")
	public String toOrderPage(int oid,Model model) {
		model.addAttribute("orderItemList", orderService.selectOrderById(oid).getOrderItemList());
		model.addAttribute("oid", oid);
		return "order.jsp";
	}
	@RequestMapping("/toPaySuccessPage")
	public String paySuccess(Integer oid,String name,String addr,String phone) {
		Orders orders=orderService.selectOrderById(oid);
		orders.setName(name);
		orders.setAddr(addr);
		orders.setPhone(phone);
		orders.setState(2);
		orderService.update(orders);
		return "paySuccessPage.jsp";
	}
	@RequestMapping("/orderSubmit")
	public String insertOrder(HttpSession session,Model model) {
		//不用session呢?????
		/*ServletRequestAttributes servletRequestAttributes=(ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
		HttpSession session=servletRequestAttributes.getRequest().getSession();*/
		Orders orders=new Orders();
		List<OrderItem> orderItemList=orderItemService.selectOrderItemsInCart();
		if (orderItemList.size()==0) {
			return "cart.jsp";
		}
		orders.setOrderItemList(orderItemList);
		orders.setOrdertime(new Date());
		orders.setState(1);//1,未付款 2，已付款，未发货 3，已发货 4，交易完成
		double total=0;
		for (OrderItem orderItem : orderItemList) {
			total+=orderItem.getSubtotal();
		}
		orders.setTotal(total);
		User user=(User) session.getAttribute("user");
		if (user==null) {
			return "login.jsp";
		}
		orders.setUser(user);
		orders.setUid(user.getUid());
		orderService.insertOrder(orders);
		
		for (OrderItem orderItem2 : orderItemList) {
			orderItem2.setOid(orders.getOid());
			orderItemService.updateByPrimaryKey(orderItem2);
		}
		
		
		model.addAttribute("orderItemList",orderService.selectOrderById(orders.getOid()).getOrderItemList());
		//其实可以直接从order获取list
		
		return "order.jsp";
	}
	@RequestMapping("/myOrder")
	public String selectOrdersByUid(HttpServletRequest request,Model model) {
		User user=(User)request.getSession().getAttribute("user");
		if (user==null) {
			return "login.jsp";
		}
		
		model.addAttribute("orderList", orderService.selectOrderByUid(user.getUid()));
		System.out.println(orderService.selectOrderByUid(user.getUid()).get(0));
		//System.out.println(orderService.selectOrderByUid(user.getUid()).get(0).getOrderItemList().get(0).getProduct().getPdesc());
		return "myOrder.jsp";
	}
	@RequestMapping("/cancelOrder")
	public String cancel(Integer oid) {
		Orders order=orderService.selectOrderById(oid);
		for (OrderItem orderItem : order.getOrderItemList()) {
			orderItemService.deleteByItemid(orderItem.getItemid());
		}
		
		orderService.delete(oid);
		return "forward:/myOrder";
	}
	@RequestMapping("/finishOrder")
	public String finishOrder(Integer oid) {
		Orders order = orderService.selectOrderById(oid);
		order.setState(4);
		orderService.update(order);
		return "forward:/myOrder";
	}
}
