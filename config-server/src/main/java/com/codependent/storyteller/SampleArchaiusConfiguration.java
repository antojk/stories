package com.codependent.storyteller;

import java.net.URISyntaxException;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.configuration.AbstractConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;

import com.netflix.config.ConcurrentMapConfiguration;
import com.netflix.config.DynamicLongProperty;
import com.netflix.config.DynamicPropertyFactory;
import com.netflix.config.DynamicStringProperty;

@RestController
public class SampleArchaiusConfiguration {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@RequestMapping(value = "/stories", params = "random=true")
	public String generateHtml(HttpServletResponse response) throws RestClientException, URISyntaxException {
		DynamicLongProperty timeToWait = DynamicPropertyFactory.getInstance().getLongProperty("longprop", 10);
		// DynamicStringProperty sampleProp =
		// DynamicPropertyFactory.getInstance().getStringProperty("longprop",
		// "");
		//ConcurrentMapConfiguration prop = new ConcurrentMapConfiguration();
		logger.info("timeToWait............ " + timeToWait.get());
		// logger.info(sampleProp.get() + " Noor " + );
		return null;
	}
}
