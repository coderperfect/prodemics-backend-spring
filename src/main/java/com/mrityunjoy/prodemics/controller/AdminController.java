package com.mrityunjoy.prodemics.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mrityunjoy.prodemics.annotation.LogAspect;
import com.mrityunjoy.prodemics.dto.NoticeRequest;
import com.mrityunjoy.prodemics.dto.SignUpRequest;
import com.mrityunjoy.prodemics.exception.BadRequestException;
import com.mrityunjoy.prodemics.model.EndUser;
import com.mrityunjoy.prodemics.model.Notice;
import com.mrityunjoy.prodemics.repository.EndUserRepository;
import com.mrityunjoy.prodemics.repository.NoticeRepository;

import lombok.extern.slf4j.Slf4j;

@RestController()
@Slf4j
@RequestMapping("/admin")
public class AdminController {

	NoticeRepository noticeRepository;
	EndUserRepository endUserRepository;
	PasswordEncoder passwordEncoder;
	
	@Autowired
	public void setNoticeRepository(NoticeRepository noticeRepository) {
		this.noticeRepository = noticeRepository;
	}
	
	@Autowired
	public void setEndUserRepository(EndUserRepository endUserRepository) {
		this.endUserRepository = endUserRepository;
	}
	
	@Autowired
	public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}
	
	@PostMapping("/notice/add")
	@LogAspect
	public Notice addNotice(@Valid @RequestBody NoticeRequest noticeRequest) {
		log.info("Adding notice and Sending response");

		return noticeRepository.save(
				new Notice(0, noticeRequest.getTitle(), noticeRequest.getDescription(), noticeRequest.getCreatedAt()));
	}
	
	@PostMapping("/user/signup")
	@LogAspect
	public EndUser signup(@Valid @RequestBody SignUpRequest signUpRequest) {
		log.info("Adding user and Sending response");
		
		endUserRepository.getByUsername(signUpRequest.getUsername()).ifPresent((endUser) -> {
			throw new BadRequestException("Username is not available");
		});

		return endUserRepository.saveWithRole(new EndUser(signUpRequest.getUsername(), signUpRequest.getEmailId(),
				signUpRequest.getName(), passwordEncoder.encode(signUpRequest.getPassword()), null), "student");
	}
}
