package com.cts.flightsearch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = "com.cts.flightsearch.repository")
@SpringBootApplication
public class FlightsearchApplication {

	public static void main(String[] args) {
		SpringApplication.run(FlightsearchApplication.class, args);
	}

}
