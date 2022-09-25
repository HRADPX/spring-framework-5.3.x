package com.hr.lookup;

import com.hr.mode.User;
import com.hr.service.PrototypeService;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

@Service
public class AwareManager implements ApplicationContextAware {

	private ApplicationContext applicationContext;

	public void process(User user) {
		PrototypeService prototypeService = applicationContext.getBean(PrototypeService.class);
		System.out.println(prototypeService);
		prototypeService.execute(user);
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}
}
