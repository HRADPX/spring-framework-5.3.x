package com.hr.delegating;

/**
 * @author huangran <huangran@kuaishou.com>
 * created on 2022-04-05
 */
public interface Lockable {

	void lock();

	void unlock();

	boolean locked();
}
