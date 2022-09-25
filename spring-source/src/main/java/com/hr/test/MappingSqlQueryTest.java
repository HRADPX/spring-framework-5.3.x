package com.hr.test;

import com.hr.mode.User;
import com.hr.tx.SqlQueryService;
import config.ApplicationTxConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author huangran <huangran@kuaishou.com>
 * created on 2022-04-17
 */
public class MappingSqlQueryTest {

	public static void main(String[] args) {

		AnnotationConfigApplicationContext configApplicationContext =
				new AnnotationConfigApplicationContext(ApplicationTxConfiguration.class);
		SqlQueryService sqlQueryService = configApplicationContext.getBean(SqlQueryService.class);
		System.out.println(sqlQueryService.queryUserBySqlQuery(1));
		System.out.println(sqlQueryService.update(new User(1, "sqlUpdate")));

	}
}
