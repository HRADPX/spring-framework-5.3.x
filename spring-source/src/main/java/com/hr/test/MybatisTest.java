package com.hr.test;

import com.hr.mybatis.service.UserService;
import config.ApplicationMybatisConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author huangran <huangran@kuaishou.com>
 * created on 2022-03-09
 */
public class MybatisTest {

	public static void main(String[] args){

		AnnotationConfigApplicationContext context =
				new AnnotationConfigApplicationContext(ApplicationMybatisConfiguration.class);
		UserService userService = context.getBean(UserService.class);
//		userService.add(null, "mybatis");
		System.out.println(userService.select(1, 23));
	}
}
