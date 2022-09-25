package com.hr.life;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.SmartLifecycle;
import org.springframework.stereotype.Component;

/**
 * @author huangran <huangran@kuaishou.com>
 * created on 2022-03-20
 */
@Component
public class LifeCycleBean implements SmartLifecycle {

	@Autowired
	private ApplicationContext applicationContext;

	private volatile boolean running;

	@Override
	public void start() {
		System.out.println(applicationContext);
		running = true;
		System.out.println("lifeCycle Bean start....");
	}

	@Override
	public void stop() {
		running = false;
		System.out.println("lifeCycle Bean end....");
	}

	@Override
	public boolean isRunning() {
		return running;
	}

	/**
	 * SmartLifeCycle 接口中的方法要被自动执行，这个方法必须返回 true
	 */
	@Override
	public boolean isAutoStartup() {
		return true;
	}
}
