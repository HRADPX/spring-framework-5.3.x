package com.hr.circular;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author huangran <huangran@kuaishou.com>
 * created on 2022-01-16
 */
@Component
@Scope("prototype")
public class CircularPrototype {

	@Autowired
	private CircularSingleton circularSingleton;
}
