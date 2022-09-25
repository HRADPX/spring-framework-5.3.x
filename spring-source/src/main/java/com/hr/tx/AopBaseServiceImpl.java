package com.hr.tx;

import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * @author huangran <huangran@kuaishou.com>
 * created on 2022-03-30
 */
@Component
public class AopBaseServiceImpl implements BaseService<AopService> {
	@Override
	public void execute(AopService aopService) {
		System.out.println("singleton params");
	}

	@Override
	public void batchExecute(Collection<AopService> list) {
		System.out.println("batch params");
	}
}
