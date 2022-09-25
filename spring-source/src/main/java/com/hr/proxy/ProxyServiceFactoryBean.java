package com.hr.proxy;

import org.springframework.aop.framework.ProxyFactoryBean;

/**
 * @author huangran <huangran@kuaishou.com>
 * created on 2022-04-05
 */
//@Component
public class ProxyServiceFactoryBean extends ProxyFactoryBean {

	public ProxyServiceFactoryBean(ProxyService target) {
		super.setTarget(target);
		setInterfaces(ProxyInterface.class);
		setInterceptorNames("com.hr.proxy.ProxyAdvisor",
				"com.hr.proxy.DebugInterceptor");
	}
}
