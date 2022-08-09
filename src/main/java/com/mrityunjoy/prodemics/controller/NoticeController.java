package com.mrityunjoy.prodemics.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mrityunjoy.prodemics.model.Notice;
import com.mrityunjoy.prodemics.repository.NoticeRepository;

import lombok.extern.slf4j.Slf4j;

@RestController()
@Slf4j
@RequestMapping("/notice")
public class NoticeController {

	NoticeRepository noticeRepository;
	
	@Autowired
	public void setNoticeRepository(NoticeRepository noticeRepository) {
		this.noticeRepository = noticeRepository;
	}
	
	@GetMapping("/list")
	public List<Notice> getNoticeList() {
		log.info("Sending response");
		return noticeRepository.findAll(Sort.by("createdAt", "id").descending());
	}
}
