package com.mrityunjoy.prodemics.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mrityunjoy.prodemics.dto.NoticeRequest;
import com.mrityunjoy.prodemics.model.Notice;
import com.mrityunjoy.prodemics.repository.NoticeRepository;

import lombok.extern.slf4j.Slf4j;

@RestController()
@Slf4j
@RequestMapping("/admin")
public class AdminController {

	NoticeRepository noticeRepository;
	
	@Autowired
	public void setNoticeRepository(NoticeRepository noticeRepository) {
		this.noticeRepository = noticeRepository;
	}
	
	@PostMapping("/notice/add")
	public Notice addNotice(@RequestBody NoticeRequest noticeRequest) {
		log.info("Adding notice and Sending response");

		return noticeRepository.save(new Notice(0, noticeRequest.getTitle(), noticeRequest.getDescription(), noticeRequest.getCreatedAt()));
	}
}
