package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * @author huangran <huangran@kuaishou.com>
 * created on 2022-01-19
 *
 * 事务分两种：命令式事务和响应式事务
 * 	命令式事务：基于注解、基于编程
 */
@Configuration
@ComponentScan(basePackages = {"com.hr.tx", "com.hr.aspect"})
@EnableTransactionManagement(proxyTargetClass = true)
//@EnableAspectJAutoProxy(proxyTargetClass = true, exposeProxy = true)
public class ApplicationTxConfiguration {
	@Bean
	public DataSource dataSource(){
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/info");
		dataSource.setUsername("root");
		dataSource.setPassword("1234");
		return dataSource;
	}
	@Bean
	public JdbcTemplate jdbcTemplate(){
		return new JdbcTemplate(dataSource());
	}
	// 配置事务管理器
	@Bean
	public DataSourceTransactionManager transactionManager() {
		return new DataSourceTransactionManager(dataSource());
	}
}
