package com.roytuts.spring.integration.another.hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.handler.ServiceActivatingHandler;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;

//@SpringBootApplication
public class SpringIntegrationHelloAnotherApp implements CommandLineRunner {

	@Autowired
	@Qualifier("helloGateway")
	private HelloService helloGateway;

	/*
	 * public static void main(String[] args) {
	 * SpringApplication.run(SpringIntegrationHelloAnotherApp.class, args); }
	 */

	@Override
	public void run(String... args) throws Exception {
		// System.out.println("helloGateway: " + helloGateway);
		System.out.println(helloGateway.sayHello("Soumitra"));
	}

	@MessagingGateway(name = "helloGateway", defaultRequestChannel = "channel")
	public interface HelloService {
		String sayHello(String name);
	}

	@Bean("channel")
	public MessageChannel channel() {
		return new DirectChannel();
	}

	@Bean
	@ServiceActivator(inputChannel = "channel")
	public MessageHandler serviceActivator() {
		// System.out.println("myServiceImpl: " + myServiceImpl());
		return new ServiceActivatingHandler(myServiceImpl(), "sayHello");
	}

	@Bean
	public MyServiceImpl myServiceImpl() {
		return new MyServiceImpl();
	}

	public class MyServiceImpl implements HelloService {
		@Override
		public String sayHello(String name) {
			return "Hello " + name;
		}
	}
}
