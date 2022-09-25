package com.hr.test;

import com.hr.autowire.BeanService;
import com.hr.autowire.ValueBeanService;
import config.ApplicationAutowireAnnotationConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;

/**
 * @author huangran <huangran@kuaishou.com>
 * created on 2022-03-20
 */
public class AutowireAnnotationTest {

	public static void main(String[] args){

		AnnotationConfigApplicationContext applicationContext =
				new AnnotationConfigApplicationContext(ApplicationAutowireAnnotationConfiguration.class);
//		AutowiredService autowiredService = applicationContext.getBean(AutowiredService.class);
//		System.out.println(autowiredService.getRequiredBeanService());
//		System.out.println(autowiredService.getRequiredOtherBeanService());
//		System.out.println(autowiredService.getAutowireType());
//		ResourceService resourceService = applicationContext.getBean(ResourceService.class);
//		System.out.println(resourceService.getResourceType());

		ValueBeanService valueBeanService = applicationContext.getBean(ValueBeanService.class);
		System.out.println(Arrays.toString(valueBeanService.getNames()));

		BeanService beanService = applicationContext.getBean(BeanService.class);
		System.out.println(beanService.getDesc());
		System.out.println(beanService.getId());

	}

}
