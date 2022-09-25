package com.hr.ioc;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @author huangran <huangran@kuaishou.com>
 * created on 2022-03-26
 */
@Component
public class OriginalBean {

	private int id;
	private String name;

	@Bean
	public final OldBean oldBean(NewBean newBean) {
		return new OldBean(newBean.getName());
	}

	@Bean
	public NewBean newBean() {
		return new NewBean();
	}
}
