package com.hr.test;

import com.hr.mode.User;
import com.hr.tx.SimpleJdbcService;
import config.ApplicationTxConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author huangran <huangran@kuaishou.com>
 * created on 2022-04-17
 */
public class SimpleJdbcTest {

	public static void main(String[] args){

		AnnotationConfigApplicationContext configApplicationContext =
				new AnnotationConfigApplicationContext(ApplicationTxConfiguration.class);
		SimpleJdbcService simpleJdbcService = configApplicationContext.getBean(SimpleJdbcService.class);
//		System.out.println(simpleJdbcService.insert(new User(1, "simpleJdbcInsert")));
//		System.out.println(simpleJdbcService.insertReturnPrimaryKey(new User(1, "simpleJdbcInsert")));
		System.out.println(simpleJdbcService.insertByBeanProperty(new User(0, "beanProperty")));
	}

}
