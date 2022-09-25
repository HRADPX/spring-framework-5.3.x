package com.hr.life;

import org.springframework.context.SmartLifecycle;
import org.springframework.stereotype.Service;

/**
 * @author huangran <huangran@kuaishou.com>
 * created on 2022-03-20
 */
@Service
public class LifeCycleService implements SmartLifecycle {

	private volatile boolean running;

	@Override
	public void start() {
		running = true;
		System.out.println("lifeCycle Service start....");
	}

	@Override
	public void stop() {
		running = false;
		System.out.println("lifeCycleService end....");
	}

	@Override
	public boolean isRunning() {
		return running;
	}
}
