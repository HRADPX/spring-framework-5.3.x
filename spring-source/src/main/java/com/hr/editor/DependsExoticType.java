package com.hr.editor;

import org.springframework.stereotype.Component;

/**
 * @author huangran <huangran@kuaishou.com>
 * created on 2022-03-27
 */
@Component
public class DependsExoticType {

	private ExoticType exoticType;

	public DependsExoticType(ExoticType exoticType) {
		this.exoticType = exoticType;
	}

	@Override
	public String toString() {
		return "DependsExoticType{" +
				"exoticType=" + exoticType +
				'}';
	}
}
