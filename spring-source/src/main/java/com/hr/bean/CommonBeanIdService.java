package com.hr.bean;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

/**
 * @author huangran <huangran@kuaishou.com>
 * Created on 2024-04-09
 */
@Service
public class CommonBeanIdService {

	@PostConstruct
	public void init() {
		System.out.println();
	}
}
