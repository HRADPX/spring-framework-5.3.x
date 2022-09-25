package com.hr.tx;

import com.hr.mode.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import java.sql.JDBCType;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.*;

/**
 * @author huangran <huangran@kuaishou.com>
 * created on 2022-01-19
 */
@Service
public class UserMapper {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	private SimpleJdbcInsert simpleJdbcInsert;

	@PostConstruct
	public void init() {
		if (namedParameterJdbcTemplate == null) {
			namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
		}
		if (simpleJdbcInsert == null) {
			simpleJdbcInsert = new SimpleJdbcInsert(Objects.requireNonNull(jdbcTemplate.getDataSource()))
					.withTableName("user");
		}
	}

	public int insertModer(User user) {
		String sql = "insert into user(id,name) values(?,?)";
		int update = jdbcTemplate.update(sql, null, user.getName());
		return update;
	}

	public int updateModel(User user, boolean isException) {

		String sql = "update user set name = ? where id = ?";
		int update = jdbcTemplate.update(sql, user.getName(), user.getId());
		if (isException) {
			int i = 1 / 0;
		}
		return update;
	}

	public List<User> queryByIdsByNameParameter(Collection<Integer> userIdList) {
		String sql = "select * from user where id in (:ids)";
		Map<String, Object> params = new HashMap<>();
		params.put("ids", userIdList);
		List<User> userList =
				namedParameterJdbcTemplate.query(sql, params, new BeanPropertyRowMapper<>(User.class));
		return CollectionUtils.isEmpty(userList) ? Collections.emptyList() : userList;
	}

	public List<User> queryByIds(Collection<Integer> userIdList) {
		String sql = "select * from user where id in (:ids)";
		Map<String, Object> params = new HashMap<>();
		params.put("ids", userIdList);
		List<User> userList =
				namedParameterJdbcTemplate.query(sql, params, new BeanPropertyRowMapper<>(User.class));
		return CollectionUtils.isEmpty(userList) ? Collections.emptyList() : userList;
	}


	public int insertReturningPrimaryKey(User user) {
		String sql = "insert into user(id,name) values(?,?)";
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(con -> {
			PreparedStatement ps = con.prepareStatement(sql, new String[]{"id, name"});
			ps.setNull(1, JDBCType.NULL.getVendorTypeNumber());
			ps.setString(2, user.getName());
			return ps;
		}, keyHolder);
		return Objects.requireNonNull(keyHolder.getKey()).intValue();
	}

	/*  -----------------------------  Batch Operation  ------------------------------ */
	/**
	 * Batch update operation by JdbcTemplate
	 */
	public int[] batchUpdate(List<User> userList) {

		if (CollectionUtils.isEmpty(userList)) {
			return new int[0];
		}
		String sql = "update user set name = ? where id = ?";
//		List<Object[]> args = userList.stream()
//				.map(user -> new Object[]{user.getName(), user.getId()})
//				.collect(Collectors.toList());
		// 这里可以给定参数的类型数组，避免参数是 NULL 的场景可能导致的问题
//		this.jdbcTemplate.batchUpdate(sql, args);
		return this.jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				User user = userList.get(i);
				ps.setString(1, user.getName());
				ps.setInt(2, user.getId());
			}

			@Override
			public int getBatchSize() {
				return userList.size();
			}
		});
	}

	/**
	 * Batch update operation by NamedParameterJdbcTemplate
	 */
	public int[] batchUpdateByNameParameter(List<User> userList) {

		if (CollectionUtils.isEmpty(userList)) {
			return new int[0];
		}
		String sql = "update user set name = :name where id = :id";
		return this.namedParameterJdbcTemplate.batchUpdate(sql, SqlParameterSourceUtils.createBatch(userList));
	}

	/*  -----------------------------  SimpleJdbc  ------------------------------  */
	public int insertBySimpleJdbc(User user) {
		return simpleJdbcInsert.execute(Collections.singletonMap("name", user.getName()));
	}

	/**
	 * 如果 auto-generated columns 不是数字，则可以调用 executeAndReturnKeyHolder
	 */
	public int insertReturnPrimaryKeyBySimpleJdbc(User user) {

		Number keyNumber = this.simpleJdbcInsert.usingGeneratedKeyColumns("id")
				.executeAndReturnKey(Collections.singletonMap("name", user.getName()));
		return keyNumber.intValue();
	}

	/**
	 * BeanPropertySqlParameterSource 和 MapSqlParameterSource 区别在于后者可以作名称映射，
	 * 而前者要求 SQL 中的参数名和 java-bean 中的属性名称一致
	 */
	public int insertByBeanProperty(User user) {
		return this.simpleJdbcInsert.execute(new BeanPropertySqlParameterSource(user));
	}

	public int insertByMapArgs(User user) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("name", user.getName());
		return this.simpleJdbcInsert.execute(params);
	}

	/*  -----------------------------  SqlQuery  ------------------------------  */
	/** @see MappingSqlQueryImpl */
	/** @see SqlUpdateImpl */
}
