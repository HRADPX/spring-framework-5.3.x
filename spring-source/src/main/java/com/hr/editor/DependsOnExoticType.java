package com.hr.editor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author huangran <huangran@kuaishou.com>
 * created on 2022-03-27
 */
@Component
public class DependsOnExoticType {
	@Value("aNameForExoticType")
	private ExoticType type;

	public void setType(ExoticType type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "DependsOnExoticType{" +
				"type=" + type +
				'}';
	}
}
