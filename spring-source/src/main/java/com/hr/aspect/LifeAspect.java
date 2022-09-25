package com.hr.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author huangran <huangran@kuaishou.com>
 * created on 2022-03-31
 */
@Aspect("perthis(this(com.hr.tx.LifeAopService))")
@Component
//@Aspect
@Scope("prototype")
public class LifeAspect {

	public LifeAspect() {
		System.out.println("lifeAspect constructor run...");
	}

	@Pointcut("execution(* com.hr.tx.LifeAopService.lifeExecute(..)))")
	private void pointCut() {}


	@Before("pointCut()")
	public void recordServiceUsage() {
		System.out.println("before....");
	}
}
