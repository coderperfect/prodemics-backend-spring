package com.mrityunjoy.prodemics;

import com.mrityunjoy.prodemics.service.EndUserService;
import jakarta.annotation.PostConstruct;

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

	private final EndUserService endUserService;
	private final String startedBy;
	private final String adminPassword;

	public ProdemicsApplication(
			EndUserService endUserService, @Value("${ADMIN_PASSWORD:#{null}}") String adminPassword,
			@Value("${STARTED_BY:#{null}}") String startedBy
	) {
		this.endUserService = endUserService;
		this.adminPassword = adminPassword;
		this.startedBy = startedBy;
	}

	static void main(String[] args) {
		SpringApplication.run(ProdemicsApplication.class, args);
	}

	@PostConstruct
	private void initialise() {
		log.info("App started by: {}", startedBy);

		if(adminPassword != null) { endUserService.setPassword("admin", adminPassword); }
	}

}
