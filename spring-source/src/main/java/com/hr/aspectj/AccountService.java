package com.hr.aspectj;

import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Configurable;

/**
 * @author huangran <huangran@kuaishou.com>
 * created on 2022-04-01
 */
@Configurable(autowire = Autowire.BY_TYPE)
public class AccountService {

	private AspectjService aspectjService;

	public void execute(int id) {
		aspectjService.execute(id);
	}
}
