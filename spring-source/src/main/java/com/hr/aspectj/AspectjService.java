package com.hr.aspectj;

import org.springframework.beans.factory.annotation.Configurable;

/**
 * @author huangran <huangran@kuaishou.com>
 * created on 2022-04-01
 */
@Configurable
public class AspectjService {

	public void execute(int id) {
		System.out.println(id);
	}
}
