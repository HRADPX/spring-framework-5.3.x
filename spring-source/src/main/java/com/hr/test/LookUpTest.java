package com.hr.test;

import config.ApplicationConfiguration;
import com.hr.lookup.AwareManager;
import com.hr.lookup.LookUpManager;
import com.hr.mode.User;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 单例 bean 引用原型 bean
 */
public class LookUpTest {

	public static void main(String[] args){

		AnnotationConfigApplicationContext configApplicationContext = new AnnotationConfigApplicationContext(ApplicationConfiguration.class);
		LookUpManager lookUpManager = configApplicationContext.getBean(LookUpManager.class);
		lookUpManager.process(new User(1, "hello lookUp"));
		lookUpManager.process(new User(2, "hello lookUp"));

		AwareManager awareManager = configApplicationContext.getBean(AwareManager.class);
		awareManager.process(new User(1, "hello aware"));
		awareManager.process(new User(2, "hello aware"));


	}

}
