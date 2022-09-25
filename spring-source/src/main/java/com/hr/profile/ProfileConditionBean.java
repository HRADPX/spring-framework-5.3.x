package com.hr.profile;

/**
 * @author huangran <huangran@kuaishou.com>
 * created on 2022-03-26
 */
public class ProfileConditionBean {

	private int id;

	private String name;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ProfileConditionBean(int id, String name) {
		this.id = id;
		this.name = name;
	}
}
