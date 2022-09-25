package com.hr.autowired;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * @author huangran <huangran@kuaishou.com>
 * created on 2022-04-24
 */
@Service
public class AutowiredBean {

	@Autowired
	@Qualifier("autowiredInjectedBeanService")
	private InjectBeanService injectBeanService;

	public void execute(String s) {
		injectBeanService.exectue(s);
	}
}
