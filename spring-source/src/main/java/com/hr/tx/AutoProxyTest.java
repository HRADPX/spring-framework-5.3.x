package com.hr.tx;

import com.hr.autoproxy.AutoProxyService;
import config.ApplicationAutoProxyConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author huangran <huangran@kuaishou.com>
 * created on 2022-04-06
 */
public class AutoProxyTest {

	public static void main(String[] args){

		AnnotationConfigApplicationContext applicationContext =
				new AnnotationConfigApplicationContext(ApplicationAutoProxyConfiguration.class);
		AutoProxyService autoProxyService = applicationContext.getBean(AutoProxyService.class);
		autoProxyService.execute("autoProxy");
	}

}
