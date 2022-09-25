package com.hr.test;

import config.ApplicationConfiguration;
import com.hr.circular.CircularPrototype;
import com.hr.circular.CircularSingleton;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author huangran <huangran@kuaishou.com>
 * created on 2022-01-16
 */
public class circularTest {

	public static void main(String[] args){
		AnnotationConfigApplicationContext configApplicationContext =
				new AnnotationConfigApplicationContext(ApplicationConfiguration.class);
		CircularSingleton circularSingleton = configApplicationContext.getBean(CircularSingleton.class);
		CircularPrototype circularPrototype = configApplicationContext.getBean(CircularPrototype.class);

	}

}
