/**
 * 
 */
package com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.dao.ProductMapper;
import com.pojo.Product;

/**
 * @description :TODO
 * @author :m1582
 * @date :2018-11-9
 */
@Controller
public class AdminProductService {
	   @Autowired
      private ProductMapper productMapper;
	   
	   public void insert(Product product) {
		productMapper.insertSelective(product);
	}
	   public void update(Product product) {
			productMapper.updateByPrimaryKeySelective(product);
		}
	   public void delete(Integer pid) {
		productMapper.deleteByPrimaryKey(pid);
	}
}
