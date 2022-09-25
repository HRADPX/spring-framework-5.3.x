package com.hr.test;

import com.hr.autowired.AutowiredMethodService;
import config.ApplicationAutowiredConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author huangran <huangran@kuaishou.com>
 * created on 2022-02-15
 */
public class AutowiredText {

	public static void main(String[] args){

		AnnotationConfigApplicationContext applicationContext =
				new AnnotationConfigApplicationContext(ApplicationAutowiredConfiguration.class);
		AutowiredMethodService autowiredBean = applicationContext.getBean(AutowiredMethodService.class);
		autowiredBean.execute("@Autowired");
	}

}
