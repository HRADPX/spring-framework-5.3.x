package com.hr.delegating;

import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.support.DelegatingIntroductionInterceptor;

/**
 * @author huangran <huangran@kuaishou.com>
 * created on 2022-04-05
 */
public class LockMixin extends DelegatingIntroductionInterceptor implements Lockable {

	private boolean locked;

	@Override
	public void lock() {
		this.locked = true;
	}

	@Override
	public void unlock() {
		this.locked = false;
	}

	@Override
	public boolean locked() {
		return locked;
	}

	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		if (locked() && invocation.getMethod().getName().indexOf("set") == 0) {
			throw new LockedException();
		}
		return super.invoke(invocation);
	}
}
