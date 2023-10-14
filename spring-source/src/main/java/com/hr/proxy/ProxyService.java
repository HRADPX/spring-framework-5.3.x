package com.hr.proxy;

import org.springframework.stereotype.Component;

/**
 * @author huangran <huangran@kuaishou.com>
 * created on 2022-04-05
 */
@Component
public class ProxyService implements ProxyInterface {

	public void execute(String s) {
		//noinspection RedundantStringFormatCall
		System.out.println(String.format("%s_%s", s.toUpperCase(), s.toLowerCase()));
	}
}
