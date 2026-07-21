package com.mrityunjoy.prodemics;

import com.mrityunjoy.prodemics.service.EndUserService;
import jakarta.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@ConfigurationPropertiesScan
@EnableAspectJAutoProxy
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@Slf4j
public class ProdemicsApplication {
	
	@Value("${ADMIN_PASSWORD:#{null}}")
	private String adminPassword;

	private static String startedBy;

	private final EndUserService endUserService;
	
	@Autowired
	public ProdemicsApplication(EndUserService endUserService) {
		this.endUserService = endUserService;
	}
	
	@Value("${STARTED_BY:#{null}}")
	public void setStartedBy(String startedBy) {
		ProdemicsApplication.startedBy = startedBy;
	}

	static void main(String[] args) {
		SpringApplication.run(ProdemicsApplication.class, args);
		log.info("App started by: {}", startedBy);
	}

	@PostConstruct
	private void initialise() {
		if(adminPassword != null) { endUserService.setPassword("admin", adminPassword); }
	}

}
