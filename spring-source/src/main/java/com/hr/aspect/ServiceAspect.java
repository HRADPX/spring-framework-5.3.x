package com.hr.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @author huangran <huangran@kuaishou.com>
 * created on 2022-01-24
 */
@Component
@Aspect
public class ServiceAspect {

	@Pointcut("execution(* com.hr.tx.*.aopExecute(..))")
	public void pointCut() {}

	@Around("pointCut()")
	public Object around(ProceedingJoinPoint pjp) throws Throwable {
		System.out.println("around before......");
		Object proceed = pjp.proceed();
		System.out.println("around after......");
		return proceed;
	}

	/**
	 * 参数名称要和 returning 属性的值一样，并且参数的类型要和方法一致，
	 * 如果不一致则跳过执行，可以使用 Object 类型，来匹配所有返回类型
	 */
	@AfterReturning(pointcut = "pointCut()")
	public void afterReturn(){
		System.out.println("afterReturning.........");
	}

	@Before("pointCut()")
	public void before(JoinPoint joinPoint){
		System.out.println("before... " + Arrays.toString(joinPoint.getArgs()));
	}

	@After("pointCut()")
	public void after(JoinPoint joinPoint){
		System.out.println("after finally... " + Arrays.toString(joinPoint.getArgs()));
	}

	@AfterThrowing(pointcut = "pointCut()")
	public void afterThrowing(){
		System.out.println("afterThrowing...........");
	}
}
