package com.hr.mode;

/**
 * @author huangran <huangran@kuaishou.com>
 * created on 2022-01-03
 */
public class BeanProxy {

	private int id;
	private String desc;
	private User user;

	public BeanProxy() {
	}

	public BeanProxy(int id, String desc, User user) {
		this.id = id;
		this.desc = desc;
		this.user = user;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	@Override
	public String toString() {
		return "BeanProxy{" +
				"id=" + id +
				", desc='" + desc + '\'' +
				", user=" + user +
				'}';
	}
}
