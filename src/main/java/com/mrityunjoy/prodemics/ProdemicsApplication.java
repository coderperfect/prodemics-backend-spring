package com.mrityunjoy.prodemics;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import com.mrityunjoy.prodemics.repository.EndUserRepository;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@EnableAspectJAutoProxy
@Slf4j
public class ProdemicsApplication {
	
	@Value("${ADMIN_PASSWORD:#{null}}")
	private String adminPassword;

	private static String startedBy;

	private EndUserRepository endUserRepository;
	
	@Autowired
	public ProdemicsApplication(EndUserRepository endUserRepository) {
		this.endUserRepository = endUserRepository;
	}
	
	@Value("${STARTED_BY:#{null}}")
	public void setStartedBy(String startedBy) {
		ProdemicsApplication.startedBy = startedBy;
	}

	public static void main(String[] args) {
		SpringApplication.run(ProdemicsApplication.class, args);
		log.info(String.format("App started by: %s", startedBy));
	}

	@PostConstruct
	private void initialise() {
		if(adminPassword != null) {
			endUserRepository.setPassword("admin", adminPassword);
		}
	}
}
