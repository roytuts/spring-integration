package com.roytuts.spring.integration.hello.service;

import org.springframework.integration.annotation.MessagingGateway;

@MessagingGateway(name = "helloGateway", defaultRequestChannel = "channel")
public interface HelloService {

	String sayHello(String name);

}
