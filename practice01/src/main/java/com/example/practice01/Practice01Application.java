package com.example.practice01;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = "com.example")
@EnableJpaRepositories(basePackages = "com.example.practice01.repository.jpa")
@EnableElasticsearchRepositories(basePackages = "com.example.practice01.repository.es")
public class Practice01Application {

	public static void main(String[] args) {
		SpringApplication.run(Practice01Application.class, args);
	}

}
