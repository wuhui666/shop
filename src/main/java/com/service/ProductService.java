/**
 * 
 */
package com.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.CategoryMapper;
import com.dao.ProductMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pojo.Category;
import com.pojo.CategorySencond;
import com.pojo.Product;
import com.pojo.ProductExample;
import com.pojo.ProductExample.Criteria;

/**
 * @description :TODO
 * @author :m1582
 * @date :2018-10-14
 */
@Service
public class ProductService {

	@Autowired
	private ProductMapper productMapper;
	@Autowired
	private CategoryMapper categoryMapper;
	
	public Product selectByPrimaryKey(int pid) {
		return productMapper.selectByPrimaryKey(pid);
	}

	/**
	 * 热门商品
	 * 
	 * @Description:TODO
	 */
	public List<Product> findHotProduct() {

		ProductExample example = new ProductExample();
		example.setOrderByClause("pdate DESC");

		Criteria criteria = example.createCriteria();
		criteria.andIsHotEqualTo(1);
		return productMapper.selectByExample(example);

	}

	/**
	 * 最新商品
	 * 
	 * @Description:TODO
	 */
	public List<Product> findNewProduct() {

		ProductExample example = new ProductExample();
		example.setOrderByClause("pdate DESC");
		return productMapper.selectByExample(example);

	}

	/**
	 * 商品详情
	 * 
	 * @Description:TODO
	 */
	public Product selectProductById(Integer pid) {

		return productMapper.selectByPrimaryKey(pid);
	}

	public List<Product> selectProductByCid(Integer cid,Integer pn) {
		List<Product> productList=new ArrayList<>();
		List<CategorySencond> categorySencondList = categoryMapper.selectByPrimaryKeyWithSecondCategory(cid).getCategorySencondList();
		List<Integer> csidList=new ArrayList<>();
		for (int i = 0; i < categorySencondList.size(); i++) {
			csidList.add(categorySencondList.get(i).getCsid());
		}
	
		    
			ProductExample example = new ProductExample();
			Criteria criteria=example.createCriteria();
			//System.out.println(Arrays.toString(csidList.toArray()));
			criteria.andCsidIn(csidList);
			
			PageHelper.startPage(pn, 16);//每页显示数量
			
			productList=productMapper.selectByExample(example);
			
			//System.out.println(productList.size()+".....");
		return productList;
	}

	public List<Product> selectProductByCsid(Integer csid,Integer pn) {
		List<Product> productList=new ArrayList<>();
		
			ProductExample example = new ProductExample();
			Criteria criteria=example.createCriteria();
			
			criteria.andCsidEqualTo(csid);
			PageHelper.startPage(pn, 9);
			productList=productMapper.selectByExample(example);
			
			//System.out.println(productList.size()+".....");
		return productList;
	}
	public List<Product> selectAllProduct() {
		return productMapper.selectByExample(null);
	}
}
