package com.rest.comcast.MNGODB;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableCircuitBreaker
@EnableDiscoveryClient
@SpringBootApplication
public class MngodbApplication {
	private static final Logger logger = LoggerFactory.getLogger(MngodbApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(MngodbApplication.class, args);
		logger.debug("--Application Started--");
	}
}
