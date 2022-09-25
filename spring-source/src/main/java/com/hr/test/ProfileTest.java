package com.hr.test;

import com.hr.profile.ProfileConditionBean;
import config.ApplicationProfileConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author huangran <huangran@kuaishou.com>
 * created on 2022-03-26
 */
public class ProfileTest {

	public static void main(String[] args){

		AnnotationConfigApplicationContext applicationContext =
				new AnnotationConfigApplicationContext(ApplicationProfileConfiguration.class);
//		applicationContext.getEnvironment().setActiveProfiles("development");
//		applicationContext.register(ApplicationProfileConfiguration.class);
//		applicationContext.refresh();
		ProfileConditionBean profileConditionBean = applicationContext.getBean(ProfileConditionBean.class);
		System.out.println(profileConditionBean.getName());
	}

}
