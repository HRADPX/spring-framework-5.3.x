package com.hr.test;

import com.hr.tx.LifeAopService;
import config.ApplicationTxConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author huangran <huangran@kuaishou.com>
 * created on 2022-01-24
 */
public class AopTest {

	public static void main(String[] args){

		AnnotationConfigApplicationContext configApplicationContext =
				new AnnotationConfigApplicationContext(ApplicationTxConfiguration.class);
		LifeAopService aopService1 = configApplicationContext.getBean(LifeAopService.class);
		LifeAopService aopService2 = configApplicationContext.getBean(LifeAopService.class);
		System.out.println(aopService1 == aopService2);
		aopService1.lifeExecute("spring aop");
		aopService2.lifeExecute("spring aop");
		aopService2.lifeExecute("spring aop");
	}

}
