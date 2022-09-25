package com.hr.service;

import org.springframework.stereotype.Service;

/**
 * @author huangran <huangran@kuaishou.com>
 * created on 2022-01-09
 */
@Service
public class IocService {

	public void execute(String s) {
		System.out.println(s);
	}
}
