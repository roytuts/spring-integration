package com.roytuts.spring.integration.rss.feed.to.file.config;

import java.io.File;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.feed.dsl.Feed;
import org.springframework.integration.file.FileWritingMessageHandler;
import org.springframework.integration.transformer.AbstractPayloadTransformer;
import org.springframework.messaging.MessageHandler;

import com.rometools.rome.feed.synd.SyndEntry;

@Configuration
public class FlowConfig {

	@Value(value = "https://spring.io/blog.atom")
	private Resource feedResource;

	@Bean
	public IntegrationFlow feedFlow() {
		return IntegrationFlows
				.from(Feed.inboundAdapter(this.feedResource, "news"), e -> e.poller(p -> p.fixedDelay(5000)))
				.transform(extractLinkFromFeed()).handle(targetDirectory())
				// .handle(System.out::println)
				.get();
	}

	@Bean
	public AbstractPayloadTransformer<SyndEntry, String> extractLinkFromFeed() {
		return new AbstractPayloadTransformer<SyndEntry, String>() {
			@Override
			protected String transformPayload(SyndEntry payload) {
				return payload.getTitle() + " " + payload.getAuthor() + " " + payload.getLink();
			}
		};

	}

	@Bean
	public MessageHandler targetDirectory() {
		FileWritingMessageHandler handler = new FileWritingMessageHandler(
				new File(System.getProperty("java.io.tmpdir") + "/spring-integration/rss-feed"));
		//handler.setFileExistsMode(FileExistsMode.APPEND);
		handler.setAutoCreateDirectory(true);
		// handler.setCharset("UTF-8");
		handler.setExpectReply(false);
		return handler;
	}
}
