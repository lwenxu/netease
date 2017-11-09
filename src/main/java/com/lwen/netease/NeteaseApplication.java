package com.lwen.netease;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
public class NeteaseApplication {

	public static void main(String[] args) {
		SpringApplication.run(NeteaseApplication.class, args);
	}
}
