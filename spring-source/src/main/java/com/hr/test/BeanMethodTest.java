package com.hr.test;

import config.ApplicationConfiguration;
import com.hr.mode.BeanProxy;
import com.hr.mode.User;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author huangran <huangran@kuaishou.com>
 * created on 2022-01-02
 */
public class BeanMethodTest {

	public static void main(String[] args){
		AnnotationConfigApplicationContext configApplicationContext =
				new AnnotationConfigApplicationContext(ApplicationConfiguration.class);
		User user = configApplicationContext.getBean(User.class);
		BeanProxy beanProxy = configApplicationContext.getBean(BeanProxy.class);
		System.out.println(user == beanProxy.getUser());
		System.out.println(beanProxy);

	}
}
