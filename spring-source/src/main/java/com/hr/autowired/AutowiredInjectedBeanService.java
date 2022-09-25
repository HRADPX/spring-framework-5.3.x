package com.hr.autowired;

import org.springframework.stereotype.Service;

/**
 * @author huangran <huangran@kuaishou.com>
 * created on 2022-02-15
 */
@Service
public class AutowiredInjectedBeanService implements InjectBeanService{

	@Override
	public void exectue(String s) {
		System.out.println("autowiredInjectedBeanService: " + s);
	}
}
