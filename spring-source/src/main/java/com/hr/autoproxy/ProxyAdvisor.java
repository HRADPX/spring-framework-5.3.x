package com.hr.autoproxy;

import org.aopalliance.aop.Advice;
import org.springframework.aop.Advisor;
import org.springframework.aop.MethodBeforeAdvice;

/**
 * @author huangran <huangran@kuaishou.com>
 * created on 2022-04-05
 */
//@Component
public class ProxyAdvisor implements Advisor {
	@Override
	public Advice getAdvice() {
		return (MethodBeforeAdvice) (method, args, target) -> System.out.println("before ....");
	}

	@Override
	public boolean isPerInstance() {
		return true;
	}
}
