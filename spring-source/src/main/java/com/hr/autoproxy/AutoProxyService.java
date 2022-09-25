package com.hr.autoproxy;

import org.springframework.stereotype.Component;

/**
 * @author huangran <huangran@kuaishou.com>
 * created on 2022-04-06
 */
@Component
public class AutoProxyService {

	public void execute(String s) {
		System.out.println(s);
	}
}
