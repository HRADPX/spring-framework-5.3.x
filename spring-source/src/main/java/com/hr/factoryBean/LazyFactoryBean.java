package com.hr.factoryBean;

import com.hr.mode.User;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

/**
 * @author huangran <huangran@kuaishou.com>
 * created on 2022-01-16
 */
@Component
public class LazyFactoryBean implements FactoryBean<User> {
	@Override
	public User getObject() throws Exception {
		return new User(1, "lazyFactoryBean");
	}

	@Override
	public Class<?> getObjectType() {
		return User.class;
	}
}
