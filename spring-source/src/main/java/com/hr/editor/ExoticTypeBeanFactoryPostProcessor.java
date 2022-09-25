package com.hr.editor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.stereotype.Component;

/**
 * @author huangran <huangran@kuaishou.com>
 * created on 2022-03-27
 */
@Component
public class ExoticTypeBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		if (beanFactory.containsBeanDefinition("dependsExoticType")) {
			System.out.println("-----------");
			GenericBeanDefinition beanDefinition = (GenericBeanDefinition) beanFactory.getBeanDefinition("dependsExoticType");
			// 这里的 String 会被 ExoticTypeEditor 转换为 ExoticType
			beanDefinition.getConstructorArgumentValues().addIndexedArgumentValue(0, "dependsExoticType");
		}
	}
}
