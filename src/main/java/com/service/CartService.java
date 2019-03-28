/**
 * 
 */
package com.service;

import org.ietf.jgss.Oid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.OrderItemMapper;
import com.dao.ProductMapper;
import com.pojo.OrderItem;
import com.pojo.OrderItemExample;
import com.pojo.OrderItemExample.Criteria;
import com.pojo.Product;

/**
 * @description :TODO
 * @author :m1582
 * @date :2018-10-25
 */
@Service
public class CartService {

	@Autowired
	private OrderItemService orderItemService;
	@Autowired
	private ProductService productService;
	
	public void insertToCart(int quantity,int pid) {
		try {//商品存在，修改数量，不存在抛异常
			OrderItem orderItem2=orderItemService.selectByPid(pid).get(0);
			orderItem2.setCount(orderItem2.getCount()+quantity);//改数量
			//改小计
			orderItem2.setSubtotal(orderItem2.getCount()*orderItem2.getProduct().getShopPrice());
			
			orderItemService.updateByPrimaryKey(orderItem2);
			
		} catch (IndexOutOfBoundsException exception) {
			//新增订单商品项
			OrderItem orderItem = new OrderItem();
			orderItem.setPid(pid);
			orderItem.setCount(quantity);
			Product product=productService.selectByPrimaryKey(pid);
			orderItem.setProduct(product);
			orderItem.setSubtotal(product.getShopPrice()*quantity);
			
			orderItemService.insertSelective(orderItem);
		}
		
	}
}
