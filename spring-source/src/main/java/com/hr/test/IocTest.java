package com.hr.test;

import com.hr.ioc.InjectBeanService;
import com.hr.ioc.OldBean;
import config.ApplicationIocConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class IocTest {

	public static void main(String[] args){

		AnnotationConfigApplicationContext configApplicationContext =
				new AnnotationConfigApplicationContext(ApplicationIocConfiguration.class);
//		UserService userService = configApplicationContext.getBean(UserService.class);
//		LookUpManager lookUpManager = configApplicationContext.getBean(LookUpManager.class);
//		userService.invokeIocService("hello constructor!");
//		lookUpManager.process(new User(1, "hello spring"));
//		userService.printUser(new User(1, "hello spring"));

//		User user = configApplicationContext.getBean(User.class);
//		System.out.println(user);
//		BeanProxy beanProxy1 = (BeanProxy) configApplicationContext.getBean("beanProxy1");
//		BeanProxy beanProxy = (BeanProxy) configApplicationContext.getBean("beanProxy");
//		System.out.println(beanProxy);
//		System.out.println(beanProxy1);
//		BeanProxy beanProxy = (BeanProxy) configApplicationContext.getBean("iocFactoryBean");
//		User lazyFactoryBean = (User) configApplicationContext.getBean("lazyFactoryBean");
//		System.out.println(beanProxy);

//		IocBean iocBean = configApplicationContext.getBean(IocBean.class);
//		iocBean.execute();

		InjectBeanService injectBeanService =
				configApplicationContext.getBean(InjectBeanService.class);
		InjectBeanService injectBeanService1 =
				configApplicationContext.getBean(InjectBeanService.class);
		OldBean oldBean = configApplicationContext.getBean(OldBean.class);
		System.out.println(oldBean);
		oldBean.execute();
		System.out.println(injectBeanService);
		System.out.println(injectBeanService1);
		injectBeanService.execute();


//

	}

}
