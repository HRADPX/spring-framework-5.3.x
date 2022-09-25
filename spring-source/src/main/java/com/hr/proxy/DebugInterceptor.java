package com.hr.proxy;

import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.interceptor.SimpleTraceInterceptor;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

/**
 * @author huangran <huangran@kuaishou.com>
 * created on 2022-04-05
 */
@Component
public class DebugInterceptor extends SimpleTraceInterceptor {

	private volatile long count;


	/**
	 * Create a new DebugInterceptor with a static logger.
	 */
	public DebugInterceptor() {
	}

	/**
	 * Create a new DebugInterceptor with dynamic or static logger,
	 * according to the given flag.
	 * @param useDynamicLogger whether to use a dynamic logger or a static logger
	 * @see #setUseDynamicLogger
	 */
	public DebugInterceptor(boolean useDynamicLogger) {
		setUseDynamicLogger(useDynamicLogger);
	}


	@Override
	@Nullable
	public Object invoke(MethodInvocation invocation) throws Throwable {
		synchronized (this) {
			this.count++;
		}
		return super.invoke(invocation);
	}

	@Override
	protected String getInvocationDescription(MethodInvocation invocation) {
		return invocation + "; count=" + this.count;
	}


	/**
	 * Return the number of times this interceptor has been invoked.
	 */
	public long getCount() {
		return this.count;
	}

	/**
	 * Reset the invocation count to zero.
	 */
	public synchronized void resetCount() {
		this.count = 0;
	}

}

