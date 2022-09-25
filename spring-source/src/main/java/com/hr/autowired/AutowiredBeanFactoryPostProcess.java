package com.hr.autowired;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.stereotype.Component;

/**
 * @author huangran <huangran@kuaishou.com>
 * created on 2022-02-15
 */
@Component
public class AutowiredBeanFactoryPostProcess implements BeanFactoryPostProcessor {
	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

		if (beanFactory.containsBean("autowiredBeanService")) {
			GenericBeanDefinition beanDefinition = (GenericBeanDefinition) beanFactory.getBeanDefinition("autowiredBeanService");
			beanDefinition.setAutowireMode(AbstractBeanDefinition.AUTOWIRE_NO);
		}
	}
}
