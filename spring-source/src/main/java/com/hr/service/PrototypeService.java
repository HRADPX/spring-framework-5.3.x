package com.hr.service;

import com.hr.mode.User;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope(value = "prototype")
public class PrototypeService {

	public void execute(User user) {
		System.out.println(user);
	}
}
