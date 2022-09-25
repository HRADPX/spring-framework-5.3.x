package com.hr.profile;

/**
 * @author huangran <huangran@kuaishou.com>
 * created on 2022-04-30
 */
public class ProfileBean {

	private int id;
	private String name;

	public ProfileBean(int id, String name) {
		this.id = id;
		this.name = name;
	}

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
}
