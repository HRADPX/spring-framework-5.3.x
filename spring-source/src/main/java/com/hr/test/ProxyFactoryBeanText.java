package com.hr.test;

import com.hr.proxy.DebugInterceptor;
import com.hr.proxy.ProxyAdvisor;
import com.hr.proxy.ProxyInterface;
import com.hr.proxy.ProxyService;
import org.springframework.aop.framework.ProxyFactory;

/**
 * @author huangran <huangran@kuaishou.com>
 * created on 2022-04-05
 */
public class ProxyFactoryBeanText {

	public static void main(String[] args){

//		AnnotationConfigApplicationContext applicationContext =
//				new AnnotationConfigApplicationContext(ApplicationProxyConfiguration.class);
//		ProxyInterface bean = (ProxyInterface) applicationContext.getBean("proxyFactoryBean");
//		bean.execute("proxyFactoryBean");
////		bean.execute("proxyFactoryBean");
//
//		DebugInterceptor interceptor = applicationContext.getBean(DebugInterceptor.class);
//		System.out.println(interceptor.getCount());

		// ProxyFactory 创建代理对象
		ProxyFactory proxyFactory = new ProxyFactory(new Class<?>[] {ProxyInterface.class});
		proxyFactory.setTarget(new ProxyService());
		// Spring 实际将其包装成 DefaultPointcutAdvisor，且其切点匹配所有的方法
		proxyFactory.addAdvice(new DebugInterceptor());
		proxyFactory.addAdvisor(new ProxyAdvisor());
		ProxyInterface proxyInterface = (ProxyInterface) proxyFactory.getProxy();
		proxyInterface.execute("proxyFactory");
	}

}
