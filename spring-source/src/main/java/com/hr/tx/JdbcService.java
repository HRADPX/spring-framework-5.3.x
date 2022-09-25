package com.hr.tx;

import com.hr.mode.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author huangran <huangran@kuaishou.com>
 * created on 2022-04-17
 */
@Service
public class JdbcService {

	@Autowired
	private UserMapper userMapper;

	public int insertReturnPrimaryId(User user) {
		return userMapper.insertReturningPrimaryKey(user);
	}

	public int[] batchUpdate(List<User> userList) {
		return userMapper.batchUpdateByNameParameter(userList);
	}
}
