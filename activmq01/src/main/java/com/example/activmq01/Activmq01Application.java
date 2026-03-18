package com.example.activmq01;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableJpaRepositories("com.example.activemq01.repository")
public class Activmq01Application {

	public static void main(String[] args) {
		SpringApplication.run(Activmq01Application.class, args);
	}

}
