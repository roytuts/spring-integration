package com.roytuts.spring.integration.hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.roytuts.spring.integration.hello.service.HelloService;

@SpringBootApplication(scanBasePackages = "com.roytuts.spring.integration.hello")
public class SpringIntegrationHelloApp implements CommandLineRunner {

	@Autowired
	@Qualifier("helloGateway")
	private HelloService helloGateway;

	public static void main(String[] args) {
		SpringApplication.run(SpringIntegrationHelloApp.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// System.out.println("helloGateway: " + helloGateway);
		System.out.println(helloGateway.sayHello("Soumitra"));
	}

}
