package com.hr.ioc;

import javax.annotation.ManagedBean;
import javax.inject.Inject;

/**
 * @author huangran <huangran@kuaishou.com>
 * created on 2022-03-26
 */
@ManagedBean
public class InjectBeanService {

	@Inject
	private UserBean userBean;

	public void execute() {
		System.out.println(userBean);
	}

}
