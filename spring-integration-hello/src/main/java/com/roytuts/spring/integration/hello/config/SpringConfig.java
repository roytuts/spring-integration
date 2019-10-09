package com.roytuts.spring.integration.hello.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.handler.ServiceActivatingHandler;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;

import com.roytuts.spring.integration.hello.service.HelloService;
import com.roytuts.spring.integration.hello.service.impl.HelloServiceImpl;

@Configuration
public class SpringConfig {

	@Bean("channel")
	public MessageChannel channel() {
		return new DirectChannel();
	}

	@Bean
	public HelloService helloService() {
		return new HelloServiceImpl();
	}

	@Bean
	@ServiceActivator(inputChannel = "channel")
	public MessageHandler serviceActivator() {
		// System.out.println("helloService: " + helloService());
		return new ServiceActivatingHandler(helloService(), "sayHello");
	}
}
