package config;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

/**
 * @author huangran <huangran@kuaishou.com>
 * created on 2022-03-09
 */
@Configuration
@ComponentScan(basePackages = "com.hr.mybatis.service")
@MapperScan(basePackages = "com.hr.mybatis.mapper")
public class ApplicationMybatisConfiguration {

	@Bean
	public DataSource dataSource(){
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/info");
		dataSource.setUsername("root");
		dataSource.setPassword("1234");
		return dataSource;
	}

//	 如果使用 mybatis 则不需要这个 Bean
//	@Bean
//	public JdbcTemplate jdbcTemplate(){
//		return new JdbcTemplate(dataSource());
//	}

	@Bean
	public SqlSessionFactoryBean sqlSessionFactoryBean(DataSource dataSource) throws Exception {
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
//        // 给 Mybatis 提供日志实现
//        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
//        configuration.setLogImpl(Log4jImpl.class);
//        sqlSessionFactoryBean.setConfiguration(configuration);
		sqlSessionFactoryBean.setDataSource(dataSource);
		return sqlSessionFactoryBean;
	}
}
