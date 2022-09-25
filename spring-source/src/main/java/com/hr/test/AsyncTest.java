package com.hr.test;

import com.hr.async.AsyncBeanService;
import config.ApplicationAsyncConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Collections;
import java.util.concurrent.TimeUnit;

/**
 * @author huangran <huangran@kuaishou.com>
 * created on 2022-05-01
 */
public class AsyncTest {

	public static void main(String[] args) throws Exception {

		AnnotationConfigApplicationContext applicationContext =
				new AnnotationConfigApplicationContext(ApplicationAsyncConfiguration.class);
		AsyncBeanService asyncBeanService = applicationContext.getBean(AsyncBeanService.class);
//		asyncBeanService.execute("@EnableAsync");
		asyncBeanService.doubleString(Collections.singleton("@EnableAsync"));
		System.out.println(asyncBeanService.doubleString("ssss").get(1, TimeUnit.SECONDS));
	}

}
