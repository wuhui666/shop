
package com.test;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.dao.CategoryMapper;
import com.pojo.Category;
import com.pojo.User;
import com.service.CategorySevice;
import com.service.UserService;

/**
 * @description :TODO
 * @author :m1582
 * @date :2018-10-3
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:spring.xml")
public class Test {
	@Autowired
	private CategoryMapper categoryMapper;
	@org.junit.Test
	public void test() {  
		//categoryMapper.selectByPrimaryKeyWithSecondCategory(cid)
	}

}
