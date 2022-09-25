package com.hr.test;

import com.hr.service.IocService;
import config.ApplicationImportConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author huangran <huangran@kuaishou.com>
 * created on 2022-01-02
 */
public class ImportNoConfigTest {

	public static void main(String[] args){

		AnnotationConfigApplicationContext applicationContext =
				new AnnotationConfigApplicationContext(ApplicationImportConfiguration.class);
		System.out.println(applicationContext.getBean(IocService.class));
	}


}
