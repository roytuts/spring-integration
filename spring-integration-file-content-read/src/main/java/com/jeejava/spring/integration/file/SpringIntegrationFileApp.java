package com.jeejava.spring.integration.file;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.integration.file.FileReadingMessageSource;

@SpringBootApplication
public class SpringIntegrationFileApp implements CommandLineRunner {

	@Autowired
	private FileReadingMessageSource fileReadingMessageSource;

	public static void main(String[] args) {
		SpringApplication.run(SpringIntegrationFileApp.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		File file = fileReadingMessageSource.receive().getPayload();
		String content = Files.readString(Paths.get(file.getPath()));
		System.out.println(content);
	}

}
