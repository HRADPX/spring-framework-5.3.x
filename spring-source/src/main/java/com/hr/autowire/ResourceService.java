package com.hr.autowire;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author huangran <huangran@kuaishou.com>
 * created on 2022-03-20
 */
@Service
public class ResourceService {

	@Resource
	private ResourceType resourceType;

	public ResourceType getResourceType() {
		return resourceType;
	}

	public void setResourceType(ResourceType resourceType) {
		this.resourceType = resourceType;
	}
}
