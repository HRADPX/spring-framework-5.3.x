package com.hr.async;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.config.TaskManagementConfigUtils;
import org.springframework.stereotype.Component;

/**
 * @author huangran <huangran@kuaishou.com>
 * created on 2022-05-01
 */
@Component
@Order(Ordered.LOWEST_PRECEDENCE - 10)
public class AsyncBeanFactoryBeanPostProcessor implements BeanFactoryPostProcessor {

	private static final String ASYNC_POST_PROCESSOR_NAME = TaskManagementConfigUtils.ASYNC_ANNOTATION_PROCESSOR_BEAN_NAME;

	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		if (!beanFactory.containsBeanDefinition(ASYNC_POST_PROCESSOR_NAME)) {
			return;
		}
		AbstractBeanDefinition beanDefinition = (AbstractBeanDefinition) beanFactory.getBeanDefinition(ASYNC_POST_PROCESSOR_NAME);
		beanDefinition.getPropertyValues().addPropertyValue("exposeProxy", true);
	}
}
