package com.hr.test;

import config.ApplicationConfiguration;
import com.hr.mode.User;
import com.hr.service.ImportSelectorService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author huangran <huangran@kuaishou.com>
 * created on 2022-01-01
 */
public class ImportTest {

	public static void main(String[] args){
		AnnotationConfigApplicationContext configApplicationContext =
				new AnnotationConfigApplicationContext(ApplicationConfiguration.class);
		ImportSelectorService importSelectorService = configApplicationContext.getBean(ImportSelectorService.class);
		importSelectorService.execute(new User(1, "hello selector"));
	}


}
