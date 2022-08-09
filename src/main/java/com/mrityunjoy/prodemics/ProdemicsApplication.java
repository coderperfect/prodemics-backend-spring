package com.mrityunjoy.prodemics;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class ProdemicsApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProdemicsApplication.class, args);
		log.info(String.format("App started by: %s", System.getenv("STARTED_BY")));
	}

}
