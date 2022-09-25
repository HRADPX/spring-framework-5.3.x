package com.hr.autowired;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author huangran <huangran@kuaishou.com>
 * created on 2022-02-15
 */
@Service
public class AutowiredBeanService {

	@Autowired
	private Map<String, InjectBeanService> injectBeanServiceMap;
//	private AutowiredInjectedBeanService autowiredInjectedBeanService;

//	public void methodInvoke(String s) {
//		this.autowiredInjectedBeanService.exectue(s);
//	}

	// AUTOWIRE_BY_NAME
//	public void setAutowiredInjectedBeanService(AutowiredInjectedBeanService autowiredInjectedBeanService) {
//		this.autowiredInjectedBeanService = autowiredInjectedBeanService;
//	}

//	public void setInjectBeanServiceMap(Map<String, InjectBeanService> map) {
//		this.injectBeanServiceMap = map;
//	}

	public Map<String, InjectBeanService> getInjectBeanServiceMap() {
		return injectBeanServiceMap;
	}

//	public void setXxx(InjectBeanService injectBean_) {
//		this.injectBean = injectBean_;
//	}
}
