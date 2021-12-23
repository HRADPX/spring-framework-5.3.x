package com.hr.service;

import com.hr.mode.User;
import org.springframework.stereotype.Service;

@Service
public class UserService {

	public void printUser(User user) {
		System.out.println(user);
	}
}
