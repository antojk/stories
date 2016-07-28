package com.codependent.storyteller;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RefreshScope
// @EnableConfigurationProperties(StoryPropertyHolder.class)
@ConfigurationProperties(prefix = "finder")
public class StoryConfig {

	private static final Logger LOG = org.slf4j.LoggerFactory.getLogger(StoryConfig.class);
	@Autowired
	private IStoryList stories;

	@Bean
	@RefreshScope
	public StoryFinder getStoryFinder() {
		LOG.info("Stories: [{}] on holder: {}", stories, stories.getClass().getSimpleName());

		return new StoryFinderImpl(stories);
	}

	@Bean
	
	public IStoryList getStoryList() {
		return new StoryList();
	}
}