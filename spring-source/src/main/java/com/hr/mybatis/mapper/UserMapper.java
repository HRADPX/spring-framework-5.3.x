package com.hr.mybatis.mapper;

import com.hr.mode.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author huangran <huangran@kuaishou.com>
 * created on 2022-03-09
 */
public interface UserMapper {

	@Select({"select * from user where id = #{id}"})
	List<User> select(@Param("id") int id);

	@Insert("insert into user values(#{id},#{name})")
	void insert(@Param("id") Integer id, @Param("name") String name);
}
