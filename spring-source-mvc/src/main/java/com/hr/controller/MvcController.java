package com.hr.controller;

import com.google.common.collect.ImmutableMap;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * @author huangran <huangran@kuaishou.com>
 * created on 2022-04-18
 */
@Controller
public class MvcController {

	@ResponseBody
	@RequestMapping("/hello")
	public String query() {
		System.out.println("mvc run....");
		return "hello SpringMVC";
	}

	@ResponseBody
	@RequestMapping("/map")
	public Map<String, String> map() {
		System.out.println("mvc map....");
		return ImmutableMap.of("mvc", "SpringMVC");
	}
}
