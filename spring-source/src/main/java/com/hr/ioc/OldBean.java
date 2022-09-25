package com.hr.ioc;

/**
 * @author huangran <huangran@kuaishou.com>
 * created on 2022-03-26
 */
public class OldBean {

	private String desc;

	public OldBean(String desc) {
		this.desc = desc;
	}

	public void execute() {
		System.out.println(desc);
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
}
