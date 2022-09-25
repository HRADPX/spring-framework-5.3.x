package com.hr.test;

import com.hr.editor.DependsExoticType;
import com.hr.editor.DependsOnExoticType;
import config.ApplicationEditorConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author huangran <huangran@kuaishou.com>
 * created on 2022-03-27
 */
public class PropertiesEditorTest {

	public static void main(String[] args){

		AnnotationConfigApplicationContext applicationContext =
				new AnnotationConfigApplicationContext(ApplicationEditorConfiguration.class);
		DependsOnExoticType bean = applicationContext.getBean(DependsOnExoticType.class);
		DependsExoticType dependsExoticType = applicationContext.getBean(DependsExoticType.class);
		System.out.println(dependsExoticType);
		System.out.println(bean);
	}

}
