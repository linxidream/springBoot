package com.benmei.sale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication
public class SaleApplication extends SpringBootServletInitializer {
//	final static Logger logger = LoggerFactory.getLogger(SaleApplication.class);

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(SaleApplication.class);
	}
	public static void main(String[] args) {
//		logger.info("~~~~~~~~~~~~~~~~~~~~ start ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		SpringApplication.run(SaleApplication.class, args);
	}

}
