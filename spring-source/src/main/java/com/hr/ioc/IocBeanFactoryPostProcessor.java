package com.hr.ioc;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.GenericBeanDefinition;

/**
 * @author huangran <huangran@kuaishou.com>
 * created on 2022-03-01
 */
//@Component
public class IocBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		if (beanFactory.containsBeanDefinition("iocBean")) {
			GenericBeanDefinition iocBean = (GenericBeanDefinition) beanFactory.getBeanDefinition("iocBean");
			iocBean.getConstructorArgumentValues().addIndexedArgumentValue(0, 1);
			iocBean.getConstructorArgumentValues().addIndexedArgumentValue(1, "iocBean");
//			iocBean.getConstructorArgumentValues().addIndexedArgumentValue(2, new UserBean(1, "userBean"));
		}
	}
}
