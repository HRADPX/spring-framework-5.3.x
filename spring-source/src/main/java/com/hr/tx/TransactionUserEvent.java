package com.hr.tx;

import org.springframework.context.ApplicationEvent;

/**
 * @author huangran <huangran@kuaishou.com>
 * created on 2022-04-16
 */
public class TransactionUserEvent extends ApplicationEvent {

	private int id;

	public TransactionUserEvent(Object source) {
		super(source);
	}

	public TransactionUserEvent(Object source, int id) {
		super(source);
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
