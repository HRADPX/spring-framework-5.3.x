package com.hr.autowired;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author huangran <huangran@kuaishou.com>
 * created on 2022-04-24
 */
@Service
public class AutowiredMethodService {

	private InjectBeanService injectBeanService;

	@Autowired
	public void setInjectBeanService(AutowiredNoInjectBeanService injectBeanService) {
		this.injectBeanService = injectBeanService;
	}

	public void execute(String s) {
		injectBeanService.exectue(s);
	}
}
