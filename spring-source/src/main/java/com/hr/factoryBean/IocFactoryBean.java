package com.hr.factoryBean;

import com.hr.mode.BeanProxy;
import com.hr.mode.User;
import org.springframework.beans.factory.SmartFactoryBean;
import org.springframework.stereotype.Component;

/**
 * @author huangran <huangran@kuaishou.com>
 * created on 2022-01-14
 */
@Component
public class IocFactoryBean implements SmartFactoryBean<BeanProxy> {
	@Override
	public BeanProxy getObject() throws Exception {
		return new BeanProxy(1, "factoryBean", new User());
	}

	@Override
	public Class<?> getObjectType() {
		return BeanProxy.class;
	}

	@Override
	public boolean isEagerInit() {
		return true;
	}
}
