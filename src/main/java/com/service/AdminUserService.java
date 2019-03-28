/**
 * 
 */
package com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.AdminUserMapper;
import com.pojo.AdminUser;
import com.pojo.AdminUserExample;
import com.pojo.AdminUserExample.Criteria;

/**
 * @description :TODO
 * @author :m1582
 * @date :2018-11-2
 */
@Service
public class AdminUserService {
	@Autowired
	private AdminUserMapper adminUserMapper;
	
	public AdminUser selectByNameAndPwd(String username, String pwd) {
		
		AdminUserExample example=new AdminUserExample();
		Criteria criteria=example.createCriteria();
		criteria.andPasswordEqualTo(pwd);
		criteria.andUsernameEqualTo(username);
		try {
			return adminUserMapper.selectByExample(example).get(0);
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
		
	}

}
