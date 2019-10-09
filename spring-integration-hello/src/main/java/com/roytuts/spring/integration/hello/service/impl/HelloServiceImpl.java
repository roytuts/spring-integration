package com.roytuts.spring.integration.hello.service.impl;

import com.roytuts.spring.integration.hello.service.HelloService;

public class HelloServiceImpl implements HelloService {

	@Override
	public String sayHello(String name) {
		return "Hello " + name;
	}

}
