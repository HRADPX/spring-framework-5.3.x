package com.hr.editor;

import org.springframework.beans.PropertyEditorRegistrar;
import org.springframework.beans.PropertyEditorRegistry;

/**
 * @author huangran <huangran@kuaishou.com>
 * created on 2022-03-27
 * 这里需要将这个转换器添加到 Spring 中，通过 CustomEditorConfigurer 这个 BeanFactoryPostProcessor
 * 完成添加
 * @see config.ApplicationEditorConfiguration
 */
public class ExoticTypeEditorRegistrar implements PropertyEditorRegistrar {

	@Override
	public void registerCustomEditors(PropertyEditorRegistry registry) {
		// 只有一个生效
		registry.registerCustomEditor(ExoticType.class, new ExoticTypeEditor());
		registry.registerCustomEditor(ExoticType.class, new ExoticTypeEditorPlus());
	}
}
