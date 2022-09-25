package com.hr.autoproxy;

import org.springframework.stereotype.Service;

/**
 * @author huangran <huangran@kuaishou.com>
 * created on 2022-04-10
 */
@Service
public class BeanNameProxyService {

	public void beanNameProxy(String s) {
		System.out.println(s);
	}
}
