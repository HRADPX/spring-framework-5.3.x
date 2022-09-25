package com.hr.test;

import config.ApplicationLifeCycleConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author huangran <huangran@kuaishou.com>
 * created on 2022-03-20
 */
public class LifeCycleTest {

	public static void main(String[] args){

		AnnotationConfigApplicationContext applicationContext =
				new AnnotationConfigApplicationContext(ApplicationLifeCycleConfiguration.class);

		applicationContext.registerShutdownHook();
		applicationContext.stop();
	}

}
