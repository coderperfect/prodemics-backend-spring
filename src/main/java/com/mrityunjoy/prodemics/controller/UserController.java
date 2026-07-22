package com.mrityunjoy.prodemics.controller;

import com.mrityunjoy.prodemics.service.EndUserService;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mrityunjoy.prodemics.annotation.LogAspect;
import com.mrityunjoy.prodemics.dto.SignUpRequest;
import com.mrityunjoy.prodemics.model.EndUser;

import lombok.extern.slf4j.Slf4j;

@RestController()
@Slf4j
@RequestMapping("/user")
public class UserController {

	private final EndUserService endUserService;

	public UserController(EndUserService endUserService) {
		this.endUserService = endUserService;
	}
	
	@PostMapping()
	@LogAspect
	public EndUser signup(@Valid @RequestBody SignUpRequest signUpRequest) {
		log.info("Adding user and Sending response");
		
		return endUserService.createUser(
				signUpRequest.getUsername(), signUpRequest.getEmailId(), signUpRequest.getName(),
				signUpRequest.getPassword(), "student"
		);
	}
}
