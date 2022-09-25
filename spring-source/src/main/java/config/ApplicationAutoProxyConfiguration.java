package config;

import org.aopalliance.aop.Advice;
import org.aopalliance.intercept.MethodInterceptor;
import org.springframework.aop.framework.autoproxy.BeanNameAutoProxyCreator;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.aop.support.NameMatchMethodPointcutAdvisor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author huangran <huangran@kuaishou.com>
 * created on 2022-04-06
 */
@Configuration
@ComponentScan(basePackages = "com.hr.autoproxy")
public class ApplicationAutoProxyConfiguration {

	@Bean
	public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
		return new DefaultAdvisorAutoProxyCreator();
	}

	@Bean
	public BeanNameAutoProxyCreator beanNameAutoProxyCreator() {
		BeanNameAutoProxyCreator beanNameAutoProxyCreator = new BeanNameAutoProxyCreator();
		beanNameAutoProxyCreator.setBeanNames("beanName*");
		beanNameAutoProxyCreator.setInterceptorNames("interceptor");
		return beanNameAutoProxyCreator;
	}

	@Bean
	public Advice interceptor() {
		return (MethodInterceptor) (invocation) -> {
			System.out.println("method invoke before.....");
			Object proceed = invocation.proceed();
			System.out.println("method invoke after.....");
			return proceed;
		};
	}

	@Bean
	public NameMatchMethodPointcutAdvisor nameMatchMethodPointcutAdvisor() {
		NameMatchMethodPointcutAdvisor nameMatchMethodPointcutAdvisor =
				new NameMatchMethodPointcutAdvisor();
		nameMatchMethodPointcutAdvisor.setMappedName("execut*");
		nameMatchMethodPointcutAdvisor.setAdvice(interceptor());
		return nameMatchMethodPointcutAdvisor;
	}
}
