package config;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * @author huangran <huangran@kuaishou.com>
 * created on 2022-05-01
 */
@Configuration
@ComponentScan(basePackages = "com.hr.async")
@EnableAsync
public class ApplicationAsyncConfiguration implements AsyncConfigurer {

	private static final Log logger = LogFactory.getLog(ApplicationAsyncConfiguration.class);

	@Override
	@Bean
	public Executor getAsyncExecutor() {
		return Executors.newFixedThreadPool(2);
	}

	@Override
	@Bean
	public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
		return (ex, m, args) -> logger.info("method: " + m.getName() + ", exception: " +  ex.getClass() + ", message: " +  ex.getMessage());
	}
}
