/**
 * 
 */
package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.dao.OrderItemMapper;
import com.pojo.OrderItem;
import com.service.CartService;
import com.service.OrderItemService;

/**
 * @description :TODO
 * @author :m1582
 * @date :2018-10-25
 */
@Controller
public class CartController {

	@Autowired
	CartService cartService;
	@Autowired
	private OrderItemService orderItemService;
	
	@RequestMapping("/addToCart")
	public String addToCart(int quantity,int pid,Model model) {
		
		cartService.insertToCart(quantity, pid);
		List<OrderItem> orderItemList = orderItemService.selectOrderItemsInCart();
		model.addAttribute("orderItemList", orderItemList);
		return "cart.jsp";
	}
	@RequestMapping("/clearCart")
	public String clearCart(Model model) {
		orderItemService.deleteItemFromCart();
		List<OrderItem> orderItemList = orderItemService.selectOrderItemsInCart();
		model.addAttribute("orderItemList", orderItemList);
		return "cart.jsp";
	}
	@RequestMapping("/deleteItem")
	public String deleteItem(int itemid,Model model) {
		orderItemService.deleteByItemid(itemid);
		List<OrderItem> orderItemList = orderItemService.selectOrderItemsInCart();
		model.addAttribute("orderItemList", orderItemList);
		return "cart.jsp";
	}
	@RequestMapping("/toCartPage")
	public String toCartPage(Model model) {
		List<OrderItem> orderItemList = orderItemService.selectOrderItemsInCart();
		model.addAttribute("orderItemList", orderItemList);
		return "cart.jsp";
	}
}
