/**
 * 
 */
package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.OrdersMapper;
import com.pojo.Orders;
import com.pojo.OrdersExample;
import com.pojo.OrdersExample.Criteria;

/**
 * @description :TODO
 * @author :m1582
 * @date :2018-10-26
 */
@Service
public class OrderService {

	@Autowired
	private OrdersMapper ordersMapper;
	
	public void insertOrder(Orders orders) {
		ordersMapper.insertSelective(orders);
		
	}
	
	public Orders selectOrderById(int oid) {
		return ordersMapper.selectByPrimaryKeyExtend(oid);
		
	}
	public List<Orders> selectOrderByUid(int uid) {
		OrdersExample example=new OrdersExample();
		Criteria criteria=example.createCriteria();
		criteria.andUidEqualTo(uid);
		return ordersMapper.selectByExampleExtend(example);
	}
	public List<Orders> selectAllOrder() {
		return ordersMapper.selectByExample(null);
		
	}
	public void update(Orders orders) {
		ordersMapper.updateByPrimaryKeySelective(orders);
	}
	public void delete(Integer oid) {
		ordersMapper.deleteByPrimaryKey(oid);
	}
}
