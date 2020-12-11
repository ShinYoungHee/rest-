package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan({"board","comment"})
@EnableJpaRepositories({"board.repository","comment.repository"})
@EntityScan({"board.entity","comment.entity"})
@EnableAutoConfiguration
public class RestBoardApplication extends SpringBootServletInitializer {
	
	@Override
	 protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
	   return builder.sources(RestBoardApplication.class);
	 }

	public static void main(String[] args) {
		SpringApplication.run(RestBoardApplication.class, args);
	}

}
