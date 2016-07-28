package com.codependent.storyteller;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;

//IStoryList
@ConfigurationProperties(prefix = "finder")
public class StoryPropertyHolder {

	private static final Logger LOGGER = LoggerFactory.getLogger(StoryPropertyHolder.class);

	private IStoryList stories = new StoryList();

	@PostConstruct
	private void init() {
		LOGGER.debug("Stories loaded .... [{}]", stories);
	}
}
