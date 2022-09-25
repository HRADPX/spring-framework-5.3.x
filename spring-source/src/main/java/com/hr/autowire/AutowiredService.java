package com.hr.autowire;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * @author huangran <huangran@kuaishou.com>
 * created on 2022-03-20
 */
@Service
public class AutowiredService {

	@Autowired
	@Qualifier("requiredBeanService")
	private AutowireType autowireType;

	private RequiredBeanService requiredBeanService;

	private RequiredOtherBeanService requiredOtherBeanService;

//	@Autowired(required = false)
//	public AutowiredService() {}

//	@Autowired(required = false)
//	public AutowiredService(@Nullable RequiredBeanService requiredBeanService) {
//		this.requiredBeanService = requiredBeanService;
//	}
//
//	@Autowired(required = false)
//	public AutowiredService(RequiredOtherBeanService requiredOtherBeanService) {
//		this.requiredOtherBeanService = requiredOtherBeanService;
//	}
//
//	@Autowired(required = false)
//	public AutowiredService(RequiredBeanService requiredBeanService, RequiredOtherBeanService requiredOtherBeanService) {
//		this.requiredBeanService = requiredBeanService;
//		this.requiredOtherBeanService = requiredOtherBeanService;
//	}

	public RequiredBeanService getRequiredBeanService() {
		return requiredBeanService;
	}

	public void setRequiredBeanService(RequiredBeanService requiredBeanService) {
		this.requiredBeanService = requiredBeanService;
	}

	public RequiredOtherBeanService getRequiredOtherBeanService() {
		return requiredOtherBeanService;
	}

	public void setRequiredOtherBeanService(RequiredOtherBeanService requiredOtherBeanService) {
		this.requiredOtherBeanService = requiredOtherBeanService;
	}

	public AutowireType getAutowireType() {
		return autowireType;
	}

	public void setAutowireType(AutowireType autowireType) {
		this.autowireType = autowireType;
	}
}
