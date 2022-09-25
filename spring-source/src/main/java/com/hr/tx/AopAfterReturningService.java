package com.hr.tx;

import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * @author huangran <huangran@kuaishou.com>
 * created on 2022-03-29
 */
@Service
public class AopAfterReturningService {

	public List<String> aopExecute(String s) {
		return Collections.singletonList(s);
	}

}
