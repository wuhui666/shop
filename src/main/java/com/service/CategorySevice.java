/**
 * 
 */
package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.CategoryMapper;
import com.dao.CategorySencondMapper;
import com.pojo.Category;
import com.pojo.CategorySencond;
import com.pojo.CategorySencondExample;
import com.pojo.CategorySencondExample.Criteria;

/**
 * @description :TODO
 * @author :m1582
 * @date :2018-10-13
 */
@Service
public class CategorySevice {
	@Autowired
	private CategoryMapper categoryMapper;
	
	@Autowired
	private CategorySencondMapper categorySencondMapper;
	/***
	 * 一级分类
	 * @Description:TODO
	 */
	public List<Category> selectAllCategoriesWithSecondCategory() {
		return categoryMapper.selectByExampleWithSecondCategory(null);
	}

	public Category selectCategoryByCid(int cid) {
		return categoryMapper.selectByPrimaryKey(cid);
	}
	public void updateCategoryByCid(Category category) {
		categoryMapper.updateByPrimaryKeySelective(category);
	}
	public void deleteCategoryByCid(int cid) {
		categoryMapper.deleteByPrimaryKey(cid);
	}
	/**
	 * 二级分类
	 * @Description:TODO
	 */
	public List<CategorySencond> selectAllSecondCategoriesByCid(Integer cid) {
		CategorySencondExample example=new CategorySencondExample();
		example.setOrderByClause("csid ASC");
    	Criteria criteria=example.createCriteria();
    	criteria.andCidEqualTo(cid);
		return categorySencondMapper.selectByExample(example);
	}
	
	public void insert(Category category) {
		categoryMapper.insertSelective(category);
	}
	//不带二级分类
	public List<Category> selectAllCategory() {
		return categoryMapper.selectByExample(null);
	}

	/**  
	 * @Description:TODO
	 */  
	public void deleteCategorySencondByCsid(int csid) {
		// TODO Auto-generated method stub
		categorySencondMapper.deleteByPrimaryKey(csid);
	}

	/**  
	 * @Description:TODO
	 */  
	public  CategorySencond selectCategorySencondByCsid(int csid) {
		// TODO Auto-generated method stub
		return categorySencondMapper.selectByPrimaryKey(csid);
		
	}

	/**  
	 * @Description:TODO
	 */  
	public void updateCategorySencondBycsid(CategorySencond categorySencond) {
		// TODO Auto-generated method stub
		categorySencondMapper.updateByPrimaryKeySelective(categorySencond);
	}
}
