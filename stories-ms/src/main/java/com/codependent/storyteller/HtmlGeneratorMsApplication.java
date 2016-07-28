package com.codependent.storyteller;

import java.net.URISyntaxException;
import java.text.MessageFormat;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;

import com.netflix.config.DynamicLongProperty;
import com.netflix.config.DynamicPropertyFactory;

@RestController
@EnableDiscoveryClient
@EnableCircuitBreaker
@SpringBootApplication
public class HtmlGeneratorMsApplication {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Value("${stories-ms-message}")
	private String message;

	@Autowired
	private StoryService storyService;

	@Autowired
	private StoryFinder storyFinder;

	/*
	 * @Value("${longprop}") private String longprop;
	 */

	@RequestMapping("/")
	public String query() {
		return message;
	}

	@RequestMapping(value = "/stories", params = "random=true")
	public String generateHtml(HttpServletResponse response) throws RestClientException, URISyntaxException {
		logger.info("[{}] generateHtml()", message);
		DynamicLongProperty timeToWait = DynamicPropertyFactory.getInstance().getLongProperty("longprop", 10);
		logger.info("timeToWait............ " + timeToWait.get());

		Map<String, String> randomImage = storyService.getRandomImage();

		String html = "<html><body>" + storyService.getRandomStory() + "</body></html>";
		html = String.format(html, randomImage.get("imageUrl"));
		response.setContentType("text/html");
		return html;
	}

	@RequestMapping(value = "/stories/list", method = RequestMethod.GET, produces = "text/html")
	public Story[] listAll() {
		try {
			return storyFinder.findAll();
		} catch (Exception e) {
			logger.error("Story Listing failed", e);
			return new Story[0];
		}

	}

	@RequestMapping(value = "/stories/{id}", method = RequestMethod.GET, produces = "application/json")
	public Story fetchStoryFor(@PathVariable("id") int id) {
		try {
			return storyFinder.findBy(id);
		} catch (Exception e) {
			logger.error(MessageFormat.format("Fetch Story name failed for id:{0}", id), e);
			return null;
		}

	}

	@RequestMapping(value = "/stories/count", method = RequestMethod.GET, produces = "text/html")
	public String countOf() {
		try {
			return Integer.toString(storyFinder.count());
		} catch (Exception e) {
			return "-1";
		}

	}

	public static void main(String[] args) {
		SpringApplication.run(HtmlGeneratorMsApplication.class, args);
	}

	// public StoryFinder getStoryFinder() {
	// return this.beanFactory.getBean(StoryFinder.class);
	// }
	//
	// @Override
	// public void setBeanFactory(BeanFactory beanFactory) throws BeansException
	// {
	// this.beanFactory = beanFactory;
	//
	// }

}
