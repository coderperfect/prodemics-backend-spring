package com.mrityunjoy.prodemics;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.mrityunjoy.prodemics.model.EndUser;
import com.mrityunjoy.prodemics.repository.EndUserRepository;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class ProdemicsApplication {
	
	@Value("${ADMIN_PASSWORD:#{null}}")
	private String adminPassword;

	private EndUserRepository endUserRepository;
	
	@Autowired
	public ProdemicsApplication(EndUserRepository endUserRepository) {
		this.endUserRepository = endUserRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(ProdemicsApplication.class, args);
		log.info(String.format("App started by: %s", System.getenv("STARTED_BY")));
	}

	@PostConstruct
	private void initialise() {
		if(adminPassword != null) {
			EndUser endUser = endUserRepository.findById("admin").get();
			endUser.setPassword(adminPassword);
			endUserRepository.save(endUser);
		}
	}
}
