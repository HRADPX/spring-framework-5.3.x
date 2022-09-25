package com.hr.tx;

import com.hr.mode.User;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.MappingSqlQuery;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

/**
 * @author huangran <huangran@kuaishou.com>
 * created on 2022-04-17
 */
@Service
public class MappingSqlQueryImpl extends MappingSqlQuery<User> {

	public MappingSqlQueryImpl(DataSource dataSource) {
		super(dataSource, "select * from user where id in (?)");
		declareParameter(new SqlParameter("id", Types.INTEGER));
		compile();
	}

	@Override
	protected User mapRow(ResultSet rs, int rowNum) throws SQLException {
		User user = new User();
		user.setId(rs.getInt("id"));
		user.setName(rs.getString("name"));
		return user;
	}
}
