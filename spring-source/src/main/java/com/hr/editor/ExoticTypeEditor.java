package com.hr.editor;

import java.beans.PropertyEditorSupport;

/**
 * @author huangran <huangran@kuaishou.com>
 * created on 2022-03-27
 * 这里需要将这个转换器添加到 Spring 中，通过 CustomEditorConfigurer 这个 BeanFactoryPostProcessor
 * 完成添加
 * @see config.ApplicationEditorConfiguration
 */
public class ExoticTypeEditor extends PropertyEditorSupport {

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		super.setValue(new ExoticType(text.toUpperCase()));
	}
}
