package config;

import com.hr.proxy.ProxyInterface;
import com.hr.proxy.ProxyService;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author huangran <huangran@kuaishou.com>
 * created on 2022-04-05
 */
@Configuration
@ComponentScan(basePackages = "com.hr.proxy")
public class ApplicationProxyConfiguration {

	@Bean
	public ProxyFactoryBean proxyFactoryBean(ProxyService proxyService) {
		ProxyFactoryBean proxyFactoryBean = new ProxyFactoryBean();
		// or setTargetName
//		proxyFactoryBean.setTargetName("proxyService");
		proxyFactoryBean.setTarget(proxyService);
		proxyFactoryBean.setInterfaces(ProxyInterface.class);
		proxyFactoryBean.setInterceptorNames("proxyAdvisor", "debugInterceptor");
		return proxyFactoryBean;
	}
}
