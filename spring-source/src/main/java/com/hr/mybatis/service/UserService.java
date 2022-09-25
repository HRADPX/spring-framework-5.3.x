package com.hr.mybatis.service;

import com.hr.mode.User;
import com.hr.mybatis.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author huangran <huangran@kuaishou.com>
 * created on 2022-03-09
 */
@Service
public class UserService {
	@Autowired
	UserMapper userMapper;

//	public List<Map<Integer,String>> query(){
//		return userMapper.select();
//	}

	//	@Transactional
	public void add(Integer id, String name){

		userMapper.insert(id,name);
//		int i = 1 / 0;
	}

	public List<User> select(int id, int id2) {
		return userMapper.select(id);
	}

}
