package com.codependent.storyteller;

import java.net.URISyntaxException;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.configuration.AbstractConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.config.server.config.EnvironmentRepositoryConfiguration;
import org.springframework.cloud.config.server.environment.EnvironmentRepository;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;

import com.netflix.config.ConcurrentMapConfiguration;
import com.netflix.config.DynamicLongProperty;
import com.netflix.config.DynamicPropertyFactory;
import com.netflix.config.DynamicStringProperty;

@EnableConfigServer
@EnableEurekaClient
@SpringBootApplication
public class ConfigServerApplication {

	private Logger logger = LoggerFactory.getLogger(getClass());
	static {

		// System.setProperty("archaius.configurationSource.defaultFileName",
		// "config.properties");
	}

	@Autowired
	EnvironmentRepositoryConfiguration config;

	@Autowired
	EnvironmentRepository repo;

	public static void main(String[] args) {
		SpringApplication.run(ConfigServerApplication.class, args);
		setProxyOn();
	}

	private static void setProxyOn() {
		System.setProperty("https.proxyHost", "kcaws1prx");
		System.setProperty("https.proxyPort", "8080");
		System.setProperty("http.proxyHost", "kcaws1prx");
		System.setProperty("http.proxyPort", "8080");
		// To By Pass the proxy
		System.setProperty("http.nonProxyHosts",
				"kcaws1bpm01.kcapps.kpmg.com|kcaws1dvbpm01.kcapps.kpmg.com|kcaws1dvexp02.kcapps.kpmg.com");
		// System.setProperty("archaius.configurationSource.defaultFileName",
		// "config.properties");
	}

	private void logConfig() {
		config.configServerHealthIndicator(repo).health().getDetails();
	}

	/*
	 * @Bean public AbstractConfiguration sampleArchaiusConfiguration() throws
	 * Exception { ConcurrentMapConfiguration concurrentMapConfiguration = new
	 * ConcurrentMapConfiguration();
	 * concurrentMapConfiguration.addProperty("testkey", "testvalue");
	 * System.out.println(concurrentMapConfiguration.getString("longprop"));
	 * return concurrentMapConfiguration; }
	 */

}
