package com.hr.test;

import com.hr.autoproxy.BeanNameProxyService;
import config.ApplicationAutoProxyConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author huangran <huangran@kuaishou.com>
 * created on 2022-04-10
 */
public class AutoProxyTest {

	public static void main(String[] args){

		AnnotationConfigApplicationContext applicationContext =
				new AnnotationConfigApplicationContext(ApplicationAutoProxyConfiguration.class);
		BeanNameProxyService beanNameProxyService = applicationContext.getBean(BeanNameProxyService.class);
		beanNameProxyService.beanNameProxy("beanNameAutoProxy");
	}

}
