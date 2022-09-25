package com.hr.autowire;

import org.springframework.stereotype.Component;

/**
 * @author huangran <huangran@kuaishou.com>
 * created on 2022-03-20
 */
@Component
public class BeanInstance {

	private String name = "aaa";

	public String getName() {
		return name;
	}
}
