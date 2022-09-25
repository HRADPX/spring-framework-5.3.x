package com.hr.annotation;

/**
 * @author huangran <huangran@kuaishou.com>
 * created on 2022-05-01
 */
public @interface EnableAutoPerf {

	PerfLevel perfLevel() default PerfLevel.P2;


	enum PerfLevel {
		P0,
		P1,
		P2,
		P3;
	}
}
