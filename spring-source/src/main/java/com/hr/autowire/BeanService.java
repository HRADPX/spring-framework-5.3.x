package com.hr.autowire;

/**
 * @author huangran <huangran@kuaishou.com>
 * created on 2022-03-20
 */
public class BeanService {

	private int id;
	private String desc;

	public BeanService(int id, String desc) {
		this.id = id;
		this.desc = desc;
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
}
