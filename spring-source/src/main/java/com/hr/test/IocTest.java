package com.hr.test;

import com.hr.ApplicationConfiguration;
import com.hr.mode.User;
import com.hr.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class IocTest {

	public static void main(String[] args){

		AnnotationConfigApplicationContext configApplicationContext =
				new AnnotationConfigApplicationContext(ApplicationConfiguration.class);
		UserService userService = configApplicationContext.getBean(UserService.class);
		userService.printUser(new User(1, "hello spring"));
	}

}
