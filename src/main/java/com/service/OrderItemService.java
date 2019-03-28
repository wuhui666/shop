/**
 * 
 */
package com.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.OrderItemMapper;
import com.dao.OrdersMapper;
import com.pojo.OrderItem;
import com.pojo.OrderItemExample;
import com.pojo.OrderItemExample.Criteria;
import com.pojo.Orders;

/**
 * @description :TODO
 * @author :m1582
 * @date :2018-10-26
 */
@Service
public class OrderItemService {
	@Autowired
	private OrderItemMapper orderItemMapper;
	@Autowired
	private OrdersMapper ordersMapper;
	
	public List<OrderItem> selectOrderItemsInCart() {
		OrderItemExample example=new OrderItemExample();
		Criteria criteria=example.createCriteria();
		criteria.andOidIsNull();
		return orderItemMapper.selectByExampleWithProduct(example);
	}
	
	public List<OrderItem> selectByPid(int pid) {
		OrderItemExample example=new OrderItemExample();
		Criteria criteria=example.createCriteria();
		criteria.andPidEqualTo(pid);
		return orderItemMapper.selectByExampleWithProduct(example);
	}

	public void insertSelective(OrderItem item) {
		orderItemMapper.insertSelective(item);
	}
	
	public void updateByPrimaryKey(OrderItem orderItem) {
		orderItemMapper.updateByPrimaryKeySelective(orderItem);
	}
	//清空购物车
	public void deleteItemFromCart() {
		OrderItemExample example=new OrderItemExample();
		Criteria criteria=example.createCriteria();
		/*List<Integer> orderIdList=new ArrayList<>();
		for (Orders orders : ordersMapper.selectByExample(null)) {
			orderIdList.add(orders.getOid());
		};
		criteria.andOidNotIn(orderIdList);*/
		criteria.andOidIsNull();
		orderItemMapper.deleteByExample(example);
	}
	public void deleteByItemid(int itemid) {
		orderItemMapper.deleteByPrimaryKey(itemid);
	}
}
