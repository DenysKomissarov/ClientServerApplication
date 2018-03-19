package com.example.clientserverapplication.server;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.atomic.AtomicInteger;


@SpringBootApplication
@EnableScheduling
public class ServerApplication {

	private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(ServerApplication.class.getName());
	//public static Integer tempCountRequest = 0;
	public static AtomicInteger tempCountRequest = new AtomicInteger(0);

	public static void main(String[] args) {
		SpringApplication.run(ServerApplication.class, args);
		logger.info("####### Start Server  #######");
	}
}
