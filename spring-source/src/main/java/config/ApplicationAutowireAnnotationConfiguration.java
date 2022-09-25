package config;

import com.hr.autowire.BeanService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

/**
 * @author huangran <huangran@kuaishou.com>
 * created on 2022-03-20
 */
@Configuration
@ComponentScan(basePackages = "com.hr.autowire")
@PropertySource("classpath:application.properties")
public class ApplicationAutowireAnnotationConfiguration {

	/**
	 * 注解 @Value 属性解析
	 * SpringBoot 的默认属性解析，如果没有找到需要的属性值，容器初始化失败
	 */
	@Bean
	public static PropertySourcesPlaceholderConfigurer propertyPlaceholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}

	@Bean
	@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
	public BeanService beanService(@Value("#{beanInstance.name}") String name) {
		return new BeanService(1, name);
	}
}
