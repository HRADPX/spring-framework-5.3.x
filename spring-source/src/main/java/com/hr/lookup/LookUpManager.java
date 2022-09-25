package com.hr.lookup;

import com.hr.mode.User;
import com.hr.service.PrototypeService;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Service;

/**
 * LookUp 注解
 * 存在 @LookUp 注解修饰方法的抽象类，其生命周期会被 Spring 管理，
 * 在运行时会通过 CGLIB 生成代理子类
 */
@Service
public abstract class LookUpManager {

	public void process(User user) {

		PrototypeService prototypeService = createPrototypeService();
		System.out.println(prototypeService);
		prototypeService.execute(user);
	}

	@Lookup
	protected abstract PrototypeService createPrototypeService();
}
