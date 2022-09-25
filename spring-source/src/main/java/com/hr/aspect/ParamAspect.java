package com.hr.aspect;

import com.hr.tx.AopService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collection;

/**
 * @author huangran <huangran@kuaishou.com>
 * created on 2022-03-30
 */
@Component
@Aspect
public class ParamAspect {

	@Pointcut("execution(* com.hr.tx.AopBaseServiceImpl.*(..))")
	private void pointCut() {}

	@Before("pointCut() && args(t)")
	public void before(JoinPoint joinPoint, AopService t){
		System.out.println("before... " + Arrays.toString(joinPoint.getArgs()));
		t.aopExecute("aopService singleton");
	}

	@After("pointCut() && args(t)")
	public void after(JoinPoint joinPoint, Collection<AopService> t){
		System.out.println("after... " + Arrays.toString(joinPoint.getArgs()));
		t.forEach(aopService -> aopService.aopExecute("batch params"));
	}
}
