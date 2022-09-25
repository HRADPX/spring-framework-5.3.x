package com.hr.tx;

import com.hr.mode.User;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Types;

/**
 * @author huangran <huangran@kuaishou.com>
 * created on 2022-04-17
 */
@Service
public class SqlUpdateImpl extends SqlUpdate {

	public SqlUpdateImpl(DataSource dataSource) {
		setDataSource(dataSource);
		setSql("update user set name = ? where id = ?");
		declareParameter(new SqlParameter("name", Types.VARCHAR));
		declareParameter(new SqlParameter("id", Types.INTEGER));
		compile();
	}

	public int update(User user) {
		return update(user.getName(), user.getId());
	}
}
