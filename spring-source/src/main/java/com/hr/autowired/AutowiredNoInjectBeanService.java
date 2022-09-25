package com.hr.autowired;

import org.springframework.stereotype.Service;

/**
 * @author huangran <huangran@kuaishou.com>
 * created on 2022-03-07
 */
@Service
public class AutowiredNoInjectBeanService implements InjectBeanService {
	@Override
	public void exectue(String s) {
		System.out.println("autowiredNoInjectBeanService: " + s);
	}
}
