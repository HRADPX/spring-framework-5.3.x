package com.hr.test;

import com.hr.mode.User;
import com.hr.tx.JdbcService;
import config.ApplicationTxConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;
import java.util.Arrays;

/**
 * @author huangran <huangran@kuaishou.com>
 * created on 2022-04-17
 */
public class JdbcTest {

	public static void main(String[] args) throws IOException {

		AnnotationConfigApplicationContext configApplicationContext =
				new AnnotationConfigApplicationContext(ApplicationTxConfiguration.class);
		JdbcService jdbcService = configApplicationContext.getBean(JdbcService.class);
//		System.out.println(jdbcService.insertReturnPrimaryId(new User(1, "autoKey")));

		int[] updates = jdbcService.batchUpdate(Arrays.asList(new User(1, "batchUpdate"),
				new User(25, "nameParameter")));
		System.out.println(Arrays.toString(updates));
	}
}
