package com.jeejava.spring.integration.file.config;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.file.FileReadingMessageSource;

@Configuration
@EnableIntegration
public class FileConfig {

	@Autowired
	private Environment env;

	@Bean
	public FileReadingMessageSource fileReadingMessageSource() {
		FileReadingMessageSource fileReadingMessageSource = new FileReadingMessageSource();
		fileReadingMessageSource.setDirectory(new File(env.getProperty("file.input.directory")));
		return fileReadingMessageSource;
	}

}
