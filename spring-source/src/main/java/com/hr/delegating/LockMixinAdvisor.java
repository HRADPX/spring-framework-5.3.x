package com.hr.delegating;

import org.springframework.aop.support.DefaultIntroductionAdvisor;

/**
 * @author huangran <huangran@kuaishou.com>
 * created on 2022-04-05
 */
public class LockMixinAdvisor extends DefaultIntroductionAdvisor {

	public LockMixinAdvisor() {
		super(new LockMixin(), Lockable.class);
	}
}
