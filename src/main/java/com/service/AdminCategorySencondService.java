/**
 * 
 */
package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.CategorySencondMapper;
import com.pojo.CategorySencond;
import com.pojo.CategorySencondExample;
import com.pojo.CategorySencondExample.Criteria;

/**
 * @description :TODO
 * @author :m1582
 * @date :2018-11-4
 */
@Service
public class AdminCategorySencondService {

	@Autowired
	private CategorySencondMapper categorySencondMapper;
	
	public List<CategorySencond> selectAllCategorySencond() {
		return categorySencondMapper.selectByExample(null);
	}

	public List<CategorySencond> selectAllCategorySencondBycid(Integer cid) {
		CategorySencondExample example=new CategorySencondExample();
		Criteria criteria=example.createCriteria();
		criteria.andCidEqualTo(cid);
		return categorySencondMapper.selectByExample(example);
	}
	/**  
	 * @Description:TODO
	 */  
	public void insert(CategorySencond categorySencond) {
		// TODO Auto-generated method stub
		categorySencondMapper.insertSelective(categorySencond);
	}
	
}
