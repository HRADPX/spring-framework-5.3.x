package com.hr.test;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.hr.bean.CommonBeanIdService;

import config.ApplicationConfiguration;

/**
 * @author huangran <huangran@kuaishou.com>
 * Created on 2024-04-09
 */
public class CommonBeanIdTest {

	public static void main(String[] args){
		AnnotationConfigApplicationContext configApplicationContext =
				new AnnotationConfigApplicationContext(ApplicationConfiguration.class);
		configApplicationContext.getBean(CommonBeanIdService.class);
	}
}
