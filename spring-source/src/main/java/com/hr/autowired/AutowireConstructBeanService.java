package com.hr.autowired;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author huangran <huangran@kuaishou.com>
 * created on 2022-03-08
 */
@Component
public class AutowireConstructBeanService {

	@Autowired
	private AutowiredInjectedBeanService autowiredInjectedBeanService;

	public AutowireConstructBeanService(AutowiredInjectedBeanService autowiredInjectedBeanService) {
		this.autowiredInjectedBeanService = autowiredInjectedBeanService;
	}

	public AutowiredInjectedBeanService getAutowiredInjectedBeanService() {
		return autowiredInjectedBeanService;
	}
}
