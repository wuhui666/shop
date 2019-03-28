/**
 * 
 */
package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.UserMapper;
import com.pojo.User;
import com.pojo.UserExample;
import com.pojo.UserExample.Criteria;

/**
 * @description :TODO
 * @author :m1582
 * @date :2018-10-3
 */
@Service
public class UserService {
	@Autowired
	private UserMapper userMapper;
	
	public List<User> selectAllUser() {
		return userMapper.selectByExample(null);
	}
    public void insertUser(User user) {
		userMapper.insertSelective(user);
	}
    public User selectUserByCode(String code) {
    	UserExample userExample=new UserExample();
    	Criteria criteria=userExample.createCriteria();
    	criteria.andCodeEqualTo(code);
    	if (userMapper.selectByExample(userExample).size()==0) {
			return null;
		}
		return userMapper.selectByExample(userExample).get(0);
		
	}
    public void updateUser(User user) {
		userMapper.updateByPrimaryKey(user);
	}
    
    public User selectOneUser(String username) {
    	UserExample example=new UserExample();
    	Criteria criteria=example.createCriteria();
    	criteria.andUsernameEqualTo(username);
    	if (userMapper.selectByExample(example).size()!=0) {
    		return userMapper.selectByExample(example).get(0);
		}
		return null;
	}
}
