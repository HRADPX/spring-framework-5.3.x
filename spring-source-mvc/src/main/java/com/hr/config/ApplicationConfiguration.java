package com.hr.config;

import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * @author huangran <huangran@kuaishou.com>
 * created on 2022-04-18
 */
@Configuration
@ComponentScan(basePackages = "com.hr.controller")
@EnableWebMvc
public class ApplicationConfiguration implements WebMvcConfigurer {

	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		System.out.println("add convert...");
		converters.add(new FastJsonHttpMessageConverter());
	}
}
