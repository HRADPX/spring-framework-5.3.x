package com.hr.test;

import com.hr.mode.User;
import com.hr.tx.UserTxService;
import config.ApplicationTxConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;
import java.util.Collections;

/**
 * @author huangran <huangran@kuaishou.com>
 * created on 2022-01-20
 */
public class TxTest {

	public static void main(String[] args) throws IOException {

		AnnotationConfigApplicationContext configApplicationContext =
				new AnnotationConfigApplicationContext(ApplicationTxConfiguration.class);
		// 注解事务
		UserTxService userTxService = configApplicationContext.getBean(UserTxService.class);
		userTxService.execute(Collections.singletonList(new User(1, "transactionEvent33", true)));
//		List<User> users = userTxService.queryByIds(Arrays.asList(1, 24));
//		System.out.println(users);

		// 编程式事务
//		UserProgrammaticService userProgrammaticService =
//				configApplicationContext.getBean(UserProgrammaticService.class);
//		System.out.println(userProgrammaticService.execute(Arrays.asList(1, 24)));
//		AopAfterReturningService bean = configApplicationContext.getBean(AopAfterReturningService.class);
//		System.out.println(bean.aopExecute("aopExecute"));

//		AopService aopService = configApplicationContext.getBean(AopService.class);
//		aopService.aopExecute("spring aop");
	}

}
