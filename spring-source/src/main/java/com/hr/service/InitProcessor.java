package com.hr.service;

/**
 * @author huangran <huangran@kuaishou.com>
 * created on 2022-01-09
 */
public interface InitProcessor {

	default void initMethod() {
		System.out.println("default init method execute....");
	}
}
