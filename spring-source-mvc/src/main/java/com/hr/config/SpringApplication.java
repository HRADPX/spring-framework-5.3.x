package com.hr.config;

import org.apache.catalina.Wrapper;
import org.apache.catalina.startup.Tomcat;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

/**
* @author huangran <huangran@kuaishou.com>
* created on 2022-04-18
*/
public class SpringApplication {

	public static void run() throws Exception {

		Tomcat tomcat = new Tomcat();
		tomcat.addContext("", "f:\\tomcat\\");

		System.out.println("==== Spring Init Start ====");
		AnnotationConfigWebApplicationContext applicationContext =
				new AnnotationConfigWebApplicationContext();
		applicationContext.register(ApplicationConfiguration.class);

		DispatcherServlet dispatcherServlet = new DispatcherServlet(applicationContext);
		Wrapper mvc = tomcat.addServlet("", "mvc", dispatcherServlet);
		mvc.setLoadOnStartup(1);
		mvc.addMapping("/*");

		tomcat.start();
		System.out.println("==== Spring Init End ====");
		tomcat.getServer().await();
	}
}
