package com.hr.test;

import config.ApplicationConfiguration;
import com.hr.mode.User;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author huangran <huangran@kuaishou.com>
 * created on 2022-01-03
 */
public class BeanStaticBeanPostProcessorTest {

	public static void main(String[] args) {

		AnnotationConfigApplicationContext configApplicationContext = new AnnotationConfigApplicationContext(ApplicationConfiguration.class);
		User user = (User) configApplicationContext.getBean("userService");
		ApplicationConfiguration applicationConfiguration = configApplicationContext.getBean(ApplicationConfiguration.class);
//		System.out.println(applicationConfiguration.environment);
		System.out.println(user);
	}
}
