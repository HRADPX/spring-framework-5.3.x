package com.hr.service;

import com.hr.mode.User;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class UserService implements InitProcessor {

	private IocService iocService;
	private String name;


	public UserService(IocService iocService) {
		this.iocService = iocService;
	}

	public UserService(IocService iocService, PrototypeService prototypeService) {
		this.iocService = iocService;
	}

	public UserService() {

	}

	public UserService(String name) {
		this.name = name;
	}

	@PostConstruct
	public void init() {
		System.out.println("userService init...");
	}

	@Override
	public void initMethod() {
		System.out.println("init method execute...");
	}

	public void printUser(User user) {
		System.out.println(user);
	}

	public void invokeIocService(String s) {
		iocService.execute(s);
	}
}
