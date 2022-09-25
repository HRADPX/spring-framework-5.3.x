package com.hr.circular;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author huangran <huangran@kuaishou.com>
 * created on 2022-01-16
 */
@Component
public class CircularSingleton {

	@Autowired
	private CircularPrototype circularPrototype;
}
