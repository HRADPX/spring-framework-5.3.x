package com.hr.ioc;

import org.springframework.stereotype.Component;

/**
 * @author huangran <huangran@kuaishou.com>
 * created on 2022-03-01
 */
@Component
public class IocBean {

//	private int id;
//	private String name;
	private UserBean userBean;

//	public IocBean() {
//	}

	public IocBean(UserBean userBean) {
		this.userBean = userBean;
	}


//	public IocBean(int id, String name) {
//		this(id, name, null);
//	}
//
//	public IocBean(int id, UserBean userBean) {
//		this(id, null, userBean);
//	}
//
//	public IocBean(int id, String name, UserBean userBean) {
//		this.id = id;
//		this.name = name;
//		this.userBean = userBean;
//	}

	public void execute() {
//		System.out.println(id + " - " + name + " - " + userBean);
	}
}
