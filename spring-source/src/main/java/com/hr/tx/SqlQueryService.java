package com.hr.tx;

import com.hr.mode.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author huangran <huangran@kuaishou.com>
 * created on 2022-04-17
 */
@Service
public class SqlQueryService {

	@Autowired
	private MappingSqlQueryImpl mappingSqlQuery;
	@Autowired
	private SqlUpdateImpl sqlUpdate;

	public User queryUserBySqlQuery(int id) {
		return mappingSqlQuery.findObject(id);
	}

	public int update(User user) {
		return sqlUpdate.update(user);
	}
}
