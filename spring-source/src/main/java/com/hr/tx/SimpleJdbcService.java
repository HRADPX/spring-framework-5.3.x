package com.hr.tx;

import com.hr.mode.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author huangran <huangran@kuaishou.com>
 * created on 2022-04-17
 */
@Service
public class SimpleJdbcService {

	@Autowired
	private UserMapper userMapper;

	public int insert(User user) {
		return userMapper.insertBySimpleJdbc(user);
	}

	public int insertReturnPrimaryKey(User user) {
		return userMapper.insertReturnPrimaryKeyBySimpleJdbc(user);
	}

	public int insertByBeanProperty(User user) {
		return userMapper.insertByBeanProperty(user);
	}
}
