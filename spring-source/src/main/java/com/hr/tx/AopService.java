package com.hr.tx;

import org.springframework.stereotype.Service;

/**
 * @author huangran <huangran@kuaishou.com>
 * created on 2022-01-24
 */
@Service
public class AopService {

	public void aopExecute(String s) {
		int i = 1 / 0;
		System.out.println(s);
	}

}
