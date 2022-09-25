package com.hr.ioc;

import org.springframework.stereotype.Component;

/**
 * @author huangran <huangran@kuaishou.com>
 * created on 2022-03-01
 */
@Component
public class UserBean {

	private int id;
	private String name;


//	public UserBean(int id, String name) {
//		this.id = id;
//		this.name = name;
//	}

	@Override
	public String toString() {
		return "UserBean{" +
				"id=" + id +
				", name='" + name + '\'' +
				'}';
	}
}
